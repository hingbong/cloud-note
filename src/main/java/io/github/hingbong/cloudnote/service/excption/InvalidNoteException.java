package io.github.hingbong.cloudnote.service.excption;

public class InvalidNoteException extends ServiceException {

  public InvalidNoteException() {
  }

  public InvalidNoteException(String message) {
    super(message);
  }
}
