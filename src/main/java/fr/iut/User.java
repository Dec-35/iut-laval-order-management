package fr.iut;

public class User {
    private String firstName;
    private String lastName;
    private int fidelityPoints;

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.fidelityPoints = 0;
    }

    public void addFidelityPoints(int points) {
        fidelityPoints += points;
    }

    public String toString() {
        return firstName + " " + lastName + " (" + fidelityPoints + " points de fidélité)";
    }
}
