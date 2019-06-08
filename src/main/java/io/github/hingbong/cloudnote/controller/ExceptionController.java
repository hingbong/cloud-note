package io.github.hingbong.cloudnote.controller;

import io.github.hingbong.cloudnote.service.excption.DeleteException;
import io.github.hingbong.cloudnote.service.excption.DuplicateTitleException;
import io.github.hingbong.cloudnote.service.excption.DuplicateUsernameException;
import io.github.hingbong.cloudnote.service.excption.FormatNotMatchException;
import io.github.hingbong.cloudnote.service.excption.InsertException;
import io.github.hingbong.cloudnote.service.excption.InvalidNoteException;
import io.github.hingbong.cloudnote.service.excption.NotebookNotFoundException;
import io.github.hingbong.cloudnote.service.excption.PasswordNotMatchException;
import io.github.hingbong.cloudnote.service.excption.ServiceException;
import io.github.hingbong.cloudnote.service.excption.UpdateException;
import io.github.hingbong.cloudnote.service.excption.UserNotFoundException;
import io.github.hingbong.cloudnote.util.JsonResponse;
import java.time.LocalDateTime;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * handle exceptions
 *
 * @author Hingbong
 */
@RestControllerAdvice
public class ExceptionController extends BaseController {

  @ExceptionHandler(ServiceException.class)
  public JsonResponse<Void> exceptionHandler(final Throwable throwable) {
    System.err.println(LocalDateTime.now() + " ==> " + throwable);
    String msg;
    int status;
    if (throwable instanceof FormatNotMatchException) {
      status = FORMAT_NOT_MATCH;
    } else if (throwable instanceof DuplicateUsernameException) {
      status = DUPLICATE_USERNAME;
    } else if (throwable instanceof UserNotFoundException) {
      status = USER_NOT_FOUND;
    } else if (throwable instanceof PasswordNotMatchException) {
      status = PASSWORD_NOT_MATCH;
    } else if (throwable instanceof DuplicateTitleException) {
      status = DUPLICATE_TITLE;
    } else if (throwable instanceof NotebookNotFoundException) {
      status = NOTEBOOK_NOT_FOUND;
    } else if (throwable instanceof InvalidNoteException) {
      status = INVALID_NOTE;
    } else if (throwable instanceof InsertException
        || throwable instanceof UpdateException
        || throwable instanceof DeleteException) {
      status = SOME_THING_ERROR;
    } else {
      status = NOT_SUCCESS;
    }
    msg = throwable.getMessage();
    return JsonResponse.response(status, msg, null);
  }
}