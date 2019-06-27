package io.github.hingbong.cloudnote.service.excption;

public class AccessDeniedException extends ServiceException {

  public AccessDeniedException() {
  }

  public AccessDeniedException(String message) {
    super(message);
  }
}
