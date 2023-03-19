package com.bridgelabz.cabinvoicegenerator;

public class CabInvoiceGenerator {
    private static final int MINIMUM_COST_PER_KILOMETER = 10;
    private static final int COST_PER_TIME = 1;

    public static double calculateFare(double distance, int time) {
        return distance * MINIMUM_COST_PER_KILOMETER + time * COST_PER_TIME;
    }
    
    public static void main(String[] args) {
        System.out.println("Welcome to CabInvoiceGenerator Application");
        System.out.println("Fare Value is:" +calculateFare(2.0, 5));
    }
}
