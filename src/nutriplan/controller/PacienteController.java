package nutriplan.controller;

import nutriplan.dao.ExceptionDAO;
import nutriplan.model.Paciente;

import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class PacienteController {

    public static boolean cadastrarPaciente(String nome, String CPF, String sexo, LocalDate dataNascimento, double altura, double peso, String atividade, int idade, double IMC, double TMB,  double GET) throws ExceptionDAO {


        if(nome != null && nome.length() >0 && sexo != "SELECIONE" && sexo.length() >0 && dataNascimento != null && altura >0 && altura <=2 && peso >0 && atividade != "SELECIONE" && atividade.length() >0){
            Paciente paciente = new Paciente(nome,CPF,sexo,dataNascimento,altura,peso,atividade,idade,IMC,TMB,GET);
            paciente.cadastrarPaciente(paciente);
            return true;
        } else{
            return false;
        }

    }

    public ArrayList<Paciente> listarPacientes(String nome) throws ExceptionDAO{
        return new Paciente().listarPacientes(nome);
    }

    public static boolean alterarPaciente(int codPaciente, String nome, String CPF, String sexo, LocalDate dataNascimento, double altura, double peso, String atividade, int idade, double IMC, double TMB,  double GET) throws ExceptionDAO {


        if(codPaciente >0 && nome != null && nome.length() >0 && sexo != "SELECIONE" && sexo.length() >0 && dataNascimento != null && altura >0 && altura <=2 && peso >0 && atividade != "SELECIONE" && atividade.length() >0){
            Paciente paciente = new Paciente(nome,CPF,sexo,dataNascimento,altura,peso,atividade,idade,IMC,TMB,GET);
            paciente.setCodPaciente(codPaciente);
            paciente.alterarPaciente(paciente);
            return true;
        } else{
            return false;
        }

    }

    public boolean apagarPaciente(int codPaciente) throws ExceptionDAO{
        if(codPaciente == 0){
            return false;
        } else {
            Paciente paciente = new Paciente();
            paciente.setCodPaciente(codPaciente);
            paciente.apagarPaciente(paciente);
            return true;
        }
    }
}
