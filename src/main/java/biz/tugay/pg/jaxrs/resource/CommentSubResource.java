package biz.tugay.pg.jaxrs.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

public class CommentSubResource
{
  @Path("{commentId}")
  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public String getCommentForMessage(
      @PathParam("messageId") String messageId,
      @PathParam("commentId") String commentId)
  {
    return String.format("Retrieving comment: %s for message: %s\n", commentId, messageId);
  }
}
