package biz.tugay.pg.jaxrs.modal;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Message
{
  private static long idCounter = 0L;

  private long id;

  private String author;

  private String content;

  private Date created;

  public Message(String author, String content) {
    this.id = ++idCounter;
    this.author = author;
    this.content = content;
    this.created = new Date();
  }

  // Required by JAX-B
  @SuppressWarnings("unused")
  public Message() {
  }
}
