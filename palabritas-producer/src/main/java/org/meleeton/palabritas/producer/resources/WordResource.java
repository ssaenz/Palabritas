package org.meleeton.palabritas.producer.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("words")
public interface WordResource {
	
	@GET
	@Path("/{offset}/{limit}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getWords(@PathParam("offset") int offset,@PathParam("limit") int limit);
}
