package nutriplan.model;

import nutriplan.dao.ExceptionDAO;
import nutriplan.dao.PacienteDAO;
import nutriplan.dao.PlanoDAO;

import java.time.LocalDate;
import java.util.ArrayList;

public class Plano {
    private int kcalNecessarias;
    private int kcalAdicionado;

    private int codPlano;
    private double kcal_necessaria;
    private LocalDate data_criacao;
    private String objetivo;
    private double gramasComida;
    private double kcalComida;

    private Paciente paciente;
    private ArrayList<Alimento> alimentos = new ArrayList<Alimento>();

    public Plano(int codPaciente, double kcal_necessaria, LocalDate data_criacao, String objetivo) {
        this.kcal_necessaria = kcal_necessaria;
        this.data_criacao = data_criacao;
        this.objetivo = objetivo;
        Paciente paciente = new Paciente();
        paciente.setCodPaciente(codPaciente);
        this.paciente = paciente;
    }

    //GETTERS

    public int getCodPlano() {
        return codPlano;
    }
    public double getKcal_necessaria() {
        return kcal_necessaria;
    }
    public LocalDate getData_criacao() {
        return data_criacao;
    }
    public String getObjetivo() {
        return objetivo;
    }
    public ArrayList<Alimento> getAlimentos() {
        return alimentos;
    }
    public int getKcalNecessarias() {
        return kcalNecessarias;
    }
    public int getKcalAdicionado() {
        return kcalAdicionado;
    }
    public double getGramasComida() {
        return gramasComida;
    }
    public double getKcalComida() {
        return kcalComida;
    }
    public Paciente getPaciente() {
        return paciente;
    }

    //SETTERS

    public void setCodPlano(int codPlano) {
        this.codPlano = codPlano;
    }
    public void setKcal_necessaria(double kcal_necessaria) {
        this.kcal_necessaria = kcal_necessaria;
    }
    public void setData_criacao(LocalDate data_criacao) {
        this.data_criacao = data_criacao;
    }
    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }
    public void setAlimentos(ArrayList<Alimento> alimentos) {
        this.alimentos = alimentos;
    }
    public void setKcalNecessarias(int kcalNecessarias) {
        this.kcalNecessarias = kcalNecessarias;
    }
    public void setKcalAdicionado(int kcalAdicionado) {
        this.kcalAdicionado = kcalAdicionado;
    }
    public void setGramasComida(double gramasComida) {
        this.gramasComida = gramasComida;
    }
    public void setKcalComida(double kcalComida) {
        this.kcalComida = kcalComida;
    }
    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
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



