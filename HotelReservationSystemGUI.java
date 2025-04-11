
import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;

public class HotelReservationSystemGUI {
    private HotelManager manager;

    public HotelReservationSystemGUI() {
        manager = new HotelManager();
        createGUI();
    }

    private void createGUI() {
        JFrame frame = new JFrame("Hotel Reservation System");
        frame.setSize(600, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JTextArea output = new JTextArea();
        output.setEditable(false);
        JScrollPane scroll = new JScrollPane(output);
        frame.add(scroll, BorderLayout.CENTER);

        JPanel panel = new JPanel(new GridLayout(9, 2));
        JTextField nameField = new JTextField();
        JTextField checkInField = new JTextField("2025-04-15");
        JTextField checkOutField = new JTextField("2025-04-17");

        JComboBox<String> roomBox = new JComboBox<>();
        for (Room r : manager.getAllRooms()) roomBox.addItem(r.toString());

        JButton searchBtn = new JButton("Search Available Rooms");
        JButton bookBtn = new JButton("Book");
        JButton viewBtn = new JButton("View Bookings");
        JButton cancelBtn = new JButton("Cancel Booking");
        JButton modifyBtn = new JButton("Modify Booking");

        panel.add(new JLabel("Customer Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Check-In Date (YYYY-MM-DD):"));
        panel.add(checkInField);
        panel.add(new JLabel("Check-Out Date (YYYY-MM-DD):"));
        panel.add(checkOutField);
        panel.add(new JLabel("Select Room:"));
        panel.add(roomBox);
        panel.add(searchBtn);
        panel.add(bookBtn);
        panel.add(viewBtn);
        panel.add(cancelBtn);
        panel.add(modifyBtn);

        frame.add(panel, BorderLayout.NORTH);

        searchBtn.addActionListener(e -> {
            LocalDate in = LocalDate.parse(checkInField.getText());
            LocalDate out = LocalDate.parse(checkOutField.getText());
            List<Room> available = manager.getAvailableRooms(in, out);
            output.setText("Available Rooms:\n");
            for (Room r : available) {
                output.append(r.toString() + "\n");
            }
        });

        bookBtn.addActionListener(e -> {
            String name = nameField.getText();
            LocalDate in = LocalDate.parse(checkInField.getText());
            LocalDate out = LocalDate.parse(checkOutField.getText());
            Room selectedRoom = manager.getAllRooms().get(roomBox.getSelectedIndex());
            boolean success = manager.bookRoom(name, selectedRoom, in, out);
            output.setText(success ? "Booking successful!" : "Room is not available.");
        });

        viewBtn.addActionListener(e -> {
            output.setText("Bookings:\n");
            for (Booking b : manager.getBookings()) {
                output.append(b.toString() + "\n");
            }
        });

        cancelBtn.addActionListener(e -> {
            String name = nameField.getText();
            boolean success = manager.cancelBooking(name);
            output.setText(success ? "Booking canceled." : "No booking found.");
        });

        modifyBtn.addActionListener(e -> {
            String name = nameField.getText();
            LocalDate in = LocalDate.parse(checkInField.getText());
            LocalDate out = LocalDate.parse(checkOutField.getText());
            Room selectedRoom = manager.getAllRooms().get(roomBox.getSelectedIndex());
            boolean success = manager.modifyBooking(name, selectedRoom, in, out);
            output.setText(success ? "Booking modified." : "Modification failed.");
        });

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new HotelReservationSystemGUI();
    }
}
