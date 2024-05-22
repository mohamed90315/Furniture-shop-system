package org.project.furniture_shop;

// Class representing an item that can be purchased
public class Purchasable {
    private String name;
    private double price;

    // Constructor to initialize the name and price of the item
    public Purchasable(String name, double price) {
        this.name = name;
        this.price = price;
    }

    // Getter for the name field
    public String getName() {
        return name;
    }

    // Setter for the name field
    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
