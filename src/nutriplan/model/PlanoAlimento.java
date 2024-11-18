package nutriplan.model;

import nutriplan.dao.ExceptionDAO;
import nutriplan.dao.PlanoAlimentoDAO;

public class PlanoAlimento {

    private int codPlano;
    private int codAlimento;
    private double quantidade;

    private Plano plano;
    private Alimento alimento;

    //getters
    public int getCodPlano() {
        return codPlano;
    }
    public int getCodAlimento() {
        return codAlimento;
    }
    public double getQuantidade() {
        return quantidade;
    }
    public Plano getPlano() {
        return plano;
    }
    public Alimento getAlimento() {
        return alimento;
    }

    //setters
    public void setCodPlano(int codPlano) {
        this.codPlano = codPlano;
    }
    public void setCodAlimento(int codAlimento) {
        this.codAlimento = codAlimento;
    }
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    public void setPlano(Plano plano) {
        this.plano = plano;
    }
    public void setAlimento(Alimento alimento) {
        this.alimento = alimento;
    }

    //calculos
    public static double calcularKcalComida(double quantidade, double kcal100) {
        if (quantidade == 0 || kcal100 == 0) {
            throw new IllegalArgumentException("A quantidade e as kcal por 100g não podem ser zero.");
        }
        double kcal = (quantidade / 100) * kcal100;
        return kcal;
    }

    //integração
    public static void montarPlanoAlimento(PlanoAlimento planoAlimento) throws ExceptionDAO {
        new PlanoAlimentoDAO().montarPlanoAlimento(planoAlimento);
    }
}
