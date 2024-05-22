package org.project.furniture_shop;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private List<Purchasable> items;

    public ShoppingCart() {
        items = new ArrayList<>();
    }

    public void addItem(Purchasable item) {
        items.add(item);
    }

    public List<Purchasable> getItems() {
        return items;
    }

    public double calculateTotal() {
        double total = 0;
        for (Purchasable item : items) {
            total += item.getPrice();
        }
        return total;
    }
}
