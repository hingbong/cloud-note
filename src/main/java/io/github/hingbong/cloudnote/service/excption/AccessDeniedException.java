package io.github.hingbong.cloudnote.service.excption;

/**
 * @author hingbong
 */
public class AccessDeniedException extends ServiceException {

  public AccessDeniedException() {
  }

  public AccessDeniedException(String message) {
    super(message);
  }
}
