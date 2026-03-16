import java.util.*;

public class UC12{

    /**
     * CLASS - RoomInventory
     * Manages the live availability of rooms.
     */
    static class RoomInventory {
        private Map<String, Integer> roomAvailability = new HashMap<>();

        public RoomInventory() {
            // Initial state (after some bookings have already happened)
            roomAvailability.put("Single", 4);
            roomAvailability.put("Double", 2);
            roomAvailability.put("Suite", 1);
        }

        public void revertInventory(String roomType) {
            int currentCount = roomAvailability.getOrDefault(roomType, 0);
            roomAvailability.put(roomType, currentCount + 1);
            System.out.println("Inventory Reverted: " + roomType + " count is now " + (currentCount + 1));
        }

        public void displayStatus() {
            System.out.println("Current Availability: " + roomAvailability);
        }
    }

    /**
     * CLASS - CancellationService
     * Use Case 12: Handles the cancellation process and triggers inventory updates.
     */
    static class CancellationService {

        public void processCancellation(String guestName, String roomType, RoomInventory inventory) {
            System.out.println("Processing Cancellation for Guest: " + guestName);

            // 1. Revert the inventory count
            inventory.revertInventory(roomType);

            // 2. Log confirmation
            System.out.println("Cancellation successful. Refund initiated for " + guestName + ".");
            System.out.println("----------------------------------------------");
        }
    }

    /**
     * MAIN CLASS - UC12bms
     */
    public static void main(String[] args) {
        System.out.println("Hotel Management System: Cancellation & Reversion\n");

        // 1. Setup
        RoomInventory inventory = new RoomInventory();
        CancellationService cancellationService = new CancellationService();

        // 2. Show state before cancellation
        System.out.print("Before Cancellation -> ");
        inventory.displayStatus();
        System.out.println();

        // 3. Perform Cancellation (User: Abhi cancels a Single Room)
        cancellationService.processCancellation("Abhi", "Single", inventory);

        // 4. Show state after cancellation
        System.out.print("After Cancellation  -> ");
        inventory.displayStatus();
    }
}