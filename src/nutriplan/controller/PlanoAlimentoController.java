package nutriplan.controller;

import nutriplan.dao.ExceptionDAO;
import nutriplan.model.Paciente;
import nutriplan.model.PlanoAlimento;

import java.time.LocalDate;

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
}
