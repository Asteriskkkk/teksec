package com.springadvanced.order.controller;

import com.springadvanced.order.dto.CreateOrderRequest;
import com.springadvanced.order.entity.CustomerOrder;
import com.springadvanced.order.service.OrderService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/rest-template")
    public ResponseEntity<CustomerOrder> createOrderWithRestTemplate(@Valid @RequestBody CreateOrderRequest request) {
        return ResponseEntity.ok(orderService.createOrderWithRestTemplate(request));
    }

    @PostMapping("/webclient")
    public ResponseEntity<CustomerOrder> createOrderWithWebClient(@Valid @RequestBody CreateOrderRequest request) {
        return ResponseEntity.ok(orderService.createOrderWithWebClient(request));
    }

    @GetMapping
    public ResponseEntity<List<CustomerOrder>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }
}
