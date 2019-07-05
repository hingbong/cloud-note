package io.github.hingbong.cloudnote.service.excption;

public class SessionInvalidException extends ServiceException {

  public SessionInvalidException() {
  }

  public SessionInvalidException(String message) {
    super(message);
  }
}
