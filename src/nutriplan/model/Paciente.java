package nutriplan.model;

import nutriplan.dao.ExceptionDAO;
import nutriplan.dao.PacienteDAO;

import java.time.LocalDate;
import java.time.Period;

public class Paciente {
    private String nome;
    private String CPF;
    private String sexo;
    private LocalDate dataNascimento;
    private int idade;
    private double altura;
    private double peso;
    private String atividade;
    private String objetivo;

    private double IMC;
    private double TMB;
    private double GET;

    private Plano plano;

    //getters
    public String getNome() {return nome;}
    public String getCPF() {return CPF;}
    public String getSexo() {return sexo;}
    public LocalDate getDataNascimento() {return dataNascimento;}
    public int getIdade() {return idade;}
    public double getAltura() {return altura;}
    public double getPeso() {return peso;}
    public String getAtividade() {return atividade;}
    public String getObjetivo() {return objetivo;}
    public double getIMC() {return IMC;}
    public double getTMB() {return TMB;}
    public double getGET() {return GET;}
    public Plano getPlano() {return plano;}

    //setters
    public void setNome(String nome) {this.nome = nome;}
    public void setCPF(String CPF) {this.CPF = CPF;}
    public void setSexo(String sexo) {this.sexo = sexo;}
    public void setDataNascimento(LocalDate dataNascimento) {this.dataNascimento = dataNascimento;}
    public void setIdade(int idade) {this.idade = idade;}
    public void setAltura(double altura) {this.altura = altura;}
    public void setPeso(double peso) {this.peso = peso;}
    public void setAtividade(String atividade) {this.atividade = atividade;}
    public void setObjetivo(String objetivo) {this.objetivo = objetivo;}
    //imc,get e tmb é calculado

    public Paciente(String nome, String CPF, String sexo, LocalDate dataNascimento, double altura, double peso, String atividade, String objetivo) {

        this.nome = nome;
        this.CPF = CPF;
        this.sexo = sexo;
        this.dataNascimento = dataNascimento;
        this.peso = peso;
        this.altura = altura;
        this.atividade = atividade;
        this.objetivo = objetivo;

        this.idade = calcularIdade(dataNascimento);
        this.IMC = calcularIMC(altura, peso);
        this.TMB = calcularTMB(sexo,altura,peso,idade);
        this.GET = calcularGET(atividade,TMB);

    }

    public void mostrarDados() {

        System.out.println("Nome: " + nome);
        System.out.println("CPF: " + CPF);
        System.out.println("Sexo: " + sexo);
        System.out.println("Data de nascimento: " + dataNascimento);
        System.out.println("Idade: " + idade);
        System.out.println("Peso: " + peso + " kg");
        System.out.println("Altura: " + altura + " m");
        System.out.println("Objetivo: " + objetivo);
        System.out.println("IMC: " + IMC);
        System.out.println("TMB: " + TMB);
        System.out.println("GET: " + GET);
    }

    public static int calcularIdade(LocalDate dataNascimento) {
        LocalDate today = LocalDate.now();
        int idade = Period.between(dataNascimento, today).getYears();
        return idade;
    }

    public static double calcularIMC(double altura, double peso){
        if(altura == 0){
            System.out.println("A Altura de paciente não pode ser zero!");
        }
        return peso / (altura * altura);
    }

    public static double calcularTMB(String sexo, double altura, double peso, int idade){
        System.out.println("Calculando TMB com os valores:");
        System.out.println("Sexo: " + sexo);
        System.out.println("Altura: " + altura);
        System.out.println("Peso: " + peso);
        System.out.println("Idade: " + idade);

        if(sexo.equals("Masculino")){
            return (13.8*peso) + (5*altura*100) - (6.8*idade) + 66;
        }else if(sexo.equals("Feminino")){
            return (9.6*peso) + (1.9*altura*100) - (4.7*idade) + 655;
        } else {
            throw new IllegalStateException("Erro desconhecido ao calcular TMB");
        }
    }

    public static double calcularGET(String atividade, double TMB){

        if(atividade.equals("Sedentário")){
            return TMB*1.2;
        }else if(atividade.equals("Levemente ativo")){
            return TMB*1.375;
        }else if(atividade.equals("Moderamente ativo")){
            return TMB*1.55;
        }else if(atividade.equals("Muito ativo")){
            return TMB*1.725;
        } else {
        	throw new IllegalStateException("Erro desconhecido ao calcular GET");
        }
    }

    public void cadastrarPaciente(Paciente paciente) throws ExceptionDAO {
        new PacienteDAO().cadastrarPaciente(paciente);
    }

}

