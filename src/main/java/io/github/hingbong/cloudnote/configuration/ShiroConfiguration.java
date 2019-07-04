package io.github.hingbong.cloudnote.configuration;

import io.github.hingbong.cloudnote.service.realm.UserRealm;
import java.util.LinkedHashMap;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
public class ShiroConfiguration {

  @Bean
  public DefaultWebSecurityManager getSecurityManager(@Autowired UserRealm realm) {
    DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
    securityManager.setRealm(realm);
    return securityManager;
  }

  @Bean
  public ShiroFilterFactoryBean getShiroFilterFactoryBean(
      @Autowired SecurityManager securityManager) {
    ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
    bean.setSecurityManager(securityManager);
    bean.setLoginUrl("/login.html");
    LinkedHashMap<String, String> map = new LinkedHashMap<>(18);
    map.put("/css/**", "anon");
    map.put("/images/**", "anon");
    map.put("/js/**", "anon");
    map.put("/index.html", "anon");
    map.put("/login.html", "anon");
    map.put("/register.html", "anon");
    map.put("/shared_note.html", "anon");
    map.put("/favicon.ico", "anon");
    map.put("/user", "anon");
    map.put("/user/session", "anon");
    map.put("/note/notes/shared/all", "anon");
    map.put("/note/shared/note/one/**", "anon");
    map.put("/**", "authc");
    bean.setFilterChainDefinitionMap(map);
    return bean;
  }

  @Bean
  public LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
    return new LifecycleBeanPostProcessor();
  }

  @Bean
  @Lazy
  public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
    return new DefaultAdvisorAutoProxyCreator();
  }

  @Bean
  public AuthorizationAttributeSourceAdvisor getAuthorizationAttributeSourceAdvisor(
      @Autowired SecurityManager securityManager) {
    AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
    advisor.setSecurityManager(securityManager);
    return advisor;
  }
}
