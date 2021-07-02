package com.github.mehdishahdoost.product.repository;

import com.github.mehdishahdoost.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
