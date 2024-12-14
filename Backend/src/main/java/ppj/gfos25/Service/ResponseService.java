/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB40/StatelessEjbClass.java to edit this template
 */
package ppj.gfos25.Service;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.ws.rs.core.Response;

/**
 *
 * @author philipp.doering
 */
@Stateless
@LocalBean
public class ResponseService {

	/*
	 * .header("Access-Control-Allow-Origin", "*")
			.header("Access-Control-Allow-Credentials", "true")
			.header(
				"Access-Control-Allow-Headers",
				"origin, content-type, accept, authorization"
			)
			.header(
				"Access-Control-Allow-Methods",
				"GET, POST, PUT, DELETE, OPTIONS, HEAD"
			)
	 */

	public Response ok(String data) {
		return Response
			.status(200)
			.entity(data)
			.build();
	}

	public Response created(String data) {
		return Response
			.status(201)
			.entity(data)
			.build();
	}

	public Response status(int status, String data) {
		return Response
			.status(status)
			.entity(data)
			.build();
	}

	public Response unprocessable (String error) {
		return Response
			.status(422)
			.entity("{\"error\": \"" + error + "\"}")
			.build();
	}

	public Response unauthorized() {
		return Response
			.status(401)
			.entity("{\"error\": \"Unauthorized\"}")
			.build();
	}

	public Response unauthorized(String error) {
		return Response
			.status(401)
			.entity("{\"error\": \"" + error + "\"}")
			.build();
	}

	public Response badRequest(String string) {
		return Response
			.status(400)
			.entity("{\"error\": \"" + string + "\"}")
			.build();
	}

	public Response notFound(String string) {
		return Response
			.status(404)
			.entity("{\"error\": \"" + string + "\"}")
			.build();
	}
	
	public Response notFound() {
		return Response
			.status(404)
			.build();
	}
}