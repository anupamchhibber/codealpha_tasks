
import java.util.Objects;

public class Room {
    int roomNumber;
    String category;
    double price;

    public Room(int roomNumber, String category, double price) {
        this.roomNumber = roomNumber;
        this.category = category;
        this.price = price;
    }

    public String toString() {
        return "Room " + roomNumber + " (" + category + ") - $" + price + "/night";
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Room)) return false;
        Room other = (Room) obj;
        return this.roomNumber == other.roomNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomNumber);
    }
}
