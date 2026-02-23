public interface RoomPricingPolicy {
    Money baseMonthly(int roomType);
}