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

    // Function to switch windows
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

    @FXML
    private void checkLogin(ActionEvent event) {
        // Get the text entered into the fields
        String username = login_username.getText();
        String password = login_password.getText();

        try {
            // Get a list with the data of the user with that username
            ArrayList<String> data = UsersDatabase.getUser(username);

            // If there is no data then the list is empty and no user was found
            if (data.isEmpty()) {
                PopUpWindow.showPopup("Warning", "Login Failed: User Not Found");
            } else {
                // If the user is found and the password matches then switch to main menu
                if (Objects.equals(password, data.get(4))) {
                    switchWindow(event, "main_menu.fxml");
                } else {
                    PopUpWindow.showPopup("Warning", "Login Failed: Wrong Password");
                }
            }
        } catch (Exception e) {
            PopUpWindow.showPopup("Error", "Error fetching user data: " + e.getMessage());
        }
    }

    @FXML
    private void checkRegister(ActionEvent event) {
        // Get all the data entered into the fields
        String name = register_name.getText();
        String username = register_username.getText();
        String email = register_email.getText();
        String password = register_password.getText();
        String c_password = register_c_password.getText();

        // Check if all the fields are filled and not empty
        if (name != null && !name.isEmpty() &&
                username != null && !username.isEmpty() &&
                email != null && !email.isEmpty() &&
                password != null && !password.isEmpty() &&
                c_password != null && !c_password.isEmpty()) {

            try {
                // Check if there is a user with the same username or email
                ArrayList<String> data = UsersDatabase.userExists(username, email);

                // if no user is found
                if (data.isEmpty()) {
                    // and the two passwords match
                    if (password.equals(c_password)) {
                        // Add the new user to the database
                        UsersDatabase.addUser(name, username, email, password);

                        // Switch to the login window
                        switchWindow(event, "cred_login.fxml");
                    } else {
                        PopUpWindow.showPopup("Warning", "Registration Failed: Passwords do not match");
                    }
                } else {
                    // If either the username or email found in the database then display warning
                    if (Objects.equals(username, data.get(2))) {
                        PopUpWindow.showPopup("Warning", "Registration Failed: Username Taken");
                    } else {
                        PopUpWindow.showPopup("Warning", "Registration Failed: Email Already Registered");
                    }
                }
            } catch (Exception e) {
                PopUpWindow.showPopup("Error", "Error fetching user data: " + e.getMessage());
            }
        } else {
            PopUpWindow.showPopup("Warning", "Please fill all fields");
        }
    }
}
