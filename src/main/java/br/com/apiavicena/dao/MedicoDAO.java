
package br.com.apiavicena.dao;

import br.com.apiavicena.factory.Conexao;
import br.com.apiavicena.model.MedicoVO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Marco
 */
public class MedicoDAO{

    private static ArrayList<MedicoVO> listaMedicos = new ArrayList<MedicoVO>();
    MedicoVO med = new MedicoVO();
    
    public MedicoDAO() {
        super();
    }   

    public int inserir(MedicoVO medicoVO) {
        int novoId = -1;

        String query = "INSERT INTO medico (nomeMedico, crm, celMensagemMedico, celularMedico, emailMedico, cpfMedico, cnpjMedico)"
                + " VALUES (?, ?, ?, ?, ?, ?, ?)";

        Connection conn = Conexao.getConnection();
        PreparedStatement prepStmt = Conexao.getPreparedStatement(conn, query, Statement.RETURN_GENERATED_KEYS);

        try {
            prepStmt.setString(1, medicoVO.getNomeMedico());
            prepStmt.setString(2, medicoVO.getCrm());
            prepStmt.setString(3, medicoVO.getCelMensagemMedico());
            prepStmt.setString(4, medicoVO.getCelularMedico());
            prepStmt.setString(5, medicoVO.getEmailMedico());
            prepStmt.setString(6, medicoVO.getCpfMedico());
            prepStmt.setString(7, medicoVO.getCnpjMedico());

            prepStmt.executeUpdate();

            ResultSet generatedKeys = prepStmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                novoId = generatedKeys.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao executar Query de Cadastro de Médico! Causa: \n: " + e.getMessage());

        } finally {
            Conexao.closePreparedStatement(prepStmt);
            Conexao.closeConnection();
        }
        return novoId;
    }
       

