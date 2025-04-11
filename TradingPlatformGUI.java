
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class TradingPlatformGUI {
    private JFrame frame;
    private JComboBox<Stock> stockComboBox;
    private JTextField quantityField;
    private JTextArea portfolioArea;
    private JLabel cashLabel;

    private List<Stock> marketData = new ArrayList<>();
    private Portfolio portfolio = new Portfolio();

    public TradingPlatformGUI() {
        // Sample data
        marketData.add(new Stock("AAPL", "Apple Inc.", 175.5));
        marketData.add(new Stock("GOOGL", "Alphabet Inc.", 2900.0));
        marketData.add(new Stock("AMZN", "Amazon.com Inc.", 3300.0));

        frame = new JFrame("Stock Trading Platform");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        stockComboBox = new JComboBox<>(marketData.toArray(new Stock[0]));
        quantityField = new JTextField(5);
        JButton buyButton = new JButton("Buy");
        JButton sellButton = new JButton("Sell");
        topPanel.add(stockComboBox);
        topPanel.add(new JLabel("Qty:"));
        topPanel.add(quantityField);
        topPanel.add(buyButton);
        topPanel.add(sellButton);
        frame.add(topPanel, BorderLayout.NORTH);

        portfolioArea = new JTextArea();
        frame.add(new JScrollPane(portfolioArea), BorderLayout.CENTER);

        cashLabel = new JLabel("Cash: $" + portfolio.getCash());
        frame.add(cashLabel, BorderLayout.SOUTH);

        buyButton.addActionListener(e -> tradeStock(true));
        sellButton.addActionListener(e -> tradeStock(false));

        updatePortfolioDisplay();
        frame.setVisible(true);
    }

    private void tradeStock(boolean buy) {
        Stock stock = (Stock) stockComboBox.getSelectedItem();
        try {
            int qty = Integer.parseInt(quantityField.getText());
            if (buy) portfolio.buyStock(stock, qty);
            else portfolio.sellStock(stock, qty);
            updatePortfolioDisplay();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Invalid quantity.");
        }
    }

    private void updatePortfolioDisplay() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Integer> entry : portfolio.getHoldings().entrySet()) {
            sb.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }
        portfolioArea.setText(sb.toString());
        cashLabel.setText("Cash: $" + String.format("%.2f", portfolio.getCash()));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TradingPlatformGUI::new);
    }
}
