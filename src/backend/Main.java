package backend;

import interface_graf.Frame1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
    	
    	Frame1 frame1 = new Frame1();
    	
    	Scanner ler = new Scanner(System.in);

    	Paciente paciente = frame1.getPaciente();
        
        int caloriasDiarias = ler.nextInt();

        // Criação do plano alimentar
        PlanoAlimentar plano = new PlanoAlimentar(caloriasDiarias);
        plano.montarDieta();

        System.out.println("Dieta finalizada!");
    	
    }
}