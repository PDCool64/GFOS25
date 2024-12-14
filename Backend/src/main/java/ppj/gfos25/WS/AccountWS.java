package ppj.gfos25.WS;

import java.util.List;
import java.util.ArrayList;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.ejb.LocalBean;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import ppj.gfos25.Entity.Account;
import ppj.gfos25.Entity.Aufgabenbearbeitung;
import ppj.gfos25.Facades.AccountFacade;
import ppj.gfos25.Service.ResponseService;
import ppj.gfos25.Service.TokenService;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

/**
 *
 * @author philipp.doering
 */
@Stateless
@LocalBean
@Path("/accounts") // <ip>/Backend/api/accounts
public class AccountWS {

	private final Jsonb jsonb = JsonbBuilder.create();

	@EJB
	ResponseService responseService = new ResponseService();

	@EJB
	TokenService tokenService = new TokenService();

	@EJB
	AccountFacade accountFacade = new AccountFacade();

	@GET
	public Response getAccount() {
		return responseService.ok("Test");
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/all")
	public Response getAllAccounts(

	) {
		return responseService.ok(
				jsonb.toJson(
						accountFacade.getAllAccounts()));
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Response getAccountById(
			@PathParam("id") int id) {
		Account account = accountFacade.getAccountById(id);
		return responseService.ok(jsonb.toJson(account));
	}

	@GET
	@Path("/test")
	@Produces(MediaType.APPLICATION_JSON)
	public Response test() {
		System.out.println("Test");
		return responseService.ok("abc");
	}

	@GET
	@Path("/aufgaben/{id}") // <ip>/Backend/api/accounts/aufgaben/43 -> Die Aufgaben von User mit id = 43
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAufgabenByAccount(
			@HeaderParam("Authorization") String token, // body, header
			@PathParam("id") int id) {
		// if (tokenService.verifyToken(token) == null) {
		// responsService.unauthorized("Token invalid");
		// }
		List<Aufgabenbearbeitung> aufgabenbearbeitungsListe = accountFacade.getAllAufgabenbearbeitungByAccountId(id);
		return responseService.ok(jsonb.toJson(aufgabenbearbeitungsListe));
	}

	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateAccount(@PathParam("id") int id, Account account) {
		account.setId(id);
		Account updatedAccount = accountFacade.updateAccount(account);
		if (updatedAccount == null) {
			return responseService.badRequest("Error updating account");
		}
		return responseService.ok(jsonb.toJson(updatedAccount));
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteAccount(@PathParam("id") int id) {
		boolean deleted = accountFacade.deleteAccount(id);
		if (!deleted) {
			return responseService.notFound("Account not found");
		}
		return responseService.notFound();
	}
}
