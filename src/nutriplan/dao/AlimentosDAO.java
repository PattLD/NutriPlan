package nutriplan.dao;

import nutriplan.model.Alimento;
import nutriplan.model.Plano;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AlimentosDAO {
    public void cadastrarAlimento(Alimento alimento) throws ExceptionDAO {
        String sql = "insert into alimento (nome_alimento,kcal100) values (?,?)";
        PreparedStatement pStatement = null;
        Connection connection = null;

        try {
            connection = new ConnectionDAO().getConnection();
            pStatement = connection.prepareStatement(sql);
            pStatement.setString(1, alimento.getNomeComida());
            pStatement.setDouble(2, alimento.getKcal100());

            pStatement.executeUpdate();
        } catch (SQLException e){
            throw new ExceptionDAO("Erro ao cadastrar o alimento: " + e.getMessage());
        } finally {

            try {
                if (pStatement != null) {pStatement.close();}
            } catch (SQLException e){
                throw new ExceptionDAO("Erro ao fechar Statement: " + e.getMessage());
            } try {
                if (connection != null) {connection.close();}
            } catch (SQLException e){
                throw new ExceptionDAO("Erro ao fechar o conexão: " + e.getMessage());
            }

        }

    }
}
