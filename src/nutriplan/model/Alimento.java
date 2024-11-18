package nutriplan.model;

import nutriplan.dao.AlimentoDAO;
import nutriplan.dao.ExceptionDAO;
import nutriplan.dao.PacienteDAO;

import java.util.ArrayList;

public class Alimento {
    private int codAlimento;
    private String nomeComida;
    private double kcal100;

    private ArrayList<PlanoAlimento> planoAlimentos = new ArrayList<PlanoAlimento>();

    public Alimento(String nomeComida, double kcal100) {
        this.nomeComida = nomeComida;
        this.kcal100 = kcal100;
    }

    public Alimento() {

    }

    //GETTERS
    public int getCodAlimento() {
        return codAlimento;
    }
    public String getNomeComida() {
        return nomeComida;
    }
    public double getKcal100() {
        return kcal100;
    }
    public ArrayList<PlanoAlimento> getPlanoAlimentos() {
        return planoAlimentos;
    }

    //SETTERS
    public void setCodAlimento(int codAlimento) {
        this.codAlimento = codAlimento;
    }
    public void setNomeComida(String nomeComida) {
        this.nomeComida = nomeComida;
    }
    public void setKcal100(double kcal100) {
        this.kcal100 = kcal100;
    }
    public void setPlanoAlimentos(ArrayList<PlanoAlimento> planoAlimentos) {
        this.planoAlimentos = planoAlimentos;
    }

    //METODOS
    public void cadastrarAlimento(Alimento alimento) throws ExceptionDAO {
        new AlimentoDAO().cadastrarAlimento(alimento);
    }
    public ArrayList<Alimento> listarAlimentos(String nome) throws ExceptionDAO {
        return new AlimentoDAO().listarAlimentos(nome);
    }
}