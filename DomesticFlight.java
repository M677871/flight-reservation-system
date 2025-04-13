package oopproject;

public class DomesticFlight extends Flight {

    private double domesticDiscount;

    public DomesticFlight(String flightNumber, CustomizedDate departureTime, CustomizedDate arrivalTime,
            String origin, String destination, double distance, Plane plane, double domesticDiscount) {
        // Call the superclass constructor and wrap discount validation in try-catch.
        super(flightNumber, departureTime, arrivalTime, origin, destination, distance, plane);
        try {
            setDomesticDiscount(domesticDiscount);
        } catch (Exception e) {
            System.out.println("Error setting domestic discount: " + e.getMessage());
            this.domesticDiscount = 0;
        }
    }

    public double getDomesticDiscount() {
        return domesticDiscount;
    }

    public void setDomesticDiscount(double domesticDiscount) {
        // Validate discount: if negative, set to 0.
        if (domesticDiscount >= 0) {
            this.domesticDiscount = domesticDiscount;
        } else {
            this.domesticDiscount = 0;
        }
    }

    public double calculateCost() {
        try {
            double baseCost = super.calculateCost();
            // Apply discount percentage reduction.
            return baseCost - ((domesticDiscount / 100) * baseCost);
        } catch (Exception e) {
            System.out.println("Error calculating cost: " + e.getMessage());
            return 0;
        }
    }

    public String toString() {
        try {
            return "Domestic Flight ;" + super.toString() +
                   "\nDomestic discount: " + domesticDiscount + "%" +
                   "\nCalculated Cost: " + calculateCost() + " $.";
        } catch (Exception e) {
            return "Error displaying DomesticFlight details: " + e.getMessage();
        }
    }
}
