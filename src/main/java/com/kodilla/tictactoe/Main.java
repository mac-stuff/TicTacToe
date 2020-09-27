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

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Main extends Application implements EventHandler<ActionEvent> {

    private Label message;
    private Button button1, button2, button3, button4, button5, button6, button7, button8, button9, clear, info, statistics;
    private HBox hBoxText, hBox1, hBox2, hBox3, hBox4;
    private VBox vBox;
    private Button[] allButtons;
    private ArrayList<Button> emptyButtons;
    private Random random;
    private boolean winner, user, computer;
    private int userWonCounter, computerWonCounter, drawCounter;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        setVariablesInitialValues();
        setButtonsInitialValues();
        setBoxesInitialValues();
        setLabelInitialValue();

        BorderPane borderPane = new BorderPane();

        sethBoxMenuSettings(hBoxText);
        hBoxText.getChildren().add(message);

        setHBoxSettings(hBox1);
        setButtonSettings(button1);
        setButtonSettings(button2);
        setButtonSettings(button3);
        hBox1.getChildren().addAll(button1, button2, button3);

        setHBoxSettings(hBox2);
        setButtonSettings(button4);
        setButtonSettings(button5);
        setButtonSettings(button6);
        hBox2.getChildren().addAll(button4, button5, button6);

        setHBoxSettings(hBox3);
        setButtonSettings(button7);
        setButtonSettings(button8);
        setButtonSettings(button9);
        hBox3.getChildren().addAll(button7, button8, button9);

        sethBoxMenuSettings(hBox4);
        setButtonMenuSettings(clear);
        setButtonMenuSettings(info);
        setButtonMenuSettings(statistics);
        hBox4.getChildren().addAll(clear, info, statistics);

        vBox.setSpacing(10);
        vBox.getChildren().addAll(hBoxText, hBox1, hBox2, hBox3, hBox4);

        borderPane.setTop(vBox);
        Scene scene = new Scene(borderPane, 570, 700);
        primaryStage.setScene(scene);
        setPrimaryStageSettings(primaryStage);

        allButtons = new Button[]{button1, button2, button3, button4, button5, button6, button7, button8, button9};
        emptyButtons = new ArrayList<>(Arrays.asList(button1, button2, button3, button4, button5, button6, button7, button8, button9));
    }

    private void setVariablesInitialValues() {
        winner = false;
        user = false;
        computer = false;
        userWonCounter = 0;
        computerWonCounter = 0;
        drawCounter = 0;
        random = new Random();
    }

    private void setButtonsInitialValues() {
        button1 = new Button();
        button2 = new Button();
        button3 = new Button();
        button4 = new Button();
        button5 = new Button();
        button6 = new Button();
        button7 = new Button();
        button8 = new Button();
        button9 = new Button();
        clear = new Button("clear");
        info = new Button("info");
        statistics = new Button("statistics");
    }

    private void setBoxesInitialValues() {
        vBox = new VBox();
        hBoxText = new HBox();
        hBox1 = new HBox();
        hBox2 = new HBox();
        hBox3 = new HBox();
        hBox4 = new HBox();
    }

    private void setLabelInitialValue() {
        message = new Label("Welcome!");
    }

    private void setHBoxSettings(HBox hBox) {
        hBox.setSpacing(10);
        hBox.setPadding(new Insets(0, 10, 0, 50));
        hBox.setStyle("-fx-font-size: 40pt;");
    }

    private void sethBoxMenuSettings(HBox hBox) {
        hBox.setSpacing(10);
        hBox.setPadding(new Insets(30, 10, 30, 50));
        hBox.setStyle("-fx-font-size: 15pt;");
    }

    private void setButtonSettings(Button button) {
        button.setPrefSize(150, 150);
        button.setOnAction(this);
    }

    private void setButtonMenuSettings(Button button) {
        button.setPrefSize(150, 50);
        button.setOnAction(this);
    }

    private void setPrimaryStageSettings(Stage primaryStage) {
        primaryStage.setTitle("TIC TAC TOE");
        primaryStage.setOpacity(0.98);
        primaryStage.resizableProperty().setValue(false);
        primaryStage.show();
        primaryStage.setOnCloseRequest(event -> {
            try {
                ResultRecorder.saveToFile(userWonCounter, computerWonCounter, drawCounter);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void handle(ActionEvent event){
        if (event.getSource() == clear) {
            clearBoard();
        } else if (event.getSource() == info) {
            InfoBox.display();
        } else if (event.getSource() == statistics) {
            StatisticsBox.display(userWonCounter, computerWonCounter, drawCounter);
        } else if (event.getSource() == button1) {
            if (button1.getText().equals("") && !winner) {
                setMove(button1);
            }
        } else if (event.getSource() == button2) {
            if (button2.getText().equals("") && !winner) {
                setMove(button2);
            }
        } else if (event.getSource() == button3) {
            if (button3.getText().equals("") && !winner) {
                setMove(button3);
            }
        } else if (event.getSource() == button4) {
            if (button4.getText().equals("") && !winner) {
                setMove(button4);
            }
        } else if (event.getSource() == button5) {
            if (button5.getText().equals("") && !winner) {
                setMove(button5);
            }
        } else if (event.getSource() == button6) {
            if (button6.getText().equals("") && !winner) {
                setMove(button6);
            }
        } else if (event.getSource() == button7) {
            if (button7.getText().equals("") && !winner) {
                setMove(button7);
            }
        } else if (event.getSource() == button8) {
            if (button8.getText().equals("") && !winner) {
                setMove(button8);
            }
        } else if (event.getSource() == button9) {
            if (button9.getText().equals("") && !winner) {
                setMove(button9);
            }
        }
    }
    
    private void setMove (Button btn) {
        btn.setText("๐");
        emptyButtons.remove(btn);
        checkIfWon();

        if (!winner) {
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
                            button.setText("✗");
                            emptyButtons.remove(button);
                        }
                    }
                    checkIfWon();
                } else {
                    message.setText("Draw!");
                    drawCounter++;
                }
            });
            new Thread(sleeper).start();
        }
    }

    private void checkIfWon () {
        checkIfButtonsHaveSameMark(0,1,2, "✗");
        checkIfButtonsHaveSameMark(3,4,5, "✗");
        checkIfButtonsHaveSameMark(6,7,8, "✗");
        checkIfButtonsHaveSameMark(0,3,6, "✗");
        checkIfButtonsHaveSameMark(1,4,7, "✗");
        checkIfButtonsHaveSameMark(2,5,8, "✗");
        checkIfButtonsHaveSameMark(2,4,6, "✗");
        checkIfButtonsHaveSameMark(0,4,8, "✗");

        checkIfButtonsHaveSameMark(0,1,2, "๐");
        checkIfButtonsHaveSameMark(3,4,5, "๐");
        checkIfButtonsHaveSameMark(6,7,8, "๐");
        checkIfButtonsHaveSameMark(0,3,6, "๐");
        checkIfButtonsHaveSameMark(1,4,7, "๐");
        checkIfButtonsHaveSameMark(2,5,8, "๐");
        checkIfButtonsHaveSameMark(2,4,6, "๐");
        checkIfButtonsHaveSameMark(0,4,8, "๐");
    }

    private void checkIfButtonsHaveSameMark(int firstButtonIndex, int secondButtonIndex, int thirdButtonIndex, String sign) {
        if (allButtons[firstButtonIndex].getText().equals(sign) && allButtons[secondButtonIndex].getText().equals(sign)
                && allButtons[thirdButtonIndex].getText().equals(sign)) {
            markIfWon(firstButtonIndex, secondButtonIndex, thirdButtonIndex, sign);
        }
    }

    private void markIfWon(int firstButtonIndex, int secondButtonIndex, int thirdButtonIndex, String sign) {
        setButtonMessage(allButtons[firstButtonIndex]);
        setButtonMessage(allButtons[secondButtonIndex]);
        setButtonMessage(allButtons[thirdButtonIndex]);
        winner = true;
        if (sign.equals("๐")) {
            user = true;
        } else {
            computer = true;
        }
        setLabelMessage();
    }

    private void setButtonMessage(Button button) {
        button.setText("✔");
        button.setStyle("-fx-text-base-color: green; -fx-font-size: 50pt;");
    }

    private void setLabelMessage() {
        if (user) {
            userWonCounter++;
            message.setText("User win!");
        } else if (computer) {
            computerWonCounter++;
            message.setText("Computer win!");
        }
    }

    private void clearBoard () {
        for (Button button : allButtons) {
            message.setText("Welcome!");
            button.setText("");
            button.setStyle("-fx-text-base-color: black;");
            emptyButtons = new ArrayList<>(Arrays.asList(button1, button2, button3, button4, button5, button6, button7, button8, button9));
            winner = false;
            user = false;
            computer = false;
        }
    }
}
