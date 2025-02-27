package com.mindhub.product_microservice.repositories;

import com.mindhub.product_microservice.models.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductModel, Long> {
}
