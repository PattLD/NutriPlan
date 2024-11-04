package nutriplan.controller;

import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Conversao {
    public static Double converterDouble(JFormattedTextField txt){
        String doubleText = txt.getText().replace(",", ".");
        try {
            return Double.parseDouble(doubleText);
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }
    public static LocalDate converterLocalDate(JFormattedTextField txt){
        String dateString = txt.getText();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = null;

        try{
            date = LocalDate.parse(dateString, formatter);
        } catch (DateTimeParseException ex) {
            return null;
        }

        return date;
    }
}
