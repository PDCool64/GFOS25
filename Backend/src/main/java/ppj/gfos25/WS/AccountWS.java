package ppj.gfos25.WS;

import java.util.List;
import java.util.ArrayList;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.ejb.LocalBean;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import ppj.gfos25.Entity.Account;
import ppj.gfos25.Facades.AccountFacade;
import ppj.gfos25.Service.ResponseService;
import jakarta.json.JsonObject;
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
@Path("/accounts")
public class AccountWS {

	private final Jsonb jsonb = JsonbBuilder.create();

	@EJB
	ResponseService responsService = new ResponseService();

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

	private class AccountWeight {
		Account a;
		float weight;

		public AccountWeight(Account a, float weight) {
			this.a = a;
			this.weight = weight;
		}
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
		for (Account account : accounts) {
			float weight = 0;
			String name = account.getName().toLowerCase();
			if (name.contains(search)) {
				weight += 255 / (name.indexOf(search) + 1); 
			}
			String vorname = account.getVorname().toLowerCase();
			if (vorname.contains(search)) {
				weight += 255 / (vorname.indexOf(search) + 1); 
			}
			String email = account.getEmail().toLowerCase();
			if (email.contains(search)) {
				weight += 127 / (email.indexOf(search) + 1); 
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
		List <Account> accountsResult = new ArrayList<>();
		for (AccountWeight accountWeight : result) {
			if (accountWeight.weight > 0)
			accountsResult.add(accountWeight.a);
		}
		return responsService.ok(jsonb.toJson(accountsResult));
	}

}
