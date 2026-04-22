package com.example.orderprocessing;

import com.example.orderprocessing.model.Order;
import com.example.orderprocessing.service.OrderPrinter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class OrderProcessingApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(OrderProcessingApplication.class, args);

        System.out.println("\n========================================");
        System.out.println("Order Processing System Demo");
        System.out.println("========================================\n");

        OrderPrinter printer = context.getBean(OrderPrinter.class);
        Order firstOrder = context.getBean("firstOrder", Order.class);
        Order secondOrder = context.getBean("secondOrder", Order.class);

        printer.printOrder(firstOrder);
        printer.printOrder(secondOrder);

        firstOrder.updateQuantity(4);
        secondOrder.updateQuantity(1);

        System.out.println("After quantity updates:\n");
        printer.printOrder(firstOrder);
        printer.printOrder(secondOrder);

        System.out.println("========================================");
        System.out.println("Demo Complete");
        System.out.println("========================================\n");

        context.close();
    }
}
