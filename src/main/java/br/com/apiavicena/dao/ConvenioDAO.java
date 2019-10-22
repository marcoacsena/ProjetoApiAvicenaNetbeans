/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.apiavicena.dao;

import br.com.apiavicena.factory.Conexao;
import br.com.apiavicena.model.ConvenioVO;
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
public class ConvenioDAO {
    
    public int inserir(ConvenioVO convenioVO) {

        int novoId = 0;

        String sql = "INSERT INTO convenio (nomeConvenio, cnpjConvenio, valorConvenio)" + " VALUES (?,?,?)";

        Connection conn = Conexao.getConnection();
        PreparedStatement prepStmt = Conexao.getPreparedStatement(conn, sql, Statement.RETURN_GENERATED_KEYS);

        try {

            prepStmt.setString(1, convenioVO.getNomeConvenio());
            prepStmt.setString(2, convenioVO.getCnpjConvenio());
            prepStmt.setString(3, convenioVO.getValor());

            prepStmt.executeUpdate();

            ResultSet generatedKeys = prepStmt.getGeneratedKeys();

            if (generatedKeys.next()) {
                novoId = generatedKeys.getInt(1);

            }

        } catch (SQLException e) {
            System.out.println("Erro ao executar o Cadastro do Convenio! Causa: \n: " + e.getMessage());

        } finally {
            Conexao.closePreparedStatement(prepStmt);
            Conexao.closeConnection();
        }
        return novoId;

    }

    public ConvenioVO lerPorId(int id) {
        ConvenioVO convenio = null;
        String query = "SELECT *from convenio " + " where codigoConvenio = ?";
        Connection conn = Conexao.getConnection();
        PreparedStatement prepStmt = Conexao.getPreparedStatement(conn, query);
        try {
            prepStmt.setInt(1, id);
            ResultSet result = prepStmt.executeQuery();

            while (result.next()) {
                convenio = new ConvenioVO();
                convenio.setCodigoConvenio(result.getInt(1));
                convenio.setNomeConvenio(result.getString(2));
                convenio.setCnpjConvenio(result.getString(3));
                convenio.setValor(result.getString(4));

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            Conexao.closePreparedStatement(prepStmt);
            Conexao.closeConnection();
        }
        return convenio;
    }

    public boolean excluir(String cnpjConvenio) {
        boolean sucessoDelete = false;

        String query = "DELETE from convenio where cnpjConvenio = ? ";

        Connection conn = Conexao.getConnection();
        PreparedStatement prepStmt = Conexao.getPreparedStatement(conn, query);

        try {
            prepStmt.setString(1, cnpjConvenio);
            int codigoRetorno = prepStmt.executeUpdate();
            if (codigoRetorno == 1) {
                sucessoDelete = true;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao executar Query de Exclusão do Convênio! Causa: \n: " + e.getMessage());
        } finally {
            Conexao.closePreparedStatement(prepStmt);
            Conexao.closeConnection();
        }
        return sucessoDelete;
    }

    public boolean alterar(ConvenioVO convenioVO) {
        boolean sucessoAtualizar = false;

        String query = "UPDATE convenio SET nomeConvenio=?, cnpjConvenio=?, valorConvenio=? " + " where cnpjConvenio = ?";

        Connection conn = Conexao.getConnection();
        PreparedStatement prepStmt = Conexao.getPreparedStatement(conn, query);

        try {

            prepStmt.setString(1, convenioVO.getNomeConvenio());
            prepStmt.setString(2, convenioVO.getCnpjConvenio());
            prepStmt.setString(3, convenioVO.getValor());
            prepStmt.setString(4, convenioVO.getCnpjConvenio());

            int codigoRetorno = prepStmt.executeUpdate();

            if (codigoRetorno == 1) {
                sucessoAtualizar = true;
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao executar Query de Atualização do Convenio!Causa: \n: " + ex.getMessage());
        } finally {
            Conexao.closePreparedStatement(prepStmt);
            Conexao.closeConnection();
        }
        return sucessoAtualizar;
    }

    public ArrayList<ConvenioVO> listar() {

        ArrayList<ConvenioVO> listaConvenios = new ArrayList<ConvenioVO>();

        String query = "select * from convenio";

        Connection conn = Conexao.getConnection();
        PreparedStatement prepStmt = Conexao.getPreparedStatement(conn, query);
        try {
            ResultSet result = prepStmt.executeQuery();

            while (result.next()) {
                ConvenioVO convenio = new ConvenioVO();

                convenio.setCodigoConvenio(result.getInt(1));
                convenio.setNomeConvenio(result.getString(2));
                convenio.setCnpjConvenio(result.getString(3));
                convenio.setValor(result.getString(4));

                listaConvenios.add(convenio);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaConvenios;
    }

    
}
