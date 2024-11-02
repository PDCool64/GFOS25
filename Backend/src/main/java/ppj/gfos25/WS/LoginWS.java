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
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.io.StringReader;
import ppj.gfos25.Entity.Account;
import ppj.gfos25.Facades.AccountFacade;
import ppj.gfos25.Service.HashingService;
import ppj.gfos25.Service.ResponseService;

/**
 *
 * @author philipp.doering
 */
@Stateless
@LocalBean
@Path("/login")
public class LoginWS {

    private final Jsonb jsonb = JsonbBuilder.create();

    @EJB
    ResponseService responsService = new ResponseService();

    @EJB
    AccountFacade accountFacade = new AccountFacade();

    @EJB
    HashingService hashingService = new HashingService();

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(String json) {
        JsonObject jsonObject = Json
            .createReader(new StringReader(json))
            .readObject();
        String email = jsonObject.getString("email");
        String passwort = jsonObject.getString("passwort");
        Account account = accountFacade.getAccountByEmail(email);
        if (account == null) {
            return responsService.unauthorized("Account not found");
        }
        String passwortHash = hashingService.convertStringToHash(passwort);
        String accountPasswortHash = account
            .getEinstellungen()
            .getPasswortHash();
        if (passwortHash.equals(accountPasswortHash)) {
            return responsService.ok("Login successful");
        } else {
            return responsService.unauthorized("Login failed");
        }
    }
}
