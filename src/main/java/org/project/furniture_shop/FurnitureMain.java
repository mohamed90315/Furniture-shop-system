package org.project.furniture_shop;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

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
}