package io.github.hingbong.cloudnote.service.excption;

/**
 * password not match
 *
 * @author Hingbong
 */
public class PasswordNotMatchException extends ServiceException {

  public PasswordNotMatchException() {
  }

  public PasswordNotMatchException(String message) {
    super(message);
  }
}
