package com.example.shopsquare.service;

import com.example.shopsquare.entity.Product;
import com.example.shopsquare.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Page<Product> getProducts(int page, int size, String sortDir) {
        Sort.Direction direction;
        if ("asc".equalsIgnoreCase(sortDir)) {
            direction = Sort.Direction.ASC;
        } else if ("desc".equalsIgnoreCase(sortDir)) {
            direction = Sort.Direction.DESC;
        } else {
            throw new IllegalArgumentException("sortDir must be 'asc' or 'desc'");
        }

        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, "price"));
        return productRepository.findAll(pageable);
    }
}
