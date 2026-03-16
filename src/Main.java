/**
 * Consolidated Use Case 2: Basic Room Types & Static Availability
 */

// --- Domain Models ---

abstract class Room {
    protected int numberOfBeds;
    protected int squareFeet;
    protected double pricePerNight;

    public Room(int numberOfBeds, int squareFeet, double pricePerNight) {
        this.numberOfBeds = numberOfBeds;
        this.squareFeet = squareFeet;
        this.pricePerNight = pricePerNight;
    }

    public void displayRoomDetails() {
        System.out.println("Room Details - Beds: " + numberOfBeds
                + ", Area: " + squareFeet + " sq.ft, Price: $" + pricePerNight);
    }
}

class SingleRoom extends Room {
    public SingleRoom() {
        super(1, 250, 1500.0);
    }
}

class DoubleRoom extends Room {
    public DoubleRoom() {
        super(2, 400, 2500.0);
    }
}

class SuiteRoom extends Room {
    public SuiteRoom() {
        super(3, 750, 5000.0);
    }
}

// --- Main Entry Point ---

public class UseCase2 {

    public static void main(String[] args) {
        // Initialization
        Room singleRoom = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suiteRoom = new SuiteRoom();

        // Static availability variables
        int singleRoomsAvailable = 10;
        int doubleRoomsAvailable = 5;
        int suiteRoomsAvailable = 2;

        System.out.println("--- Hotel Room Initialization ---");

        System.out.print("Single Room (Available: " + singleRoomsAvailable + ") -> ");
        singleRoom.displayRoomDetails();

        System.out.print("Double Room (Available: " + doubleRoomsAvailable + ") -> ");
        doubleRoom.displayRoomDetails();

        System.out.print("Suite Room (Available: " + suiteRoomsAvailable + ") -> ");
        suiteRoom.displayRoomDetails();
    }
}