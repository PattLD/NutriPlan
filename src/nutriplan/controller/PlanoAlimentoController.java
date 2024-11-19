package nutriplan.controller;

import nutriplan.dao.ExceptionDAO;
import nutriplan.model.Alimento;
import nutriplan.model.Plano;
import nutriplan.model.PlanoAlimento;

import java.util.ArrayList;

public class PlanoAlimentoController {
    public static boolean montarPlanoAlimento(int codComida, int codPlano, double kcalComida, double gramasComida) throws ExceptionDAO {


        if(codComida >0 && codPlano >0 && kcalComida >0 && gramasComida >0){
            PlanoAlimento planoAlimento = new PlanoAlimento(codPlano,codComida,gramasComida,kcalComida);
            planoAlimento.montarPlanoAlimento(planoAlimento);
            return true;
        } else{
            return false;
        }

    }

    public ArrayList<PlanoAlimento> listarPlanoAlimentos(int codPlano) throws ExceptionDAO{
        return new PlanoAlimento().listaPlanoAlimentos(codPlano);
    }

    public static boolean alterarPlanoAlimento(int codComida, int codPlano, double kcalComida, double gramasComida) throws ExceptionDAO {


        if(codComida >0 && codPlano >0 && kcalComida >0 && gramasComida >0){
            PlanoAlimento planoAlimento = new PlanoAlimento(codPlano,codComida,gramasComida,kcalComida);
            planoAlimento.alterarPlanoAlimento(planoAlimento);
            return true;
        } else{
            return false;
        }

    }

    public static boolean apagarPlanoAlimento(int codComida, int codPlano) throws ExceptionDAO {


        if(codComida >0 && codPlano >0){
            Plano plano = new Plano();
            plano.setCodPlano(codPlano);

            Alimento alimento = new Alimento();
            alimento.setCodAlimento(codComida);

            PlanoAlimento planoAlimento = new PlanoAlimento();
            planoAlimento.setAlimento(alimento);
            planoAlimento.setPlano(plano);
            planoAlimento.apagarPlanoAlimento(planoAlimento);
            return true;
        } else{
            return false;
        }

    }

    public double somarCaloriasPlano(int codPlano) throws ExceptionDAO{
        try {
            return new PlanoAlimento().somarCaloriasPlano(codPlano);
        } catch (ExceptionDAO e) {
            e.printStackTrace();
            return 0;
        }
    }
}
