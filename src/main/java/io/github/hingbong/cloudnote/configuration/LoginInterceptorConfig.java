package io.github.hingbong.cloudnote.configuration;

import io.github.hingbong.cloudnote.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class LoginInterceptorConfig implements WebMvcConfigurer {

  private LoginInterceptor loginInterceptor;

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry
        .addInterceptor(loginInterceptor)
        .addPathPatterns("/**")
        .excludePathPatterns(
            "/css/**",
            "/images/**",
            "/js/**",
            "/login.html",
            "/register.html",
            "/index.html",
            "/user",
            "/user/session");
  }

  @Autowired
  public void setLoginInterceptor(LoginInterceptor loginInterceptor) {
    this.loginInterceptor = loginInterceptor;
  }
}
