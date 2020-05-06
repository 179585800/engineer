/*
package com.wxw.engineer.config.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userServiceDetailImpl")
@Log4j2
public class UserServiceDetailImpl implements UserDetailsService
{

    private static final String ZERO = "0";
    @Autowired
    private UserDao userDao;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        User userDetails = userDao.findByAccount(username);

        if (userDetails == null)
        {
            throw new UsernameNotFoundException(username);
        }

//        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        // 可用性 :true:可用 false:不可用
        boolean enabled = true;
        // 过期性 :true:没过期 false:过期
        boolean accountNonExpired = true;
        // 有效性 :true:凭证有效 false:凭证无效
        boolean credentialsNonExpired = true;
        // 锁定性 :true:未锁定 false:已锁定
        boolean accountNonLocked = true;
//        for (Role role : userDetails.getRoles()) {
//            //角色必须是ROLE_开头，可以在数据库中设置
//            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getRoleName());
//            grantedAuthorities.add(grantedAuthority);
//            //获取权限
//            for (Permission permission : role.getPermissions()) {
//                GrantedAuthority authority = new SimpleGrantedAuthority(permission.getUri());
//                grantedAuthorities.add(authority);
//            }
//        }
//        User user = new User(member.getMemberName(), member.getPassword(),
//                enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, grantedAuthorities);
        return userDetails;

    }


}
*/
