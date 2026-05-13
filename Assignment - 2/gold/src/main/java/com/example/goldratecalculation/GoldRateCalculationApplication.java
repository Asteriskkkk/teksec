package com.example.goldratecalculation;

import com.example.goldratecalculation.component.GoldRateInfo;
import com.example.goldratecalculation.config.GoldConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;

public class GoldRateCalculationApplication {

    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(GoldConfig.class);
             Scanner scanner = new Scanner(System.in)) {

            GoldRateInfo goldRateInfo = context.getBean(GoldRateInfo.class);

            System.out.print("Enter carat value (18K/22K/24K): ");
            String caratValue = scanner.nextLine();

            System.out.print("Enter gold weight in grams: ");
            double weightInGrams = scanner.nextDouble();

            double totalRate = goldRateInfo.calculateTotalRate(caratValue, weightInGrams);
            System.out.printf("Total gold rate for %.2f grams of %s is: %.2f%n", weightInGrams, caratValue.toUpperCase(), totalRate);
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
        }
    }
}
