package org.project.furniture_shop;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

// Controller class for managing suppliers
public class SuppliersController {

    @FXML
    private ChoiceBox<Supplier> supplierChoiceBox;  // ChoiceBox for selecting suppliers

    @FXML
    private VBox item1;
    @FXML
    private VBox item2;
    @FXML
    private VBox item3;
    @FXML
    private VBox item4;
    @FXML
    private VBox item5;
    @FXML
    private VBox item6;

    // Method called when the controller is initialized
    public void initialize() {
        // Create supplier objects
        Supplier supplier1 = new Supplier("Supplier 1");
        Supplier supplier2 = new Supplier("Supplier 2");
        Supplier supplier3 = new Supplier("Supplier 3");

        // Create an observable list of suppliers
        ObservableList<Supplier> suppliers = FXCollections.observableArrayList(supplier1, supplier2, supplier3);

        // Populate the choice box with suppliers
        supplierChoiceBox.setItems(suppliers);
    }

    // Method called when "Add to Cart" button is clicked
    @FXML
    void addToCart() {
        // Logic to add selected item to cart
        VBox selectedItemBox = getSelectedItemBox();
        if (selectedItemBox != null) {
            String itemName = ((Label) selectedItemBox.getChildren().get(1)).getText();
            // Perform action such as adding the selected item to the shopping cart
            System.out.println("Added item to cart: " + itemName);
        }
    }

    // Method called when "Send Request" button is clicked
    @FXML
    void sendRequest() {
        //send request to supplier
        Supplier selectedSupplier = supplierChoiceBox.getValue();
        // Perform action such as sending a request to the selected supplier
        System.out.println("Sent request to supplier: " + selectedSupplier);
    }

    // Helper method to get the VBox of the selected item
    private VBox getSelectedItemBox() {
        // Logic to determine which item VBox is selected
        return null;
    }

    @FXML
    void goToHomeScreen(ActionEvent event) {
        try {
            // Load the supplier FXML file
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("main_menu.fxml")));
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
