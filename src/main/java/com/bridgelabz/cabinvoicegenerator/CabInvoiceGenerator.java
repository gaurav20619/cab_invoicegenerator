package com.bridgelabz.cabinvoicegenerator;
import java.util.*;

public class CabInvoiceGenerator {
    private static final int NORMAL_RIDE_MINIMUM_COST_PER_KILOMETER = 10;
    private static final int NORMAL_RIDE_COST_PER_TIME = 1;
    private static final int NORMAL_RIDE_MIN_FARE = 5;

    private static final int PREMIUM_RIDE_MINIMUM_COST_PER_KILOMETER = 15;
    private static final int PREMIUM_RIDE_COST_PER_TIME = 2;
    private static final int PREMIUM_RIDE_MIN_FARE = 10;

    private static final String NORMAL_RIDE = "NORMAL";
    private static final String PREMIUM_RIDE = "PREMIUM";

    public Map<String, List<Ride>> userRideRepository = new HashMap<>();

    
    public double calculateFare(double distance, int time, String rideType) {
        double totalFare = 0.0;
        if (NORMAL_RIDE.equalsIgnoreCase(rideType)) {
            totalFare = distance * NORMAL_RIDE_MINIMUM_COST_PER_KILOMETER + time * NORMAL_RIDE_COST_PER_TIME;
            return Math.max(totalFare, NORMAL_RIDE_MIN_FARE);
        } else if (PREMIUM_RIDE.equalsIgnoreCase(rideType)) {
            totalFare = distance * PREMIUM_RIDE_MINIMUM_COST_PER_KILOMETER + time * PREMIUM_RIDE_COST_PER_TIME;
            return Math.max(totalFare, PREMIUM_RIDE_MIN_FARE);
        }
        return totalFare;
    }

    
    public InvoiceSummary calculateFare(Ride[] rides) {
        double totalFare = 0;
        for (Ride ride : rides) {
            totalFare += this.calculateFare(ride.getDistance(), ride.getTime(), ride.getRideType());
        }
        return new InvoiceSummary(rides.length, totalFare);
    }

    
    public InvoiceSummary calculateFare(String userId) throws CabInvoiceCustomException{
        List<Ride> rides = this.userRideRepository.get(userId);
        if (rides == null) {
            throw new CabInvoiceCustomException("Invalid User", CabInvoiceCustomException.ExceptionType.INVALID_USER);
        }
        double totalFare = 0;
        for (Ride ride : rides) {
            totalFare += this.calculateFare(ride.getDistance(), ride.getTime(), ride.getRideType());
        }
        return new InvoiceSummary(rides.size(), totalFare);
    }

    
    public static void main(String[] args) {
        System.out.println("Welcome to CabInvoiceGenerator Application!");
    }
}
