package com.example.userapplication.controller;

import com.example.userapplication.service.ProductClientService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final ProductClientService productClientService;

    public UserController(ProductClientService productClientService) {
        this.productClientService = productClientService;
    }

    @GetMapping("/user/product-info")
    public String getProductInfo() {
        return productClientService.fetchProductDetails();
    }
}
