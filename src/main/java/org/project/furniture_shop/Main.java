package org.project.furniture_shop;

import javafx.application.Application;  // Importing JavaFX Application class
import javafx.fxml.FXMLLoader;  // Importing FXMLLoader to load FXML files
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Overriding the start method

        Parent root = FXMLLoader.load(getClass().getResource("/org/project/furniture_shop/home.fxml"));

        // Creating a new scene with the root node
        Scene scene = new Scene(root, 800, 600);

        primaryStage.setTitle("Furniture Shop");

        // Setting the scene to the primary stage
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);  // Calling launch to start the JavaFX
    }
}
