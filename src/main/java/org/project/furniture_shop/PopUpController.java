package org.project.furniture_shop;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class PopUpController {

    @FXML
    private Label messageLabel;

    // Set the message of the popup
    public void setMessage(String message) {
        messageLabel.setText(message);
    }

    // Button function to close the popup
    @FXML
    private void closePopup() {
        Stage stage = (Stage) messageLabel.getScene().getWindow();
        stage.close();
    }
}