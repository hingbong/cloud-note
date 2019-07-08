package io.github.hingbong.cloudnote.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

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
  static final int ACCESS_DENIED = 4009;
  static final int SESSION_INVALID = 4010;

  static final int NOT_SUCCESS = 0;
  static final int SOME_THING_ERROR = 5001;

  /**
   * get shiro subject
   *
   * @return shiro subject
   */
  private Subject getSubject() {
    return SecurityUtils.getSubject();
  }

  /**
   * get shiro session
   *
   * @return session
   */
  final Session getSession() {
    return getSubject().getSession();
  }
}
