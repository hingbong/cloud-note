package io.github.hingbong.cloudnote.service.excption;

public class NoteNotFoundException extends ServiceException {

  public NoteNotFoundException() {
  }

  public NoteNotFoundException(String message) {
    super(message);
  }
}
