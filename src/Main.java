import java.util.*;

public class UC11bms {

    /**
     * CLASS - NotificationService
     * Use Case 11: Handles real-time communication with guests.
     */
    static class NotificationService {

        /**
         * Simulates sending a confirmation notification to the guest.
         */
        public void sendBookingConfirmation(String guestName, String roomType, String roomId) {
            System.out.println("[NOTIFICATION SENT]");
            System.out.println("To: " + guestName);
            System.out.println("Message: Your booking for a " + roomType + " is confirmed!");
            System.out.println("Your assigned Room ID is: " + roomId);
            System.out.println("------------------------------------------");
        }

        public void sendFailureAlert(String guestName, String roomType) {
            System.err.println("[NOTIFICATION ALERT]");
            System.err.println("To: " + guestName);
            System.err.println("Message: We're sorry, the " + roomType + " is currently unavailable.");
            System.err.println("------------------------------------------");
        }
    }

    /**
     * CLASS - Room (Characteristics)
     */
    static class Room {
        private String type;
        public Room(String type) { this.type = type; }
        public String getType() { return type; }
    }

    /**
     * MAIN CLASS - UC11bms
     */
    public static void main(String[] args) {
        System.out.println("Hotel Management System: Real-time Notification Dispatcher\n");

        // 1. Initialize Notification Service
        NotificationService notificationService = new NotificationService();

        // 2. Mock Data for demonstration
        // In a full system, these variables would come from the Allocation Service
        String guest1 = "Abhi";
        String roomType1 = "Single Room";
        String roomId1 = "SR-101";

        String guest2 = "Subha";
        String roomType2 = "Double Room";
        String roomId2 = "DR-202";

        // 3. Trigger Real-time Notifications
        // These are triggered immediately after the "Single Source of Truth" (Inventory) is updated
        notificationService.sendBookingConfirmation(guest1, roomType1, roomId1);
        notificationService.sendBookingConfirmation(guest2, roomType2, roomId2);

        // 4. Example of a failure notification (e.g., if inventory was 0)
        notificationService.sendFailureAlert("Vanmathi", "Suite Room");
    }
}