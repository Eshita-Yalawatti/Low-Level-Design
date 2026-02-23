import java.util.*;

public class CafeteriaSystem {
    private final Map<String, MenuItem> menu = new LinkedHashMap<>();
    private final InvoiceStore store;
    private int invoiceSeq = 1000;

    private final PriceCalculator priceCalc = new PriceCalculator();
    private final TaxCalculator taxCalc = new TaxCalculator();
    private final DiscountCalculator discountCalc = new DiscountCalculator();
    private final InvoiceFormatter formatter = new InvoiceFormatter();

    public CafeteriaSystem(InvoiceStore store) {
        this.store = store;
    }

    public void addToMenu(MenuItem i) { menu.put(i.id, i); }

    public void checkout(String customerType, List<OrderLine> lines) {
        String invId = "INV-" + (++invoiceSeq);

        PriceCalculator.PricingResult pricing =
                priceCalc.compute(menu, lines);

        double subtotal = pricing.subtotal;

        double taxPct = taxCalc.taxPercent(customerType);
        double tax = taxCalc.taxAmount(subtotal, taxPct);

        double discount =
                discountCalc.discountAmount(customerType, subtotal, lines.size());

        double total = subtotal + tax - discount;

        String printable = formatter.format(
                invId,
                pricing.lines,
                subtotal,
                taxPct,
                tax,
                discount,
                total
        );

        System.out.print(printable);

        store.save(invId, printable);
        System.out.println("Saved invoice: " + invId +
                " (lines=" + store.countLines(invId) + ")");
    }
}
