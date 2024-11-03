package nutriplan.controller;

import nutriplan.model.Paciente;

import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class PacienteController {

    public static boolean cadastrarPaciente(String nome, String CPF, String sexo, LocalDate dataNascimento, double altura, double peso, String atividade, String objetivo) {


        if(nome != null && nome.length() >0 && sexo != "SELECIONE" && sexo.length() >0 && dataNascimento != null && altura >0 && altura <=2 && peso >0 && atividade != "SELECIONE" && atividade.length() >0){
            Paciente paciente = new Paciente(nome,CPF,sexo,dataNascimento,altura,peso,atividade,objetivo);
            paciente.cadastrarPaciente(paciente);
            return true;
        } else{
            return false;
        }

    }

    public static Double converterDouble(JFormattedTextField txt){
        String doubleText = txt.getText().replace(",", "."); // Substitui a vírgula pelo ponto
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
