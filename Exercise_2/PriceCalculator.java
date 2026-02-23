import java.util.*;

public class PriceCalculator {

    public PricingResult compute(Map<String, MenuItem> menu, List<OrderLine> lines) {
        double subtotal = 0.0;
        List<PricedLine> pricedLines = new ArrayList<>();

        for (OrderLine l : lines) {
            MenuItem item = menu.get(l.itemId);
            double lineTotal = item.price * l.qty;
            subtotal += lineTotal;
            pricedLines.add(new PricedLine(item.name, l.qty, lineTotal));
        }

        return new PricingResult(subtotal, pricedLines);
    }

    public static class PricedLine {
        public final String name;
        public final int qty;
        public final double lineTotal;

        public PricedLine(String name, int qty, double lineTotal) {
            this.name = name;
            this.qty = qty;
            this.lineTotal = lineTotal;
        }
    }

    public static class PricingResult {
        public final double subtotal;
        public final List<PricedLine> lines;

        public PricingResult(double subtotal, List<PricedLine> lines) {
            this.subtotal = subtotal;
            this.lines = lines;
        }
    }
}