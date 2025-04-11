
import java.util.HashMap;
import java.util.Map;

public class Portfolio {
    private Map<String, Integer> holdings = new HashMap<>();
    private double cash = 10000.0;

    public void buyStock(Stock stock, int quantity) {
        double cost = stock.getPrice() * quantity;
        if (cost <= cash) {
            holdings.put(stock.getSymbol(), holdings.getOrDefault(stock.getSymbol(), 0) + quantity);
            cash -= cost;
        }
    }

    public void sellStock(Stock stock, int quantity) {
        String symbol = stock.getSymbol();
        if (holdings.getOrDefault(symbol, 0) >= quantity) {
            holdings.put(symbol, holdings.get(symbol) - quantity);
            cash += stock.getPrice() * quantity;
        }
    }

    public double getCash() {
        return cash;
    }

    public Map<String, Integer> getHoldings() {
        return holdings;
    }
}
