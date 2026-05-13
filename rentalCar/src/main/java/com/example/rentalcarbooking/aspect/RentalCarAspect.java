package com.example.rentalcarbooking.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class RentalCarAspect {

    @Around("execution(* com.example.rentalcarbooking.component.RentalCarBooking.bookCar(..)) || execution(* com.example.rentalcarbooking.component.RentalCarBooking.releaseCar(..))")
    public Object manageBookingFlow(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("[AOP][Around] Starting " + joinPoint.getSignature().getName() + " with args " + Arrays.toString(joinPoint.getArgs()));
        try {
            Object result = joinPoint.proceed();
            System.out.println("[AOP][Around] Completed " + joinPoint.getSignature().getName() + " with result: " + result);
            return result;
        } catch (Throwable throwable) {
            System.out.println("[AOP][Around] Propagating exception from " + joinPoint.getSignature().getName() + ": " + throwable.getMessage());
            throw throwable;
        }
    }

    @AfterReturning(pointcut = "execution(* com.example.rentalcarbooking.component.RentalCarBooking.releaseCar(..))", returning = "result")
    public void logSuccessfulRelease(JoinPoint joinPoint, Object result) {
        System.out.println("[AOP][AfterReturning] Release completed successfully: " + result);
    }

    @After("execution(* com.example.rentalcarbooking.component.RentalCarBooking.releaseCar(..))")
    public void thankYouAfterRelease(JoinPoint joinPoint) {
        System.out.println("[AOP][After] Thank you for using the rental car service.");
    }

    @AfterThrowing(pointcut = "execution(* com.example.rentalcarbooking.component.RentalCarBooking.bookCar(..)) || execution(* com.example.rentalcarbooking.component.RentalCarBooking.releaseCar(..))", throwing = "exception")
    public void handleBookingErrors(JoinPoint joinPoint, Throwable exception) {
        System.out.println("[AOP][AfterThrowing] Error while executing " + joinPoint.getSignature().getName() + ": " + exception.getMessage());
    }
}
