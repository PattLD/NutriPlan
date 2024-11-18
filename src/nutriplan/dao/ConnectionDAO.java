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
            if (con != null) {
                System.out.println("Conexao estabelecida com sucesso!");
            } else {
                System.out.println("Falha ao conectar ao banco de dados.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return con;
    }
}
