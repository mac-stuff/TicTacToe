package com.kodilla.tictactoe;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class AlertBox {

    public static void display(String title, String message) {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);

        Label label = new Label();
        label.setMaxSize(350, 350);
        label.setText(message);
        label.setWrapText(true);
        label.setTextAlignment(TextAlignment.JUSTIFY);

        VBox layout = new VBox(10);
        layout.getChildren().add(label);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 500, 500);
        window.setScene(scene);
        window.showAndWait();
    }
}
