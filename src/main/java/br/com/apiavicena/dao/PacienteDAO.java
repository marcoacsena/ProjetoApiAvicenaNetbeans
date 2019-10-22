package br.com.apiavicena.dao;

import br.com.apiavicena.factory.Conexao;
import br.com.apiavicena.model.PacienteVO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Marco
 */
public class PacienteDAO {
    
    public int inserir(PacienteVO pacienteVO) {
        int novoId = -1;

        String query = "INSERT INTO paciente (nomePaciente, celMensagemPaciente, "
                + "foneResidencial, foneComercial, emailPaciente, cpfPaciente, "
                + "cnpjPaciente, logradouro, numeroLogradouro, complemento, bairro, "
                + "cidade, uf, cep)"
                + " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        Connection conn = Conexao.getConnection();
        PreparedStatement prepStmt = Conexao.getPreparedStatement(conn, query, Statement.RETURN_GENERATED_KEYS);

        try {
            prepStmt.setString(1, pacienteVO.getNomePaciente());
            prepStmt.setString(2, pacienteVO.getCelMensagemPaciente());
            prepStmt.setString(3, pacienteVO.getFoneResidencial());
            prepStmt.setString(4, pacienteVO.getFoneComercial());
            prepStmt.setString(5, pacienteVO.getEmailPaciente());
            prepStmt.setString(6, pacienteVO.getCpfPaciente());
            prepStmt.setString(7, pacienteVO.getCnpjPaciente());
            prepStmt.setString(8, pacienteVO.getLogradouro());
            prepStmt.setString(9, pacienteVO.getNumLogradouro());
            prepStmt.setString(10, pacienteVO.getComplemento());
            prepStmt.setString(11, pacienteVO.getBairro());
            prepStmt.setString(12, pacienteVO.getCidade());
            prepStmt.setString(13, pacienteVO.getUf());
            prepStmt.setString(14, pacienteVO.getCep());

            prepStmt.executeUpdate();

            ResultSet generatedKeys = prepStmt.getGeneratedKeys();

            if (generatedKeys.next()) {
                novoId = generatedKeys.getInt(1);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao executar Query de Cadastro de Produto! Causa: \n: " + e.getMessage());

        } finally {
            Conexao.closePreparedStatement(prepStmt);
            Conexao.closeConnection();
        }
        return novoId;
    }

    public boolean excluir(String cpfPaciente) {

        boolean sucessoNaExclusao = false;

        String query = "DELETE from paciente where cpfPaciente = ? ";

        Connection conn = Conexao.getConnection();
        PreparedStatement prepStmt = Conexao.getPreparedStatement(conn, query);

        try {
            prepStmt.setString(1, cpfPaciente);
            int codigoRetorno = prepStmt.executeUpdate();
            if (codigoRetorno == 1) {
                sucessoNaExclusao = true;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao executar Query de Exclus�o do Paciente! Causa: \n: " + e.getMessage());
        } finally {
            Conexao.closePreparedStatement(prepStmt);
            Conexao.closeConnection();
        }
        return sucessoNaExclusao;
    }

    public boolean alterar(PacienteVO pacienteVO) {

        boolean atualizacao = false;

        String query = "UPDATE paciente SET nomePaciente = ?, celMensagemPaciente = ?,"
                + " foneResidencial = ?, foneComercial = ?, emailPaciente = ?, cpfPaciente = ?,"
                + " cnpjPaciente = ?, logradouro = ?, numeroLogradouro = ?, complemento = ?,"
                + " bairro = ?, cidade = ?, uf = ?, cep = ?"
                + " WHERE cpfPaciente = ?";

        Connection conn = Conexao.getConnection();
        PreparedStatement prepStmt = Conexao.getPreparedStatement(conn, query);

        try {
            prepStmt.setString(1, pacienteVO.getNomePaciente());
            prepStmt.setString(2, pacienteVO.getCelMensagemPaciente());
            prepStmt.setString(3, pacienteVO.getFoneResidencial());
            prepStmt.setString(4, pacienteVO.getFoneComercial());
            prepStmt.setString(5, pacienteVO.getEmailPaciente());
            prepStmt.setString(6, pacienteVO.getCpfPaciente());
            prepStmt.setString(7, pacienteVO.getCnpjPaciente());
            prepStmt.setString(8, pacienteVO.getLogradouro());
            prepStmt.setString(9, pacienteVO.getNumLogradouro());
            prepStmt.setString(10, pacienteVO.getComplemento());
            prepStmt.setString(11, pacienteVO.getBairro());
            prepStmt.setString(12, pacienteVO.getCidade());
            prepStmt.setString(13, pacienteVO.getUf());
            prepStmt.setString(14, pacienteVO.getCep());
            prepStmt.setString(15, pacienteVO.getCpfPaciente());
            int codigoRetorno = prepStmt.executeUpdate();

            if (codigoRetorno == 1) {
                atualizacao = true;
            }

        } catch (SQLException e) {
            System.out.println("Erro ao executar Query de Atualização do Paciente! Causa: \n: " + e.getMessage());
        } finally {
            Conexao.closePreparedStatement(prepStmt);
            Conexao.closeConnection();
        }

        return atualizacao;

    }

    public ArrayList<PacienteVO> listar() {

        String query = "select * from paciente";
        Connection conn = Conexao.getConnection();
        PreparedStatement prepStmt = Conexao.getPreparedStatement(conn, query);

       ArrayList<PacienteVO> paciente = new ArrayList<PacienteVO>();

        try {
            ResultSet resultado = prepStmt.executeQuery(query);

            while (resultado.next()) {

                PacienteVO pacienteVO = new PacienteVO();
                pacienteVO.setCodigoPaciente(resultado.getInt(1));
                pacienteVO.setNomePaciente(resultado.getString("nomePaciente"));
                pacienteVO.setCelMensagemPaciente(resultado.getString("celMensagemPaciente"));
                pacienteVO.setFoneResidencial(resultado.getString("foneResidencial"));
                pacienteVO.setFoneComercial(resultado.getString("foneComercial"));
                pacienteVO.setEmailPaciente(resultado.getString("emailPaciente"));
                pacienteVO.setCpfPaciente(resultado.getString("cpfPaciente"));
                pacienteVO.setCnpjPaciente(resultado.getString("cnpjPaciente"));
                pacienteVO.setLogradouro(resultado.getString("logradouro"));
                pacienteVO.setNumLogradouro(resultado.getString("numeroLogradouro"));
                pacienteVO.setComplemento(resultado.getString("complemento"));
                pacienteVO.setBairro(resultado.getString("bairro"));
                pacienteVO.setCidade(resultado.getString("cidade"));
                pacienteVO.setUf(resultado.getString("uf"));
                pacienteVO.setCep(resultado.getString("cep"));

                paciente.add(pacienteVO);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return paciente;
    }
    
    public PacienteVO lerPorId(int id) {
        String query = "SELECT * from paciente " + " where codigoPaciente = ?";

        Connection conn = Conexao.getConnection();
        PreparedStatement prepStmt = Conexao.getPreparedStatement(conn, query);
        PacienteVO paciente = null;
        try {
            prepStmt.setInt(1, id);
            ResultSet result = prepStmt.executeQuery();

            while (result.next()) {

                paciente = new PacienteVO();
                paciente.setCodigoPaciente(result.getInt(1));
                paciente.setNomePaciente(result.getString(2));
                paciente.setCelMensagemPaciente(result.getString(3));
                paciente.setFoneResidencial(result.getString(4));
                paciente.setFoneComercial(result.getString(5));
                paciente.setEmailPaciente(result.getString(6));
                paciente.setCpfPaciente(result.getString(7));
                paciente.setCnpjPaciente(result.getString(8));
                paciente.setLogradouro(result.getString(9));
                paciente.setNumLogradouro(result.getString(10));
                paciente.setComplemento(result.getString(11));
                paciente.setBairro(result.getString(12));
                paciente.setCidade(result.getString(13));
                paciente.setUf(result.getString(14));
                paciente.setCep(result.getString(15));

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            Conexao.closePreparedStatement(prepStmt);
            Conexao.closeConnection();
        }
        return paciente;
    }
}
