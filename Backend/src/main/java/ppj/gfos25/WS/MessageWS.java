/*
 * 
 * 
 */
package ppj.gfos25.WS;

import jakarta.ejb.Stateless;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.websocket.server.PathParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
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

	private MessageFacade messageFacade = new MessageFacade();

	@EJB
	ResponseService responseService = new ResponseService();

	@GET
	@Path("/received/{id}")
	public Response getMessagesForAccountId(
			@PathParam("id") int id) {
		return responseService.ok(
				jsonb.toJson(
						messageFacade.getMessagesForAccountId(id)));
	}

	@GET
	@Path("/all")
	public Response getAllMessages() {
		return responseService.ok(
				jsonb.toJson(
						messageFacade.getAllMessages()));
	}
}
