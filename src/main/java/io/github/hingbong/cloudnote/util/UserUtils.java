package io.github.hingbong.cloudnote.util;

import io.github.hingbong.cloudnote.entity.User;
import org.apache.shiro.SecurityUtils;

/**
 * @author hingbong
 */
public class UserUtils {

  public static User getCurrentUser() {
    return (User) SecurityUtils.getSubject().getPrincipal();
  }

  public static Integer getCurrentUid() {
    return getCurrentUser().getUid();
  }

  public static String getCurrentUsername() {
    return getCurrentUser().getUsername();
  }
}
