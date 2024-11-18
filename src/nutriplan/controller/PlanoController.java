package nutriplan.controller;

import nutriplan.dao.ExceptionDAO;
import nutriplan.model.Paciente;
import nutriplan.model.Plano;

import java.time.LocalDate;
import java.util.ArrayList;

public class PlanoController {

    public static boolean cadastrarPlano(int codPaciente, double kcal_necessaria, LocalDate data_criacao, String objetivo) throws ExceptionDAO {


        if(codPaciente >0 && objetivo != null && objetivo.length() >0 && data_criacao != null && kcal_necessaria >0){
            Plano plano = new Plano(codPaciente, kcal_necessaria, data_criacao, objetivo);
            plano.cadastrarPlano(plano);
            return true;
        } else{
            return false;
        }
    }

    public ArrayList<Plano> listarPlanos(String nome) throws ExceptionDAO{
        return new Plano().listarPlanos(nome);
    }

    public static boolean alterarPlano(int codPlano, int codPaciente, double kcal_necessaria, LocalDate data_criacao, String objetivo) throws ExceptionDAO {


        if(codPlano >0 && codPaciente >0 && kcal_necessaria >0 && data_criacao != null && objetivo != null && objetivo.length() >0){
            Plano plano = new Plano(codPaciente, kcal_necessaria, data_criacao, objetivo);
            plano.setCodPlano(codPlano);
            plano.alterarPlano(plano);
            return true;
        } else{
            return false;
        }

    }
}

