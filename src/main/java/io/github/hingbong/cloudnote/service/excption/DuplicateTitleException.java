package io.github.hingbong.cloudnote.service.excption;

public class DuplicateTitleException extends ServiceException {

  public DuplicateTitleException() {
  }

  public DuplicateTitleException(String message) {
    super(message);
  }
}
