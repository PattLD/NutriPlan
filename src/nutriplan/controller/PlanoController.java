package nutriplan.controller;

import nutriplan.model.Plano;

public class PlanoController {

    public static boolean cadastrarPlano(String nomeComida, double gramasComida, double kcal100, double kcalComida) {


        if(nomeComida != null && nomeComida.length() >0 && gramasComida >0 && kcal100 >0 && kcalComida >0){
            Plano plano = new Plano(nomeComida, gramasComida, kcal100, kcalComida);
            plano.cadastrarPlano(plano);
            return true;
        } else{
            return false;
        }

    }
}

