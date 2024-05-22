package org.project.furniture_shop;

public class Item extends Purchasable {
    private String imagePath;

    // Constructor to initialize the name, price, and image path of the item
    public Item(String name, double price, String imagePath) {
        super(name, price);  // Call the constructor of the superclass (Purchasable)
        this.imagePath = imagePath;
    }

    // Getter for the imagePath field
    public String getImagePath() {
        return imagePath;
    }

    // Setter for the imagePath field
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
