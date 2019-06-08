package io.github.hingbong.cloudnote.controller;

import io.github.hingbong.cloudnote.entity.User;
import io.github.hingbong.cloudnote.service.UserService;
import io.github.hingbong.cloudnote.util.JsonResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    return JsonResponse.response(SUCCESS, "注册成功", null);
  }

  /**
   * login
   *
   * @param username username for login
   * @param session  session for storing login status
   * @return success json
   */
  @PostMapping("/session")
  public JsonResponse<User> login(
      String username, String password, HttpServletRequest request, HttpServletResponse response) {
    User login = userService.login(username, password);
    HttpSession session = request.getSession();
    session.setAttribute("uid", login.getUid());
    Cookie cookie = new Cookie("JSESSIONID", session.getId());
    cookie.setPath(request.getContextPath());
    response.addCookie(cookie);
    return JsonResponse.response(SUCCESS, "登录成功", login);
  }

  @DeleteMapping("/session")
  public JsonResponse<Void> logout(HttpSession session) {
    session.removeAttribute("uid");
    return JsonResponse.response(SUCCESS, "注销成功", null);
  }

  @PutMapping("/password")
  public JsonResponse<Void> changePassword(
      @RequestParam("origin_password") String originPassword,
      @RequestParam("new_password") String newPassword,
      HttpSession session) {
    Integer uid = getUidFromSeesion(session);
    userService.changePassword(uid, originPassword, newPassword);
    return JsonResponse.response(SUCCESS, "修改成功", null);
  }

  @Autowired
  public void setUserService(UserService userService) {
    this.userService = userService;
  }
}
