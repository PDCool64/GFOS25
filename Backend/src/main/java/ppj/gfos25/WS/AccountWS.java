/*
 * 
 * 
 */
package ppj.gfos25.WS;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.ejb.LocalBean;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import ppj.gfos25.Service.ResponseService;

/**
 *
 * @author philipp.doering
 */
@Stateless
@LocalBean
@Path("/account")
public class AccountWS {

	@EJB 
	ResponseService responsService = new ResponseService();

	@GET
	@Path("/")
	public Response getAccount() {
		return responsService.ok("Test");
	}

}
