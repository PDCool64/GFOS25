package ppj.gfos25.WS;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

import jakarta.ejb.LocalBean;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import ppj.gfos25.Entity.Aufgabe;
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
@Path("/aufgaben")
public class AufgabeWS {
	private final Jsonb jsonb = JsonbBuilder.create();

	@EJB
	ResponseService responseService;

	@EJB
	AufgabeFacade aufgabeFacade;

	@EJB
	TokenService tokenService;

	/**
	 *
	 * @return Response
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/all")
	public Response getAllAufgaben() {
		return responseService.ok(
				jsonb.toJson(
						aufgabeFacade.getAllAufgaben()));
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Response getAufgabeById(
			@PathParam("id") int id) {
		return responseService.ok(
				jsonb.toJson(
						aufgabeFacade.getAufgabeById(id)));
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createAufgabe(
			@HeaderParam("Authorization") String token,
			String aufgabe) {
		TokenEmail tokenEmail = tokenService.verifyToken(token);
		if (tokenEmail == null || tokenEmail.email == null) {
			return responseService.unauthorized();
		}
		Aufgabe a;
		try {
			a = jsonb.fromJson(aufgabe, Aufgabe.class);
		} catch (Exception e) {
			return responseService.unprocessable("Invalid JSON");
		}
		return responseService.created(
				jsonb.toJson(
						aufgabeFacade.createAufgabe(
								jsonb.fromJson(aufgabe, Aufgabe.class))));
	}

	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateAufgabe(@PathParam("id") int id, String aufgabeJson) {
		Aufgabe aufgabe;
		try {
			aufgabe = jsonb.fromJson(aufgabeJson, Aufgabe.class);
		} catch (Exception e) {
			return responseService.unprocessable("Invalid JSON");
		}
		aufgabe.setId(id);
		Aufgabe updatedAufgabe = aufgabeFacade.updateAufgabe(aufgabe);
		if (updatedAufgabe == null) {
			return responseService.badRequest("Error updating Aufgabe");
		}
		return responseService.ok(jsonb.toJson(updatedAufgabe));
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteAufgabe(@PathParam("id") int id) {
		boolean deleted = aufgabeFacade.deleteAufgabe(id);
		if (!deleted) {
			return responseService.notFound("Aufgabe not found");
		}
		return responseService.notFound();
	}
}
