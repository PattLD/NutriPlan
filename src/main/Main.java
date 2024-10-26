package main;

import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);

        System.out.println("----Nutricionista João Pedro Bindá----");
        System.out.println("----Cadastro do paciente----");
        System.out.println("Sexo: ");
        String sexo=ler.nextLine();
        System.out.println("objetivo: ");
        String objetivo=ler.nextLine();
        System.out.println("Nome: ");
        String nome=ler.nextLine();
        System.out.println("Idade: ");
        int idade=ler.nextInt();
        System.out.println("Peso (Kg): ");
        double peso=ler.nextDouble();
        System.out.println("Altura (M): ");
        double altura=ler.nextDouble();
        ler.nextLine();


        System.out.println("Informe com qual frequencia pratica atividades: ");
        System.out.println("1.Sedentário");
        System.out.println("2.Levemente ativo");
        System.out.println("3.Moderadamente ativo");
        System.out.println("4.Muito ativo");
        int atividade=ler.nextInt();


        Paciente paciente=new Paciente(sexo, nome, idade, peso, altura);
        Nutri nutri = new Nutri(nome, objetivo, atividade);

        System.out.println("Gasto calórico basal: "+nutri.calcularBasal(paciente));
        System.out.println("Gasto energético real: "+nutri.calcularFatorAtividade(paciente, atividade));
        System.out.println("IMC do paciente: "+ nutri.calcularIMC(paciente));
        ler.nextLine();
        System.out.print("Calorias diárias necessárias: ");
        int caloriasDiarias = ler.nextInt();

        // Criação do plano alimentar
        PlanoAlimentar plano = new PlanoAlimentar(caloriasDiarias);
        plano.montarDieta();

        System.out.println("Dieta finalizada!");
    }
}
