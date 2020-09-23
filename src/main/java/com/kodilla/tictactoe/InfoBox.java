package com.kodilla.tictactoe;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class InfoBox {

    public static void display() {

        String message = "Zasady i przebieg gry\n\n\n" +
        "    •  Kółko i krzyżyk to gra strategiczna, rozgrywana przez dwóch graczy. W naszej grze bierze udział gracz oraz komputer. \n" +
        "    •  Gracze obejmują pola na przemian, dążąc do objęcia trzech pól w jednej linii, przy jednoczesnym uniemożliwieniu tego samego przeciwnikowi.\n" +
        "    •  Pole może być objęte przez jednego gracza i nie zmienia swego właściciela przez cały przebieg gry. \n" +
        "    •  Planszą gry jest tablica gry, o wymiarach 3x3, na której gracz, za pomocą myszki, wstawia znak kółka w wybranym polu, po ruchu gracza następuje ruch komputera, komputer wstawia znak krzyżyka, w wybranym polu.\n" +
        "    •  Po zakończonej rozgrywce u góry planszy wyświetlane są statystyki, planszę można wyczyścić celem rozpoczęcia nowej gry, za pomocą klawisza ‘clean’.\n" +
        "    •  Zasady gry zostaną wyświetlone po kliknięciu myszką w klawisz ‘info’\n" +
        "    •  Statystyki gry zostaną wyświetlone po kliknięciu myszką w klawisz ‘winner’. Ponadto, statystyki gry zapisują się automatycznie po każdej rozgrywce do pliku ‘statistic.txt’.\n\n Miłej zabawy!!";

        Stage stage = new Stage();
        stage.setTitle("INFO");
        HBox hBox = new HBox();
        hBox.setSpacing(10);
        hBox.setPadding(new Insets(50, 50, 50, 50));
        hBox.setStyle("-fx-font-size: 12pt;");

        Label label = new Label(message);
        label.setWrapText(true);
        label.setTextAlignment(TextAlignment.CENTER);

        hBox.getChildren().addAll(label);
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(hBox);

        Scene scene = new Scene(borderPane, 550, 650);
        stage.setScene(scene);
        stage.resizableProperty().setValue(false);
        stage.show();
    }
}