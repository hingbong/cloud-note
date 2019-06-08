package io.github.hingbong.cloudnote.service.excption;

public class NotebookNotFoundException extends ServiceException {

  public NotebookNotFoundException() {
  }

  public NotebookNotFoundException(String message) {
    super(message);
  }
}
