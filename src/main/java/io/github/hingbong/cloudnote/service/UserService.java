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
   */
  void login(String username, String password, Boolean rememberMe);

  /**
   * change user's password
   *
   * @param uid            user id
   * @param originPassword origin password
   * @param newPassword    new password
   */
  void changePassword(Integer uid, String originPassword, String newPassword);

  User findByUid(Integer uid);
}
