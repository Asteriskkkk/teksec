package com.example.orderprocessing.service;

import com.example.orderprocessing.model.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderPrinter {

    public void printOrder(Order order) {
        System.out.println("Order ID: " + order.getOrderId());
        System.out.println("Book Title: " + order.getBook().getTitle());
        System.out.println("Author: " + order.getBook().getAuthor());
        System.out.println("Quantity: " + order.getQuantity());
        System.out.printf("Total Price: $%.2f%n", order.getTotalPrice());
        System.out.println();
    }
}
