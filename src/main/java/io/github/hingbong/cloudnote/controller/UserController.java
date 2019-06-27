package io.github.hingbong.cloudnote.controller;

import io.github.hingbong.cloudnote.entity.User;
import io.github.hingbong.cloudnote.service.UserService;
import io.github.hingbong.cloudnote.util.JsonResponse;
import javax.servlet.http.HttpSession;
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
   * @param session session for storing login status
   * @return success json
   */
  @PostMapping("/session")
  public JsonResponse<User> login(String username, String password, HttpSession session) {
    User login = userService.login(username, password);
    session.setAttribute("uid", login.getUid());
    session.setAttribute("username", login.getUsername());
    return JsonResponse.success("登录成功", login);
  }

  @DeleteMapping("/session")
  public JsonResponse<Void> logout(HttpSession session) {
    session.removeAttribute("uid");
    return JsonResponse.success("注销成功");
  }

  @PutMapping("/password")
  public JsonResponse<Void> changePassword(
      @RequestParam("origin_password") String originPassword,
      @RequestParam("new_password") String newPassword,
      HttpSession session) {
    Integer uid = getUidFromSession(session);
    userService.changePassword(uid, originPassword, newPassword);
    return JsonResponse.success("修改成功");
  }

  @Autowired
  private void setUserService(UserService userService) {
    this.userService = userService;
  }
}
