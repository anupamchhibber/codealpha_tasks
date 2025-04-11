
import java.time.LocalDate;

public class Booking {
    String customerName;
    Room room;
    LocalDate checkInDate;
    LocalDate checkOutDate;

    public Booking(String customerName, Room room, LocalDate checkInDate, LocalDate checkOutDate) {
        this.customerName = customerName;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    public String toString() {
        return customerName + " - " + room + " from " + checkInDate + " to " + checkOutDate;
    }

    public boolean overlaps(LocalDate in, LocalDate out) {
        return !(checkOutDate.isBefore(in) || checkInDate.isAfter(out));
    }
}
