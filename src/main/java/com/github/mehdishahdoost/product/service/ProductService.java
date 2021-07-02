package com.github.mehdishahdoost.product.service;

import com.github.mehdishahdoost.product.entity.Product;
import com.github.mehdishahdoost.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    public ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }
}
