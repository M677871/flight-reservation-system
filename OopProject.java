package oopproject;
import plane.java;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class OopProject {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Create the ArrayLists for flights and fleet
        ArrayList<Flight> flights = new ArrayList<>();
        ArrayList<Plane> fleet = new ArrayList<>();

        // Main loop for the menu
        while (true) {
            System.out.println("\n--- Airline Management System ---");
            System.out.println("1. Add Plane to Fleet");
            System.out.println("2. Delete Plane from Fleet");
            System.out.println("3. Add Flight");
            System.out.println("4. Delete Flight");
            System.out.println("5. Search Flight by Flight Number");
            System.out.println("6. Search Flights by Origin/Destination");
            System.out.println("7. Search Flights by Departure Date");
            System.out.println("8. Search Flights by Arrival Date");
            System.out.println("9. Manage Seat Reservations");
            System.out.println("10. Exit");
            System.out.print("Choose an option: ");
            
            int choice = 0;
            // Wrap input reading in try-catch to avoid InputMismatchException
            while (true) {
                try {
                    choice = input.nextInt();
                    if (choice > 0 && choice <= 10) {
                        break;
                    } else {
                        System.out.println("The option should be between 1 and 10.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a number between 1 and 10.");
                    input.next(); // clear the invalid token
                }
            }
            
            input.nextLine(); // consume newline

            if (choice == 10) {
                System.out.println("Exiting....");
                break;
            }

            // Switch with each case calling the appropriate method
            switch (choice) {
                case 1:
                    addPlaneToFleet(fleet);
                    break;
                case 2:
                    deletePlaneFromFleet(fleet);
                    break;
                case 3:
                    addFlight(flights, fleet);
                    break;
                case 4:
                    deleteFlight(flights);
                    break;
                case 5:
                    searchFlightByNumber(flights);
                    break;
                case 6:
                    searchFlightsByOriginDestination(flights);
                    break;
                case 7:
                    searchFlightsByDepartureDate(flights);
                    break;
                case 8:
                    searchFlightsByArrivalDate(flights);
                    break;
                case 9:
                    manageSeatReservations(flights);
                    break;
            }
        }
        input.close();
    }

    // Add a plane to the fleet
    public static void addPlaneToFleet(ArrayList<Plane> fleet) {
        Scanner input = new Scanner(System.in);
        String planeID;
        while (true) {
            System.out.print("Enter plane ID: ");
            planeID = input.nextLine().trim();

            // Check if the plane ID already exists in the fleet
            boolean idExists = false;
            for (Plane plane : fleet) {
                if (plane.getPlaneID().equalsIgnoreCase(planeID)) {
                    idExists = true;
                    break;
                }
            }

            if (idExists) {
                System.out.println("Error: Plane ID already exists. Enter another one.");
            } else {
                break;
            }
        }

        System.out.print("Enter plane model: ");
        String model = input.nextLine();

        int capacity = 0;
        while (true) {
            try {
                System.out.print("Enter plane capacity: ");
                capacity = input.nextInt();
                if (capacity > 0) {
                    break;
                } else {
                    System.out.println("The capacity should be greater than zero. Try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number for capacity.");
                input.next(); // clear invalid input
            }
        }
        input.nextLine(); // consume newline

        // Create a new plane object and add it to the fleet
        Plane plane = new Plane(planeID, model, capacity);
        fleet.add(plane);
        System.out.println("Plane added to the fleet.");
    }

    // Delete a plane from the fleet
    public static void deletePlaneFromFleet(ArrayList<Plane> fleet) {
        Scanner input = new Scanner(System.in);
        if (fleet.isEmpty()) {
            System.out.println("No planes available in the fleet. Cannot delete a plane.");
            return;
        }

        System.out.println("Available planes in the fleet:");
        for (Plane plane : fleet) {
            System.out.println("Plane ID: " + plane.getPlaneID() + " ; Model: " + plane.getModel());
        }

        String planeID;
        Plane planeToDelete = null;
        while (true) {
            System.out.print("Enter plane ID to delete: ");
            planeID = input.nextLine().trim();
            for (Plane plane : fleet) {
                if (plane.getPlaneID().equalsIgnoreCase(planeID)) {
                    planeToDelete = plane;
                    break;
                }
            }
            if (planeToDelete != null) {
                break;
            } else {
                System.out.println("Invalid plane ID. Please enter a valid plane ID from the list.");
            }
        }
        fleet.remove(planeToDelete);
        System.out.println("Plane with ID " + planeID + " removed from the fleet.");
    }

    // Add a flight with try-catch for numerical inputs
    public static void addFlight(ArrayList<Flight> flights, ArrayList<Plane> fleet) {
        Scanner input = new Scanner(System.in);
        if (fleet.isEmpty()) {
            System.out.println("No planes available in the fleet. Cannot add a flight.");
            return;
        }
        System.out.print("Enter flight number: ");
        String flightNumber;
        while (true) {
            flightNumber = input.nextLine().trim();
            if (flightNumber.matches("[a-zA-Z]{3}\\d{3}")) {
                boolean flightExists = false;
                for (Flight flight : flights) {
                    if (flight.getFlightNumber().equalsIgnoreCase(flightNumber)) {
                        flightExists = true;
                        break;
                    }
                }
                if (flightExists) {
                    System.out.println("Flight number already exists. Please enter a different flight number.");
                } else {
                    break;
                }
            } else {
                System.out.println("The flight number should be 3 characters followed by 3 digits. Try again.");
            }
        }

        System.out.print("Enter origin: ");
        String origin = input.nextLine().trim();
        System.out.print("Enter destination: ");
        String destination = input.nextLine().trim();

        double distance = 0;
        while (true) {
            try {
                System.out.print("Enter distance: ");
                distance = input.nextDouble();
                if (distance > 0) {
                    break;
                } else {
                    System.out.println("The distance should be greater than zero. Try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number for distance.");
                input.next(); // clear invalid input
            }
        }

        System.out.println("Enter departure date (YYYY-MM-DD HH:MM): ");
        boolean validDate = false;
        int year = 0, month = 0, day = 0, hours = 0, minutes = 0;
        CustomizedDate departureTime = null;
        while (!validDate) {
            try {
                System.out.print("Enter the year: ");
                year = input.nextInt();
                System.out.print("Enter the month: ");
                month = input.nextInt();
                System.out.print("Enter the day: ");
                day = input.nextInt();
                System.out.print("Enter the hours: ");
                hours = input.nextInt();
                System.out.print("Enter the minutes: ");
                minutes = input.nextInt();
                input.nextLine();
                departureTime = new CustomizedDate(year, month, day, hours, minutes);
                if (departureTime.getDay() == day && departureTime.getMinute() == minutes &&
                        departureTime.getHours() == hours && departureTime.getMonth() == month &&
                        departureTime.getYear() == year) {
                    validDate = true;
                } else {
                    System.out.println("Invalid date entered. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter numbers for the date.");
                input.next(); // clear invalid input
            }
        }

        CustomizedDate arrivalTime = null;
        while (true) {
            System.out.println("Enter arrival date (YYYY-MM-DD HH:MM): ");
            validDate = false;
            while (!validDate) {
                try {
                    System.out.print("Enter the year: ");
                    year = input.nextInt();
                    System.out.print("Enter the month: ");
                    month = input.nextInt();
                    System.out.print("Enter the day: ");
                    day = input.nextInt();
                    System.out.print("Enter the hours: ");
                    hours = input.nextInt();
                    System.out.print("Enter the minutes: ");
                    minutes = input.nextInt();
                    arrivalTime = new CustomizedDate(year, month, day, hours, minutes);
                    if (arrivalTime.getDay() == day && arrivalTime.getMinute() == minutes &&
                            arrivalTime.getHours() == hours && arrivalTime.getMonth() == month &&
                            arrivalTime.getYear() == year) {
                        validDate = true;
                    } else {
                        System.out.println("Invalid date entered. Please try again.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter numbers for the date.");
                    input.next(); // clear invalid input
                }
            }
            if (arrivalTime.isBefore(departureTime)) {
                System.out.println("Arrival time should be after departure time.");
            } else {
                break;
            }
        }
        input.nextLine(); // clear buffer

        System.out.print("Is this an International flight? (yes/no): ");
        String flightType;
        while (true) {
            flightType = input.nextLine().trim();
            if (flightType.equalsIgnoreCase("yes") || flightType.equalsIgnoreCase("no")) {
                break;
            } else {
                System.out.println("You should enter 'yes' or 'no'.");
            }
        }

        Plane selectedPlane = null;
        while (selectedPlane == null) {
            System.out.println("Available Plane IDs and Models: ");
            for (Plane plane : fleet) {
                System.out.println("Plane ID: " + plane.getPlaneID() + " ; Model: " + plane.getModel());
            }
            System.out.print("Enter a valid plane ID to assign to the flight: ");
            String planeID = input.nextLine().trim();
            for (Plane plane : fleet) {
                if (plane.getPlaneID().equalsIgnoreCase(planeID)) {
                    selectedPlane = plane;
                    break;
                }
            }
            if (selectedPlane == null) {
                System.out.println("Invalid plane ID. Please enter a valid plane ID.");
            }
        }

        Flight newFlight;
        if (flightType.equalsIgnoreCase("yes")) {
            double internationalFees = 0;
            while (true) {
                try {
                    System.out.print("Enter international fees: ");
                    internationalFees = input.nextDouble();
                    if (internationalFees > 0) {
                        break;
                    } else {
                        System.out.println("International fees should be greater than zero.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a valid number for fees.");
                    input.next();
                }
            }
            System.out.print("Requires Visa? (y/n): ");
            char c;
            while (true) {
                try {
                    c = input.next().charAt(0);
                    c = Character.toLowerCase(c);
                    if (c == 'y' || c == 'n') {
                        break;
                    } else {
                        System.out.println("You should enter y or n.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter y or n.");
                    input.next();
                }
            }
            boolean requiresVisa = (c == 'y');
            newFlight = new InternationalFlight(flightNumber, departureTime,
                    arrivalTime, origin, destination, distance, internationalFees,
                    selectedPlane, requiresVisa);
        } else {
            double domesticDiscount = 0;
            while (true) {
                try {
                    System.out.print("Enter domestic discount: ");
                    domesticDiscount = input.nextDouble();
                    if (domesticDiscount >= 0) {
                        break;
                    } else {
                        System.out.println("Discount has to be positive. Try again.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a valid number for discount.");
                    input.next();
                }
            }
            newFlight = new DomesticFlight(flightNumber, departureTime,
                    arrivalTime, origin, destination, distance, selectedPlane, domesticDiscount);
        }
        flights.add(newFlight);
        System.out.println("Flight added.");
    }

    // Delete a flight with try-catch around confirmations and inputs
    public static void deleteFlight(ArrayList<Flight> flights) {
        Scanner input = new Scanner(System.in);
        if (flights.isEmpty()) {
            System.out.println("No available flights to delete.");
            return;
        }
        System.out.println("Available Flights:");
        for (Flight flight : flights) {
            System.out.println(flight.getFlightNumber());
        }
        String flightNumber;
        Flight flightToDelete = null;
        while (true) {
            System.out.print("Enter a valid flight number to delete: ");
            flightNumber = input.nextLine().trim();
            for (Flight flight : flights) {
                if (flight.getFlightNumber().equalsIgnoreCase(flightNumber)) {
                    flightToDelete = flight;
                    break;
                }
            }
            if (flightToDelete != null) {
                System.out.print("Are you sure you want to delete this flight? (yes/no): ");
                String confirmation = input.nextLine().trim();
                if (confirmation.equalsIgnoreCase("yes")) {
                    flights.remove(flightToDelete);
                    System.out.println("Flight " + flightNumber + " deleted.");
                } else {
                    System.out.println("Flight deletion cancelled.");
                }
                break;
            } else {
                System.out.println("Invalid flight number. Please enter a valid one.");
            }
        }
    }

    // Search for a flight by its flight number
    public static void searchFlightByNumber(ArrayList<Flight> flights) {
        Scanner input = new Scanner(System.in);
        if (flights.isEmpty()) {
            System.out.println("No available flights.");
            return;
        }
        System.out.println("Available Flights:");
        for (Flight flight : flights) {
            System.out.println("Flight Number: " + flight.getFlightNumber());
        }
        String flightNumber;
        boolean flightFound = false;
        while (true) {
            System.out.print("Enter flight number to search: ");
            flightNumber = input.nextLine().trim();
            for (Flight flight : flights) {
                if (flight.getFlightNumber().equalsIgnoreCase(flightNumber)) {
                    System.out.println(flight);
                    flightFound = true;
                    break;
                }
            }
            if (flightFound) {
                break;
            } else {
                System.out.println("Flight not found. Please enter a valid flight number.");
            }
        }
    }

    // Search for flights by origin or destination
    public static void searchFlightsByOriginDestination(ArrayList<Flight> flights) {
        Scanner input = new Scanner(System.in);
        if (flights.isEmpty()) {
            System.out.println("No available flights.");
            return;
        }
        System.out.print("Enter origin to search (or leave blank): ");
        String origin = input.nextLine().trim();
        System.out.print("Enter destination to search (or leave blank): ");
        String destination = input.nextLine().trim();

        boolean foundFlight = false;
        for (Flight flight : flights) {
            boolean match = true;
            if (!origin.isEmpty() && !flight.getOrigin().equalsIgnoreCase(origin)) {
                match = false;
            }
            if (!destination.isEmpty() && !flight.getDestination().equalsIgnoreCase(destination)) {
                match = false;
            }
            if (match) {
                System.out.println(flight);
                foundFlight = true;
            }
        }
        if (!foundFlight) {
            System.out.println("No flights found.");
        }
    }

    // Search for flights by departure date
    public static void searchFlightsByDepartureDate(ArrayList<Flight> flights) {
        Scanner input = new Scanner(System.in);
        if (flights.isEmpty()) {
            System.out.println("No available flights.");
            return;
        }
        boolean validDate = false;
        int year = 0, month = 0, day = 0, hours = 0, minutes = 0;
        while (!validDate) {
            System.out.println("Enter departure date (YYYY-MM-DD HH:MM): ");
            try {
                System.out.print("Enter the year: ");
                year = input.nextInt();
                System.out.print("Enter the month: ");
                month = input.nextInt();
                System.out.print("Enter the day: ");
                day = input.nextInt();
                System.out.print("Enter the hours: ");
                hours = input.nextInt();
                System.out.print("Enter the minutes: ");
                minutes = input.nextInt();
                CustomizedDate inputDate = new CustomizedDate(year, month, day, hours, minutes);
                if (inputDate.getDay() == day && inputDate.getMinute() == minutes &&
                        inputDate.getHours() == hours && inputDate.getMonth() == month &&
                        inputDate.getYear() == year) {
                    validDate = true;
                } else {
                    System.out.println("Invalid date entered. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter numbers for the date.");
                input.next(); // clear invalid input
            }
        }
        CustomizedDate searchDate = new CustomizedDate(year, month, day, hours, minutes);
        boolean found = false;
        for (Flight flight : flights) {
            if (flight.getDepartureTime().equalsDate(searchDate)) {
                System.out.println(flight);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No flights found for the given date.");
        }
    }

    // Search for flights by arrival date
    public static void searchFlightsByArrivalDate(ArrayList<Flight> flights) {
        Scanner input = new Scanner(System.in);
        if (flights.isEmpty()) {
            System.out.println("No available flights.");
            return;
        }
        boolean validDate = false;
        int year = 0, month = 0, day = 0, hours = 0, minutes = 0;
        while (!validDate) {
            System.out.println("Enter arrival date (YYYY-MM-DD HH:MM): ");
            try {
                System.out.print("Enter the year: ");
                year = input.nextInt();
                System.out.print("Enter the month: ");
                month = input.nextInt();
                System.out.print("Enter the day: ");
                day = input.nextInt();
                System.out.print("Enter the hours: ");
                hours = input.nextInt();
                System.out.print("Enter the minutes: ");
                minutes = input.nextInt();
                input.nextLine();
                CustomizedDate inputDate = new CustomizedDate(year, month, day, hours, minutes);
                if (inputDate.getDay() == day && inputDate.getMinute() == minutes &&
                        inputDate.getHours() == hours && inputDate.getMonth() == month &&
                        inputDate.getYear() == year) {
                    validDate = true;
                } else {
                    System.out.println("Invalid date entered. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter numbers for the date.");
                input.next();
            }
        }
        CustomizedDate searchDate = new CustomizedDate(year, month, day, hours, minutes);
        boolean found = false;
        for (Flight flight : flights) {
            if (flight.getArrivalTime().equalsDate(searchDate)) {
                System.out.println(flight);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No flights found for the given date.");
        }
    }

    // Manage seat reservations with try-catch for option input
    public static void manageSeatReservations(ArrayList<Flight> flights) {
        Scanner input = new Scanner(System.in);
        if (flights.isEmpty()) {
            System.out.println("No available flights.");
            return;
        }
        System.out.println("Available Flights:");
        for (Flight flight : flights) {
            System.out.println("Flight Number: " + flight.getFlightNumber());
        }
        String flightNumber;
        boolean flightFound = false;
        while (true) {
            System.out.print("Enter flight number to manage reservations: ");
            flightNumber = input.nextLine().trim();
            if (!flightNumber.matches("[a-zA-Z]{3}\\d{3}")) {
                System.out.println("The flight number should be 3 characters followed by 3 digits. Try again.");
                continue;
            }
            for (Flight flight : flights) {
                if (flight.getFlightNumber().equalsIgnoreCase(flightNumber)) {
                    flightFound = true;
                    break;
                }
            }
            if (flightFound) {
                break;
            } else {
                System.out.println("Flight not found. Please enter a valid flight number.");
            }
        }
        for (Flight flight : flights) {
            if (flight.getFlightNumber().equalsIgnoreCase(flightNumber)) {
                System.out.println("Choose an option:");
                int option = 0;
                while (true) {
                    System.out.println("1. Reserve Seats");
                    System.out.println("2. Cancel Reservation");
                    try {
                        option = input.nextInt();
                        if (option == 1 || option == 2) {
                            break;
                        } else {
                            System.out.println("You should enter 1 or 2.");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter 1 or 2.");
                        input.next();
                    }
                }
                if (option == 1) {
                    int[] availableSeats = flight.getAvailableSeats();
                    if (availableSeats.length == 0) {
                        System.out.println("No available seats to reserve.");
                    } else {
                        System.out.println("Available seats: " + Arrays.toString(availableSeats));
                        int seat;
                        while (true) {
                            try {
                                System.out.print("Enter seat number to reserve: ");
                                seat = input.nextInt();
                                if (seat > 0 && seat <= availableSeats.length) {
                                    break;
                                } else {
                                    System.out.println("Seat number should be between 1 and " + availableSeats.length);
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("Invalid input. Please enter a valid seat number.");
                                input.next();
                            }
                        }
                        flight.reserveSeat(seat);
                        System.out.println("Seat " + seat + " has been reserved.");
                    }
                } else if (option == 2) {
                    int[] reservedSeats = flight.getReservedSeats();
                    if (reservedSeats.length == 0) {
                        System.out.println("No reserved seats to cancel.");
                    } else {
                        System.out.println("Reserved seats: " + Arrays.toString(reservedSeats));
                        int seatToCancel = 0;
                        boolean validSeatToCancel = false;
                        while (!validSeatToCancel) {
                            try {
                                System.out.print("Enter seat number to cancel: ");
                                seatToCancel = input.nextInt();
                                if (seatToCancel > 0 && isSeatReserved(reservedSeats, seatToCancel)) {
                                    validSeatToCancel = true;
                                } else {
                                    System.out.println("Enter a valid reserved seat number.");
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("Invalid input. Please enter a valid seat number.");
                                input.next();
                            }
                        }
                        flight.cancelSeat(seatToCancel);
                        System.out.println("Seat " + seatToCancel + " has been cancelled.");
                    }
                }
                break;
            }
        }
    }

    // Helper method to check if a seat is reserved
    public static boolean isSeatReserved(int[] reservedSeats, int seat) {
        for (int reservedSeat : reservedSeats) {
            if (reservedSeat == seat) {
                return true;
            }
        }
        return false;
    }
}
