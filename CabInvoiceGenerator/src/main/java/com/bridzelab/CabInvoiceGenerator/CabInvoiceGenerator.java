package com.bridzelab.CabInvoiceGenerator;

/**
 * Creating CabInvoiceGenerator class for invoice generator for the given distance and time
 */
public class CabInvoiceGenerator {
    private static final int MINIMUM_COST_PER_KILOMETER = 10;
    private static final int COST_PER_TIME = 1;

    /**
     * Creating calculateFare to calculate the fare for the given distance and time
     * @param distance - double distance
     * @param time - int time
     * @return total fare
     */
    public static double calculateFare(double distance, int time) {
        return distance * MINIMUM_COST_PER_KILOMETER + time * COST_PER_TIME;
    }

    /**
     * calculate the aggregatetotal for all
     * @param rides
     * @return
     */

    public double calculateFare(Ride[] rides) {
        double totalFare = 0;
        for (Ride ride : rides) {

            totalFare += this.calculateFare(ride.getDistance(), ride.getTime());
        }
        return totalFare;
    }
    /**
     * Main method to print Welcome message
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("Welcome to CabInvoiceGenerator Application!");
        System.out.println(calculateFare(2.0, 5));
    }
}