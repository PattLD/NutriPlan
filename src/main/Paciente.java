package main;

public class Paciente {
    private String sexo;
    private String nome;
    private int idade;
    private double peso;
    private double altura;


    public Paciente(String sexo, String nome, int idade, double peso, double altura) {
        this.sexo = sexo;
        this.nome = nome;
        this.idade = idade;
        this.peso = peso;
        this.altura = altura;
    }

    // getters
    public String getSexo() {return sexo;}
    public String getNome() {return nome;}
    public int getIdade() {return idade;}
    public double getPeso() {return peso;}
    public double getAltura() {return altura;}

    // setters
    public void setSexo(String sexo) {this.sexo = sexo;}
    public void setNome(String nome) {this.nome = nome;}
    public void setIdade(int idade) {this.idade = idade;}
    public void setPeso(double peso) {this.peso = peso;}
    public void setAltura(double altura) {this.altura = altura;}

    public void mostrarDados() {
        System.out.println("Sexo: " + sexo);
        System.out.println("Nome: " + nome);
        System.out.println("Idade: " + idade);
        System.out.println("Peso: " + peso + " kg");
        System.out.println("Altura: " + altura + " m");
    }
}

