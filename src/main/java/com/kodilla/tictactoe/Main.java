package com.kodilla.tictactoe;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.Stage;


public class Main extends Application implements EventHandler<ActionEvent> {

    Button button1, button2, button3, button4, button5, button6, button7, button8, button9, clear, info;
    BorderPane borderPane;
    HBox hBox1, hBox2, hBox3, hBox4;
    VBox vBox1;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        borderPane = new BorderPane();

        // first
        hBox1 = new HBox();
        hBox1.setSpacing(10);
        hBox1.setPadding(new Insets(50, 10,0, 50));
        hBox1.setStyle("-fx-font-size: 20pt;");
        
        button1 = new Button();
        button1.setPrefSize(150, 150);
        button1.setOnAction(this);

        button2 = new Button();
        button2.setPrefSize(150, 150);
        button2.setOnAction(this);

        button3 = new Button();
        button3.setPrefSize(150, 150);
        button3.setOnAction(this);
        
        hBox1.getChildren().addAll(button1, button2, button3);

        //second
        hBox2 = new HBox();
        hBox2.setSpacing(10);
        hBox2.setPadding(new Insets(0, 10,0, 50));
        hBox2.setStyle("-fx-font-size: 20pt;");
        
        button4 = new Button();
        button4.setPrefSize(150, 150);
        button4.setOnAction(this);

        button5 = new Button();
        button5.setPrefSize(150, 150);
        button5.setOnAction(this);

        button6 = new Button();
        button6.setPrefSize(150, 150);
        button6.setOnAction(this);
        
        hBox2.getChildren().addAll(button4, button5, button6);

        //third 
        hBox3 = new HBox();
        hBox3.setSpacing(10);
        hBox3.setPadding(new Insets(0, 10,0, 50));
        hBox3.setStyle("-fx-font-size: 20pt;");
        
        button7 = new Button();
        button7.setPrefSize(150, 150);
        button7.setOnAction(this);

        button8 = new Button();
        button8.setPrefSize(150, 150);
        button8.setOnAction(this);

        button9 = new Button();
        button9.setPrefSize(150, 150);
        button9.setOnAction(this);
        
        hBox3.getChildren().addAll(button7, button8, button9);
        
        //four
        hBox4 = new HBox();
        hBox4.setSpacing(10);
        hBox4.setPadding(new Insets(20, 10,0, 50));
        hBox4.setStyle("-fx-font-size: 20pt;");
        
        clear = new Button("clear");
        clear.setPrefSize(150, 50);
        clear.setOnAction(this);

        info = new Button("info");
        info.setPrefSize(150, 50);
        info.setOnAction(this);
        
        hBox4.getChildren().addAll(clear, info);

        //vbox
        vBox1 = new VBox();
        vBox1.setSpacing(10);
        vBox1.getChildren().addAll(hBox1, hBox2, hBox3, hBox4);
        borderPane.setTop(vBox1);

        Scene scene = new Scene(borderPane, 700, 700);

        primaryStage.setTitle("TIC TAC TOE");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void handle(ActionEvent event) {
        if (event.getSource() == clear) {
            clear.setText("change");
        } else if (event.getSource() == info) {
            info.setText("open");
        } else if (event.getSource() == button1) {
            button1.setText("o");
        } else if (event.getSource() == button2) {
            button2.setText("o");
        } else if (event.getSource() == button3) {
            button3.setText("o");
        } else if (event.getSource() == button4) {
            button4.setText("o");
        } else if (event.getSource() == button5) {
            button5.setText("o");
        } else if (event.getSource() == button6) {
            button6.setText("o");
        } else if (event.getSource() == button7) {
            button7.setText("o");
        } else if (event.getSource() == button8) {
            button8.setText("o");
        } else if (event.getSource() == button9) {
            button9.setText("o");
        }
    }
}
