package io.github.hingbong.cloudnote.service;

import io.github.hingbong.cloudnote.entity.User;

/**
 * services about users
 *
 * @author Hingbong
 */
public interface UserService {

  /**
   * register service
   *
   * @param user user to register
   */
  void register(User user);

  /**
   * login service
   *
   * @param username username
   * @param password password
   * @param rememberMe remember me or not
   * @param host remote host
   */
  void login(String username, String password, Boolean rememberMe, String host);

  /**
   * change user's password
   *
   * @param originPassword origin password
   * @param newPassword new password
   */
  void changePassword(String originPassword, String newPassword);

  /**
   * find user by user id
   *
   * @param uid user id
   * @return user
   */
  User findByUid(Integer uid);
}
