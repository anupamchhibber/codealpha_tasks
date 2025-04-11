
import java.time.LocalDate;
import java.util.*;

public class HotelManager {
    private List<Room> rooms;
    private List<Booking> bookings;

    public HotelManager() {
        rooms = new ArrayList<>();
        bookings = new ArrayList<>();
        initRooms();
    }

    private void initRooms() {
        rooms.add(new Room(101, "Single", 100));
        rooms.add(new Room(102, "Double", 150));
        rooms.add(new Room(103, "Suite", 250));
        rooms.add(new Room(201, "Single", 100));
        rooms.add(new Room(202, "Double", 150));
    }

    public List<Room> getAvailableRooms(LocalDate checkIn, LocalDate checkOut) {
        List<Room> available = new ArrayList<>();
        for (Room room : rooms) {
            boolean isBooked = false;
            for (Booking b : bookings) {
                if (b.room.equals(room) && b.overlaps(checkIn, checkOut)) {
                    isBooked = true;
                    break;
                }
            }
            if (!isBooked) available.add(room);
        }
        return available;
    }

    public boolean bookRoom(String name, Room room, LocalDate checkIn, LocalDate checkOut) {
        for (Booking b : bookings) {
            if (b.room.equals(room) && b.overlaps(checkIn, checkOut)) {
                return false;
            }
        }
        bookings.add(new Booking(name, room, checkIn, checkOut));
        return true;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public boolean cancelBooking(String name) {
        return bookings.removeIf(b -> b.customerName.equalsIgnoreCase(name));
    }

    public boolean modifyBooking(String name, Room newRoom, LocalDate newCheckIn, LocalDate newCheckOut) {
        Booking toModify = null;
        for (Booking b : bookings) {
            if (b.customerName.equalsIgnoreCase(name)) {
                toModify = b;
                break;
            }
        }
        if (toModify != null) {
            bookings.remove(toModify);
            boolean success = bookRoom(name, newRoom, newCheckIn, newCheckOut);
            if (!success) bookings.add(toModify); // rollback
            return success;
        }
        return false;
    }

    public List<Room> getAllRooms() {
        return rooms;
    }
}
