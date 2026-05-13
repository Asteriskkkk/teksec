package com.example.rentalcarbooking.config;

import com.example.rentalcarbooking.model.Car;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan(basePackages = "com.example.rentalcarbooking")
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class RentalCarConfig {

	@Bean
	public Car sedanCar() {
		return new Car("Toyota Camry");
	}

	@Bean
	public Car suvCar() {
		return new Car("Honda CR-V");
	}

	@Bean
	public Car luxuryCar() {
		return new Car("BMW 5 Series");
	}
}
