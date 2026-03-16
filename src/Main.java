import java.util.*;

public class UC6 {

    // --- FROM USE CASE 3 & 4 ---
    static class RoomInventory {
        private Map<String, Integer> roomAvailability = new HashMap<>();

        public RoomInventory() {
            roomAvailability.put("Single", 5);
            roomAvailability.put("Double", 3);
            roomAvailability.put("Suite", 2);
        }

        public Map<String, Integer> getRoomAvailability() {
            return roomAvailability;
        }

        public void updateAvailability(String roomType, int count) {
            roomAvailability.put(roomType, count);
        }
    }

    // --- FROM USE CASE 5 ---
    static class Reservation {
        private String guestName;
        private String roomType;

        public Reservation(String guestName, String roomType) {
            this.guestName = guestName;
            this.roomType = roomType;
        }

        public String getGuestName() { return guestName; }
        public String getRoomType() { return roomType; }
    }

    static class BookingRequestQueue {
        private Queue<Reservation> requestQueue = new LinkedList<>();

        public void addRequest(Reservation reservation) { requestQueue.offer(reservation); }
        public Reservation getNextRequest() { return requestQueue.poll(); }
        public boolean hasPendingRequests() { return !requestQueue.isEmpty(); }
    }

    // --- USE CASE 6 LOGIC ---
    static class RoomAllocationService {
        private Set<String> allocatedRoomIds = new HashSet<>();
        private Map<String, Set<String>> assignedRoomsByType = new HashMap<>();

        /**
         * Confirms a booking request by assigning a unique ID and updating inventory.
         */
        public void allocateRoom(Reservation reservation, RoomInventory inventory) {
            String type = reservation.getRoomType();
            Map<String, Integer> availability = inventory.getRoomAvailability();

            if (availability.getOrDefault(type, 0) > 0) {
                // 1. Generate unique Room ID
                String roomId = generateRoomId(type);

                // 2. Assign and track
                allocatedRoomIds.add(roomId);
                assignedRoomsByType.computeIfAbsent(type, k -> new HashSet<>()).add(roomId);

                // 3. Update inventory immediately
                inventory.updateAvailability(type, availability.get(type) - 1);

                // 4. Output confirmation
                System.out.println("Booking confirmed for Guest: " + reservation.getGuestName()
                        + ", Room ID: " + roomId);
            } else {
                System.out.println("Booking failed for Guest: " + reservation.getGuestName()
                        + ". No " + type + " rooms available.");
            }
        }

        private String generateRoomId(String roomType) {
            // Logic to create ID based on current count of assigned rooms of that type
            int count = assignedRoomsByType.getOrDefault(roomType, new HashSet<>()).size() + 1;
            return roomType + "-" + count;
        }
    }

    /**
     * MAIN CLASS - Execution point
     */
    public static void main(String[] args) {
        System.out.println("Room Allocation Processing");

        // Initialize Services
        RoomInventory inventory = new RoomInventory();
        BookingRequestQueue bookingQueue = new BookingRequestQueue();
        RoomAllocationService allocationService = new RoomAllocationService();

        // Simulate Use Case 5: Capturing Requests in FIFO order
        bookingQueue.addRequest(new Reservation("Abhi", "Single"));
        bookingQueue.addRequest(new Reservation("Subha", "Single")); // Changed to match your screenshot output
        bookingQueue.addRequest(new Reservation("Vanmathi", "Suite"));

        // Process Use Case 6: Consume Queue and Allocate Rooms
        while (bookingQueue.hasPendingRequests()) {
            Reservation request = bookingQueue.getNextRequest();
            allocationService.allocateRoom(request, inventory);
        }
    }
}