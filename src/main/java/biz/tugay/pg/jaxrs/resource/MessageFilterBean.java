package biz.tugay.pg.jaxrs.resource;

import javax.ws.rs.QueryParam;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
class MessageFilterBean
{
  @QueryParam("author")
  private String author;

  @QueryParam("content")
  private String content;
}
