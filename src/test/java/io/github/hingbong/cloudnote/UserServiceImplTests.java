package io.github.hingbong.cloudnote;

import io.github.hingbong.cloudnote.entity.User;
import io.github.hingbong.cloudnote.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceImplTests {

  private UserService service;

  @Test
  void testReg() {
    User u = new User();
    u.setUsername("奥特曼");
    u.setNickname("特慢奥");
    u.setPassword("1234");
    service.register(u);
  }

  @Autowired
  void setService(UserService service) {
    this.service = service;
  }
}
