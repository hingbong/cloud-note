package io.github.hingbong.cloudnote.mapper;

import io.github.hingbong.cloudnote.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * mapper for table t_user
 *
 * @author Hingbong
 */
@Repository
public interface UserMapper {

  /**
   * insert a new user
   *
   * @param user new user
   * @return rows affected
   */
  Integer insert(User user);

  /**
   * find a user by user name
   *
   * @param username username of a user
   * @return user which want to be found
   */
  User findUserByUsername(String username);

  /**
   * find a user by user id
   *
   * @param uid user id
   * @return user which want to be found
   */
  User findByUid(Integer uid);

  /**
   * update password by user id
   *
   * @param uid user id
   * @param password new password
   * @return rows affected
   */
  Integer updatePassword(@Param("uid") Integer uid, @Param("password") String password);
}
