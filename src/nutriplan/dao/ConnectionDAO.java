package nutriplan.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDAO {

    public Connection getConnection() {
        Connection con = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/nutricionista?useSSL=false", "root", "");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return con;
    }

//    public static void connectionBD() {
//        String URL = "jdbc:mysql://localhost:3306/nutricionista?useSSL=false";
//        String USER = "root";//usuario
//        String PASSWORD = "";//senha
//        Connection conn = null;
//        try{
//            // Estabelecendo a conexão
//            conn = DriverManager.getConnection(URL, USER, PASSWORD);
//            if (conn != null) {
//                System.out.println("Conexao estabelecida com sucesso!");
//            } else {
//                System.out.println("Falha ao conectar ao banco de dados.");
//            }
//        }catch (SQLException e) {
//            System.out.println("Deu Algum Problema");
//            e.printStackTrace();
//        } finally {
//            try {
//                // Fechar a conexão se ela foi aberta
//                if (conn != null) {
//                    conn.close();
//                }
//            } catch (SQLException ex) {
//                System.out.println("Deu Algum Problema para Fechar conexao");
//                ex.printStackTrace();
//            }
//        }
//    }
}
