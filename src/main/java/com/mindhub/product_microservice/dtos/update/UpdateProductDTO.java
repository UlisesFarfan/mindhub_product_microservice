package com.mindhub.product_microservice.dtos.update;

public record UpdateProductDTO (String name, String description, Double price, Integer stock) {
}
