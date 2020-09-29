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
    private Button[] allButtons;
    private ArrayList<Button> emptyButtons;
    private Button clear, info, statistics;
    private HBox hBoxText, hBox1, hBox2, hBox3, hBox4;
    private VBox vBox;
    private Random random;
    private boolean isWinner, isUserWin, isComputerWin;
    private int userCounter, computerCounter, drawCounter;
    private String statisticsMessage, infoMessage;

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

        setHBoxMenuSettings(hBoxText);
        hBoxText.getChildren().add(message);

        addButtonsInToHBox(hBox1, allButtons[0], allButtons[1], allButtons[2]);
        addButtonsInToHBox(hBox2, allButtons[3], allButtons[4], allButtons[5]);
        addButtonsInToHBox(hBox3, allButtons[6], allButtons[7], allButtons[8]);
        addMenuButtonsInToHBox(hBox4, clear, info, statistics);

        vBox.setSpacing(10);
        vBox.getChildren().addAll(hBoxText, hBox1, hBox2, hBox3, hBox4);

        borderPane.setTop(vBox);

        Scene scene = new Scene(borderPane, 570, 700);
        primaryStage.setScene(scene);
        setPrimaryStageSettings(primaryStage);
    }

    private void setVariablesInitialValues() {
        isWinner = false;
        isUserWin = false;
        isComputerWin = false;
        userCounter = 0;
        computerCounter = 0;
        drawCounter = 0;
        random = new Random();
    }

    private void setButtonsInitialValues() {
        allButtons = new Button[9];
        for (int i = 0; i < allButtons.length; i++) {
            allButtons[i] = new Button();
        }

        emptyButtons = new ArrayList<>(Arrays.asList(allButtons));

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

    private void setHBoxMenuSettings(HBox hBox) {
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
                ResultRecorder.saveToFile(userCounter, computerCounter, drawCounter);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void setStatisticsMessages() {
        statisticsMessage = "Statystyki\n\n\n" +
                "    •  Użytkownik wygrał: " + userCounter + " raz(y).\n" +
                "    •  Komputer wygrał: " + computerCounter + " raz(y).\n" +
                "    •  Remis padł: " + drawCounter + " raz(y).\n";
    }

    private void setInfoMessages() {
        infoMessage = "Zasady i przebieg gry\n\n\n" +
                "    •  Kółko i krzyżyk to gra strategiczna, rozgrywana przez dwóch graczy. W naszej grze bierze udział gracz oraz komputer. \n" +
                "    •  Gracze obejmują pola na przemian, dążąc do objęcia trzech pól w jednej linii, przy jednoczesnym uniemożliwieniu tego samego przeciwnikowi.\n" +
                "    •  Pole może być objęte przez jednego gracza i nie zmienia swego właściciela przez cały przebieg gry. \n" +
                "    •  Planszą gry jest tablica gry, o wymiarach 3x3, na której gracz, za pomocą myszki, wstawia znak kółka w wybranym polu, po ruchu gracza następuje ruch komputera, komputer wstawia znak krzyżyka, w wybranym polu.\n" +
                "    •  Po zakończonej rozgrywce u góry planszy wyświetlane są statystyki, planszę można wyczyścić celem rozpoczęcia nowej gry, za pomocą klawisza ‘clean’.\n" +
                "    •  Zasady gry zostaną wyświetlone po kliknięciu myszką w klawisz ‘info’\n" +
                "    •  Statystyki gry zostaną wyświetlone po kliknięciu myszką w klawisz ‘winner’. Ponadto, statystyki gry zapisują się automatycznie po każdej rozgrywce do pliku ‘statistic.txt’.\n\n Miłej zabawy!!";
    }

    private void addButtonsInToHBox(HBox hBox, Button button1, Button button2, Button button3) {
        setHBoxSettings(hBox);
        setButtonSettings(button1);
        setButtonSettings(button2);
        setButtonSettings(button3);
        hBox.getChildren().addAll(button1, button2, button3);
    }

    private void addMenuButtonsInToHBox(HBox hBox, Button button1, Button button2, Button button3) {
        setHBoxMenuSettings(hBox);
        setButtonMenuSettings(button1);
        setButtonMenuSettings(button2);
        setButtonMenuSettings(button3);
        hBox.getChildren().addAll(button1, button2, button3);
    }

    private void checkIfTheSelectedFieldIsEmpty(Button button) {
        if (button.getText().equals("") && !isWinner) {
            setMove(button);
        }
    }

    @Override
    public void handle(ActionEvent event) {
        if (event.getSource() == clear) {
            clearBoard();
        } else if (event.getSource() == info) {
            setInfoMessages();
            MessageBox.display(infoMessage, "INFO");
        } else if (event.getSource() == statistics) {
            setStatisticsMessages();
            MessageBox.display(statisticsMessage, "STATISTICS");
        } else {
            for (Button button : allButtons) {
                if (event.getSource() == button) {
                    checkIfTheSelectedFieldIsEmpty(button);
                }
            }
        }
    }
    
    private void setMove (Button btn) {
        btn.setText("๐");
        emptyButtons.remove(btn);
        checkIfWon();

        if (!isWinner) {
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
        isWinner = true;
        if (sign.equals("๐")) {
            isUserWin = true;
        } else {
            isComputerWin = true;
        }
        setLabelMessage();
    }

    private void setButtonMessage(Button button) {
        button.setText("✔");
        button.setStyle("-fx-text-base-color: green; -fx-font-size: 50pt;");
    }

    private void setLabelMessage() {
        if (isUserWin) {
            userCounter++;
            message.setText("User win!");
        } else if (isComputerWin) {
            computerCounter++;
            message.setText("Computer win!");
        }
    }

    private void clearBoard () {
        for (Button button : allButtons) {
            message.setText("Welcome!");
            button.setText("");
            button.setStyle("-fx-text-base-color: black;");
            emptyButtons = new ArrayList<>(Arrays.asList(allButtons));
            isWinner = false;
            isUserWin = false;
            isComputerWin = false;
        }
    }
}
