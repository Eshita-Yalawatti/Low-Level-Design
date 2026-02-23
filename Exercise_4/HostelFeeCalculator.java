import java.util.*;

public class HostelFeeCalculator {
    private final FakeBookingRepo repo;
    private final RoomPricingPolicy roomPricing = new LegacyRoomPricingPolicy();
    private final AddOnPricingPolicy addOnPricing = new LegacyAddOnPricingPolicy();

    public HostelFeeCalculator(FakeBookingRepo repo) { this.repo = repo; }

    // OCP violation: switch + add-on branching + printing + persistence.
    public void process(BookingRequest req) {
        Money monthly = calculateMonthly(req);
        Money deposit = new Money(5000.00);

        ReceiptPrinter.print(req, monthly, deposit);

        String bookingId = "H-" + (7000 + new Random(1).nextInt(1000)); // deterministic-ish
        repo.save(bookingId, req, monthly, deposit);
    }

    private Money calculateMonthly(BookingRequest req) {
        Money base = roomPricing.baseMonthly(req.roomType);

        Money add = new Money(0.0);
        for (AddOn a : req.addOns) {
            add = add.plus(addOnPricing.monthlyCharge(a));
        }

        return base.plus(add);
    }
}