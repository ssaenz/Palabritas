package org.meleeton.palabritas.producer.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("hello")
public interface HelloResource {
	
	@GET
	@Path("/{name}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response sayHello(@PathParam("name") String name);
}
