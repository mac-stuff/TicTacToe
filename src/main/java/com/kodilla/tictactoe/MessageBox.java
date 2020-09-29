package com.kodilla.tictactoe;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class MessageBox {

    public static void display(String message, String text) {

        Stage stage = new Stage();
        stage.setTitle(text);
        HBox hBox = new HBox();
        hBox.setSpacing(10);
        hBox.setPadding(new Insets(50, 50, 50, 50));
        hBox.setStyle("-fx-font-size: 12pt;");

        Label label = new Label(message);
        label.setWrapText(true);
        label.setTextAlignment(TextAlignment.JUSTIFY);

        hBox.getChildren().addAll(label);
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(hBox);

        Scene scene = new Scene(borderPane, 550, 650);
        stage.setScene(scene);
        stage.resizableProperty().setValue(false);
        stage.show();
    }
}