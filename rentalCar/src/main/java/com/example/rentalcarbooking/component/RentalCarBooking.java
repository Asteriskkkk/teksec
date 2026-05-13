package com.example.rentalcarbooking.component;

import com.example.rentalcarbooking.model.Car;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RentalCarBooking {

    private final List<Car> cars;

    public RentalCarBooking(List<Car> cars) {
        this.cars = cars;
    }

    public String bookCar(String model, String customerName) {
        Car car = findCarByModel(model);
        car.book(customerName);
        return "Booking confirmed for " + customerName.trim() + " on " + car.getModel() + ".";
    }

    public String releaseCar(String model) {
        Car car = findCarByModel(model);
        return car.release();
    }

    public String viewCarStatus(String model) {
        return findCarByModel(model).describeStatus();
    }

    public String viewAllCarStatuses() {
        return cars.stream()
                .map(Car::describeStatus)
                .reduce((left, right) -> left + System.lineSeparator() + right)
                .orElse("No cars available.");
    }

    private Car findCarByModel(String model) {
        String normalizedModel = model == null ? "" : model.trim();
        if (normalizedModel.isEmpty()) {
            throw new IllegalArgumentException("Car model must not be empty.");
        }

        return cars.stream()
                .filter(car -> car.getModel().equalsIgnoreCase(normalizedModel))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No car found for model: " + normalizedModel));
    }
}
