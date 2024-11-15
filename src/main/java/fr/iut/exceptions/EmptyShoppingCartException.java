package fr.iut.exceptions;

public class EmptyShoppingCartException extends Exception {
    public EmptyShoppingCartException() {
        super("Votre panier est vide");
    }
}
