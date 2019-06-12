package io.github.hingbong.cloudnote.controller;

import javax.servlet.http.HttpSession;

/**
 * some attributions for use
 *
 * @author Hingbong
 */
public abstract class BaseController {

  static final int FORMAT_NOT_MATCH = 4001;
  static final int DUPLICATE_USERNAME = 4002;
  static final int USER_NOT_FOUND = 4003;
  static final int PASSWORD_NOT_MATCH = 4004;
  static final int DUPLICATE_TITLE = 4005;
  static final int NOTEBOOK_NOT_FOUND = 4006;
  static final int INVALID_NOTE = 4007;
  static final int DELETE_DEFAULT_NOTEBOOK = 4008;

  static final int NOT_SUCCESS = 0;
  static final int SOME_THING_ERROR = 5001;

  final Integer getUidFromSession(HttpSession session) {
    return Integer.valueOf(session.getAttribute("uid").toString());
  }
}
