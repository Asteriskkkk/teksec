package com.example.rentalcarbooking.model;

import org.springframework.beans.factory.annotation.Value;

public class Car {

    private final String model;
    private boolean booked;
    private String bookedBy;

    public Car(@Value("${rental.car.model:Toyota Camry}") String model) {
        this.model = model;
    }

    public synchronized void book(String customerName) {
        String normalizedCustomerName = customerName == null ? "" : customerName.trim();
        if (normalizedCustomerName.isEmpty()) {
            throw new IllegalArgumentException("Customer name must not be empty.");
        }
        if (booked) {
            throw new IllegalStateException("Car " + model + " is already booked by " + bookedBy + ".");
        }
        booked = true;
        bookedBy = normalizedCustomerName;
    }

    public synchronized String release() {
        if (!booked) {
            throw new IllegalStateException("Car " + model + " is not currently booked.");
        }
        String previousCustomer = bookedBy;
        booked = false;
        bookedBy = null;
        return "Car " + model + " released successfully after being booked by " + previousCustomer + ".";
    }

    public synchronized String describeStatus() {
        return booked
                ? "Car " + model + " is booked by " + bookedBy + "."
                : "Car " + model + " is currently available.";
    }

    public String getModel() {
        return model;
    }

    public synchronized boolean isBooked() {
        return booked;
    }
}
