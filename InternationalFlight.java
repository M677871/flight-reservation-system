package oopproject;

public class InternationalFlight extends Flight {
    private double internationalFees;
    private boolean requiresVisa;

    public InternationalFlight(String flightNumber, CustomizedDate departureTime, CustomizedDate arrivalTime,
                                 String origin, String destination, double distance, double internationalFees, Plane plane, boolean requiresVisa) {
        // Call parent class constructor first
        super(flightNumber, departureTime, arrivalTime, origin, destination, distance, plane);
        try {
            // Validate and set the international fees
            setInternationalFees(internationalFees);
            // Set whether the flight requires a visa
            setRequiresVisa(requiresVisa);
        } catch (Exception e) {
            System.out.println("Error initializing InternationalFlight: " + e.getMessage());
        }
    }

    public double getInternationalFees() {
        return internationalFees;
    }

    public void setInternationalFees(double internationalFees) {
        // Validate that fees are non-negative.
        if (internationalFees < 0) {
            throw new IllegalArgumentException("International fees must be non-negative.");
        }
        this.internationalFees = internationalFees;
    }

    public void setRequiresVisa(boolean requiresVisa) {
        this.requiresVisa = requiresVisa;
    }

    public boolean requiresVisa() {
        return requiresVisa;
    }

    // Determine if the flight is international by comparing origin and destination.
    public boolean isInternational() {
        try {
            return !origin.equals(destination);
        } catch (Exception e) {
            System.out.println("Error determining if flight is international: " + e.getMessage());
            return false;
        }
    }

    // Calculate the total cost including the international fees.
    public double calculateCost() {
        return internationalFees + super.calculateCost();
    }

    // Return a string representation of the flight with cost and visa requirement details.
    public String toString() {
        try {
            return "International flight; " + super.toString() +
                   "\nCalculated Cost: " + calculateCost() + "$" +
                   "\n" + (requiresVisa() ? "Requires a Visa." : "Doesn't require a Visa.");
        } catch (Exception e) {
            return "Error displaying InternationalFlight details: " + e.getMessage();
        }
    }
}
