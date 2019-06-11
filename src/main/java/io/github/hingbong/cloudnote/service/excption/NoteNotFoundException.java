package io.github.hingbong.cloudnote.service.excption;

/**
 * Note not found
 *
 * @author Hingbong
 */
public class NoteNotFoundException extends ServiceException {

  public NoteNotFoundException() {
  }

  public NoteNotFoundException(String message) {
    super(message);
  }
}
