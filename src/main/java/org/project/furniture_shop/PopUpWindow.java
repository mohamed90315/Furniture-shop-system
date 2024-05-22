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
            Image icon = new Image(String.valueOf(PopUpWindow.class.getResource("/org/project/furniture_shop/images/favicon.png")));

            FXMLLoader loader = new FXMLLoader(PopUpWindow.class.getResource("popup.fxml"));
            Parent root = loader.load();
            PopUpController controller = loader.getController();
            controller.setMessage(message);

            Stage popupStage = new Stage();
            popupStage.getIcons().add(icon);
            popupStage.initModality(Modality.APPLICATION_MODAL);
            popupStage.setTitle(title);
            popupStage.setScene(new Scene(root, 250, 150));
            popupStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
