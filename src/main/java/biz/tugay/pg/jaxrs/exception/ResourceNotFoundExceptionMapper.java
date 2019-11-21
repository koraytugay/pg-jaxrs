package biz.tugay.pg.jaxrs.exception;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import biz.tugay.pg.jaxrs.modal.ErrorMessage;

@Provider
public class ResourceNotFoundExceptionMapper
    implements ExceptionMapper<ResourceNotFoundException>
{
  @Override
  public Response toResponse(ResourceNotFoundException exception) {
    return Response.status(Status.NOT_FOUND).
        entity(ErrorMessage.builder().errorMessage("Resource Not Found").documentation("Call me").build())
        .type(MediaType.APPLICATION_JSON)
        .build();
  }
}
