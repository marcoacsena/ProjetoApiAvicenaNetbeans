/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.apiavicena.server;

import br.com.apiavicena.dao.MedicoDAO;
import br.com.apiavicena.dao.PacienteDAO;
import br.com.apiavicena.factory.Conexao;
import br.com.apiavicena.model.MedicoVO;
import br.com.apiavicena.model.PacienteVO;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Marco
 */

@Path("/paciente")
public class PacienteServer {
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<PacienteVO> getPacienteVO() {
        PacienteDAO pacienteDao = new PacienteDAO();
        return pacienteDao.listar();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public PacienteVO getPacienteVO(@PathParam("id") int id) {
        PacienteDAO pacienteDao = new PacienteDAO();
        PacienteVO pacienteVO = pacienteDao.lerPorId(id);
        Conexao.closeConnection();
        return pacienteVO;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public int cadastrar(@FormParam("dado") String dadosJSON) {
       Gson gson = new Gson();
       PacienteVO pacienteVO = gson.fromJson(dadosJSON, PacienteVO.class);
       PacienteDAO pacienteDao = new PacienteDAO();
       int lastId = pacienteDao.inserir(pacienteVO);
       Conexao.closeConnection();
        return lastId;
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Path("{id}")
    public Boolean alterar(@PathParam("id") int id, @FormParam("dado") String dadosJSON) {
        Gson gson = new Gson();
       PacienteVO pacienteVO = gson.fromJson(dadosJSON, PacienteVO.class);

        if (id == pacienteVO.getCodigoPaciente()) {
            pacienteVO.setCodigoPaciente(id);
        } else {
            return false;
        }

        PacienteDAO pacienteDao = new PacienteDAO();;
        Boolean res = pacienteDao.alterar(pacienteVO);
        Conexao.closeConnection();
        return res;
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{cpf}")
    public Boolean deletar(@PathParam("cpf") String cpf) {
        PacienteDAO pacienteDao = new PacienteDAO();
        Boolean res = pacienteDao.excluir(cpf);
        Conexao.closeConnection();
        return res;
    }
}
