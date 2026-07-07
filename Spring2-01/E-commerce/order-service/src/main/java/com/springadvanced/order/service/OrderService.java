package com.springadvanced.order.service;

import com.springadvanced.order.dto.CreateOrderRequest;
import com.springadvanced.order.dto.ProductDto;
import com.springadvanced.order.entity.CustomerOrder;
import com.springadvanced.order.exception.RemoteServiceException;
import com.springadvanced.order.repository.OrderRepository;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class OrderService {

    private static final String PRODUCT_SERVICE_URL = "http://PRODUCT-SERVICE/products/"\;

    private final OrderRepository orderRepository;
    private final RestTemplate restTemplate;
    private final WebClient.Builder webClientBuilder;

    public OrderService(OrderRepository orderRepository, RestTemplate restTemplate, WebClient.Builder webClientBuilder) {
        this.orderRepository = orderRepository;
        this.restTemplate = restTemplate;
        this.webClientBuilder = webClientBuilder;
    }

    public CustomerOrder createOrderWithRestTemplate(CreateOrderRequest request) {
        ProductDto product = fetchProductWithRestTemplate(request.getProductId());
        validateStock(product, request.getQuantity());
        updateStockWithRestTemplate(product.getId(), product.getStock() - request.getQuantity());
        return saveOrder(request, product);
    }

    public CustomerOrder createOrderWithWebClient(CreateOrderRequest request) {
        ProductDto product = fetchProductWithWebClient(request.getProductId());
        validateStock(product, request.getQuantity());
        updateStockWithWebClient(product.getId(), product.getStock() - request.getQuantity());
        return saveOrder(request, product);
    }

    public List<CustomerOrder> getAllOrders() {
        return orderRepository.findAll();
    }

    private ProductDto fetchProductWithRestTemplate(Long productId) {
        try {
            ProductDto product = restTemplate.getForObject(PRODUCT_SERVICE_URL + productId, ProductDto.class);
            if (product == null) {
                throw new RemoteServiceException("Product not found with id: " + productId);
            }
            return product;
        } catch (HttpStatusCodeException ex) {
            throw new RemoteServiceException("Failed to fetch product: " + ex.getResponseBodyAsString());
        } catch (Exception ex) {
            throw new RemoteServiceException("Product service unavailable");
        }
    }

    private ProductDto fetchProductWithWebClient(Long productId) {
        try {
            ProductDto product = webClientBuilder.build()
                    .get()
                    .uri(PRODUCT_SERVICE_URL + productId)
                    .retrieve()
                    .onStatus(HttpStatusCode::isError,
                            response -> response.bodyToMono(String.class)
                                    .map(body -> new RemoteServiceException("Failed to fetch product: " + body)))
                    .bodyToMono(ProductDto.class)
                    .block();

            if (product == null) {
                throw new RemoteServiceException("Product not found with id: " + productId);
            }
            return product;
        } catch (RemoteServiceException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new RemoteServiceException("Product service unavailable");
        }
    }

    private void updateStockWithRestTemplate(Long productId, Integer newStock) {
        try {
            restTemplate.put(PRODUCT_SERVICE_URL + productId + "/stock/" + newStock, null);
        } catch (Exception ex) {
            throw new RemoteServiceException("Failed to update product stock");
        }
    }

    private void updateStockWithWebClient(Long productId, Integer newStock) {
        try {
            webClientBuilder.build()
                    .put()
                    .uri(PRODUCT_SERVICE_URL + productId + "/stock/" + newStock)
                    .retrieve()
                    .toBodilessEntity()
                    .block();
        } catch (Exception ex) {
            throw new RemoteServiceException("Failed to update product stock");
        }
    }

    private void validateStock(ProductDto product, Integer quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }
        if (product.getStock() < quantity) {
            throw new IllegalArgumentException("Insufficient stock for product id: " + product.getId());
        }
    }

    private CustomerOrder saveOrder(CreateOrderRequest request, ProductDto product) {
        CustomerOrder order = new CustomerOrder();
        order.setProductId(product.getId());
        order.setProductName(product.getName());
        order.setQuantity(request.getQuantity());
        order.setUnitPrice(product.getPrice());
        order.setTotalPrice(product.getPrice() * request.getQuantity());
        order.setCreatedAt(LocalDateTime.now());
        return orderRepository.save(order);
    }
}
