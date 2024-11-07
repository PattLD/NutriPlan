package nutriplan.model;

import nutriplan.dao.AlimentosDAO;
import nutriplan.dao.ExceptionDAO;

import java.util.ArrayList;

public class Alimento {
    private String nomeComida;
    private double kcal100;

    private ArrayList<Plano> planos = new ArrayList<Plano>();

    public Alimento(String nomeComida, double kcal100) {
        this.nomeComida = nomeComida;
        this.kcal100 = kcal100;
    }

    //GETTERS
    public String getNomeComida() {
        return nomeComida;
    }
    public double getKcal100() {
        return kcal100;
    }
    public ArrayList<Plano> getPlanos() {
        return planos;
    }

    //SETTERS
    public void setNomeComida(String nomeComida) {
        this.nomeComida = nomeComida;
    }
    public void setKcal100(double kcal100) {
        this.kcal100 = kcal100;
    }
    public void setPlanos(ArrayList<Plano> planos) { this.planos = planos; }

    //METODOS
    public void cadastrarAlimento(Alimento alimento) throws ExceptionDAO {
        new AlimentosDAO().cadastrarAlimento(alimento);
    }
}