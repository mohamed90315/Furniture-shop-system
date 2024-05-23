package org.project.furniture_shop;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;
public class paymentController {
    public static void switchWindow(ActionEvent event, String window) {
        try {
            // Load the new FXML file
            Parent root = FXMLLoader.load(Objects.requireNonNull(CredController.class.getResource(window)));
            // Create a new scene with the loaded FXML
            Scene scene = new Scene(root, 800, 600);
            // Get the stage from the event source
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            // Check if the stage is maximized
            boolean isMaximized = stage.isMaximized();
            // Set the new scene on the stage and show the new stage
            stage.setScene(scene);
            // Restore the maximized state if it was maximized before
            if (isMaximized) {
                stage.setWidth(javafx.stage.Screen.getPrimary().getVisualBounds().getWidth());
                stage.setHeight(javafx.stage.Screen.getPrimary().getVisualBounds().getHeight());
                stage.setMaximized(true);
            }
            // Show the new stage
            stage.show();
        } catch (IOException e) {
            PopUpWindow.showPopup("Error", "Error changing windows: " + e.getMessage());
        }

    }

    @FXML
    private void changeWindow(ActionEvent event) {
        // Getting the user data sent from the button
        Button clickedButton = (Button) event.getSource();
        String userData = String.valueOf(clickedButton.getUserData());
//        String buttonText = clickedButton.getText();

        switchWindow(event, userData);
    }

}
