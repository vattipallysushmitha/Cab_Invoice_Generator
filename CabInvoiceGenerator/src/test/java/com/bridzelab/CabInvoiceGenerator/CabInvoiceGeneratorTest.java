package com.bridzelab.CabInvoiceGenerator;

import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.Test;

public class CabInvoiceGeneratorTest {
    CabInvoiceGenerator cabInvoiceGenerator = new CabInvoiceGenerator();

    /**
     * test for calculate fare *UC1
     */
    @Test
    public void givenDistanceAndTime_ShouldReturnTotalFare() {
        double distance = 2.0;
        int time = 5;
        double fare = cabInvoiceGenerator.calculateFare(distance, time);
        Assertions.assertEquals(25, fare, 0.0);
    }

    /**
     * test for minimum input
     */
    @Test
    public void givenLessDistanceAndTime_ShouldReturnMinFare() {
        double distance = 0.1;
        int time = 1;
        double fare = cabInvoiceGenerator.calculateFare(distance, time);
        Assertions.assertEquals(2, fare, 0.0);
    }

    /**
     * test for multiple rides input *UC2
     */
    @Test
    public void givenMultipleRides_ShouldReturnTotalFare() {
        Ride[] rides = {
                new Ride(2.0, 5),
                new Ride(0.1, 1)
        };
        double totalFare = cabInvoiceGenerator.calculateFare(rides);
        Assertions.assertEquals(27.0, totalFare, 0.0);
    }
}