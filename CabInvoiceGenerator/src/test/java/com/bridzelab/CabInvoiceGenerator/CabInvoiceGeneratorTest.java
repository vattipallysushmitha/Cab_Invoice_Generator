package com.bridzelab.CabInvoiceGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class CabInvoiceGeneratorTest {
    CabInvoiceGenerator cabInvoiceGenerator = new CabInvoiceGenerator();


    @Test
    public void givenDistanceAndTime_ShouldReturnTotalFare() {
        double distance = 2.0;
        int time = 5;
        double fare = cabInvoiceGenerator.calculateFare(distance, time, "NORMAL");
        Assertions.assertEquals(25, fare, 0.0);
    }

    @Test
    public void givenLessDistanceAndTime_ShouldReturnMinFare() {
        double distance = 0.1;
        int time = 1;
        double fare = cabInvoiceGenerator.calculateFare(distance, time, "NORMAL");
        Assertions.assertEquals(5, fare, 0.0);
    }

    @Test
    public void givenMultipleRides_ShouldReturnInvoiceSummary() {
        Ride[] rides = {
                new Ride(2.0, 5, "Normal"),
                new Ride(0.1, 1, "Premium")
        };
        InvoiceSummary invoiceSummary = cabInvoiceGenerator.calculateFare(rides);
        InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(2, 35.0);
        Assertions.assertEquals(invoiceSummary, expectedInvoiceSummary);
    }

    @Test
    public void givenUserId_whenValidUser_ShouldReturnInvoiceSummary() {
        Ride[] rides = {
                new Ride(2.0, 5, "Premium"),
                new Ride(0.1, 1, "Premium")
        };
        cabInvoiceGenerator.userRideRepository.put("User001", Arrays.asList(rides));
        try {
            InvoiceSummary invoiceSummary = cabInvoiceGenerator.calculateFare("User001");
            InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(2, 50.0);
            Assertions.assertEquals(invoiceSummary, expectedInvoiceSummary);
        } catch (CabInvoiceCustomException e) {}
    }

    @Test
    public void givenUserId_whenInValidUser_ShouldThrowException() {
        Ride[] rides = {
                new Ride(2.0, 5, "Premium"),
                new Ride(0.1, 1, "Premium")
        };
        cabInvoiceGenerator.userRideRepository.put("User001", Arrays.asList(rides));
        try {
            InvoiceSummary invoiceSummary = cabInvoiceGenerator.calculateFare("User002");
            InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(2, 50.0);
        } catch (CabInvoiceCustomException e) {
            Assertions.assertEquals(CabInvoiceCustomException.ExceptionType.INVALID_USER, e.type);
        }
    }
}
