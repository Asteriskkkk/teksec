package com.example.orderprocessing.model;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class Order {
    private String orderId;
    private Book book;
    private int quantity;

    public Order() {
    }

    public Order(String orderId, Book book, int quantity) {
        this.orderId = orderId;
        this.book = book;
        this.quantity = quantity;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void updateQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return book.getPrice() * quantity;
    }
}