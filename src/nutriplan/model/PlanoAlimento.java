package nutriplan.model;

import nutriplan.dao.ExceptionDAO;
import nutriplan.dao.PlanoAlimentoDAO;

import java.util.ArrayList;

public class PlanoAlimento {

    private double gramasComida;
    private double kcalComida;

    private Plano plano;
    private Alimento alimento;

    public PlanoAlimento(int codPlano, int codComida, double gramasComida, double kcalComida) {
        this.gramasComida = gramasComida;
        this.kcalComida = kcalComida;
        Plano plano = new Plano();
        plano.setCodPlano(codPlano);
        this.plano = plano;
        Alimento alimento = new Alimento();
        alimento.setCodAlimento(codComida);
        this.alimento = alimento;

    }

    public PlanoAlimento() {

    }

    //getters
    public double getGramasComida() {
        return gramasComida;
    }
    public Plano getPlano() {
        return plano;
    }
    public Alimento getAlimento() {
        return alimento;
    }
    public double getKcalComida() {
        return kcalComida;
    }

    //setters
    public void setPlano(Plano plano) {
        this.plano = plano;
    }
    public void setAlimento(Alimento alimento) {
        this.alimento = alimento;
    }
    public void setGramasComida(double gramasComida) {
        this.gramasComida = gramasComida;
    }
    public void setKcalComida(double kcalComida) {
        this.kcalComida = kcalComida;
    }

    //calculos
    public static double calcularKcalComida(double quantidade, double kcal100) {
        if (quantidade == 0 || kcal100 == 0) {
            throw new IllegalArgumentException("A gramasComida e as kcal por 100g não podem ser zero.");
        }
        double kcal = (quantidade / 100) * kcal100;
        return kcal;
    }

    //integração
    public static void montarPlanoAlimento(PlanoAlimento planoAlimento) throws ExceptionDAO {
        new PlanoAlimentoDAO().montarPlanoAlimento(planoAlimento);
    }

    public ArrayList<PlanoAlimento> listaPlanoAlimentos(int codPlano) throws ExceptionDAO {
        return new PlanoAlimentoDAO().listarPlanoAlimento(codPlano);
    }

    public static void alterarPlanoAlimento(PlanoAlimento planoAlimento) throws ExceptionDAO {
        new PlanoAlimentoDAO().alterarPlanoAlimento(planoAlimento);
    }

    public static void apagarPlanoAlimento(PlanoAlimento planoAlimento) throws ExceptionDAO {
        new PlanoAlimentoDAO().apagarPlanoAlimento(planoAlimento);
    }

    public double somarCaloriasPlano(int codPlano) throws ExceptionDAO {
        return new PlanoAlimentoDAO().somarCaloriasPlano(codPlano);
    }

}
