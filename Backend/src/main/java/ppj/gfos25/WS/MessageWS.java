/*
 *
 *
 */
package ppj.gfos25.WS;

import jakarta.ejb.EJB;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
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
import java.io.StringReader;
import java.util.List;
import ppj.gfos25.Entity.Message;
import ppj.gfos25.Facades.AccountFacade;
import ppj.gfos25.Facades.MessageFacade;
import ppj.gfos25.Service.ResponseService;
import ppj.gfos25.Service.TokenService;
import ppj.gfos25.Service.TokenService.TokenEmail;

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
    private AccountFacade accountFacade;

    @EJB
    private ResponseService responseService;

    @EJB
    private TokenService tokenService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/received/{id}")
    public Response getMessagesForAccountId(@PathParam("id") int id) {
        System.out.println("id: " + id);
        String json = jsonb.toJson(
            messageFacade.getUnreadMessagesForAccountId(id)
        );
        System.out.println(json);
        return responseService.ok(json);
    }

    public class SentAndReceived {

        public List<Message> sent;
        public List<Message> received;

        public SentAndReceived() {}
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/chat/{senderID}/{receiverID}")
    public Response getMessagesForChat(
        @PathParam("senderID") int senderID,
        @PathParam("receiverID") int receiverID,
        @HeaderParam("Authorization") String token
    ) {
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
        return responseService.ok(jsonb.toJson(messageFacade.getAllMessages()));
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response getMessageById(@PathParam("id") int id) {
        return responseService.ok(
            jsonb.toJson(messageFacade.getMessageById(id))
        );
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createMessage(
        @HeaderParam("Authorization") String token,
        String json
    ) {
        TokenEmail tokenEmail = tokenService.verifyToken(token);
        if (tokenEmail == null || tokenEmail.email == null) {
            return responseService.unauthorized();
        }
        JsonObject object = Json
            .createReader(new StringReader(json))
            .readObject();
        // build writable json object
        JsonObjectBuilder writable = Json.createObjectBuilder(object);
        int senderId = 0, receiverId = 0;
        if (object.containsKey("receiver")) {
            receiverId = Integer.parseInt(object.getString("receiver"));
            writable.remove("receiver");
        }
        if (object.containsKey("sender")) {
            senderId = Integer.parseInt(object.getString("sender"));
            writable.remove("sender");
        }
        json = writable.build().toString();
        Message m;
        try {
            m = jsonb.fromJson(json, Message.class);
        } catch (Exception e) {
            System.out.println(e);
            return responseService.badRequest("Invalid JSON");
        }
        try {
            m.setReceiver(accountFacade.getAccountById(receiverId));
            m.setSender(accountFacade.getAccountById(senderId));
        } catch (Exception e) {
            return responseService.badRequest("Invalid receiver or sender");
        }
        return responseService.created(
            jsonb.toJson(messageFacade.createMessage(m))
        );
    }
}
