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

@Service
public class UserRealm extends AuthorizingRealm {

  private UserMapper userMapper;

  @Override
  public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
    // 构建凭证匹配对象
    HashedCredentialsMatcher cMatcher = new HashedCredentialsMatcher();
    // 设置加密算法
    cMatcher.setHashAlgorithmName("SHA-256");
    // 设置加密次数
    cMatcher.setHashIterations(3);
    super.setCredentialsMatcher(cMatcher);
  }

  @Override
  protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

    return null;
  }

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
