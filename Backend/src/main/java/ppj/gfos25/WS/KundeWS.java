package ppj.gfos25.WS;

import jakarta.ejb.EJB;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import ppj.gfos25.Entity.Kunde;
import ppj.gfos25.Facades.KundeFacade;
import ppj.gfos25.Service.ResponseService;

/**
 *  @author philipp.doering
 */

@Stateless
@LocalBean
@Path("/kunde")
public class KundeWS {

    private final Jsonb jsonb = JsonbBuilder.create();

    @EJB
    KundeFacade kundeFacade;

    @EJB
    ResponseService responseService;

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllKunden() {
        return responseService.ok(jsonb.toJson(kundeFacade.getAllKunden()));
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getKundeById(int id) {
        return responseService.ok(jsonb.toJson(kundeFacade.getKundeById(id)));
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createKunde(String json) {
        Kunde k;
        try {
            k = jsonb.fromJson(json, Kunde.class);
        } catch (Exception e) {
            return responseService.badRequest("Invalid JSON");
        }
        return responseService.ok(jsonb.toJson(kundeFacade.createKunde(k)));
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateKunde(String json) {
        Kunde k;
        try {
            k = jsonb.fromJson(json, Kunde.class);
        } catch (Exception e) {
            return responseService.badRequest("Invalid JSON");
        }
        return responseService.ok(jsonb.toJson(kundeFacade.updateKunde(k)));
    }
}
