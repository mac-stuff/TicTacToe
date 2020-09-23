package com.kodilla.tictactoe;

import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Main extends Application implements EventHandler<ActionEvent> {

    BorderPane borderPane;
    Label message;
    Button button1, button2, button3, button4, button5, button6, button7, button8, button9, clear, info, winner;
    HBox hBox, hBox1, hBox2, hBox3, hBox4;
    VBox vBox1;
    Button[] allButtons;
    ArrayList<Button> emptyButtons;
    Random random;
    boolean win, user, computer;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        win = false;
        user = false;
        computer = false;
        
        random = new Random();
        borderPane = new BorderPane();

        //message
        hBox = new HBox();
        hBox.setSpacing(10);
        hBox.setPadding(new Insets(50, 10, 0, 50));
        hBox.setStyle("-fx-font-size: 15pt;");

        message = new Label("Welcome!");

        hBox.getChildren().addAll(message);

        // first
        hBox1 = new HBox();
        hBox1.setSpacing(10);
        hBox1.setPadding(new Insets(50, 10, 0, 50));
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
        hBox2.setPadding(new Insets(0, 10, 0, 50));
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
        hBox3.setPadding(new Insets(0, 10, 0, 50));
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

        //all buttons arrays
        allButtons = new Button[]{button1, button2, button3, button4, button5, button6, button7, button8, button9};
        emptyButtons = new ArrayList<>(Arrays.asList(button1, button2, button3, button4, button5, button6, button7, button8, button9));

        //four
        hBox4 = new HBox();
        hBox4.setSpacing(10);
        hBox4.setPadding(new Insets(20, 10, 0, 50));
        hBox4.setStyle("-fx-font-size: 15pt;");

        clear = new Button("clear");
        clear.setPrefSize(150, 50);
        clear.setOnAction(this);

        info = new Button("info");
        info.setPrefSize(150, 50);
        info.setOnAction(this);

        winner = new Button("winner");
        winner.setPrefSize(150, 50);
        winner.setOnAction(this);

        hBox4.getChildren().addAll(clear, info, winner);

        //vbox
        vBox1 = new VBox();
        vBox1.setSpacing(10);
        vBox1.getChildren().addAll(hBox, hBox1, hBox2, hBox3, hBox4);
        borderPane.setTop(vBox1);

        Scene scene = new Scene(borderPane, 570, 700);

        primaryStage.setTitle("TIC TAC TOE");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void handle (ActionEvent event){
        if (event.getSource() == clear) {
            clearBoard();
        } else if (event.getSource() == info) {
            InfoBox.display();
        } else if (event.getSource() == winner) {
            StatisticsBox.display();
        } else if (event.getSource() == button1) {
            if (button1.getText().equals("") && !win) {
                setMove(button1);
            }
        } else if (event.getSource() == button2) {
            if (button2.getText().equals("") && !win) {
                setMove(button2);
            }
        } else if (event.getSource() == button3) {
            if (button3.getText().equals("") && !win) {
                setMove(button3);
            }
        } else if (event.getSource() == button4) {
            if (button4.getText().equals("") && !win) {
                setMove(button4);
            }
        } else if (event.getSource() == button5) {
            if (button5.getText().equals("") && !win) {
                setMove(button5);
            }
        } else if (event.getSource() == button6) {
            if (button6.getText().equals("") && !win) {
                setMove(button6);
            }
        } else if (event.getSource() == button7) {
            if (button7.getText().equals("") && !win) {
                setMove(button7);
            }
        } else if (event.getSource() == button8) {
            if (button8.getText().equals("") && !win) {
                setMove(button8);
            }
        } else if (event.getSource() == button9) {
            if (button9.getText().equals("") && !win) {
                setMove(button9);
            }
        }
    }

    private void setMove (Button btn){
        //player move
        btn.setText("0");
        emptyButtons.remove(btn);
        checkIfWon();

        //computer move
        if (!win) {
            Task<Void> sleeper = new Task<>() {
                @Override
                protected Void call() {
                    try {
                        for (Button button : allButtons) {
                            button.setDisable(true);
                        }

                        Thread.sleep(800);

                        for (Button button : allButtons) {
                            button.setDisable(false);
                        }
                    } catch (InterruptedException ignored) {
                    }
                    return null;
                }
            };
            sleeper.setOnSucceeded(event -> {
                if (emptyButtons.size() >= 1) {
                    Button randomMove = emptyButtons.get(random.nextInt(emptyButtons.size()));
                    for (Button button : allButtons) {
                        if (button == randomMove) {
                            button.setText("X");
                            emptyButtons.remove(button);
                        }
                    }
                    checkIfWon();
                } else {
                    message.setText("Draw!");
                }
            });
            new Thread(sleeper).start();
        }
    }

    private void checkIfWon () {
        if (allButtons[0].getText().equals("X") && allButtons[1].getText().equals("X") && allButtons[2].getText().equals("X")) {
            allButtons[0].setText("✔");
            allButtons[0].setStyle("-fx-text-base-color: green; -fx-font-size: 50pt;");
            allButtons[1].setText("✔");
            allButtons[1].setStyle("-fx-text-base-color: green; -fx-font-size: 50pt;");
            allButtons[2].setText("✔");
            allButtons[2].setStyle("-fx-text-base-color: green; -fx-font-size: 50pt;");
            win = true;
            computer = true;
            setLabelMessage();
        } else if (allButtons[3].getText().equals("X") && allButtons[4].getText().equals("X") && allButtons[5].getText().equals("X")) {
            allButtons[3].setText("✔");
            allButtons[3].setStyle("-fx-text-base-color: green; -fx-font-size: 50pt;");
            allButtons[4].setText("✔");
            allButtons[4].setStyle("-fx-text-base-color: green; -fx-font-size: 50pt;");
            allButtons[5].setText("✔");
            allButtons[5].setStyle("-fx-text-base-color: green; -fx-font-size: 50pt;");
            win = true;
            computer = true;
            setLabelMessage();
        } else if (allButtons[6].getText().equals("X") && allButtons[7].getText().equals("X") && allButtons[8].getText().equals("X")) {
            allButtons[6].setText("✔");
            allButtons[6].setStyle("-fx-text-base-color: green; -fx-font-size: 50pt;");
            allButtons[7].setText("✔");
            allButtons[7].setStyle("-fx-text-base-color: green; -fx-font-size: 50pt;");
            allButtons[8].setText("✔");
            allButtons[8].setStyle("-fx-text-base-color: green; -fx-font-size: 50pt;");
            win = true;
            computer = true;
            setLabelMessage();
        } else if (allButtons[0].getText().equals("X") && allButtons[3].getText().equals("X") && allButtons[6].getText().equals("X")) {
            allButtons[0].setText("✔");
            allButtons[0].setStyle("-fx-text-base-color: green; -fx-font-size: 50pt;");
            allButtons[3].setText("✔");
            allButtons[3].setStyle("-fx-text-base-color: green; -fx-font-size: 50pt;");
            allButtons[6].setText("✔");
            allButtons[6].setStyle("-fx-text-base-color: green; -fx-font-size: 50pt;");
            win = true;
            computer = true;
            setLabelMessage();
        } else if (allButtons[1].getText().equals("X") && allButtons[4].getText().equals("X") && allButtons[7].getText().equals("X")) {
            allButtons[1].setText("✔");
            allButtons[1].setStyle("-fx-text-base-color: green; -fx-font-size: 50pt;");
            allButtons[4].setText("✔");
            allButtons[4].setStyle("-fx-text-base-color: green; -fx-font-size: 50pt;");
            allButtons[7].setText("✔");
            allButtons[7].setStyle("-fx-text-base-color: green; -fx-font-size: 50pt;");
            win = true;
            computer = true;
            setLabelMessage();
        } else if (allButtons[2].getText().equals("X") && allButtons[5].getText().equals("X") && allButtons[8].getText().equals("X")) {
            allButtons[2].setText("✔");
            allButtons[2].setStyle("-fx-text-base-color: green; -fx-font-size: 50pt;");
            allButtons[5].setText("✔");
            allButtons[5].setStyle("-fx-text-base-color: green; -fx-font-size: 50pt;");
            allButtons[8].setText("✔");
            allButtons[8].setStyle("-fx-text-base-color: green; -fx-font-size: 50pt;");
            win = true;
            computer = true;
            setLabelMessage();
        } else if (allButtons[2].getText().equals("X") && allButtons[4].getText().equals("X") && allButtons[6].getText().equals("X")) {
            allButtons[2].setText("✔");
            allButtons[2].setStyle("-fx-text-base-color: green; -fx-font-size: 50pt;");
            allButtons[4].setText("✔");
            allButtons[4].setStyle("-fx-text-base-color: green; -fx-font-size: 50pt;");
            allButtons[6].setText("✔");
            allButtons[6].setStyle("-fx-text-base-color: green; -fx-font-size: 50pt;");
            win = true;
            computer = true;
            setLabelMessage();
        } else if (allButtons[0].getText().equals("X") && allButtons[4].getText().equals("X") && allButtons[8].getText().equals("X")) {
            allButtons[0].setText("✔");
            allButtons[0].setStyle("-fx-text-base-color: green; -fx-font-size: 50pt;");
            allButtons[4].setText("✔");
            allButtons[4].setStyle("-fx-text-base-color: green; -fx-font-size: 50pt;");
            allButtons[8].setText("✔");
            allButtons[8].setStyle("-fx-text-base-color: green; -fx-font-size: 50pt;");
            win = true;
            computer = true;
            setLabelMessage();
        } else if (allButtons[0].getText().equals("0") && allButtons[1].getText().equals("0") && allButtons[2].getText().equals("0")) {
            allButtons[0].setText("✔");
            allButtons[0].setStyle("-fx-text-base-color: green; -fx-font-size: 50pt;");
            allButtons[1].setText("✔");
            allButtons[1].setStyle("-fx-text-base-color: green; -fx-font-size: 50pt;");
            allButtons[2].setText("✔");
            allButtons[2].setStyle("-fx-text-base-color: green; -fx-font-size: 50pt;");
            win = true;
            user = true;
            setLabelMessage();
        } else if (allButtons[3].getText().equals("0") && allButtons[4].getText().equals("0") && allButtons[5].getText().equals("0")) {
            allButtons[3].setText("✔");
            allButtons[3].setStyle("-fx-text-base-color: green; -fx-font-size: 50pt;");
            allButtons[4].setText("✔");
            allButtons[4].setStyle("-fx-text-base-color: green; -fx-font-size: 50pt;");
            allButtons[5].setText("✔");
            allButtons[5].setStyle("-fx-text-base-color: green; -fx-font-size: 50pt;");
            win = true;
            user = true;
            setLabelMessage();
        } else if (allButtons[6].getText().equals("0") && allButtons[7].getText().equals("0") && allButtons[8].getText().equals("0")) {
            allButtons[6].setText("✔");
            allButtons[6].setStyle("-fx-text-base-color: green; -fx-font-size: 50pt;");
            allButtons[7].setText("✔");
            allButtons[7].setStyle("-fx-text-base-color: green; -fx-font-size: 50pt;");
            allButtons[8].setText("✔");
            allButtons[8].setStyle("-fx-text-base-color: green; -fx-font-size: 50pt;");
            win = true;
            user = true;
            setLabelMessage();
        } else if (allButtons[0].getText().equals("0") && allButtons[3].getText().equals("0") && allButtons[6].getText().equals("0")) {
            allButtons[0].setText("✔");
            allButtons[0].setStyle("-fx-text-base-color: green; -fx-font-size: 50pt;");
            allButtons[3].setText("✔");
            allButtons[3].setStyle("-fx-text-base-color: green; -fx-font-size: 50pt;");
            allButtons[6].setText("✔");
            allButtons[6].setStyle("-fx-text-base-color: green; -fx-font-size: 50pt;");
            win = true;
            user = true;
            setLabelMessage();
        } else if (allButtons[1].getText().equals("0") && allButtons[4].getText().equals("0") && allButtons[7].getText().equals("0")) {
            allButtons[1].setText("✔");
            allButtons[1].setStyle("-fx-text-base-color: green; -fx-font-size: 50pt;");
            allButtons[4].setText("✔");
            allButtons[4].setStyle("-fx-text-base-color: green; -fx-font-size: 50pt;");
            allButtons[7].setText("✔");
            allButtons[7].setStyle("-fx-text-base-color: green; -fx-font-size: 50pt;");
            win = true;
            user = true;
            setLabelMessage();
        } else if (allButtons[2].getText().equals("0") && allButtons[5].getText().equals("0") && allButtons[8].getText().equals("0")) {
            allButtons[2].setText("✔");
            allButtons[2].setStyle("-fx-text-base-color: green; -fx-font-size: 50pt;");
            allButtons[5].setText("✔");
            allButtons[5].setStyle("-fx-text-base-color: green; -fx-font-size: 50pt;");
            allButtons[8].setText("✔");
            allButtons[8].setStyle("-fx-text-base-color: green; -fx-font-size: 50pt;");
            win = true;
            user = true;
            setLabelMessage();
        } else if (allButtons[0].getText().equals("0") && allButtons[4].getText().equals("0") && allButtons[8].getText().equals("0")) {
            allButtons[0].setText("✔");
            allButtons[0].setStyle("-fx-text-base-color: green; -fx-font-size: 50pt;");
            allButtons[4].setText("✔");
            allButtons[4].setStyle("-fx-text-base-color: green; -fx-font-size: 50pt;");
            allButtons[8].setText("✔");
            allButtons[8].setStyle("-fx-text-base-color: green; -fx-font-size: 50pt;");
            win = true;
            user = true;
            setLabelMessage();
        } else if (allButtons[2].getText().equals("0") && allButtons[4].getText().equals("0") && allButtons[6].getText().equals("0")) {
            allButtons[2].setText("✔");
            allButtons[2].setStyle("-fx-text-base-color: green; -fx-font-size: 50pt;");
            allButtons[4].setText("✔");
            allButtons[4].setStyle("-fx-text-base-color: green; -fx-font-size: 50pt;");
            allButtons[6].setText("✔");
            allButtons[6].setStyle("-fx-text-base-color: green; -fx-font-size: 50pt;");
            win = true;
            user = true;
            setLabelMessage();
        }
    }

    private void setLabelMessage() {
        if (user) {
            message.setText("User Win!");
        } else if (computer) {
            message.setText("Computer Win!");
        }
    }

    private void clearBoard () {
        for (Button button : allButtons) {
            message.setText("Welcome!");
            button.setText("");
            button.setStyle("-fx-text-base-color: black;");
            emptyButtons = new ArrayList<>(Arrays.asList(button1, button2, button3, button4, button5, button6, button7, button8, button9));
            win = false;
            user = false;
            computer = false;
        }
    }
}
