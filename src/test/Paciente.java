package test;

public class Paciente {
    private String sexo;
    private String nome;
    private int idade;
    private double peso;
    private double altura;
    private String atividade;
    private String objetivo;


    public Paciente(String sexo, String nome, int idade, double peso, double altura, String atividade, String objetivo) {
        this.sexo = sexo;
        this.nome = nome;
        this.idade = idade;
        this.peso = peso;
        this.altura = altura;
        this.atividade=atividade;
        this.objetivo=objetivo;
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
        System.out.println("Objetivo: " + objetivo);
    }

    public double calcularIMC(){
        if(altura == 0){
            System.out.println("A Altura de paciente não pode ser zero!");
        }
        return peso / (altura * altura);
    }

    public double calcularBasal(){
    	if(sexo == "masculino"){
            return (13.8*peso) + (5*altura*100) - (6.8*idade) + 66;
        }else{
            return (9.6*peso) + (1.9*altura*100) - (4.7*idade) + 655;
        }
    }

    public double calcularFatorAtividade(){
        if(atividade.equals("Sedentário")){
            return calcularBasal()*1.2;
        }else if(atividade.equals("Levemente ativo")){
            return calcularBasal()*1.375;
        }else if(atividade.equals("Moderamente ativo")){
            return calcularBasal()*1.55;
        }else if(atividade.equals("Muito ativo")){
            return calcularBasal()*1.725;
        } else {
        	throw new IllegalStateException("Erro desconhecido ao calcular o fator de atividade."); // exceção caso não consiga calcular 
        }
    
    	}
}

