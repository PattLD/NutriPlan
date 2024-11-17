package nutriplan.model;

import nutriplan.dao.ExceptionDAO;
import nutriplan.dao.PlanoDAO;

import java.time.LocalDate;
import java.util.ArrayList;

public class Plano {

    private int kcalAdicionado;

    private int codPlano;
    private double kcalNecessarias;
    private LocalDate dataCriacao;
    private String objetivo;

    private Paciente paciente;
    private ArrayList<Alimento> alimentos = new ArrayList<Alimento>();

    public Plano(int codPaciente, double kcalNecessaria, LocalDate dataCriacao, String objetivo) {
        this.kcalNecessarias = kcalNecessaria;
        this.dataCriacao = dataCriacao;
        this.objetivo = objetivo;
        Paciente paciente = new Paciente();
        paciente.setCodPaciente(codPaciente);
        this.paciente = paciente;
    }

    //GETTERS
    public int getKcalAdicionado() {
        return kcalAdicionado;
    }
    public int getCodPlano() {
        return codPlano;
    }
    public double getKcalNecessarias() {
        return kcalNecessarias;
    }
    public LocalDate getDataCriacao() {
        return dataCriacao;
    }
    public String getObjetivo() {
        return objetivo;
    }
    public Paciente getPaciente() {
        return paciente;
    }
    public ArrayList<Alimento> getAlimentos() {
        return alimentos;
    }


    //SETTERS
    public void setKcalAdicionado(int kcalAdicionado) {
        this.kcalAdicionado = kcalAdicionado;
    }
    public void setCodPlano(int codPlano) {
        this.codPlano = codPlano;
    }
    public void setKcalNecessarias(double kcalNecessarias) {
        this.kcalNecessarias = kcalNecessarias;
    }
    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }
    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
    public void setAlimentos(ArrayList<Alimento> alimentos) {
        this.alimentos = alimentos;
    }

    //METODOS
    public static double calcularKcalComida(double gramasComida, double kcal100) {
        double kcal= (gramasComida/100) * kcal100;
        return kcal;
    }

    public void cadastrarPlano(Plano plano) throws ExceptionDAO {
        new PlanoDAO().cadastrarPlano(plano);
    }



//    public void montarDieta() {
//        while (kcalAdicionado < kcalNecessarias) {
//            Scanner ler=new Scanner(System.in);
//
//            System.out.println("\n---- Adicionar Alimento ----");
//            System.out.print("Nome do Alimento: ");
//            String alimento=ler.nextLine();
//
//            System.out.print("Quantidade (g): ");
//            int quantidade = ler.nextInt();
//
//            System.out.print("Calorias desse alimento: ");
//            int calorias = ler.nextInt();
//            ler.nextLine();
//
//            kcalAdicionado = kcalAdicionado + calorias;
//
//            System.out.println("Alimento adicionado: " + quantidade + "g de " + alimento + " (" + calorias + " cal)");
//            System.out.println("Calorias restantes: " + (kcalNecessarias - kcalAdicionado));
//
//            if (kcalAdicionado >= kcalNecessarias) {
//                System.out.println("As calorias diárias foram atingidas!");
//            }
//        }
//    }

}



