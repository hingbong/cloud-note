package io.github.hingbong.cloudnote.service.excption;

/**
 * Duplicate Title Exception
 *
 * @author Hingbong
 */
public class DuplicateTitleException extends ServiceException {

  public DuplicateTitleException() {
  }

  public DuplicateTitleException(String message) {
    super(message);
  }
}
