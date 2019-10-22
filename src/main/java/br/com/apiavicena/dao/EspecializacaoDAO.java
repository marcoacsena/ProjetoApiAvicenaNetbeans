/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.apiavicena.dao;

import br.com.apiavicena.factory.Conexao;
import br.com.apiavicena.model.EspecialidadeVO;
import br.com.apiavicena.model.EspecializacaoVO;
import br.com.apiavicena.model.MedicoVO;
import br.com.apiavicena.dao.MedicoDAO;
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
public class EspecializacaoDAO {
    private EspecialidadeDAO especialidadeDAO = new EspecialidadeDAO();
    private MedicoDAO medicoDAO = new MedicoDAO();

    public int inserir(EspecializacaoVO especializacaoVO) {

        int novoId = 0;

        String sql = "INSERT INTO especializacao (codigoEspecialidade, codigoMedico, anoEspecializacao)"
                + " VALUES (?,?,?)";

        Connection conn = Conexao.getConnection();
        PreparedStatement prepStmt = Conexao.getPreparedStatement(conn, sql, Statement.RETURN_GENERATED_KEYS);

        try {

            prepStmt.setInt(1, especializacaoVO.getEspecialidadeVO().getCodigoEspecialidade());
            prepStmt.setInt(2, especializacaoVO.getMedicoVO().getCodigoMedico());
            prepStmt.setString(3, especializacaoVO.getAnoEspecializacao());

            prepStmt.executeUpdate();

            ResultSet generatedKeys = prepStmt.getGeneratedKeys();

            if (generatedKeys.next()) {
                novoId = generatedKeys.getInt(1);

            }

        } catch (SQLException e) {
            System.out.println("Erro ao executar o Cadastro do Especializac�o! Causa: \n: " + e.getMessage());

        } finally {
            Conexao.closePreparedStatement(prepStmt);
            Conexao.closeConnection();
        }
        return novoId;

    }

    public boolean excluir(int codigoEspecializacao) {
        boolean sucessoDelete = false;

        String query = "DELETE from especializacao where codigoEspecializacao = ? ";

        Connection conn = Conexao.getConnection();
        PreparedStatement prepStmt = Conexao.getPreparedStatement(conn, query);

        try {
            prepStmt.setInt(1, codigoEspecializacao);
            int codigoRetorno = prepStmt.executeUpdate();
            if (codigoRetorno == 1) {
                sucessoDelete = true;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao executar Query de Exclus�o do Especializac�o! Causa: \n: " + e.getMessage());
        } finally {
            Conexao.closePreparedStatement(prepStmt);
            Conexao.closeConnection();
        }
        return sucessoDelete;
    }

    public boolean alterar(EspecializacaoVO especializacaoVO) {

        System.out.println("model.dao.Especializacao: " + especializacaoVO);
        //System.out.println("model.dao.Especializacao: " + codigoEspecializacao);
        boolean sucessoAtualizar = false;

        String query = "UPDATE especializacao SET codigoEspecialidade=?, codigoMedico=?, anoEspecializacao=? "
                + " where codigoEspecializacao = ?";

        Connection conn = Conexao.getConnection();
        PreparedStatement prepStmt = Conexao.getPreparedStatement(conn, query);

        try {

            prepStmt.setInt(1, especializacaoVO.getEspecialidadeVO().getCodigoEspecialidade());
            prepStmt.setInt(2, especializacaoVO.getMedicoVO().getCodigoMedico());
            prepStmt.setString(3, especializacaoVO.getAnoEspecializacao());
            prepStmt.setInt(4, especializacaoVO.getCodigoEspecializacao());

            int codigoRetorno = prepStmt.executeUpdate();

            if (codigoRetorno == 1) {
                sucessoAtualizar = true;
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao executar Query de Atualiza��o do Especializac�o!Causa: \n: " + ex.getMessage());
        } finally {
            Conexao.closePreparedStatement(prepStmt);
            Conexao.closeConnection();
        }
        System.out.println("model.dao " + sucessoAtualizar);
        return sucessoAtualizar;
    }
    
    public ArrayList<EspecializacaoVO> listarTodasEspecializacoes() {
        String query = "SELECT * from especializacao";
        ArrayList<EspecializacaoVO> listaEspecializacao = new ArrayList<EspecializacaoVO>();

        Connection conn = Conexao.getConnection();
        PreparedStatement prepStmt = Conexao.getPreparedStatement(conn, query);
        try {
            ResultSet result = prepStmt.executeQuery();

            while (result.next()) {
                EspecializacaoVO especializacao = new EspecializacaoVO();
                especializacao.setCodigoEspecializacao(result.getInt(1));

                EspecialidadeVO especialidadeVO = especialidadeDAO.lerEspecialidadePorId(result.getInt(2));
                especializacao.setEspecialidadeVO(especialidadeVO);
                MedicoVO medicoVO = medicoDAO.lerMedicoVOPorId(result.getInt(3));
                especializacao.setMedicoVO(medicoVO);
                especializacao.setAnoEspecializacao(result.getString(4));

                listaEspecializacao.add(especializacao);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaEspecializacao;
    }

        
    public EspecializacaoVO lerEspecializacaoPorId(int id) {

        String query = "SELECT * FROM especializacao WHERE codigoEspecializacao = ? ";

        Connection conn = Conexao.getConnection();
        PreparedStatement prepStmt = Conexao.getPreparedStatement(conn, query);
        EspecializacaoVO especializacao = null;
        try {
            prepStmt.setInt(1, id);
            ResultSet result = prepStmt.executeQuery();

            while (result.next()) {
                especializacao = new EspecializacaoVO();

                especializacao.setCodigoEspecializacao(result.getInt(1));
                EspecialidadeVO especialidadeVO = especialidadeDAO.lerEspecialidadePorId(result.getInt(2));
                especializacao.setEspecialidadeVO(especialidadeVO);
                MedicoVO medicoVO = medicoDAO.lerMedicoVOPorId(result.getInt(3));
                especializacao.setMedicoVO(medicoVO);
                especializacao.setAnoEspecializacao(result.getString(4));

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            Conexao.closePreparedStatement(prepStmt);
            Conexao.closeConnection();
        }
        return especializacao;
    }
    
}
