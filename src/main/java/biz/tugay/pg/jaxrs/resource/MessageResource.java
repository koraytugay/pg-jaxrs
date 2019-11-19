package biz.tugay.pg.jaxrs.resource;

import java.util.List;
import java.util.Optional;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import biz.tugay.pg.jaxrs.modal.Message;
import biz.tugay.pg.jaxrs.service.MessageService;

@Path("/messages")
@Produces(MediaType.APPLICATION_JSON)
public class MessageResource
{
  private static MessageService messageService = new MessageService();

  private CommentSubResource commentSubResource = new CommentSubResource();

  @GET
  // curl -i localhost:8080/api/messages
  // When we do not return a Response, by default 200 OK is returned.
  public List<Message> getAll() {
    return messageService.getAll();
  }

  @GET
  @Path("filter")
  // curl -i 'localhost:8080/api/messages/filter?author=koray&content=hello'
  public Response filter(@QueryParam("author") String author, @QueryParam("content") String content) {
    List<Message> filter = messageService.filter(
        Optional.ofNullable(author).orElse(""),
        Optional.ofNullable(content).orElse(""));
    if (filter.isEmpty()) {
      return Response.status(Status.NO_CONTENT).build();
    }
    // I needed the `toArray` otherwise I was getting:
    // MessageBodyWriter not found for media type=application/json, type=class java.util.ArrayList,
    // genericType=class java.util.ArrayList.
    return Response.status(Status.FOUND).entity(filter.toArray(new Message[0])).build();
  }

  @GET
  @Path("beanFilter")
  // This one is just an example on how we can compact the query params in case they start to get too big
  // curl -i 'localhost:8080/api/messages/beanFilter?author=koray&content=hello'
  public Response beanFilter(@BeanParam MessageFilterBean messageFilterBean) {
    List<Message> filter = messageService.filter(
        Optional.ofNullable(messageFilterBean.getAuthor()).orElse(""),
        Optional.ofNullable(messageFilterBean.getContent()).orElse(""));
    if (filter.isEmpty()) {
      return Response.status(Status.NO_CONTENT).build();
    }
    // I needed the `toArray` otherwise I was getting:
    // MessageBodyWriter not found for media type=application/json, type=class java.util.ArrayList,
    // genericType=class java.util.ArrayList.
    return Response.status(Status.FOUND).entity(filter.toArray(new Message[0])).build();
  }

  @GET
  @Path("{messageId}")
  // curl -i localhost:8080/api/messages/1
  public Response findById(@PathParam("messageId") long id) {
    return Response.status(Status.FOUND).entity(messageService.findById(id)).build();
  }

  // curl -i -d '{"author":"John","content":"Hi!"}' -H Content-Type:application/json localhost:8080/api/messages
  // -d defaults to POST, hence no need for -X POST
  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  public Response addMessage(Message message) {
    return Response.status(Status.CREATED).entity(messageService.addMessage(message)).build();
  }

  // This is an example for using sub-resources. Cool feature!
  // curl localhost:8080/api/messages/5/comments/2
  @Path("{messageId}/comments")
  public CommentSubResource commentSubResource() {
    return commentSubResource;
  }
}
