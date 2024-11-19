package nutriplan.dao;

import nutriplan.model.Alimento;
import nutriplan.model.Plano;
import nutriplan.model.PlanoAlimento;

import java.sql.*;
import java.util.ArrayList;

public class PlanoAlimentoDAO {

    public void montarPlanoAlimento(PlanoAlimento planoAlimento) throws ExceptionDAO {
        String sql = "insert into plano_alimento (id_plano, id_alimento, quantidade, kcal_alimento) values (?,?,?,?)";
        PreparedStatement pStatement = null;
        Connection connection = null;

        try {
            connection = new ConnectionDAO().getConnection();
            pStatement = connection.prepareStatement(sql);
            pStatement.setInt(1, planoAlimento.getPlano().getCodPlano());
            pStatement.setInt(2, planoAlimento.getAlimento().getCodAlimento());
            pStatement.setDouble(3, planoAlimento.getGramasComida());
            pStatement.setDouble(4, planoAlimento.getKcalComida());

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

    public ArrayList<PlanoAlimento> listarPlanoAlimento(int codPlano) throws ExceptionDAO {
        String sql = "select pl.id_plano, al.id_alimento, al.nome_alimento, al.kcal100, pa.quantidade, pa.kcal_alimento from plano pl, alimento al, plano_alimento pa WHERE al.id_alimento = pa.id_alimento and pl.id_plano = pa.id_plano and pa.id_plano =?";
        Connection connection = null;
        PreparedStatement pStatement = null;
        ArrayList<PlanoAlimento> planoAlimentos = new ArrayList<>();

        try {
            connection = new ConnectionDAO().getConnection();
            pStatement = connection.prepareStatement(sql);
            pStatement.setInt(1, codPlano);

            ResultSet rs = pStatement.executeQuery();
            if (rs!=null) {
                planoAlimentos = new ArrayList<>();
                while (rs.next()) {
                    Plano plano = new Plano();
                    plano.setCodPlano(rs.getInt("id_plano"));

                    Alimento alimento = new Alimento();
                    alimento.setCodAlimento(rs.getInt("id_alimento"));
                    alimento.setNomeComida(rs.getString("nome_alimento"));
                    alimento.setKcal100(rs.getDouble("kcal100"));

                    PlanoAlimento planoAlimento = new PlanoAlimento();
                    planoAlimento.setGramasComida(rs.getDouble("quantidade"));
                    planoAlimento.setKcalComida(rs.getDouble("kcal_alimento"));

                    planoAlimento.setPlano(plano);
                    planoAlimento.setAlimento(alimento);
                    planoAlimentos.add(planoAlimento);
                }
            }
        } catch (SQLException e){
            throw new ExceptionDAO("Erro ao listar os alimentos do plano: " + e.getMessage());
        } finally {
            try {
                if(pStatement!=null) {pStatement.close();}
            } catch (SQLException e) {
                throw new ExceptionDAO("Erro ao fechar o pStatement: " + e.getMessage());
            }
            try{
                if (connection!=null) {connection.close();}
            } catch (SQLException e){
                throw new ExceptionDAO("Erro ao fechar o connection: " + e.getMessage());
            }
        }

        return planoAlimentos;
    }

    public void alterarPlanoAlimento(PlanoAlimento planoAlimento) throws ExceptionDAO {
        String sql = "update plano_alimento set id_plano=?, id_alimento=?, quantidade=?, kcal_alimento=? where id_plano=? and id_alimento=?";
        PreparedStatement pStatement = null;
        Connection connection = null;

        try {
            if (planoAlimento.getAlimento() ==null || planoAlimento.getAlimento().getCodAlimento()<=0){
                throw new ExceptionDAO("Alimento inválido ou código do alimento não informado.");
            }
            if (planoAlimento.getGramasComida() <=0) {
                throw new ExceptionDAO("quantidade do alimento não informado.");
            }
            if (planoAlimento.getPlano().getCodPlano() <=0) {
                throw new ExceptionDAO("Código do plano inválido: "+ planoAlimento.getPlano().getCodPlano());
            }

            connection = new ConnectionDAO().getConnection();
            pStatement = connection.prepareStatement(sql);
            pStatement.setInt(1, planoAlimento.getPlano().getCodPlano());
            pStatement.setInt(2, planoAlimento.getAlimento().getCodAlimento());
            pStatement.setDouble(3, planoAlimento.getGramasComida());
            pStatement.setDouble(4, planoAlimento.getKcalComida());
            pStatement.setInt(5, planoAlimento.getPlano().getCodPlano());
            pStatement.setInt(6, planoAlimento.getAlimento().getCodAlimento());

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

    public void apagarPlanoAlimento(PlanoAlimento planoAlimento) throws ExceptionDAO {
        String sql = "delete from plano_alimento where id_plano=? and id_alimento=?";
        PreparedStatement pStatement = null;
        Connection connection = null;

        try {
            connection = new ConnectionDAO().getConnection();
            connection.setAutoCommit(false);

            pStatement = connection.prepareStatement(sql);
            pStatement.setInt(1, planoAlimento.getPlano().getCodPlano());
            pStatement.setInt(2, planoAlimento.getAlimento().getCodAlimento());
            pStatement.executeUpdate();
            pStatement.close();

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

    public double somarCaloriasPlano(int codPlano) throws ExceptionDAO {
        String sql = "SELECT SUM(pa.kcal_alimento) AS calorias_adicionadas " +
                "FROM plano_alimento pa " +
                "JOIN alimento al ON pa.id_alimento = al.id_alimento " +
                "WHERE pa.id_plano = ?";

        double totalCalorias = 0;
        Connection connection = null;
        PreparedStatement pStatement = null;
        ResultSet rs = null;

        try {
            connection = new ConnectionDAO().getConnection();
            pStatement = connection.prepareStatement(sql);
            pStatement.setInt(1, codPlano);
            rs = pStatement.executeQuery();

            if (rs.next()) {
                totalCalorias = rs.getDouble("calorias_adicionadas");
            }
        } catch (SQLException e) {
            throw new ExceptionDAO("Erro ao somar as calorias do plano: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pStatement != null) pStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                throw new ExceptionDAO("Erro ao fechar os recursos: " + e.getMessage());
            }
        }

        return totalCalorias;
    }
}
