package io.github.hingbong.cloudnote.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class LoginInterceptor implements HandlerInterceptor {

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    String uid = "uid";
    if (request.getSession().getAttribute(uid) == null) {
      response.sendRedirect("/new/login.html");
      return false;
    }
    return true;
  }
}
