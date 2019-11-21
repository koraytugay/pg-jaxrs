package biz.tugay.pg.jaxrs.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import biz.tugay.pg.jaxrs.exception.ResourceNotFoundException;

@Path("exception")
public class ExceptionResource
{
  @GET
  @Path("resourceNotFoundException")
  // curl -i http://localhost:8080/api/exception/resourceNotFoundException
  public void dataNotFound() {
    throw new ResourceNotFoundException();
  }
}
