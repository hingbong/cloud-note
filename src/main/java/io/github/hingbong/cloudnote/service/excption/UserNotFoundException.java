package io.github.hingbong.cloudnote.service.excption;

/**
 * user not found
 *
 * @author Hingbong
 */
public class UserNotFoundException extends ServiceException {

  public UserNotFoundException() {
  }

  public UserNotFoundException(String message) {
    super(message);
  }
}
