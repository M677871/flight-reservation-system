package oopproject;

public class Plane {
    private String planeID;
    private String model;
    private int capacity;

    public Plane(String planeID, String model, int capacity) {
        try {
            setPlaneID(planeID);
            setModel(model);
            setCapacity(capacity);
        } catch (Exception e) {
            System.out.println("Error initializing Plane: " + e.getMessage());
        }
    }

    public String getPlaneID() {
        return planeID;
    }

    public void setPlaneID(String planeID) {
        // You might want to check for null or empty string here
        this.planeID = planeID;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        // Optional: validate model string if needed
        this.model = model;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        // Optional: add validation to ensure capacity is positive
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be greater than zero.");
        }
        this.capacity = capacity;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Plane) {
            return planeID.equals(((Plane) obj).planeID);
        }
        return false;
    }

    @Override
    public String toString() {
        try {
            return "ID: " + planeID + " , of model: " + model + " , with capacity of " + capacity + " seats.";
        } catch (Exception e) {
            // In case something unexpected happens
            return "Error displaying plane details: " + e.getMessage();
        }
    }
}
