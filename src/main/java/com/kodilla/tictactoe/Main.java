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
    Button button1, button2, button3, button4, button5, button6, button7, button8, button9, clear, info, win;
    HBox hBox1, hBox2, hBox3, hBox4;
    VBox vBox1;
    Button[] allButtons;
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
        allButtons = new Button[]{button1, button2, button3, button4, button5, button6, button7, button8, button9};
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

        win = new Button("winner");
        win.setPrefSize(150, 50);
        win.setOnAction(this);

        hBox4.getChildren().addAll(clear, info, win);

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
                    for (Button button : allButtons) {
                        button.setDisable(true);
                    }
                    Thread.sleep(1000);
                    for (Button button : allButtons) {
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
        if (allButtons[0].getText().equals("X") && allButtons[1].getText().equals("X") && allButtons[2].getText().equals("X")) {
            allButtons[0].setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
            allButtons[1].setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
            allButtons[2].setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
            blockButtons();
        } else if (allButtons[3].getText().equals("X") && allButtons[4].getText().equals("X") && allButtons[5].getText().equals("X")) {
            allButtons[3].setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
            allButtons[4].setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
            allButtons[5].setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
            blockButtons();
        } else if (allButtons[6].getText().equals("X") && allButtons[7].getText().equals("X") && allButtons[8].getText().equals("X")) {
            allButtons[6].setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
            allButtons[7].setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
            allButtons[8].setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
            blockButtons();
        } else if (allButtons[0].getText().equals("X") && allButtons[3].getText().equals("X") && allButtons[6].getText().equals("X")) {
            allButtons[0].setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
            allButtons[3].setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
            allButtons[6].setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
            blockButtons();
        } else if (allButtons[1].getText().equals("X") && allButtons[4].getText().equals("X") && allButtons[7].getText().equals("X")) {
            allButtons[1].setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
            allButtons[4].setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
            allButtons[7].setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
            blockButtons();
        } else if (allButtons[3].getText().equals("X") && allButtons[4].getText().equals("X") && allButtons[5].getText().equals("X")) {
            allButtons[3].setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
            allButtons[4].setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
            allButtons[5].setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
            blockButtons();
        } else if (allButtons[6].getText().equals("X") && allButtons[7].getText().equals("X") && allButtons[8].getText().equals("X")) {
            allButtons[6].setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
            allButtons[7].setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
            allButtons[8].setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
            blockButtons();
        } else if (allButtons[0].getText().equals("X") && allButtons[4].getText().equals("X") && allButtons[8].getText().equals("X")) {
            allButtons[0].setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
            allButtons[4].setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
            allButtons[8].setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
            blockButtons();
        } else if (allButtons[2].getText().equals("X") && allButtons[4].getText().equals("X") && allButtons[6].getText().equals("X")) {
            allButtons[2].setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
            allButtons[4].setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
            allButtons[6].setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
            blockButtons();
        } else if (allButtons[2].getText().equals("0") && allButtons[5].getText().equals("0") && allButtons[8].getText().equals("0")) {
            allButtons[2].setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
            allButtons[5].setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
            allButtons[8].setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
            blockButtons();
        } else if (allButtons[0].getText().equals("0") && allButtons[1].getText().equals("0") && allButtons[2].getText().equals("0")) {
            allButtons[0].setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
            allButtons[1].setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
            allButtons[2].setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
            blockButtons();
        } else if (allButtons[3].getText().equals("0") && allButtons[4].getText().equals("0") && allButtons[5].getText().equals("0")) {
            allButtons[3].setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
            allButtons[4].setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
            allButtons[5].setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
            blockButtons();
        } else if (allButtons[6].getText().equals("0") && allButtons[7].getText().equals("0") && allButtons[8].getText().equals("0")) {
            allButtons[6].setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
            allButtons[7].setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
            allButtons[8].setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
            blockButtons();
        } else if (allButtons[0].getText().equals("0") && allButtons[3].getText().equals("0") && allButtons[6].getText().equals("0")) {
            allButtons[0].setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
            allButtons[3].setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
            allButtons[6].setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
            blockButtons();
        } else if (allButtons[1].getText().equals("0") && allButtons[4].getText().equals("0") && allButtons[7].getText().equals("0")) {
            allButtons[1].setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
            allButtons[4].setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
            allButtons[7].setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
            blockButtons();
        } else if (allButtons[2].getText().equals("0") && allButtons[5].getText().equals("0") && allButtons[8].getText().equals("0")) {
            allButtons[2].setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
            allButtons[5].setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
            allButtons[8].setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
            blockButtons();
        } else if (allButtons[0].getText().equals("0") && allButtons[4].getText().equals("0") && allButtons[8].getText().equals("0")) {
            allButtons[0].setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
            allButtons[4].setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
            allButtons[8].setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
            blockButtons();
        } else if (allButtons[2].getText().equals("0") && allButtons[4].getText().equals("0") && allButtons[6].getText().equals("0")) {
            allButtons[2].setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
            allButtons[4].setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
            allButtons[6].setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
            blockButtons();
        }
    }

    private void blockButtons() {
        String title = "INFO";
        String message = "The button's action, which is invoked whenever the button is fired. This may be due to the user clicking on the button with the mouse, or by a touch event, or by a key press, or if the developer programmatically invokes the fire() method.";
        for (Button button : allButtons) {
            button.setDisable(true);
        }
        win.fire();
        win.setOnAction(e ->
                AlertBox.display(title, message));
        winner = true;
    }
}
