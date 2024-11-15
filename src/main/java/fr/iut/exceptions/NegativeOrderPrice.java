package fr.iut.exceptions;

public class NegativeOrderPrice extends Exception {
    public NegativeOrderPrice() {
        super("Le prix de votre panier est incohérent");
    }
}
