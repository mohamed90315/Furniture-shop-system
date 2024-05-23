package org.project.furniture_shop;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class PopUpWindow {
    public static void showPopup(String title, String message) {
        try {
            // Load the favicon image from the resources folder
            Image icon = new Image(String.valueOf(PopUpWindow.class.getResource("/org/project/furniture_shop/images/favicon.png")));

            // Load the FXML file for the popup window
            FXMLLoader loader = new FXMLLoader(PopUpWindow.class.getResource("popup.fxml"));
            Parent root = loader.load();

            // Get the controller for the FXML and set the message
            PopUpController controller = loader.getController();
            controller.setMessage(message);

            // Create a new window (stage) for the popup
            Stage popupStage = new Stage();
            // Set the favicon for the popup window
            popupStage.getIcons().add(icon);
            // Block other windows until this one is closed
            popupStage.initModality(Modality.APPLICATION_MODAL);
            // Set the title of the popup window
            popupStage.setTitle(title);
            // Set the size and content of the popup
            popupStage.setScene(new Scene(root, 300, 200));

            // Show the popup window and wait until it is closed
            popupStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
