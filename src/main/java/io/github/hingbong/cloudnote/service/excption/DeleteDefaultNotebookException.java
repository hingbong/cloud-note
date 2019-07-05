package io.github.hingbong.cloudnote.service.excption;

/**
 * can't delete default notebook
 *
 * @author hingbong
 */
public class DeleteDefaultNotebookException extends ServiceException {

  public DeleteDefaultNotebookException() {
  }

  public DeleteDefaultNotebookException(String message) {
    super(message);
  }
}
