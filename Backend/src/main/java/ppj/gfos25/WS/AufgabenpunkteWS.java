package ppj.gfos25.WS;

import jakarta.ejb.EJB;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import ppj.gfos25.Entity.Aufgabenpunkt;
import ppj.gfos25.Facades.AufgabeFacade;
import ppj.gfos25.Service.ResponseService;
import ppj.gfos25.Service.TokenService;
import ppj.gfos25.Service.TokenService.TokenEmail;

/**
 *
 * @author philipp.doering
 */

@Stateless
@LocalBean
@Path("/aufgaben/punkte")
public class AufgabenpunkteWS {
    private final Jsonb jsonb = JsonbBuilder.create();

    @EJB
    private AufgabeFacade aufgabenFacade;

    @EJB
    private ResponseService responseService;

    @EJB
    private AufgabeFacade aufgabeFacade;

    @EJB
    private TokenService tokenService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/all")
    public Response getAllAufgabenpunkte() {
        return responseService.ok(
                jsonb.toJson(
                        aufgabeFacade.getAllAufgabenpunkte()));
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response getAufgabenpunktById(
            @PathParam("id") int id) {
        return responseService.ok(jsonb.toJson(
                aufgabeFacade.getAufgabenpunktById(id)));
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createAufgabenpunkt(
            @HeaderParam("Authorization") String token,
            String aufgabenpunkt) {
        TokenEmail tokenEmail = tokenService.verifyToken(token);
        if (tokenEmail == null || tokenEmail.email == null) {
            return responseService.unauthorized();
        }
        Aufgabenpunkt ap;
        try {
            ap = jsonb.fromJson(aufgabenpunkt, Aufgabenpunkt.class);
        } catch (Exception e) {
            return responseService.unprocessable("Invalid JSON");
        }
        return responseService.created(
                jsonb.toJson(
                        aufgabeFacade.createAufgabenpunkt(ap)));
    }

}
