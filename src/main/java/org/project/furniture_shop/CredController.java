package org.project.furniture_shop;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

public class CredController {

    @FXML
    public TextField login_username;

    @FXML
    public PasswordField login_password;

    @FXML
    public TextField register_name;

    @FXML
    public TextField register_username;

    @FXML
    public TextField register_email;

    @FXML
    public PasswordField register_password;

    @FXML
    public PasswordField register_c_password;

    @FXML
    private void changeWindow(ActionEvent event) {
        try {
            Button clickedButton = (Button) event.getSource();
            // String buttonText = clickedButton.getText();
            String userData = String.valueOf(clickedButton.getUserData());

            // Load the new FXML file
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(userData)));
            // Create a new scene with the loaded FXML
            Scene scene = new Scene(root, 800, 600);
            // Get the stage from the event source
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            // Set the new scene on the stage
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            PopUpWindow.showPopup("Error", "Error changing windows: " + e.getMessage());
        }
    }

    @FXML
    private void checkLogin(ActionEvent event) {
        String username = login_username.getText();
        String password = login_password.getText();

        try {
            ArrayList<String> data = DatabaseConnection.getUser(username);

            assert data != null;
            if (data.isEmpty()) {
                PopUpWindow.showPopup("Warning", "User Not Found");
            }
            else {
                PopUpWindow.showPopup("Error", data.get(0) + " " + data.get(1) + " " + data.get(2) + " " + data.get(3) + " ");
            }
        } catch (Exception e) {
            PopUpWindow.showPopup("Error", "Error fetching user data: " + e.getMessage());
        }
    }
}
