package ppj.gfos25.WS;

import java.util.List;
import java.util.ArrayList;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.ejb.LocalBean;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
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
	ResponseService responsService = new ResponseService();

	@EJB
	TokenService tokenService = new TokenService();

	@EJB
	AccountFacade accountFacade = new AccountFacade();

	@GET
	public Response getAccount() {
		return responsService.ok("Test");
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/all")
	public Response getAllAccounts(

	) {
		return responsService.ok(
				jsonb.toJson(
						accountFacade.getAllAccounts()));
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Response getAccountById(
			@PathParam("id") int id) {
		Account account = accountFacade.getAccountById(id);
		return responsService.ok(jsonb.toJson(account));
	}

	private class AccountWeight {
		Account a;
		float weight;

		public AccountWeight(Account a, float weight) {
			this.a = a;
			this.weight = weight;
		}
	}

	@GET
	@Path("/test")
	@Produces(MediaType.APPLICATION_JSON)
	public Response test() {
		System.out.println("Test");
		return responsService.ok("abc");
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/search/{search}")
	public Response searchAccount(
			@PathParam("search") String search,
			String json) {
		List<Account> accounts = accountFacade.getAllAccounts();
		List<AccountWeight> result = new ArrayList<>();
		search = search.toLowerCase();
		String[] words = search.split(" ");
		for (Account account : accounts) {
			float weight = 0;
			String name = account.getNachname().toLowerCase();
			String vorname = account.getVorname().toLowerCase();
			String email = account.getEmail().toLowerCase();
			for (String word : words) {
				int index = name.indexOf(word);
				if (index >= 0) {
					weight += 256 / (index + 1);
				}
				index = vorname.indexOf(word);
				if (index >= 0) {
					weight += 256 / (index + 1);
				}
				index = email.indexOf(word);
				if (index >= 0) {
					weight += 128 / (index + 1);
				}
			}
			result.add(new AccountWeight(account, weight));
		}
		result.sort((a, b) -> {
			if (a.weight > b.weight) {
				return -1;
			} else if (a.weight < b.weight) {
				return 1;
			} else {
				return 0;
			}
		});
		List<Account> accountsResult = new ArrayList<>();
		int max = 10;
		int i = 0;
		for (AccountWeight accountWeight : result) {
			if (++i > max) {
				break;
			}
			if (accountWeight.weight > 0)
				accountsResult.add(accountWeight.a);
		}
		return responsService.ok(jsonb.toJson(accountsResult));
	}

	// GET, POST, PUT, DELETE
	// R C U D
	// CRUD -> CREATE, READ, UPDATE, DELETE
	@GET
	@Path("/aufgaben/{id}") // <ip>/Backend/api/accounts/aufgaben/43 -> Die Aufgaben von User mit id = 43
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAufgabenByAccount(
			@HeaderParam("Authorization") String token, // body, header
			@PathParam("id") int id) {
		// if (tokenService.verifyToken(token) == null) {
		// 	responsService.unauthorized("Token invalid");
		// }
		List<Aufgabenbearbeitung> aufgabenbearbeitungsListe = accountFacade.getAllAufgabenbearbeitungByAccountId(id);
		return responsService.ok(jsonb.toJson(aufgabenbearbeitungsListe));
	}
}
