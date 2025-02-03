package com.mindhub.product_microservice.services;

import com.mindhub.product_microservice.dtos.get.GetProductDTO;
import com.mindhub.product_microservice.dtos.post.PostProductDTO;
import com.mindhub.product_microservice.dtos.update.UpdateProductDTO;
import com.mindhub.product_microservice.exceptions.GenericException;
import com.mindhub.product_microservice.models.ProductModel;

import java.util.List;

public interface ProductService {
    GetProductDTO create(PostProductDTO product) throws GenericException;

    ProductModel save(ProductModel product) throws GenericException;

    List<ProductModel> getAll() throws GenericException;

    ProductModel getById(Long id) throws GenericException;

    GetProductDTO getDTOById(Long id) throws GenericException;

    void patchStock(Long id, Integer quantity, String type) throws GenericException;

    GetProductDTO update(Long id, UpdateProductDTO product) throws GenericException;

    void delete(Long id) throws GenericException;
}
