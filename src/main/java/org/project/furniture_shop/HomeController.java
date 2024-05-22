package org.project.furniture_shop;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

// Controller class for handling UI interactions and business logic for the home screen
public class HomeController {

    // FXML annotations to link with the corresponding UI elements in the FXML file
    @FXML
    private Button addChairButton;

    @FXML
    private Button addTableButton;

    @FXML
    private Button addDeskButton;

    @FXML
    private Button addCupboardButton;

    @FXML
    private Button addBedButton;

    @FXML
    private Button addDoorButton;

    @FXML
    private ListView<String> cartListView;

    @FXML
    private Text totalText;

    // ShoppingCart instance to manage items
    private ShoppingCart cart;
    private ObservableList<String> cartItems;

    // Initialize method is called after FXML fields are injected
    public void initialize() {
        cart = new ShoppingCart();  // Initialize the shopping cart
        cartItems = FXCollections.observableArrayList();  // Observable list for cart items
        cartListView.setItems(cartItems);  // Set the observable list to the ListView

        // Set action listeners for add buttons, calling addToCart method with specific item
        addChairButton.setOnAction(event -> addToCart(new Item("Chair", 50.0, "/org/project/furniture_shop/images/chair.png")));
        addTableButton.setOnAction(event -> addToCart(new Item("Table", 150.0, "/org/project/furniture_shop/images/table.png")));
        addDeskButton.setOnAction(event -> addToCart(new Item("Desk", 120.0, "/org/project/furniture_shop/images/desk.png")));
        addCupboardButton.setOnAction(event -> addToCart(new Item("Cupboard", 200.0, "/org/project/furniture_shop/images/cupboard.png")));
        addBedButton.setOnAction(event -> addToCart(new Item("Bed", 300.0, "/org/project/furniture_shop/images/bed.png")));
        addDoorButton.setOnAction(event -> addToCart(new Item("Door", 100.0, "/org/project/furniture_shop/images/door.png")));
    }

    // Method to add an item to the cart and update the cart view
    private void addToCart(Purchasable item) {
        cart.addItem(item);  // Add the item to the cart
        updateCartView();  // Update the ListView and total text
    }

    // Method to update the ListView and total text based on the cart's content
    private void updateCartView() {
        cartItems.clear();  // Clear the existing items
        for (Purchasable item : cart.getItems()) {  // Add each item in the cart to the observable list
            cartItems.add(item.getName() + " - $" + item.getPrice());
        }
        totalText.setText("Total: $" + cart.calculateTotal());  // Update the total text
    }

    // Method to handle switching to the supplier screen
    @FXML
    void goToSupplierScreen(ActionEvent event) {
        try {
            // Load the supplier FXML file
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("supplier.fxml")));
            // Create a new scene with the loaded FXML
            Scene scene = new Scene(root);
            // Get the stage from the event source
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            // Set the new scene on the stage
            stage.setScene(scene);
            stage.show();  // Show the new scene
        } catch (IOException e) {
            e.printStackTrace();  // Print the stack trace if there is an error
        }
    }
}
