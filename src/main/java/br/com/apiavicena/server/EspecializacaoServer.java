package br.com.apiavicena.server;

import br.com.apiavicena.dao.EspecialidadeDAO;
import br.com.apiavicena.dao.EspecializacaoDAO;
import br.com.apiavicena.factory.Conexao;
import br.com.apiavicena.model.EspecialidadeVO;
import br.com.apiavicena.model.EspecializacaoVO;
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
@Path("/especializacao")
public class EspecializacaoServer {
 
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<EspecializacaoVO> getEspecializacaoVO() {
        EspecializacaoDAO especializacaoDAO = new EspecializacaoDAO();
        return especializacaoDAO.listarTodasEspecializacoes();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public EspecializacaoVO getEspecializacaoVO(@PathParam("id") int id) {
        EspecializacaoDAO especializacaoDAO = new EspecializacaoDAO();
        EspecializacaoVO especializacaoVO = especializacaoDAO.lerEspecializacaoPorId(id);
        Conexao.closeConnection();
        return especializacaoVO;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public int cadastrar(@FormParam("dado") String dadosJSON) {
       Gson gson = new Gson();
       EspecializacaoVO especializacaoVO = gson.fromJson(dadosJSON, EspecializacaoVO.class);
       EspecializacaoDAO especializacaoDAO = new EspecializacaoDAO();
       int lastId = especializacaoDAO.inserir(especializacaoVO);
       Conexao.closeConnection();
       return lastId;
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Path("{id}")
    public Boolean alterar(@PathParam("id") int id, @FormParam("dado") String dadosJSON) {
        Gson gson = new Gson();
        EspecializacaoVO especializacaoVO = gson.fromJson(dadosJSON, EspecializacaoVO.class);

        if (id == especializacaoVO.getCodigoEspecializacao() ) {
            especializacaoVO.setCodigoEspecializacao(id);
        } else {
            return false;
        }

        EspecializacaoDAO especializacaoDAO = new EspecializacaoDAO();
        Boolean res = especializacaoDAO.alterar(especializacaoVO);
        Conexao.closeConnection();
        return res;
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Boolean deletar(@PathParam("id") int id) {
        EspecializacaoDAO especializacaoDAO = new EspecializacaoDAO();
        Boolean res = especializacaoDAO.excluir(id);
        Conexao.closeConnection();
        return res;
    }
}
