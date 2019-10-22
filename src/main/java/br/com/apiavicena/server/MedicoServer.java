
package br.com.apiavicena.server;

import br.com.apiavicena.dao.MedicoDAO;
import br.com.apiavicena.factory.Conexao;
import br.com.apiavicena.model.MedicoVO;
import java.util.List;
import com.google.gson.Gson;
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

@Path("/medico")
public class MedicoServer {
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<MedicoVO> getMedicoVO() {
        MedicoDAO medicoDao = new MedicoDAO();
        return medicoDao.listarTodosOsMedicos();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public MedicoVO getMedicoVO(@PathParam("id") int id) {
        MedicoDAO medicoDao = new MedicoDAO();
        MedicoVO medicoVO = medicoDao.lerMedicoVOPorId(id);
        Conexao.closeConnection();
        return medicoVO;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public int cadastrar(@FormParam("dado") String dadosJSON) {
       Gson gson = new Gson();
       MedicoVO medicoVO = gson.fromJson(dadosJSON, MedicoVO.class);
       MedicoDAO medicoDao = new MedicoDAO();
       int lastId = medicoDao.inserir(medicoVO);
       Conexao.closeConnection();
        return lastId;
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Path("{id}")
    public Boolean alterar(@PathParam("id") int id, @FormParam("dado") String dadosJSON) {
        Gson gson = new Gson();
        MedicoVO medicoVO = gson.fromJson(dadosJSON, MedicoVO.class);

        if (id == medicoVO.getCodigoMedico()) {
            medicoVO.setCodigoMedico(id);
        } else {
            return false;
        }

        MedicoDAO medicoDao = new MedicoDAO();
        Boolean res = medicoDao.alterar(medicoVO);
        Conexao.closeConnection();
        return res;
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Boolean deletar(@PathParam("id") int id) {
        MedicoDAO medicoDao = new MedicoDAO();
        Boolean res = medicoDao.excluir(id);
        Conexao.closeConnection();
        return res;
    }

}
