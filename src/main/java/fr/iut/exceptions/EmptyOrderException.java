package fr.iut.exceptions;

public class EmptyOrderException extends Exception {
    public EmptyOrderException() {
        super("Votre commande est vide");
    }
}
