package ppj.gfos25.WS;

import ppj.gfos25.Entity.Termin;
import ppj.gfos25.Facades.TerminFacade;
import ppj.gfos25.Service.ResponseService;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;


/**
 *
 * @author philipp.doering
 */
@Stateless
@Path("/termine")
public class TerminWS {

	private final Jsonb jsonb = JsonbBuilder.create();

    @EJB
    private TerminFacade terminFacade;

    @EJB
    private ResponseService responseService;

    @GET
	@Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllTermine() {
        List<Termin> termine = terminFacade.getAllTermine();
        return responseService.ok(jsonb.toJson(termine));
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTerminById(@PathParam("id") int id) {
        Termin termin = terminFacade.getTerminById(id);
        if (termin == null) {
            return responseService.notFound("Termin not found");
        }
        return responseService.ok(jsonb.toJson(termin));
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createTermin(Termin termin) {
        Termin createdTermin = terminFacade.createTermin(termin);
        if (createdTermin == null) {
            return responseService.badRequest("Error creating Termin");
        }
        return responseService.created(jsonb.toJson(createdTermin));
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateTermin(@PathParam("id") int id, Termin termin) {
        termin.setId(id);
        Termin updatedTermin = terminFacade.updateTermin(termin);
        if (updatedTermin == null) {
            return responseService.badRequest("Error updating Termin");
        }
        return responseService.ok(jsonb.toJson(updatedTermin));
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteTermin(@PathParam("id") int id) {
        boolean deleted = terminFacade.deleteTermin(id);
        if (!deleted) {
            return responseService.notFound("Termin not found");
        }
        return responseService.notFound();
    }

    @GET
    @Path("/account/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTermineForAccount(@PathParam("id") int id) {
        List<Termin> termine = terminFacade.getTermineForAccountId(id);
        return responseService.ok(jsonb.toJson(termine));
    }
}