package io.github.hingbong.cloudnote.service.impl;

import io.github.hingbong.cloudnote.entity.User;
import io.github.hingbong.cloudnote.mapper.UserMapper;
import io.github.hingbong.cloudnote.service.NotebookService;
import io.github.hingbong.cloudnote.service.UserService;
import io.github.hingbong.cloudnote.service.excption.DuplicateUsernameException;
import io.github.hingbong.cloudnote.service.excption.FormatNotMatchException;
import io.github.hingbong.cloudnote.service.excption.InsertException;
import io.github.hingbong.cloudnote.service.excption.PasswordNotMatchException;
import io.github.hingbong.cloudnote.service.excption.UpdateException;
import io.github.hingbong.cloudnote.service.excption.UserNotFoundException;
import java.util.UUID;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * implement of UserService
 *
 * @author Hingbong
 */
@Service
public class UserServiceImpl implements UserService {

  private UserMapper userMapper;
  private NotebookService notebookService;

  @Override
  public void register(User user) {
    // check the user's information
    checkUser(user);

    // find user by username to check whether the username is in use
    User userInDb = findUserByUsername(user.getUsername());

    if (userInDb != null) {
      throw new DuplicateUsernameException("此用户名已被占用");
    }
    // random uuid
    String salt = UUID.randomUUID().toString();
    // encrypt the password
    String sha3Password = getSha3Password(user.getPassword(), salt);
    user.setSalt(salt);
    user.setPassword(sha3Password);

    insertUser(user);

    // generate key
    Integer uid = user.getUid();
    // add a default notebook when user register
    String title = "默认笔记本";
    String description = "默认笔记本";
    notebookService.addNotebook(title, description, uid);
  }

  @Override
  public User login(String username, String password) {
    // check username and password
    if (username == null || "".equals(username)) {
      throw new UserNotFoundException("用户名不能为空");
    }

    // find user in db by username
    User userInDb = findUserByUsername(username);
    // if can't find the user in db, that's username or password wrong
    if (userInDb == null) {
      throw new UserNotFoundException("用户名或密码错误");
    }
    if (!username.equals(userInDb.getUsername())) {
      throw new PasswordNotMatchException("用户名或密码错误");
    }

    // calculate the password and check
    String loginSha3password = getSha3Password(password, userInDb.getSalt());
    if (!loginSha3password.equals(userInDb.getPassword())) {
      throw new PasswordNotMatchException("用户名或密码错误");
    }
    userInDb.setPassword(null);
    userInDb.setSalt(null);

    return userInDb;
  }

  @Override
  public void changePassword(Integer uid, String originPassword, String newPassword) {
    checkPasswordValid(originPassword, newPassword);

    User user = findByUid(uid);

    String originSha3Password = getSha3Password(originPassword, user.getSalt());
    if (!originSha3Password.equals(user.getPassword())) {
      throw new PasswordNotMatchException("原密码错误");
    }
    String newSha3Password = getSha3Password(newPassword, user.getSalt());

    updateUser(uid, newSha3Password);
  }

  @Override
  public User findByUid(Integer uid) {
    User user = userMapper.findByUid(uid);
    if (user == null) {
      throw new UserNotFoundException("用户还未登录");
    }
    return user;
  }

  private void updateUser(Integer uid, String newSha3Password) {
    Integer integer = userMapper.updatePassword(uid, newSha3Password);
    if (!integer.equals(1)) {
      throw new UpdateException("未知错误");
    }
  }

  private void checkPasswordValid(String originPassword, String newPassword) {
    if (originPassword == null
        || newPassword == null
        || "".equals(newPassword)
        || "".equals(originPassword)) {
      throw new PasswordNotMatchException("密码错误");
    }
  }

  private void insertUser(User user) {
    Integer insert = userMapper.insert(user);
    // check the insert result
    if (insert < 1) {
      throw new InsertException("注册失败");
    }
  }

  private User findUserByUsername(String username) {
    return userMapper.findUserByUsername(username);
  }
  /**
   * check a user
   *
   * @param user user to be checked
   */
  private void checkUser(User user) {
    if (user == null) {
      throw new FormatNotMatchException("用户无效");
    }
    if (user.getUsername() == null || "".equals(user.getUsername())) {
      throw new FormatNotMatchException("用户名无效");
    }
    if (user.getPassword() == null || "".equals(user.getPassword())) {
      throw new FormatNotMatchException("密码无效");
    }
    if (user.getNickname() == null || "".equals(user.getNickname())) {
      throw new FormatNotMatchException("昵称无效");
    }
  }

  /**
   *  encrypt password by sha-3 256 with uuid three times
   *
   * @param password origin password
   * @param salt uuid
   * @return encrypted password
   */
  private String getSha3Password(String password, String salt) {
    // SHA3-256, 3 times, password + salt
    String str = password + salt;
    int times = 3;
    for (int i = 0; i < times; i++) {
      str = DigestUtils.sha3_256Hex(str);
    }
    return str;
  }

  @Autowired
  private void setNotebookService(NotebookService notebookService) {
    this.notebookService = notebookService;
  }

  @Autowired
  private void setUserMapper(UserMapper userMapper) {
    this.userMapper = userMapper;
  }
}
