package ppj.gfos25.WS;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

/**
 *  @author philipp.doering
 */

@Stateless
@LocalBean
@Path("/flush")
public class FlushWS {
    @PersistenceContext
    EntityManager em;

    @GET
    public Response flush() {
        em.flush();
        return Response.ok("Flushed").build();
    }
}
