package com.example.rentalcarbooking;

import com.example.rentalcarbooking.component.RentalCarBooking;
import com.example.rentalcarbooking.config.RentalCarConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class RentalCarBookingApplication {

    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(RentalCarConfig.class)) {
            RentalCarBooking rentalCarBooking = context.getBean(RentalCarBooking.class);

            System.out.println("Initial fleet status:\n" + rentalCarBooking.viewAllCarStatuses());
            System.out.println(rentalCarBooking.bookCar("Toyota Camry", "Alicia Morgan"));
            System.out.println("Status after booking Toyota Camry: " + rentalCarBooking.viewCarStatus("Toyota Camry"));
            System.out.println(rentalCarBooking.bookCar("Honda CR-V", "Brian Thomas"));
            System.out.println("Status after booking Honda CR-V: " + rentalCarBooking.viewCarStatus("Honda CR-V"));

            try {
                rentalCarBooking.bookCar("Toyota Camry", "Carol Smith");
            } catch (Exception exception) {
                System.out.println("Main handled booking error: " + exception.getMessage());
            }

            System.out.println(rentalCarBooking.releaseCar("Toyota Camry"));

            try {
                rentalCarBooking.releaseCar("Toyota Camry");
            } catch (Exception exception) {
                System.out.println("Main handled release error: " + exception.getMessage());
            }

            System.out.println("Final fleet status:\n" + rentalCarBooking.viewAllCarStatuses());
        }
    }
}
