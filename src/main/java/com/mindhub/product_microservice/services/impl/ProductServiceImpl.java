package com.mindhub.product_microservice.services.impl;

import com.mindhub.product_microservice.dtos.get.GetProductDTO;
import com.mindhub.product_microservice.dtos.post.PostProductDTO;
import com.mindhub.product_microservice.dtos.update.UpdateProductDTO;
import com.mindhub.product_microservice.exceptions.GenericException;
import com.mindhub.product_microservice.models.ProductModel;
import com.mindhub.product_microservice.repositories.ProductRepository;
import com.mindhub.product_microservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public GetProductDTO create(PostProductDTO product) throws GenericException {
        try {
            ProductModel productModel = new ProductModel(product.name(), product.description(), product.price(), product.stock());
            ProductModel savedProduct = productRepository.save(productModel);
            return new GetProductDTO(savedProduct);
        } catch (Exception e) {
            throw new GenericException(e.getMessage());
        }
    }

    @Override
    public ProductModel save(ProductModel product) throws GenericException {
        try {
            return productRepository.save(product);
        } catch (Exception e) {
            throw new GenericException(e.getMessage());
        }
    }

    @Override
    public List<ProductModel> getAll() throws GenericException {
        try {
            return productRepository.findAll();
        } catch (Exception e) {
            throw new GenericException(e.getMessage());
        }
    }

    @Override
    public ProductModel getById(Long id) throws GenericException {
        return productRepository.findById(id).orElseThrow(() -> new GenericException("product not found"));
    }

    @Override
    public GetProductDTO getDTOById(Long id) throws GenericException {
        try {
            return new GetProductDTO(getById(id));
        } catch (Exception e) {
            throw new GenericException(e.getMessage());
        }
    }

    @Override
    public void patchStock(Long id, Integer quantity, String type) throws GenericException {
        try {
            ProductModel productModel = productRepository.findById(id)
                    .orElseThrow(() -> new GenericException("product not found"));
            if (quantity < 1) {
                throw new GenericException("invalid quantity");
            }
            if (Objects.equals(type, "res") && productModel.getStock() < quantity) {
                throw new GenericException("insufficient stock");
            }
            productModel.setStock(Objects.equals(type, "sum") ? productModel.getStock() + quantity : productModel.getStock() - quantity);
            productRepository.save(productModel);
        } catch (Exception e) {
            throw new GenericException(e.getMessage());
        }
    }

    @Override
    public GetProductDTO update(Long id, UpdateProductDTO product) throws GenericException {
        try {
            ProductModel productModel = productRepository.findById(id)
                    .orElseThrow(() -> new GenericException("product not found"));
            productModel.setName(product.name());
            productModel.setDescription(product.description());
            productModel.setPrice(product.price());
            productModel.setStock(product.stock());
            productModel = productRepository.save(productModel);
            return new GetProductDTO(productModel);
        } catch (Exception e) {
            throw new GenericException(e.getMessage());
        }
    }

    @Override
    public void delete(Long id) throws GenericException {
        try {
            productRepository.deleteById(id);
        } catch (Exception e) {
            throw new GenericException(e.getMessage());
        }
    }
}
