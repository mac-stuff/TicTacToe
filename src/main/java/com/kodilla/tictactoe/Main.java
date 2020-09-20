package com.kodilla.tictactoe;

import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Main extends Application implements EventHandler<ActionEvent> {

    BorderPane borderPane;
    Button button1, button2, button3, button4, button5, button6, button7, button8, button9, clear, info;
    HBox hBox1, hBox2, hBox3, hBox4;
    VBox vBox1;
    Button[] board;
    ArrayList<Button> emptyButtons;
    Random random;
    boolean winner;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        winner = false;
        random = new Random();

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

        //all buttons array
        board = new Button[]{button1, button2, button3, button4, button5, button6, button7, button8, button9};
        emptyButtons = new ArrayList<>(Arrays.asList(button1, button2, button3, button4, button5, button6, button7, button8, button9));

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
            setMove(button1);
        } else if (event.getSource() == button2) {
            setMove(button2);
        } else if (event.getSource() == button3) {
            setMove(button3);
        } else if (event.getSource() == button4) {
            setMove(button4);
        } else if (event.getSource() == button5) {
            setMove(button5);
        } else if (event.getSource() == button6) {
            setMove(button6);
        } else if (event.getSource() == button7) {
            setMove(button7);
        } else if (event.getSource() == button8) {
            setMove(button8);
        } else if (event.getSource() == button9) {
            setMove(button9);
        }
    }

    private void setMove(Button btn) {

        if (!winner) {
            btn.setText("0");
        }
        emptyButtons.remove(btn);
        setWinnerRow();

        Task<Void> sleeper = new Task<>() {
            @Override
            protected Void call() {
                try {

                    for (Button button : board) {
                        button.setDisable(true);
                    }

                    Thread.sleep(1000);

                    for (Button button : board) {
                        button.setDisable(false);
                    }

                } catch (InterruptedException ignored) {
                }
                return null;
            }
        };
        sleeper.setOnSucceeded(event -> {

            Button computerMove = emptyButtons.get(random.nextInt(emptyButtons.size()));
            if (!winner) {
                computerMove.setText("X");
            }
            emptyButtons.remove(computerMove);
            setWinnerRow();
        });
        new Thread(sleeper).start();
    }

    private void setWinnerRow() {
        if (board[0].getText().equals("X") && board[1].getText().equals("X") && board[2].getText().equals("X")) {
            board[0].setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
            board[1].setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
            board[2].setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
            blockButtons();
        } else if (board[3].getText().equals("X") && board[4].getText().equals("X") && board[5].getText().equals("X")) {
            board[3].setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
            board[4].setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
            board[5].setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
            blockButtons();
        } else if (board[6].getText().equals("X") && board[7].getText().equals("X") && board[8].getText().equals("X")) {
            board[6].setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
            board[7].setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
            board[8].setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
            blockButtons();
        } else if (board[0].getText().equals("X") && board[3].getText().equals("X") && board[6].getText().equals("X")) {
            board[0].setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
            board[3].setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
            board[6].setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
            blockButtons();
        } else if (board[1].getText().equals("X") && board[4].getText().equals("X") && board[7].getText().equals("X")) {
            board[1].setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
            board[4].setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
            board[7].setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
            blockButtons();
        } else if (board[3].getText().equals("X") && board[4].getText().equals("X") && board[5].getText().equals("X")) {
            board[3].setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
            board[4].setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
            board[5].setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
            blockButtons();
        } else if (board[6].getText().equals("X") && board[7].getText().equals("X") && board[8].getText().equals("X")) {
            board[6].setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
            board[7].setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
            board[8].setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
            blockButtons();
        } else if (board[0].getText().equals("X") && board[4].getText().equals("X") && board[8].getText().equals("X")) {
            board[0].setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
            board[4].setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
            board[8].setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
            blockButtons();
        } else if (board[2].getText().equals("X") && board[4].getText().equals("X") && board[6].getText().equals("X")) {
            board[2].setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
            board[4].setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
            board[6].setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
            blockButtons();
        } else if (board[2].getText().equals("0") && board[5].getText().equals("0") && board[8].getText().equals("0")) {
            board[2].setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
            board[5].setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
            board[8].setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
            blockButtons();
        } else if (board[0].getText().equals("0") && board[1].getText().equals("0") && board[2].getText().equals("0")) {
            board[0].setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
            board[1].setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
            board[2].setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
            blockButtons();
        } else if (board[3].getText().equals("0") && board[4].getText().equals("0") && board[5].getText().equals("0")) {
            board[3].setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
            board[4].setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
            board[5].setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
            blockButtons();
        } else if (board[6].getText().equals("0") && board[7].getText().equals("0") && board[8].getText().equals("0")) {
            board[6].setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
            board[7].setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
            board[8].setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
            blockButtons();
        } else if (board[0].getText().equals("0") && board[3].getText().equals("0") && board[6].getText().equals("0")) {
            board[0].setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
            board[3].setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
            board[6].setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
            blockButtons();
        } else if (board[1].getText().equals("0") && board[4].getText().equals("0") && board[7].getText().equals("0")) {
            board[1].setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
            board[4].setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
            board[7].setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
            blockButtons();
        } else if (board[2].getText().equals("0") && board[5].getText().equals("0") && board[8].getText().equals("0")) {
            board[2].setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
            board[5].setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
            board[8].setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
            blockButtons();
        } else if (board[0].getText().equals("0") && board[4].getText().equals("0") && board[8].getText().equals("0")) {
            board[0].setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
            board[4].setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
            board[8].setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
            blockButtons();
        } else if (board[2].getText().equals("0") && board[4].getText().equals("0") && board[6].getText().equals("0")) {
            board[2].setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
            board[4].setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
            board[6].setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
            blockButtons();
        }
    }

    private void blockButtons() {
        for (Button button : board) {
            button.setDisable(true);
        }
        winner = true;
    }
}
