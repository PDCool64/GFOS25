/*
 * 
 * 
 */
package ppj.gfos25.WS;

import jakarta.ejb.Stateless;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.MediaType;
import ppj.gfos25.Facades.MessageFacade;
import ppj.gfos25.Service.ResponseService;
import jakarta.ejb.EJB;
import jakarta.ejb.LocalBean;

/**
 *
 * @author philipp.doering
 */
@Stateless
@LocalBean
@Path("/messages")
public class MessageWS {
	private final Jsonb jsonb = JsonbBuilder.create();

	@EJB
	private MessageFacade messageFacade;

	@EJB
	ResponseService responseService = new ResponseService();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/received/{id}")
	public Response getMessagesForAccountId(
			@PathParam("id") int id) {
		System.out.println("id: " + id);
		String json = jsonb.toJson(
				messageFacade.getMessagesForAccountId(id));
		System.out.println(json);
		return responseService.ok(
				jsonb.toJson(
						messageFacade.getMessagesForAccountId(id)));
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/all")
	public Response getAllMessages() {
		return responseService.ok(
				jsonb.toJson(
						messageFacade.getAllMessages()));
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Response getMessageById(
			@PathParam("id") int id) {
		return responseService.ok(
				jsonb.toJson(
						messageFacade.getMessageById(id)));
	}
}
