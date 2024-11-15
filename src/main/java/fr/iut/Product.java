package fr.iut;

import fr.iut.exceptions.InvalidPriceException;
import fr.iut.exceptions.InvalidStockQuantityException;

public class Product {
    private String name;
    private double price;
    private int stockQuantity;


    public Product(String name, double price, int stockQuantity) {
        this.name = name;
        if (price < 0) {
            throw new InvalidPriceException("Le prix ne peut pas être négatif: " + price);
        }
        this.price = price;
        if (stockQuantity < 0) {
            throw new InvalidStockQuantityException("La quantité en stock ne peut pas être négative: " + stockQuantity);
        }
        this.stockQuantity = stockQuantity;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void decrementStock() {
        if (stockQuantity > 0) {
            stockQuantity--;
        }
    }

    public void incrementStock() {
        stockQuantity++;
    }
}