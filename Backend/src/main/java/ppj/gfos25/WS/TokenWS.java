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
    ResponseService responseService = new ResponseService();

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
            return responseService.badRequest("Invalid JSON");
        }
        Account account = accountFacade.getAccountByEmail(email);
        if (account == null) {
            System.out.println("Something went wrong");
            return responseService.unauthorized("Account not found");
        }
        String passwortHash = hashingService.convertStringToHash(passwort);
        String accountPasswortHash = account
                .getEinstellungen()
                .getPasswortHash();
        if (!passwortHash.equals(accountPasswortHash)) {
            System.out.println(email);
            System.out.println(passwort.equals(accountPasswortHash));
            System.out.println(passwort.compareTo(accountPasswortHash));
            System.out.println(accountPasswortHash);
            System.out.println(passwortHash);
            return responseService.unauthorized("Login failed");
        }
        String token = tokenService.createNewToken(email);
        String refresh_token = tokenService.createNewRefreshToken();
        account.setRefreshToken(refresh_token);
        accountFacade.updateAccount(account);
        JsonObject response = Json.createObjectBuilder()
                .add("token", token)
                .add("refresh_token", refresh_token)
                .add("account", jsonb.toJson(account))
                .build();
        return responseService.ok(jsonb.toJson(response));

    }

    @Path("/refresh")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response refresh(String json) {
        JsonObject jsonObject = Json.createReader(new StringReader(json)).readObject();
        String email, old_refresh_token;
        try {
            email = jsonObject.getString("email");
            old_refresh_token = jsonObject.getString("refresh_token");
        } catch (Exception e) {
            System.out.println(e);
            System.out.println(json);
            return responseService.badRequest("Could not parse JSON");
        }
        Account account = accountFacade.getAccountByEmail(email);
        if (account == null) {
            System.out.println("Account not found");
            return responseService.unauthorized("Account not found");
        }
        if (!account.getRefreshToken().equals(old_refresh_token)) {
            return responseService.unauthorized("Refresh Token is invalid");
        }
        String new_refresh_token = tokenService.createNewRefreshToken();
        account.setRefreshToken(new_refresh_token);
        accountFacade.updateAccount(account);
        String token = tokenService.createNewToken(email);
        JsonObject response = Json.createObjectBuilder()
                .add("refresh_token", new_refresh_token)
                .add("token", token)
                .build();
        return responseService.ok(jsonb.toJson(response));
    }

    @Path("/logout")
    @POST
    public Response logout(String json) {
        TokenEmail tokenEmail = tokenService.verifyToken(json);
        if (tokenEmail == null || tokenEmail.email == null) {
            return responseService.unauthorized("Token is invalid");
        }
        Account account = accountFacade.getAccountByEmail(tokenEmail.email);
        account.setRefreshToken(null);
        accountFacade.updateAccount(account);
        return responseService.ok("Logout successful");
    }
}
