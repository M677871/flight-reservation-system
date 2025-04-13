package oopproject;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Flight {
    protected String flightNumber;
    protected CustomizedDate departureTime;
    protected CustomizedDate arrivalTime;
    protected String origin;
    protected String destination;
    protected double distance;
    protected static double baseCost = 45;
    protected boolean[] seats;
    protected Plane plane;

    // Constructor with try-catch block to catch any unexpected errors during initialization.
    public Flight(String flightNumber, CustomizedDate departureTime, CustomizedDate arrivalTime,
                  String origin, String destination, double distance, Plane plane) {
        try {
            setFlightNumber(flightNumber);
            setDepartureTime(departureTime);
            setArrivalTime(arrivalTime);
            setOrigin(origin);
            setDestination(destination);
            setDistance(distance);
            setPlane(plane);
            this.seats = new boolean[plane.getCapacity()];
        } catch (Exception e) {
            System.out.println("Error initializing Flight: " + e.getMessage());
        }
    }

    // Setters and Getters

    public void setFlightNumber(String flightNumber) {
        if (isValidFlightNumber(flightNumber)) {
            this.flightNumber = flightNumber;
        } else {
            this.flightNumber = "XXX000";
        }
    }

    public void setDepartureTime(CustomizedDate departureTime) {
        this.departureTime = departureTime;
    }

    public void setArrivalTime(CustomizedDate arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setDistance(double distance) {
        if (distance < 0) {
            this.distance = 0;
        } else {
            this.distance = distance;
        }
    }

    public void setPlane(Plane plane) {
        this.plane = plane;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public CustomizedDate getDepartureTime() {
        return departureTime;
    }

    public CustomizedDate getArrivalTime() {
        return arrivalTime;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public double getDistance() {
        return distance;
    }

    public double getBaseCost() {
        return baseCost;
    }

    public boolean[] getSeats() {
        return seats;
    }

    public Plane getPlane() {
        return plane;
    }

    public double calculateCost() {
        return baseCost * (distance / 100.0);
    }

    // Returns the array that holds the reservation status for all seats.
    public boolean[] getAllSeats() {
        return seats;
    }

    // Returns an array of reserved seat numbers.
    public int[] getReservedSeats() {
        int reservedCount = 0;
        for (boolean seat : seats) {
            if (seat) {
                reservedCount++;
            }
        }

        int[] reservedSeats = new int[reservedCount];
        int index = 0;
        for (int i = 0; i < seats.length; i++) {
            if (seats[i]) {
                reservedSeats[index++] = i + 1;
            }
        }
        return reservedSeats;
    }

    // Returns an array of available seat numbers.
    public int[] getAvailableSeats() {
        int availableCount = 0;
        for (boolean seat : seats) {
            if (!seat) {
                availableCount++;
            }
        }

        int[] availableSeats = new int[availableCount];
        int index = 0;
        for (int i = 0; i < seats.length; i++) {
            if (!seats[i]) {
                availableSeats[index++] = i + 1;
            }
        }
        return availableSeats;
    }

    // Attempts to reserve a specific seat.
    public boolean reserveSeat(int seat) {
        if (seat < 1 || seat > plane.getCapacity()) {
            return false;
        }
        if (seats[seat - 1]) {
            return false;
        }
        seats[seat - 1] = true;
        return true;
    }

    // Cancels the reservation for a specific seat.
    public boolean cancelSeat(int seat) {
        if (seat < 1 || seat > plane.getCapacity()) {
            return false;
        }
        if (!seats[seat - 1]) {
            return false;
        }
        seats[seat - 1] = false;
        return true;
    }

    // Interactive method to reserve a seat with try-catch to handle invalid input.
    public boolean reserveSeat() {
        Scanner input = new Scanner(System.in);
        int[] availableSeats = getAvailableSeats();
        if (availableSeats.length == 0) {
            System.out.println("No available seats.");
            return false;
        }

        System.out.println("Available seats: " + Arrays.toString(availableSeats));
        System.out.print("Enter seat number to reserve: ");
        try {
            int seat = input.nextInt();
            return reserveSeat(seat);
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid seat number.");
            input.next(); // Clear the invalid token.
            return false;
        }
    }

    // Checks if two Flight objects are the same.
    public boolean equals(Object obj) {
        if (obj instanceof Flight) {
            Flight otherFlight = (Flight) obj;
            return flightNumber.equals(otherFlight.flightNumber)
                    && departureTime.equalsDateAndTime(otherFlight.departureTime);
        }
        return false;
    }

    // Determines the flight status based on the current time.
    public int getFlightStatus() {
        try {
            CustomizedDate now = new CustomizedDate(); // Current time
            if (now.isBefore(departureTime)) {
                return 0; // Scheduled
            } else if (now.isBefore(arrivalTime)) {
                return 1; // En route
            } else {
                return 2; // Completed
            }
        } catch (Exception e) {
            System.out.println("Error determining flight status: " + e.getMessage());
            return -1; // Unknown status
        }
    }

    // Returns a string representation of the Flight.
    public String toString() {
        try {
            return ": " +
                    "\nFlightNumber: " + flightNumber + '\n' +
                    "DepartureTime: " + departureTime +
                    "\nArrivalTime: " + arrivalTime +
                    "\nOrigin: " + origin + '\n' +
                    "Destination: " + destination +
                    "\nDistance: " + distance + " Km." +
                    "\nBaseCost: " + baseCost + " $." +
                    "\nPlane: " + plane;
        } catch (Exception e) {
            return "Error displaying flight details: " + e.getMessage();
        }
    }

    // Checks if the flight number is valid using the pattern: 3 letters followed by 3 digits.
    private boolean isValidFlightNumber(String flightNumber) {
        return flightNumber.matches("[a-zA-Z]{3}\\d{3}");
    }
}
