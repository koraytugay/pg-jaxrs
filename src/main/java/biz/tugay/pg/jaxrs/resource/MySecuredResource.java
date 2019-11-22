package biz.tugay.pg.jaxrs.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import biz.tugay.pg.jaxrs.binding.RequiresAuthentication;

@Path("secure")
public class MySecuredResource
{
  @GET
  @RequiresAuthentication
  public String secureMessage() {
    return "A secure message!";
  }
}
