import java.util.*;

public class LegacyRoomPricingPolicy implements RoomPricingPolicy {

    private final Map<Integer, Money> prices = new HashMap<>();

    public LegacyRoomPricingPolicy() {
        prices.put(LegacyRoomTypes.SINGLE, new Money(14000.0));
        prices.put(LegacyRoomTypes.DOUBLE, new Money(15000.0));
        prices.put(LegacyRoomTypes.TRIPLE, new Money(12000.0));
        prices.put(LegacyRoomTypes.DELUXE, new Money(16000.0));
    }

    public Money baseMonthly(int roomType) {
        return prices.getOrDefault(roomType, new Money(16000.0));
    }
}