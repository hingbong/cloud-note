package io.github.hingbong.cloudnote;

import io.github.hingbong.cloudnote.entity.User;
import io.github.hingbong.cloudnote.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserMapperTests {

  private UserMapper userMapper;

  @Test
  void insert() {
    User u = new User();
    u.setUsername("奥特曼");
    u.setPassword("1234");
    Integer insert = userMapper.insert(u);
    System.out.println(insert);
  }

  @Test
  void findOne() {
    System.out.println(userMapper.findUserByUsername("奥特曼"));
  }

  @Autowired
  void setUserMapper(UserMapper userMapper) {
    this.userMapper = userMapper;
  }
}
