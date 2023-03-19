package com.bridgelabz.cabinvoicegenerator;
import java.util.*;

public class CabInvoiceGenerator {
    private static final int MINIMUM_COST_PER_KILOMETER = 10;
    private static final int COST_PER_TIME = 1;
    private static final int MIN_FARE = 5;
    public Map<String, List<Ride>> userRideRepository = new HashMap<>();
    
    public double calculateFare(double distance, int time) {
        double totalFare = distance * MINIMUM_COST_PER_KILOMETER + time * COST_PER_TIME;
        return Math.max(totalFare, MIN_FARE);
    }

    
    public InvoiceSummary calculateFare(Ride[] rides) {
        double totalFare = 0;
        for (Ride ride : rides) {
            totalFare += this.calculateFare(ride.getDistance(), ride.getTime());
        }
        return new InvoiceSummary(rides.length, totalFare);
    }
    
    public InvoiceSummary calculateFare(String userId) {
        List<Ride> rides = this.userRideRepository.get(userId);
        double totalFare = 0;
        for (Ride ride : rides) {
            totalFare += this.calculateFare(ride.getDistance(), ride.getTime());
        }
        return new InvoiceSummary(rides.size(), totalFare);
    }

    
    public static void main(String[] args) {
        System.out.println("Welcome to CabInvoiceGenerator!");
    }
}
