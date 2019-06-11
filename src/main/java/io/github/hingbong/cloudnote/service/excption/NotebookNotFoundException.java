package io.github.hingbong.cloudnote.service.excption;

/**
 * notebook not found
 *
 * @author Hingbong
 */
public class NotebookNotFoundException extends ServiceException {

  public NotebookNotFoundException() {
  }

  public NotebookNotFoundException(String message) {
    super(message);
  }
}
