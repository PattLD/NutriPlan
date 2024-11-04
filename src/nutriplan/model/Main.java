package nutriplan.model;

import nutriplan.dao.ConnectionBD;
import nutriplan.view.*;

public class Main {
    public static void main(String[] args) {

        ConnectionBD.connectionBD();

        MainFrame mainFrame = new MainFrame();



/*

    	//FramePaciente frame1 = new FramePaciente();
        //FramePlano frame2 = new FramePlano();

    	
    	Scanner ler = new Scanner(System.in);

    	//Paciente paciente = frame1.getPaciente();
        
        int caloriasDiarias = ler.nextInt();

        // Criação do plano alimentar
        Plano plano = new Plano(caloriasDiarias);
        plano.montarDieta();

        System.out.println("Dieta finalizada!");
*/



    }
}