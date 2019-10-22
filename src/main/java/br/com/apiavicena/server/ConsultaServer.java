
package br.com.apiavicena.server;

import br.com.apiavicena.dao.ConsultaDAO;
import br.com.apiavicena.factory.Conexao;
import br.com.apiavicena.model.ConsultaVO;
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

@Path("/consulta")
public class ConsultaServer {
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ConsultaVO> getConsulta() {
        ConsultaDAO consultaDAO= new ConsultaDAO();
        return consultaDAO.listar();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public ConsultaVO getConvenioVO(@PathParam("id") int id) {
        ConsultaDAO consultaDAO= new ConsultaDAO();
        ConsultaVO consultaVO = consultaDAO.lerPorId(id);
        Conexao.closeConnection();
        return consultaVO;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public int cadastrar(@FormParam("dado") String dadosJSON) {
       Gson gson = new Gson();
       ConsultaVO consultaVO = gson.fromJson(dadosJSON, ConsultaVO.class);
       ConsultaDAO consultaDAO= new ConsultaDAO();
       int lastId = consultaDAO.cadastrar(consultaVO);
       Conexao.closeConnection();
        return lastId;
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Path("{id}")
    public Boolean alterar(@PathParam("id") int id, @FormParam("dado") String dadosJSON) {
       Gson gson = new Gson();
       ConsultaVO consultaVO = gson.fromJson(dadosJSON, ConsultaVO.class);

        if (id == consultaVO.getCodigoConsulta()) {
            consultaVO.setCodigoConsulta(id);
        } else {
            return false;
        }

        ConsultaDAO consultaDAO = new ConsultaDAO();
        Boolean res = consultaDAO.alterar(consultaVO);
        Conexao.closeConnection();
        return res;
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Boolean deletar(@PathParam("id") int id) {
        ConsultaDAO consultaDAO = new ConsultaDAO();
        Boolean res = consultaDAO.excluir(id);
        Conexao.closeConnection();
        return res;
    }
    
}
