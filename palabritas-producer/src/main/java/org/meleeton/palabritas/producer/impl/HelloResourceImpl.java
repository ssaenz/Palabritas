package org.meleeton.palabritas.producer.impl;

import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.meleeton.palabritas.producer.resources.HelloResource;
import org.springframework.stereotype.Service;

@Path("/hello")
@Service
public class HelloResourceImpl implements HelloResource {

	@Override
	public Response sayHello(String name) {
		return Response.ok("Hello " + name).build();
	}

}
