package com.example.userapplication.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ProductClientService {

    private final RestTemplate restTemplate;
    private final String productServiceUrl;

    public ProductClientService(RestTemplate restTemplate,
                                @Value("${product.service.url}") String productServiceUrl) {
        this.restTemplate = restTemplate;
        this.productServiceUrl = productServiceUrl;
    }

    public String fetchProductDetails() {
        try {
            return restTemplate.getForObject(productServiceUrl, String.class);
        } catch (RestClientException exception) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_GATEWAY,
                    "Unable to fetch product details from product service",
                    exception
            );
        }
    }
}
