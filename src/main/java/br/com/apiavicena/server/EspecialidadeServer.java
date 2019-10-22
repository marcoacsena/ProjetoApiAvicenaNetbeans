/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.apiavicena.server;

import br.com.apiavicena.dao.EspecialidadeDAO;
import br.com.apiavicena.dao.MedicoDAO;
import br.com.apiavicena.factory.Conexao;
import br.com.apiavicena.model.EspecialidadeVO;
import br.com.apiavicena.model.MedicoVO;
import com.google.gson.Gson;
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

@Path("/especialidade")
public class EspecialidadeServer {
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<EspecialidadeVO> getEspecialidadeVO() {
        EspecialidadeDAO especialidadeDAO = new EspecialidadeDAO();
        return especialidadeDAO.listarTodasEspecialidades();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public EspecialidadeVO getEspecialidadeVO(@PathParam("id") int id) {
        EspecialidadeDAO especialidadeDAO = new EspecialidadeDAO();
        EspecialidadeVO especialidadeVO = especialidadeDAO.lerEspecialidadePorId(id);
        Conexao.closeConnection();
        return especialidadeVO;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public int cadastrar(@FormParam("dado") String dadosJSON) {
       Gson gson = new Gson();
        EspecialidadeVO especialidadeVO = gson.fromJson(dadosJSON, EspecialidadeVO.class);
        EspecialidadeDAO especialidadeDAO = new EspecialidadeDAO();
        int lastId = especialidadeDAO.inserir(especialidadeVO);
        Conexao.closeConnection();
        return lastId;
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Path("{id}")
    public Boolean alterar(@PathParam("id") int id, @FormParam("dado") String dadosJSON) {
        Gson gson = new Gson();
        EspecialidadeVO especialidadeVO = gson.fromJson(dadosJSON, EspecialidadeVO.class);

        if (id == especialidadeVO.getCodigoEspecialidade()) {
            especialidadeVO.setCodigoEspecialidade(id);
        } else {
            return false;
        }

        EspecialidadeDAO especialidadeDAO = new EspecialidadeDAO();
        Boolean res = especialidadeDAO.alterar(especialidadeVO);
        Conexao.closeConnection();
        return res;
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Boolean deletar(@PathParam("id") int id) {
        EspecialidadeDAO especialidadeDAO = new EspecialidadeDAO();
        Boolean res = especialidadeDAO.excluir(id);
        Conexao.closeConnection();
        return res;
    }

}
