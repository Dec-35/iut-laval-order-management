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

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getFidelityPoints() {
        return fidelityPoints;
    }

    public String toString() {
        return firstName + " " + lastName + " (" + fidelityPoints + " points de fidélité)";
    }

    public void setFidelityPoints(int points) {
        fidelityPoints = points;
    }
}
