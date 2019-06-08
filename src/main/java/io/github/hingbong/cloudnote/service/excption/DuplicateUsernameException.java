package io.github.hingbong.cloudnote.service.excption;

/**
 * register duplicate username
 *
 * @author Hingbong
 */
public class DuplicateUsernameException extends ServiceException {

  public DuplicateUsernameException() {
  }

  public DuplicateUsernameException(String message) {
    super(message);
  }
}
