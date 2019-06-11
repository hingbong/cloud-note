package io.github.hingbong.cloudnote.service.excption;

/**
 * Invalid Note
 *
 * @author Hingbong
 */
public class InvalidNoteException extends ServiceException {

  public InvalidNoteException() {
  }

  public InvalidNoteException(String message) {
    super(message);
  }
}
