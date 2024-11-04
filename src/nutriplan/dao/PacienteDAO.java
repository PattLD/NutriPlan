package nutriplan.dao;

import nutriplan.model.Paciente;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PacienteDAO {

    public void cadastrarPaciente(Paciente paciente) throws ExceptionDAO {
        String sql = "insert into pessoa (nome, cpf, sexo,data_nascimento,peso,altura,objetivo,nivel_atividade,idade, imc, tmb, `get`) values (?,?,?,?,?,?,?,?,?,?,?,?)";
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
            pStatement.setString(7, paciente.getObjetivo());
            pStatement.setString(8, paciente.getAtividade());
            pStatement.setInt(9, paciente.getIdade());
            pStatement.setDouble(10, paciente.getIMC());
            pStatement.setDouble(11, paciente.getTMB());
            pStatement.setDouble(12, paciente.getGET());

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
