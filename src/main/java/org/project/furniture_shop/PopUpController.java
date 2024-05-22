package org.project.furniture_shop;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class PopUpController {

    @FXML
    private Label messageLabel;

    public void setMessage(String message) {
        messageLabel.setText(message);
    }

    @FXML
    private void closePopup() {
        Stage stage = (Stage) messageLabel.getScene().getWindow();
        stage.close();
    }
}