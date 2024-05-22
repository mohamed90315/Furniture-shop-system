package org.project.furniture_shop;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FurnitureMain extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // Loading favicon image
        Image icon = new Image(String.valueOf(getClass().getResource("/org/project/furniture_shop/images/favicon.png")));

        // Loading first fxml file
        FXMLLoader fxmlLoader = new FXMLLoader(FurnitureMain.class.getResource("cred.fxml"));
        // Creating scene with fxmlLoader
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);

        // Setting favicon for the stage
        stage.getIcons().add(icon);
        // Setting minimum Width and Height of window
        stage.setMinWidth(800);
        stage.setMinHeight(600);
        // Setting stage name
        stage.setTitle("Furniture Shop");
        // Setting first scene
        stage.setScene(scene);
        // Setting stage to show
        stage.show();

    }

    // Main function to run the program
    public static void main(String[] args) {
        launch();
    }

    public static class HomeController {

        @FXML
        private ListView<Item> itemListView;

        @FXML
        private Spinner<Integer> quantitySpinner;

        @FXML
        private DatabaseConnection.ShoppingCart shoppingCart;

        public void initialize() {
            shoppingCart = new DatabaseConnection.ShoppingCart();

            ObservableList<Item> items = FXCollections.observableArrayList(
                    new Item("Chair", 50.0),
                    new Item("Table", 150.0),
                    new Item("Desk", 120.0),
                    new Item("Cupboard", 200.0),
                    new Item("Bed", 300.0),
                    new Item("Door", 100.0)
            );

            itemListView.setItems(items);
            itemListView.getSelectionModel().selectFirst();

            SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 1);
            quantitySpinner.setValueFactory(valueFactory);
        }

        @FXML
        public void addToCart(ActionEvent event) {
            try {
                Item selectedItem = itemListView.getSelectionModel().getSelectedItem();
                int quantity = quantitySpinner.getValue();
                shoppingCart.addItem(selectedItem, quantity);

                StorageManager.saveCart(shoppingCart);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static class Item {
        private String name;
        private double price;

        public Item(String name, double price) {
            this.name = name;
            this.price = price;
        }

        // Getters and Setters with Encapsulation
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        @Override
        public String toString() {
            return name + " - $" + price;
        }
    }

    public static class StorageManager {
        private static final String FILE_NAME = "shopping_cart.txt";

        public static void saveCart(DatabaseConnection.ShoppingCart cart) throws IOException {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
                List<Item> items = cart.getItems();
                List<Integer> quantities = cart.getQuantities();
                for (int i = 0; i < items.size(); i++) {
                    writer.write(items.get(i).getName() + "," + items.get(i).getPrice() + "," + quantities.get(i));
                    writer.newLine();
                }
            }
        }
    }
}