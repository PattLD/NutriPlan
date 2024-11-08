package nutriplan.controller;

import java.time.LocalDate;
import nutriplan.dao.ExceptionDAO;
import nutriplan.model.Paciente;

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
}
