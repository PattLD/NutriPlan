package nutriplan.dao;

import nutriplan.model.Alimento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class AlimentoDAO {
    public void cadastrarAlimento(Alimento alimento) throws ExceptionDAO {
        String sql = "insert into alimento (nome_alimento,kcal100) values (?,?)";
        PreparedStatement pStatement = null;
        Connection connection = null;

        try {
            connection = new ConnectionDAO().getConnection();pStatement = connection.prepareStatement(sql);
            pStatement.setString(1, alimento.getNomeComida());
            pStatement.setDouble(2, alimento.getKcal100());

            pStatement.executeUpdate();
        } catch (SQLException e){
            if (e.getSQLState().equals("23000")) { // SQLState 23000 -> Violação de restrição
                throw new ExceptionDAO("Alimento já está cadastrado!");
            } else {
                throw new ExceptionDAO("\"Erro ao cadastrar o alimento: " + e.getMessage());
            }
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

    public ArrayList<Alimento> listarAlimentos(String nome) throws ExceptionDAO {
        String sql = "select * from alimento WHERE nome_alimento like '%" + nome + "%' order by nome_alimento";
        Connection connection = null;
        PreparedStatement pStatement = null;
        ArrayList<Alimento> alimentos = null;

        try {
            connection = new ConnectionDAO().getConnection();
            pStatement = connection.prepareStatement(sql);
            ResultSet rs = pStatement.executeQuery(sql);
            if (rs != null) {
                alimentos = new ArrayList<>();
                while (rs.next()) {
                    Alimento alimento = new Alimento();
                    alimento.setCodAlimento(rs.getInt("id_alimento"));
                    alimento.setNomeComida(rs.getString("nome_alimento"));
                    alimento.setKcal100(rs.getDouble("kcal100"));
                    alimentos.add(alimento);
                }
            }
        } catch (SQLException e) {
            throw new ExceptionDAO("Erro ao listar os alimentos: " + e.getMessage());
        } finally {
            try {
                if (pStatement != null) {
                    pStatement.close();
                }
            } catch (SQLException e) {
                throw new ExceptionDAO("Erro ao fechar o pStatement: " + e.getMessage());
            }
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new ExceptionDAO("Erro ao fechar o connection: " + e.getMessage());
            }
        }

        return alimentos;
    }

    public void alterarAlimento(Alimento alimento) throws ExceptionDAO {
        String sql = "update alimento set nome_alimento=?,kcal100=? where id_alimento=?";
        PreparedStatement pStatement = null;
        Connection connection = null;

        try {
            connection = new ConnectionDAO().getConnection();
            pStatement = connection.prepareStatement(sql);
            pStatement.setString(1, alimento.getNomeComida());
            pStatement.setDouble(2, alimento.getKcal100());
            pStatement.setInt(3, alimento.getCodAlimento());

            pStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e){
            throw new ExceptionDAO("\"Erro ao alterar o alimento: " + e.getMessage());
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

    public void apagarAlimento(Alimento alimento) throws ExceptionDAO {
        String deletePlanoAlimentoSQL = "DELETE FROM plano_alimento WHERE id_alimento=?";
        String deleteAlimentoSQL = "DELETE FROM alimento WHERE id_alimento=?";
        PreparedStatement pStatement = null;
        Connection connection = null;

        try {
            connection = new ConnectionDAO().getConnection();
            connection.setAutoCommit(false);

            // Excluir registros relacionados na tabela plano_alimento
            pStatement = connection.prepareStatement(deletePlanoAlimentoSQL);
            pStatement.setInt(1, alimento.getCodAlimento());
            pStatement.executeUpdate();
            pStatement.close();

            // Excluir o registro do alimento
            pStatement = connection.prepareStatement(deleteAlimentoSQL);
            pStatement.setInt(1, alimento.getCodAlimento());
            pStatement.executeUpdate();

            // Confirmar a transação
            connection.commit();

        } catch (SQLException e) {
            // Reverter a transação em caso de erro
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException rollbackEx) {
                    throw new ExceptionDAO("Erro ao reverter a transação: " + rollbackEx.getMessage());
                }
            }
            throw new ExceptionDAO("Erro ao deletar o alimento: " + e.getMessage());
        } finally {
            // Fechar os recursos
            try {
                if (pStatement != null) {
                    pStatement.close();
                }
            } catch (SQLException e) {
                throw new ExceptionDAO("Erro ao fechar Statement: " + e.getMessage());
            }
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new ExceptionDAO("Erro ao fechar a conexão: " + e.getMessage());
            }
        }
    }

}
