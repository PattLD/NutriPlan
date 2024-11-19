package nutriplan.controller;

import nutriplan.dao.ExceptionDAO;
import nutriplan.model.Alimento;

import java.util.ArrayList;

public class AlimentoController {
    public static boolean cadastrarAlimento(String nomeComida, double kcal100) throws ExceptionDAO {


        if(nomeComida != null && nomeComida.length() >0 && kcal100 >0){
            Alimento alimento = new Alimento(nomeComida, kcal100);
            alimento.cadastrarAlimento(alimento);
            return true;
        } else{
            return false;
        }

    }

    public static boolean alterarAlimento(int codAlimento, String nomeComida, double kcal100) throws ExceptionDAO {


        if(codAlimento >0 && nomeComida != null && nomeComida.length() >0 && kcal100 >0){
            Alimento alimento = new Alimento(nomeComida, kcal100);
            alimento.setCodAlimento(codAlimento);
            alimento.alterarAlimento(alimento);
            return true;
        } else{
            return false;
        }

    }

    public ArrayList<Alimento> listarAlimentos(String nome) throws ExceptionDAO{
        return new Alimento().listarAlimentos(nome);
    }

    public boolean apagarAlimento(int codAlimento) throws ExceptionDAO{
        if(codAlimento == 0){
            return false;
        } else {
            Alimento alimento = new Alimento();
            alimento.setCodAlimento(codAlimento);
            alimento.apagarAlimento(alimento);
            return true;
        }
    }
}
