public class TaxCalculator {

    public double taxPercent(String customerType) {
        return TaxRules.taxPercent(customerType);
    }

    public double taxAmount(double subtotal, double pct) {
        return subtotal * (pct / 100.0);
    }
}