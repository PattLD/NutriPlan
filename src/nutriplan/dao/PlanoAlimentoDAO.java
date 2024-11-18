package nutriplan.dao;

import nutriplan.model.Plano;
import nutriplan.model.PlanoAlimento;

import java.sql.*;

public class PlanoAlimentoDAO {

    public void montarPlanoAlimento(PlanoAlimento planoAlimento) throws ExceptionDAO {
        String sql = "insert into plano_alimento (id_plano, id_alimento, quantidade) values (?,?,?)";
        PreparedStatement pStatement = null;
        Connection connection = null;

        try {
            connection = new ConnectionDAO().getConnection();
            pStatement = connection.prepareStatement(sql);
            pStatement.setInt(1, planoAlimento.getPlano().getCodPlano());
            pStatement.setInt(2, planoAlimento.getAlimento().getCodAlimento());
            pStatement.setDouble(3, planoAlimento.getQuantidade());

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
