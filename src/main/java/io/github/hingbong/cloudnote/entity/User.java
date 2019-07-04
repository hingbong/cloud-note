package io.github.hingbong.cloudnote.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * t_user table
 *
 * @author Hingbong
 */
public class User implements Serializable {

  private Integer uid;
  private String username;
  private String nickname;
  private String password;
  private String salt;

  public Integer getUid() {
    return uid;
  }

  public User setUid(Integer uid) {
    this.uid = uid;
    return this;
  }

  public String getUsername() {
    return username;
  }

  public User setUsername(String username) {
    this.username = username;
    return this;
  }

  public String getNickname() {
    return nickname;
  }

  public User setNickname(String nickname) {
    this.nickname = nickname;
    return this;
  }

  public String getPassword() {
    return password;
  }

  public User setPassword(String password) {
    this.password = password;
    return this;
  }

  public String getSalt() {
    return salt;
  }

  public User setSalt(String salt) {
    this.salt = salt;
    return this;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("User{");
    sb.append("uid=").append(uid);
    sb.append(", username='").append(username).append('\'');
    sb.append(", nickname='").append(nickname).append('\'');
    sb.append(", password='").append(password).append('\'');
    sb.append(", salt='").append(salt).append('\'');
    sb.append('}');
    return sb.toString();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    User user = (User) o;
    return uid.equals(user.uid);
  }

  @Override
  public int hashCode() {
    return Objects.hash(uid);
  }
}
