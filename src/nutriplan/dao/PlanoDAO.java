package nutriplan.dao;

import nutriplan.model.Plano;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PlanoDAO {

    public void cadastrarPlano(Plano plano) throws ExceptionDAO {
        String sql = "insert into planoalimentar (nome_alimento,quantidade,kcal_por_100g,kcal_alimento) values (?,?,?,?)";
        PreparedStatement pStatement = null;
        Connection connection = null;

        try {
            connection = new ConnectionDAO().getConnection();
            pStatement = connection.prepareStatement(sql);
            pStatement.setString(1, plano.getNomeComida());
            pStatement.setDouble(2, plano.getGramasComida());
            pStatement.setDouble(3, plano.getKcal100());
            pStatement.setDouble(4, plano.getKcalComida());

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
