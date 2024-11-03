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
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.io.StringReader;
import ppj.gfos25.Entity.Account;
import ppj.gfos25.Facades.AccountFacade;
import ppj.gfos25.Service.HashingService;
import ppj.gfos25.Service.ResponseService;
import ppj.gfos25.Service.TokenService;
import ppj.gfos25.Service.TokenService.TokenEmail;

/**
 *
 * @author philipp.doering
 */
@Stateless
@LocalBean
@Path("/tokens")
public class TokenWS {

    private final Jsonb jsonb = JsonbBuilder.create();

    @EJB
    ResponseService responsService = new ResponseService();

    @EJB
    AccountFacade accountFacade = new AccountFacade();

    @EJB
    HashingService hashingService = new HashingService();

    @EJB
    TokenService tokenService = new TokenService();

    @Path("/login")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(String json) {
        JsonObject jsonObject = Json
                .createReader(new StringReader(json))
                .readObject();
        String email, passwort;
        try {
            email = jsonObject.getString("email");
            passwort = jsonObject.getString("passwort");
        } catch (Exception e) {
            return responsService.badRequest("Invalid JSON");
        }
        Account account = accountFacade.getAccountByEmail(email);
        if (account == null) {
            return responsService.unauthorized("Account not found");
        }
        String passwortHash = hashingService.convertStringToHash(passwort);
        String accountPasswortHash = account
                .getEinstellungen()
                .getPasswortHash();
        String token = tokenService.createNewToken(email);
        JsonObject response = Json.createObjectBuilder()
                .add("token", token)
                .build();
        if (passwortHash.equals(accountPasswortHash)) {
            return responsService.ok(jsonb.toJson(response));
        } else {
            return responsService.unauthorized("Login failed");
        }
    }

    @Path("/refresh")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response refresh(
            @HeaderParam("Authorization") String token) {
        TokenEmail tokenEmail = tokenService.verifyToken(token);
        if (tokenEmail.email == null) {
            return responsService.unauthorized("Invalid token");
        }
        JsonObject response = Json.createObjectBuilder()
                .add("token", tokenEmail.token)
                .build();
        return responsService.ok(jsonb.toJson(response));
    }
}
