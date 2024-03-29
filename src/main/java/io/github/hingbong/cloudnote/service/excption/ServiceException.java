package io.github.hingbong.cloudnote.service.excption;

/**
 * exception in service
 *
 * @author Hingbong
 */
public class ServiceException extends RuntimeException {

  ServiceException() {
  }

  ServiceException(String message) {
    super(message);
  }

  @Override
  public synchronized Throwable fillInStackTrace() {
    return this;
  }
}
