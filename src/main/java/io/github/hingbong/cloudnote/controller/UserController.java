package io.github.hingbong.cloudnote.controller;

import io.github.hingbong.cloudnote.entity.User;
import io.github.hingbong.cloudnote.service.UserService;
import io.github.hingbong.cloudnote.util.JsonResponse;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * route to the services about user
 *
 * @author Hingbong
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

  private UserService userService;

  /**
   * register
   *
   * @param user user for register
   * @return success json
   */
  @PostMapping
  public JsonResponse<Void> register(User user) {
    userService.register(user);
    return JsonResponse.success("注册成功");
  }

  /**
   * login
   *
   * @param username username for login
   * @return success json
   */
  @PostMapping("/session")
  public JsonResponse<User> login(String username, String password) {
    Subject subject = SecurityUtils.getSubject();
    UsernamePasswordToken token = new UsernamePasswordToken(username, password);
    subject.login(token);
    User user = new User().setUsername(getUsernameFromSession()).setUid(getUidFromSession());
    return JsonResponse.success("登录成功", user);
  }

  @DeleteMapping("/session")
  public JsonResponse<Void> logout() {
    SecurityUtils.getSubject().logout();
    return JsonResponse.success("注销成功");
  }

  @PutMapping("/password")
  public JsonResponse<Void> changePassword(
      @RequestParam("origin_password") String originPassword,
      @RequestParam("new_password") String newPassword) {
    Integer uid = getUidFromSession();
    userService.changePassword(uid, originPassword, newPassword);
    return JsonResponse.success("修改成功");
  }

  @Autowired
  private void setUserService(UserService userService) {
    this.userService = userService;
  }
}
