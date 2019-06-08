package io.github.hingbong.cloudnote.service.excption;

/**
 * when insert in db get error
 *
 * @author Hingbong
 */
public class InsertException extends ServiceException {

  public InsertException() {
  }

  public InsertException(String message) {
    super(message);
  }
}
