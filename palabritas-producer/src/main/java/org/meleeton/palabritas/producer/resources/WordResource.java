package org.meleeton.palabritas.producer.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("commitwords")
public interface WordResource {
	
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getWords(@QueryParam("offset") int offset,@QueryParam("limit") int limit);
}
