package org.project.furniture_shop;

import java.io.*;
import java.util.List;

// Class responsible for managing storage operations
public class StorageManager {
    private static final String FILE_NAME = "shopping_cart.txt";  // Name of the file to save the cart data

    // Static method to save the shopping cart contents to a file
    public static void saveCart(ShoppingCart cart) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            List<Purchasable> items = cart.getItems();  // Get the list of items from the shopping cart
            // Iterate through each item in the cart
            for (Purchasable item : items) {
                // Write the name and price of each item to the file
                writer.write(item.getName() + "," + item.getPrice());
                writer.newLine();
            }
        }
    }
}
