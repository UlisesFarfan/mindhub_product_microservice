package com.mindhub.product_microservice.dtos.get;

import com.mindhub.product_microservice.models.ProductModel;

public class GetProductDTO {
    private final String name;
    private final String description;
    private final Double price;
    private final Integer stock;

    public GetProductDTO(ProductModel productModel) {
        this.name = productModel.getName();
        this.description = productModel.getDescription();
        this.price = productModel.getPrice();
        this.stock = productModel.getStock();
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getStock() {
        return stock;
    }
}
