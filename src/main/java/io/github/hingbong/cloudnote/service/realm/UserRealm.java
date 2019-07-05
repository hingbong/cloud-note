package io.github.hingbong.cloudnote.service.realm;

import io.github.hingbong.cloudnote.configuration.ShiroConfiguration;
import io.github.hingbong.cloudnote.entity.User;
import io.github.hingbong.cloudnote.mapper.UserMapper;
import io.github.hingbong.cloudnote.service.excption.UserNotFoundException;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * for user login
 *
 * @author hingbong
 */
@Service
public class UserRealm extends AuthorizingRealm {

  private UserMapper userMapper;

  @Override
  public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
    HashedCredentialsMatcher cMatcher = new HashedCredentialsMatcher();
    // set hash algorithm
    cMatcher.setHashAlgorithmName("SHA-256");
    // set encrypt times
    cMatcher.setHashIterations(3);
    super.setCredentialsMatcher(cMatcher);
  }

  /**
   * only anon and user, so don't need this
   *
   * @param principals principals
   * @return authorization info
   */
  @Override
  protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
    return null;
  }

  /**
   * @param token have authentication info
   * @return authentication info
   * @throws AuthenticationException if can't authenticate
   */
  @Override
  protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
      throws AuthenticationException {
    UsernamePasswordToken userToken = (UsernamePasswordToken) token;
    String username = userToken.getUsername();
    User user = userMapper.findUserByUsername(username);
    if (user == null) {
      throw new UserNotFoundException();
    }
    Session session = SecurityUtils.getSubject().getSession();
    session.setTimeout(ShiroConfiguration.SECONDS_OF_DAY * 30 * 1000L);
    session.setAttribute("username", user.getUsername());
    session.setAttribute("uid", user.getUid());
    return new SimpleAuthenticationInfo(
        user, user.getPassword(), ByteSource.Util.bytes(user.getSalt()), getClass().getName());
  }

  @Autowired
  private UserRealm setUserMapper(UserMapper userMapper) {
    this.userMapper = userMapper;
    return this;
  }
}
