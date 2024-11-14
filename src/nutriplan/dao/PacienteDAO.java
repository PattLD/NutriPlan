package nutriplan.dao;

import nutriplan.model.Paciente;

import java.sql.*;
import java.util.ArrayList;

public class PacienteDAO {

    public void cadastrarPaciente(Paciente paciente) throws ExceptionDAO {
        String sql = "insert into pessoa (nome, cpf, sexo,data_nascimento,peso,altura,nivel_atividade,idade, imc, tmb, `get`) values (?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement pStatement = null;
        Connection connection = null;

        try {
            connection = new ConnectionDAO().getConnection();
            pStatement = connection.prepareStatement(sql);
            pStatement.setString(1, paciente.getNome());
            pStatement.setString(2, paciente.getCPF());
            pStatement.setString(3, paciente.getSexo());
            pStatement.setDate(4, Date.valueOf(paciente.getDataNascimento()));
            pStatement.setDouble(5, paciente.getPeso());
            pStatement.setDouble(6, paciente.getAltura());
            pStatement.setString(7, paciente.getAtividade());
            pStatement.setInt(8, paciente.getIdade());
            pStatement.setDouble(9, paciente.getIMC());
            pStatement.setDouble(10, paciente.getTMB());
            pStatement.setDouble(11, paciente.getGET());

            pStatement.executeUpdate();
        } catch (SQLException e){
            throw new ExceptionDAO("Erro ao cadastrar o paciente: " + e.getMessage());
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

    public ArrayList<Paciente> listarPacientes(String nome) throws ExceptionDAO {
        String sql = "select * from pessoa WHERE nome like '%" + nome + "%' order by nome";
        Connection connection = null;
        PreparedStatement pStatement = null;
        ArrayList<Paciente> pacientes = null;

        try{
            connection = new ConnectionDAO().getConnection();
            pStatement = connection.prepareStatement(sql);
            ResultSet rs = pStatement.executeQuery(sql);
            if(rs!=null){
                pacientes = new ArrayList<>();
                while(rs.next()){
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
                    pacientes.add(paciente);
                }
            }
        } catch (SQLException e){
            throw new ExceptionDAO("Erro ao listar os pacientes: " + e.getMessage());
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

        return pacientes;
    }

    public void alterarPaciente(Paciente paciente) throws ExceptionDAO {
        String sql = "update pessoa set nome=?, cpf=?, sexo=?,data_nascimento=?,peso=?,altura=?,nivel_atividade=?,idade=?, imc=?, tmb=?, `get`=? where id_pessoa=?";
        PreparedStatement pStatement = null;
        Connection connection = null;

        try {
            connection = new ConnectionDAO().getConnection();
            pStatement = connection.prepareStatement(sql);
            pStatement.setString(1, paciente.getNome());
            pStatement.setString(2, paciente.getCPF());
            pStatement.setString(3, paciente.getSexo());
            pStatement.setDate(4, Date.valueOf(paciente.getDataNascimento()));
            pStatement.setDouble(5, paciente.getPeso());
            pStatement.setDouble(6, paciente.getAltura());
            pStatement.setString(7, paciente.getAtividade());
            pStatement.setInt(8, paciente.getIdade());
            pStatement.setDouble(9, paciente.getIMC());
            pStatement.setDouble(10, paciente.getTMB());
            pStatement.setDouble(11, paciente.getGET());
            pStatement.setInt(12, paciente.getCodPaciente());

            pStatement.executeUpdate();
        } catch (SQLException e){
            throw new ExceptionDAO("Erro ao alterar o paciente: " + e.getMessage());
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

    public void apagarPaciente(Paciente paciente) throws ExceptionDAO {
        String sql = "delete from pessoa where id_pessoa=?";
        PreparedStatement pStatement = null;
        Connection connection = null;

        try {
            connection = new ConnectionDAO().getConnection();
            pStatement = connection.prepareStatement(sql);
            pStatement.setInt(1, paciente.getCodPaciente());

            pStatement.executeUpdate();
        } catch (SQLException e){
            throw new ExceptionDAO("Erro ao cadastrar o paciente: " + e.getMessage());
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