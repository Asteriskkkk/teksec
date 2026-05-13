package com.example.orderprocessing.config;

import com.example.orderprocessing.model.Book;
import com.example.orderprocessing.model.Order;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderConfig {

    @Bean("effectiveJavaBook")
    public Book effectiveJavaBook() {
        return new Book("Effective Java", "Joshua Bloch", 54.99);
    }

    @Bean("springInActionBook")
    public Book springInActionBook() {
        return new Book("Spring in Action", "Craig Walls", 49.99);
    }

    @Bean("firstOrder")
    public Order firstOrder(@Qualifier("effectiveJavaBook") Book book) {
        return new Order("ORD-1001", book, 2);
    }

    @Bean("secondOrder")
    public Order secondOrder(@Qualifier("springInActionBook") Book book) {
        return new Order("ORD-1002", book, 3);
    }
}
