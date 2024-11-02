package ppj.gfos25.WS;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

import jakarta.ejb.LocalBean;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import ppj.gfos25.Facades.AufgabeFacade;
import ppj.gfos25.Service.ResponseService;

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
	ResponseService responsService = new ResponseService();

	@EJB
	AufgabeFacade aufgabeFacade = new AufgabeFacade();

	/**
	 *
	 * @return Response
	 */
	@GET 
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/all")
	public Response getAllAufgaben() {
		return responsService.ok(
				jsonb.toJson(
						aufgabeFacade.getAllAufgaben()));
	}	

	@GET 
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Response getAufgabeById(
			@PathParam("id") int id
	) {
		return responsService.ok(
				jsonb.toJson(
						aufgabeFacade.getAufgabeById(id)));
	}


}
