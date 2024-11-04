package nutriplan.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionBD {
    public static void connectionBD() {
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
        }
    }
}
