
package br.com.apiavicena.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import br.com.apiavicena.factory.Conexao;

import br.com.apiavicena.model.ConsultaVO;
import br.com.apiavicena.model.ConvenioVO;
import br.com.apiavicena.model.EspecializacaoVO;
import br.com.apiavicena.model.PacienteVO;


/**
 *
 * @author Marco
 */
public class ConsultaDAO {
    
    private EspecializacaoDAO especializacaoDAO = new EspecializacaoDAO();
    private ConvenioDAO convenioDAO = new ConvenioDAO();
    private PacienteDAO pacienteDAO = new PacienteDAO();

    public int cadastrar(ConsultaVO consultaVO) {
        Date data =  (Date) consultaVO.getDataConsulta();
        consultaVO.setDataConsulta(data);
        Calendar c = Calendar.getInstance();
        c.setTime(consultaVO.getDataConsulta());
        Date dataSQL = new Date(c.getTimeInMillis());

        int novoId = 0;

        String sql = "INSERT INTO consulta (codigoEspecializacao, codigoPaciente, codigoConvenio, dataConsulta, atencaoEspecial, horarioConsulta, valorConsulta)"
                + " VALUES (?,?,?,?,?,?,?)";

        Connection conn = Conexao.getConnection();
        PreparedStatement prepStmt = Conexao.getPreparedStatement(conn, sql, Statement.RETURN_GENERATED_KEYS);

        try {

            prepStmt.setInt(1, consultaVO.getEspecializacaoVO().getCodigoEspecializacao());
            prepStmt.setInt(2, consultaVO.getPacienteVO().getCodigoPaciente());
            prepStmt.setInt(3, consultaVO.getConvenioVO().getCodigoConvenio());
            prepStmt.setDate(4, dataSQL);
            prepStmt.setString(5, consultaVO.getAtencaoEspecial());
            prepStmt.setString(6, consultaVO.getHorarioConsulta());
            prepStmt.setString(7, consultaVO.getValorConsulta());
            prepStmt.executeUpdate();

            ResultSet generatedKeys = prepStmt.getGeneratedKeys();

            if (generatedKeys.next()) {
                novoId = generatedKeys.getInt(1);

            }

        } catch (SQLException e) {
            System.out.println("Erro ao executar o Cadastro do Consulta! Causa: \n: " + e.getMessage());

        } finally {
            Conexao.closePreparedStatement(prepStmt);
            Conexao.closeConnection();
        }
        System.out.println("cadastrado " + novoId);
        return novoId;

    }

    public boolean excluir(int codigoConsulta) {
        boolean sucessoDelete = false;

        String query = "DELETE from consulta where codigoConsulta = ? ";

        Connection conn = Conexao.getConnection();
        PreparedStatement prepStmt = Conexao.getPreparedStatement(conn, query);

        try {
            prepStmt.setInt(1, codigoConsulta);
            int codigoRetorno = prepStmt.executeUpdate();
            if (codigoRetorno == 1) {
                sucessoDelete = true;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao executar Query de Exclus�o do Consulta! Causa: \n: " + e.getMessage());
        } finally {
            Conexao.closePreparedStatement(prepStmt);
            Conexao.closeConnection();
        }
        return sucessoDelete;
    }

    public boolean alterar(ConsultaVO consultaVO) {
        boolean sucessoAtualizar = false;

        Date data = (Date) consultaVO.getDataConsulta();
        consultaVO.setDataConsulta(data);
        Calendar c = Calendar.getInstance();
        c.setTime(consultaVO.getDataConsulta());
        Date dataSQL = new Date(c.getTimeInMillis());
        String query = "UPDATE consulta SET codigoEspecializacao=?, codigoPaciente=?, codigoConvenio=?, dataConsulta=?, atencaoEspecial=?, horarioConsulta=?, valorConsulta=?"
                + " where codigoConsulta = ? ";

        Connection conn = Conexao.getConnection();
        PreparedStatement prepStmt = Conexao.getPreparedStatement(conn, query);

        try {

            prepStmt.setInt(1, consultaVO.getEspecializacaoVO().getCodigoEspecializacao());
            prepStmt.setInt(2, consultaVO.getPacienteVO().getCodigoPaciente());
            prepStmt.setInt(3, consultaVO.getConvenioVO().getCodigoConvenio());
            prepStmt.setDate(4, dataSQL);
            prepStmt.setString(5, consultaVO.getAtencaoEspecial());
            prepStmt.setString(6, consultaVO.getHorarioConsulta());
            prepStmt.setString(7, consultaVO.getValorConsulta());
            prepStmt.setInt(8, consultaVO.getCodigoConsulta());

            int codigoRetorno = prepStmt.executeUpdate();

            if (codigoRetorno == 1) {
                sucessoAtualizar = true;
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao executar Query de Atualizaçãoo do Consulta!Causa: \n: " + ex.getMessage());
        } finally {
            Conexao.closePreparedStatement(prepStmt);
            Conexao.closeConnection();
        }

        return sucessoAtualizar;
    }

    public ArrayList<ConsultaVO> listar() {

        ArrayList<ConsultaVO> listaConsultas = new ArrayList<ConsultaVO>();
        String query = "select * from consulta";

        Connection conn = Conexao.getConnection();
        PreparedStatement prepStmt = Conexao.getPreparedStatement(conn, query);
        try {
            ResultSet result = prepStmt.executeQuery();

            while (result.next()) {
                ConsultaVO consulta = new ConsultaVO();

                consulta.setCodigoConsulta(result.getInt(1));
                EspecializacaoVO especializacaoVO = especializacaoDAO.lerEspecializacaoPorId(result.getInt(2));
                consulta.setEspecializacaoVO(especializacaoVO);
                PacienteVO pacienteVO = pacienteDAO.lerPorId(result.getInt(3));
                consulta.setPacienteVO(pacienteVO);
                ConvenioVO convenioVO = convenioDAO.lerPorId(result.getInt(4));
                consulta.setConvenioVO(convenioVO);
                consulta.setDataConsulta(result.getDate(5));
                consulta.setAtencaoEspecial(result.getString(6));
                consulta.setHorarioConsulta(result.getString(7));
                consulta.setValorConsulta(result.getString(8));

                listaConsultas.add(consulta);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaConsultas;
    }

    public ConsultaVO lerPorId(int id) {
        ConsultaVO consulta = new ConsultaVO();

        String query = "SELECT * from consulta " + " where codigoConsulta like ?";

        Connection conn = Conexao.getConnection();
        PreparedStatement prepStmt = Conexao.getPreparedStatement(conn, query);
        try {
            prepStmt.setInt(1, id);
            ResultSet result = prepStmt.executeQuery();

            while (result.next()) {

                consulta.setCodigoConsulta(result.getInt(1));
                EspecializacaoVO especializacaoVO = especializacaoDAO.lerEspecializacaoPorId(result.getInt(2));
                consulta.setEspecializacaoVO(especializacaoVO);
                PacienteVO pacienteVO = pacienteDAO.lerPorId(result.getInt(3));
                consulta.setPacienteVO(pacienteVO);
                ConvenioVO convenioVO = convenioDAO.lerPorId(result.getInt(4));
                consulta.setConvenioVO(convenioVO);
                consulta.setDataConsulta(result.getDate(5));
                consulta.setAtencaoEspecial(result.getString(6));
                consulta.setHorarioConsulta(result.getString(7));
                consulta.setValorConsulta(result.getString(8));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            Conexao.closePreparedStatement(prepStmt);
            Conexao.closeConnection();
        }
        return consulta;
    }
    
}
