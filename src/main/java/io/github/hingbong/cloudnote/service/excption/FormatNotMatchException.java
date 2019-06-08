package io.github.hingbong.cloudnote.service.excption;

/**
 * format not match
 *
 * @author Hingbong
 */
public class FormatNotMatchException extends ServiceException {

  public FormatNotMatchException() {
  }

  public FormatNotMatchException(String message) {
    super(message);
  }
}
