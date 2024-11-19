package nutriplan.dao;

import nutriplan.model.Alimento;
import nutriplan.model.Paciente;
import nutriplan.model.Plano;

import java.sql.*;
import java.util.ArrayList;

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

    public ArrayList<Plano> listarPlanos(String nome) throws ExceptionDAO {
        String sql = "select pl.id_plano, pl.kcal_necessaria, pl.data_criacao, pl.objetivo, pe.id_pessoa, pe.nome, pe.sexo, pe.data_nascimento, pe.peso, pe.altura, pe.nivel_atividade, pe.cpf, pe.idade, pe.imc, pe.tmb, pe.get from plano pl, pessoa pe WHERE pl.id_pessoa = pe.id_pessoa and pe.nome like '%" + nome + "%' order by nome";
        Connection connection = null;
        PreparedStatement pStatement = null;
        ArrayList<Plano> planos = null;

        try{
            connection = new ConnectionDAO().getConnection();
            pStatement = connection.prepareStatement(sql);
            ResultSet rs = pStatement.executeQuery(sql);
            if(rs!=null){
                planos = new ArrayList<>();
                while(rs.next()){
                    Plano plano = new Plano();
                    plano.setCodPlano(rs.getInt("id_plano"));
                    plano.setKcalNecessarias(rs.getDouble("kcal_necessaria"));
                    plano.setDataCriacao(rs.getDate("data_criacao").toLocalDate());
                    plano.setObjetivo(rs.getString("objetivo"));

                    Paciente paciente = new Paciente();
                    paciente.setCodPaciente(rs.getInt("id_pessoa"));
                    paciente.setNome(rs.getString("nome"));
                    paciente.setSexo(rs.getString("sexo"));
                    paciente.setDataNascimento(rs.getDate("data_nascimento").toLocalDate());
                    paciente.setPeso(rs.getDouble("peso"));
                    paciente.setAltura(rs.getDouble("altura"));
                    paciente.setAtividade(rs.getString("nivel_atividade"));
                    paciente.setCPF(rs.getString("cpf"));
                    paciente.setIdade(rs.getInt("idade"));
                    paciente.setIMC(rs.getDouble("imc"));
                    paciente.setTMB(rs.getDouble("tmb"));
                    paciente.setGET(rs.getDouble("get"));

                    plano.setPaciente(paciente);
                    planos.add(plano);
                }
            }
        } catch (SQLException e){
            throw new ExceptionDAO("Erro ao listar os planos: " + e.getMessage());
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

        return planos;
    }

    public void alterarPlano(Plano plano) throws ExceptionDAO {
        String sql = "update plano set id_pessoa=?, kcal_necessaria=?, data_criacao=?, objetivo=? where id_plano=?";
        PreparedStatement pStatement = null;
        Connection connection = null;

        try {
            if (plano.getPaciente() == null || plano.getPaciente().getCodPaciente() <= 0) {
                throw new ExceptionDAO("Paciente inválido ou código do paciente não informado.");
            }
            if (plano.getDataCriacao() == null) {
                throw new ExceptionDAO("Data de criação não pode ser nula.");
            }
            if (plano.getCodPlano() <= 0) {
                throw new ExceptionDAO("Código do plano inválido: "+plano.getCodPlano());
            }

            connection = new ConnectionDAO().getConnection();
            pStatement = connection.prepareStatement(sql);
            pStatement.setInt(1, plano.getPaciente().getCodPaciente());
            pStatement.setDouble(2, plano.getKcalNecessarias());
            pStatement.setDate(3, Date.valueOf(plano.getDataCriacao()));
            pStatement.setString(4, plano.getObjetivo());
            pStatement.setInt(5, plano.getCodPlano());

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

    public void apagarPlano(Plano plano) throws ExceptionDAO {
        String sql = "DELETE FROM plano WHERE id_plano=?";
        PreparedStatement pStatement = null;
        Connection connection = null;

        try {
            connection = new ConnectionDAO().getConnection();
            connection.setAutoCommit(false);

            pStatement = connection.prepareStatement(sql);
            pStatement.setInt(1, plano.getCodPlano());
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

}
