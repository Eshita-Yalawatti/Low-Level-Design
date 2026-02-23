public interface AddOnPricingPolicy {
    Money monthlyCharge(AddOn a);
}