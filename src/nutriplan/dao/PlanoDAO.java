package nutriplan.dao;

import nutriplan.model.Plano;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PlanoDAO {

    public void cadastrarPlano(Plano plano) throws ExceptionDAO {
        String sql = "insert into plano (id_pessoa, kcal_necessaria, data_criacao, objetivo) values (?,?,?,?)";
        PreparedStatement pStatement = null;
        Connection connection = null;

        try {
            connection = new ConnectionDAO().getConnection();
            pStatement = connection.prepareStatement(sql);
            pStatement.setInt(1, plano.getPaciente().getCodPaciente());
            pStatement.setDouble(2, plano.getKcalNecessarias());
            pStatement.setDate(3, Date.valueOf(plano.getDataCriacao()));
            pStatement.setString(4, plano.getObjetivo());

            pStatement.executeUpdate();
        } catch (SQLException e){
            throw new ExceptionDAO("Erro ao cadastrar o plano: " + e.getMessage());
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
