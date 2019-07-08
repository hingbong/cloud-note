package io.github.hingbong.cloudnote.controller;

import io.github.hingbong.cloudnote.entity.User;
import io.github.hingbong.cloudnote.service.UserService;
import io.github.hingbong.cloudnote.util.JsonResponse;
import io.github.hingbong.cloudnote.util.UserUtils;
import org.apache.shiro.SecurityUtils;
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
   * @param user user info for register
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
  public JsonResponse<User> login(
      String username,
      String password,
      @RequestParam(value = "remember_me", required = false) String rememberMe) {
    String host = getSession().getHost();
    userService.login(username, password, Boolean.valueOf(rememberMe), host);
    return JsonResponse.success("登录成功", UserUtils.getCurrentUser());
  }

  /**
   * logout
   *
   * @return response
   */
  @DeleteMapping("/session")
  public JsonResponse<Void> logout() {
    SecurityUtils.getSubject().logout();
    return JsonResponse.success("注销成功");
  }

  /**
   * change password
   *
   * @param originPassword old password
   * @param newPassword new password
   * @return response
   */
  @PutMapping("/password")
  public JsonResponse<Void> changePassword(
      @RequestParam("origin_password") String originPassword,
      @RequestParam("new_password") String newPassword) {
    userService.changePassword(originPassword, newPassword);
    return JsonResponse.success("修改成功");
  }

  @Autowired
  private void setUserService(UserService userService) {
    this.userService = userService;
  }
}
