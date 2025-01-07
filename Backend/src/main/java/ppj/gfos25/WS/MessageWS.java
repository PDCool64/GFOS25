/*
 * 
 * 
 */
package ppj.gfos25.WS;

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
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.MediaType;
import ppj.gfos25.Entity.Message;
import ppj.gfos25.Facades.MessageFacade;
import ppj.gfos25.Service.ResponseService;
import ppj.gfos25.Service.TokenService;
import ppj.gfos25.Service.TokenService.TokenEmail;

import java.util.List;

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
	private ResponseService responseService;

	@EJB
	private TokenService tokenService;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/received/{id}")
	public Response getMessagesForAccountId(
			@PathParam("id") int id) {
		System.out.println("id: " + id);
		String json = jsonb.toJson(
				messageFacade.getUnreadMessagesForAccountId(id));
		System.out.println(json);
		return responseService.ok(json);
	}

	public class SentAndReceived {
		public List<Message> sent;
		public List<Message> received;

		public SentAndReceived() {
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/chat/{senderID}/{receiverID}")
	public Response getMessagesForChat(
			@PathParam("senderID") int senderID,
			@PathParam("receiverID") int receiverID,
			@HeaderParam("Authorization") String token) {
		// TokenEmail tokenEmail = tokenService.verifyToken(token);
		// if (tokenEmail == null || tokenEmail.email == null) {
		// return responseService.unauthorized();
		// }
		SentAndReceived sar = new SentAndReceived();
		sar.sent = messageFacade.getMessagesForChat(senderID, receiverID);
		sar.received = messageFacade.getMessagesForChat(receiverID, senderID);
		if (sar.sent == null || sar.received == null) {
			return responseService.unprocessable("Error fetching messages");
		}
		return responseService.ok(jsonb.toJson(sar));
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

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createMessage(
			@HeaderParam("Authorization") String token,
			String json) {
		TokenEmail tokenEmail = tokenService.verifyToken(token);
		if (tokenEmail == null || tokenEmail.email == null) {
			return responseService.unauthorized();
		}
		Message m;
		try {
			m = jsonb.fromJson(json, Message.class);
		} catch (Exception e) {
			return responseService.badRequest("Invalid JSON");
		}
		return responseService.created(
				jsonb.toJson(
						messageFacade.createMessage(m)));
	}
}
