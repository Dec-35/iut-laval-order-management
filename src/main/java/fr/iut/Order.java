package fr.iut;

import fr.iut.exceptions.InvalidDiscountCodeException;

public class Order {
    private ShoppingCart shoppingCart;
    private double totalPrice;
    private double discount;
    private double deliveryFee;
    private User user;
    private static final double BASE_DELIVERY_FEE = 5.0;

    public Order(ShoppingCart shoppingCart, User user) {
        this.shoppingCart = shoppingCart;
        this.discount = 0.0;
        this.deliveryFee = BASE_DELIVERY_FEE * shoppingCart.getItemCount();
        user.addFidelityPoints(Constants.FIDELITY_POINTS_PER_TEN_EUROS * (int) shoppingCart.getTotalPrice() / 10);
        this.user = user;
        calculateTotal();
    }

    public void calculateTotal() {
        double subtotal = shoppingCart.getTotalPrice();
        totalPrice = subtotal + deliveryFee;
        if (discount > 0) {
            totalPrice = totalPrice * (1 - discount); //Readded delivery fee
        }
    }

    public void applyDiscount(String discountCode) throws InvalidDiscountCodeException {
        switch (discountCode) {
            case "PROMO10":
                this.discount = 0.10; // 15% -> 10% discount
                break;
            case "PROMO20":
                this.discount = 0.20;
                break;
            default:
                throw new InvalidDiscountCodeException("Code de réduction invalide: " + discountCode);
        }
        calculateTotal();
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public double getDeliveryFee() {
        return deliveryFee;
    }

    public double getDiscount() {
        return discount;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public User getUser() {
        return user;
    }
}