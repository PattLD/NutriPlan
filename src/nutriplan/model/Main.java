package nutriplan.model;

import nutriplan.view.*;

import java.util.Scanner;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;

public class Main {
    public static void main(String[] args) {
        //String URL = "jdbc:postgresql://localhost:5432/listatelefonica";
        String URL = "jdbc:mysql://localhost:3306/nutricionista?useSSL=false";
        String USER = "root";//usuario
        String PASSWORD = "";//senha
        Connection conn = null;
        try{
            // Estabelecendo a conexão
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            if (conn != null) {
                System.out.println("Conexao estabelecida com sucesso!");
            } else {
                System.out.println("Falha ao conectar ao banco de dados.");
            }
        }catch (SQLException e) {
            System.out.println("Deu Algum Problema");
            e.printStackTrace();
        } finally {
            try {
                // Fechar a conexão se ela foi aberta
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println("Deu Algum Problema para Fechar conexao");
                ex.printStackTrace();
            }

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
}