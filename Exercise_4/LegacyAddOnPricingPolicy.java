import java.util.*;

public class LegacyAddOnPricingPolicy implements AddOnPricingPolicy {

    private final Map<AddOn, Money> prices = new HashMap<>();

    public LegacyAddOnPricingPolicy() {
        prices.put(AddOn.MESS, new Money(1000.0));
        prices.put(AddOn.LAUNDRY, new Money(500.0));
        prices.put(AddOn.GYM, new Money(300.0));
    }

    public Money monthlyCharge(AddOn a) {
        return prices.getOrDefault(a, new Money(0.0));
    }
}