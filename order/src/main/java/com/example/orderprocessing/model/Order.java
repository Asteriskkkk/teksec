package com.example.orderprocessing.model;

public class Order {
    private final String orderId;
    private final Book book;
    private int quantity;

    public Order(String orderId, Book book, int quantity) {
        this.orderId = orderId;
        this.book = book;
        this.quantity = quantity;
    }

    public String getOrderId() {
        return orderId;
    }

    public Book getBook() {
        return book;
    }

    public int getQuantity() {
        return quantity;
    }

    public void updateQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return book.getPrice() * quantity;
    }
}