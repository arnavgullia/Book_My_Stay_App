import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

public class UC3 {

    /**
     * Inner Class - Room
     * Defines the static characteristics.
     */
    static class Room {
        private String type;
        private int beds;
        private int size;
        private double pricePerNight;

        public Room(String type, int beds, int size, double pricePerNight) {
            this.type = type;
            this.beds = beds;
            this.size = size;
            this.pricePerNight = pricePerNight;
        }

        public String getType() { return type; }
        public int getBeds() { return beds; }
        public int getSize() { return size; }
        public double getPricePerNight() { return pricePerNight; }
    }

    /**
     * Inner Class - RoomInventory
     * Manages centralized availability.
     */
    static class RoomInventory {
        private Map<String, Integer> roomAvailability;

        public RoomInventory() {
            roomAvailability = new HashMap<>();
            initializeInventory();
        }

        private void initializeInventory() {
            roomAvailability.put("Single Room", 5);
            roomAvailability.put("Double Room", 3);
            roomAvailability.put("Suite Room", 2);
        }

        public Map<String, Integer> getRoomAvailability() {
            return roomAvailability;
        }
    }

    /**
     * Entry Point
     */
    public static void main(String[] args) {
        RoomInventory inventory = new RoomInventory();

        List<Room> roomSpecs = new ArrayList<>();
        roomSpecs.add(new Room("Single Room", 1, 250, 1500.0));
        roomSpecs.add(new Room("Double Room", 2, 400, 2500.0));
        roomSpecs.add(new Room("Suite Room", 3, 750, 5000.0));

        System.out.println("Hotel Room Inventory Status\n");

        Map<String, Integer> currentAvailability = inventory.getRoomAvailability();

        for (Room room : roomSpecs) {
            System.out.println(room.getType() + ":");
            System.out.println("Beds: " + room.getBeds());
            System.out.println("Size: " + room.getSize() + " sqft");
            System.out.println("Price per night: " + room.getPricePerNight());

            int availableCount = currentAvailability.getOrDefault(room.getType(), 0);
            System.out.println("Available Rooms: " + availableCount);
            System.out.println();
        }
    }
}