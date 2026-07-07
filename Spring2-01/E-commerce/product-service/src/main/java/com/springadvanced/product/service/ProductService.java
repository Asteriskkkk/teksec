package com.springadvanced.product.service;

import com.springadvanced.product.entity.Product;
import com.springadvanced.product.exception.ProductNotFoundException;
import com.springadvanced.product.repository.ProductRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));
    }

    public Product updateStock(Long id, Integer stock) {
        Product product = getProductById(id);
        product.setStock(stock);
        return productRepository.save(product);
    }
}