    public boolean alterar(MedicoVO medicoVO) {
        boolean sucessoAtualizar = false;

        String query = "UPDATE medico SET nomeMedico=?, crm=?, celMensagemMedico=?, celularMedico=?, emailMedico=?, cpfMedico=?, cnpjMedico=?"
                + " where cpfMedico = ?";

        Connection conn = Conexao.getConnection();
        PreparedStatement prepStmt = Conexao.getPreparedStatement(conn, query);

        try {
            prepStmt.setString(1, medicoVO.getNomeMedico());
            prepStmt.setString(2, medicoVO.getCrm());
            prepStmt.setString(3, medicoVO.getCelMensagemMedico());
            prepStmt.setString(4, medicoVO.getCelularMedico());
            prepStmt.setString(5, medicoVO.getEmailMedico());
            prepStmt.setString(6, medicoVO.getCpfMedico());
            prepStmt.setString(7, medicoVO.getCnpjMedico());
            prepStmt.setString(8, medicoVO.getCpfMedico());

            int codigoRetorno = prepStmt.executeUpdate();

            if (codigoRetorno == 1) {
                sucessoAtualizar = true;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao executar Query de Atualização do Médico! Causa: \n: " + e.getMessage());
        } finally {
            Conexao.closePreparedStatement(prepStmt);
            Conexao.closeConnection();
        }
        return sucessoAtualizar;
    }
    
    public boolean excluir(int codigoMedico) {
        boolean sucessoDelete = false;

        String query = "DELETE from medico where codigoMedicoo = ? ";

        Connection conn = Conexao.getConnection();
        PreparedStatement prepStmt = Conexao.getPreparedStatement(conn, query);

        try {
            prepStmt.setInt(1, codigoMedico);
            int codigoRetorno = prepStmt.executeUpdate();
            if (codigoRetorno == 1) {
                sucessoDelete = true;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao executar Query de Exclusão do Médico! Causa: \n: " + e.getMessage());
        } finally {
            Conexao.closePreparedStatement(prepStmt);
            Conexao.closeConnection();
        }
        return sucessoDelete;
    } 

    public ArrayList<MedicoVO> listarTodosOsMedicos() {

        ArrayList<MedicoVO> listaMedicos = new ArrayList<MedicoVO>();
        String query = "select * from medico";

        Connection conn = Conexao.getConnection();
        PreparedStatement prepStmt = Conexao.getPreparedStatement(conn, query);
        try {
            ResultSet result = prepStmt.executeQuery();

            while (result.next()) {
                MedicoVO medico = new MedicoVO();

                medico.setCodigoMedico(result.getInt(1));
                medico.setNomeMedico(result.getString(2));
                medico.setCrm(result.getString(3));
                medico.setCelMensagemMedico(result.getString(4));
                medico.setCelularMedico(result.getString(5));
                medico.setEmailMedico(result.getString(6));
                medico.setCpfMedico(result.getString(7));
                medico.setCnpjMedico(result.getString(8));

                listaMedicos.add(medico);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaMedicos;
    }

    
    public MedicoVO lerMedicoVOPorId(int codigoMedico) {
        String query = "SELECT * from medico " + " where codigoMedico = ?";

        Connection conn = Conexao.getConnection();
        PreparedStatement prepStmt = Conexao.getPreparedStatement(conn, query);
        MedicoVO medico = null;
        ArrayList<MedicoVO> medicos = new ArrayList<MedicoVO>();

        try {
            prepStmt.setInt(1, codigoMedico);
            ResultSet result = prepStmt.executeQuery();

            while (result.next()) {
                medico = new MedicoVO();

                medico.setCodigoMedico(result.getInt(1));
                medico.setNomeMedico(result.getString(2));
                medico.setCrm(result.getString(3));
                medico.setCelMensagemMedico(result.getString(4));
                medico.setCelularMedico(result.getString(5));
                medico.setEmailMedico(result.getString(6));
                medico.setCpfMedico(result.getString(7));
                medico.setCnpjMedico(result.getString(8));

                medicos.add(medico);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            Conexao.closePreparedStatement(prepStmt);
            Conexao.closeConnection();
        }
        return medico;
    }
    
    public ArrayList<MedicoVO> exibirMedicoPorNome(String nomeMedico) {

        String query = "SELECT *from medico " + " where nomeMedico = ?";

        Connection conn = Conexao.getConnection();
        PreparedStatement prepStmt = Conexao.getPreparedStatement(conn, query);
        MedicoVO medico = null;
        ArrayList<MedicoVO> medicos = new ArrayList<MedicoVO>();

        try {
            prepStmt.setString(1, '%' + nomeMedico + '%');
            ResultSet result = prepStmt.executeQuery();

            while (result.next()) {
                medico = new MedicoVO();

                medico.setCodigoMedico(result.getInt(1));
                medico.setNomeMedico(result.getString(2));
                medico.setCrm(result.getString(3));
                medico.setCelMensagemMedico(result.getString(4));
                medico.setCelularMedico(result.getString(5));
                medico.setEmailMedico(result.getString(6));
                medico.setCpfMedico(result.getString(7));
                medico.setCnpjMedico(result.getString(8));

                medicos.add(medico);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            Conexao.closePreparedStatement(prepStmt);
            Conexao.closeConnection();
        }
        return medicos;
    }
    
    public MedicoVO pesquisarMedicoVOPorCpf(String cpfMedico) {

        String query = "SELECT *from medico " + " where cpfMedico = ?";

        Connection conn = Conexao.getConnection();
        PreparedStatement prepStmt = Conexao.getPreparedStatement(conn, query);
        MedicoVO medico = null;

        try {
            prepStmt.setString(1, cpfMedico);
            ResultSet result = prepStmt.executeQuery();

            while (result.next()) {
                medico = new MedicoVO();

                medico.setCodigoMedico(result.getInt(1));
                medico.setNomeMedico(result.getString(2));
                medico.setCrm(result.getString(3));
                medico.setCelMensagemMedico(result.getString(4));
                medico.setCelularMedico(result.getString(5));
                medico.setEmailMedico(result.getString(6));
                medico.setCpfMedico(result.getString(7));
                medico.setCnpjMedico(result.getString(8));

            }
        } catch (SQLException e) {
            System.out.println("Erro ao executar a Query de Consulta de Médicos !Causa: \n: " + e.getMessage());
        } finally {
            Conexao.closePreparedStatement(prepStmt);
            Conexao.closeConnection();
        }
        return medico;
    }
    
}
