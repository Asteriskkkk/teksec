package com.example.orderprocessing;

import com.example.orderprocessing.model.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class OrderProcessingApplicationTests {

    @Autowired
    private org.springframework.context.ApplicationContext context;

    @Test
    void contextLoads() {
        assertThat(context).isNotNull();
        assertThat(context.getBean("firstOrder", Order.class).getBook().getTitle()).isEqualTo("Effective Java");
        assertThat(context.getBean("secondOrder", Order.class).getQuantity()).isEqualTo(3);
    }
}