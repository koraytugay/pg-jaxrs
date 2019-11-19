package biz.tugay.pg.jaxrs.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.ws.rs.NotFoundException;

import biz.tugay.pg.jaxrs.modal.Message;

public class MessageService
{
  private static final List<Message> messages = new ArrayList<>();

  public MessageService() {
    addMessage("Koray Tugay", "Hello World!");
    addMessage("Koray Tugay", "Goodbye World..");
  }

  public List<Message> getAll() {
    return messages;
  }

  public Message addMessage(Message message) {
    return addMessage(message.getAuthor(), message.getContent());
  }

  public Message findById(long id) {
    return getAll().stream().filter(m -> m.getId() == id).findAny().orElseThrow(NotFoundException::new);
  }

  private Message addMessage(String author, String content) {
    Message message = new Message(author, content);
    messages.add(message);
    return message;
  }

  public List<Message> filter(String author, String content) {
    return getAll().stream()
        .filter(m -> m.getAuthor().toLowerCase().contains(author.toLowerCase()))
        .filter(m -> m.getContent().toLowerCase().contains(content.toLowerCase()))
        .collect(Collectors.toList());
  }
}
