package biz.tugay.pg.jaxrs.client;

import java.util.List;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import biz.tugay.pg.jaxrs.modal.Message;

// Start jetty and then play with this client
public class RestApiClient
{
  private static final WebTarget BASE_TARGET = ClientBuilder.newClient().target("http://localhost:8080/api");

  private static final WebTarget MESSAGES = BASE_TARGET.path("messages");

  private static final WebTarget BY_ID = MESSAGES.path("{id}");

  public static void main(String[] args) {
    Message message = new Message();
    message.setAuthor("koraytugay");
    message.setContent("From JAX-RS client!");

    Response response = MESSAGES.request().post(Entity.json(message));
    System.out.println(response);
    // {context=ClientResponse{method=POST, uri=http://localhost:8080/api/messages, status=201, reason=Created}}

    response = MESSAGES.request(MediaType.APPLICATION_JSON).get();
    System.out.println(response);
    // {context=ClientResponse{method=GET, uri=http://localhost:8080/api/messages, status=200, reason=OK}}

    // Handling Generic Types, such as List<Message>
    List<Message> messages = response.readEntity(new GenericType<List<Message>>(){});
    for (Message m: messages) {
      System.out.println(m);
    }

    message = BY_ID.resolveTemplate("id", 1).request(MediaType.APPLICATION_JSON).get(Message.class);
    System.out.println(message);
    // Message(id=1, author=Koray Tugay, content=Hello World!, created=Mon Nov 25 20:33:38 EST 2019)
  }
}
