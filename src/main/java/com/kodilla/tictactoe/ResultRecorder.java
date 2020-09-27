package com.kodilla.tictactoe;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ResultRecorder {

    public static void saveToFile(int userWonCounter, int computerWonCounter, int drawCounter) throws IOException {
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = localDateTime.format(dateTimeFormatter);
        String message = "Statystyki z dnia: " + formattedDate + "\n" +
                "    •  Użytkownik wygrał: " + userWonCounter + " raz(y).\n" +
                "    •  Komputer wygrał: " + computerWonCounter + " raz(y).\n" +
                "    •  Remis padł: " + drawCounter + " raz(y).\n";

        String fileName = "statistics.txt";
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));
        writer.append('\n');
        writer.append(message);
        writer.close();
    }
}
