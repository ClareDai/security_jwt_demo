package cn.claredai.security;

import cn.claredai.mapper.SysMenuMapper;
import cn.claredai.mapper.SysUserMapper;
import cn.claredai.model.JwtUser;
import cn.claredai.model.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author daixiaosong
 * @Date create in 19:19 2019/3/6
 */
@Slf4j
@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    SysUserMapper userMapper;
    @Autowired
    SysMenuMapper menuMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser user = userMapper.selectByLoginName(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户名不存在");
        } else {
            List<String> list = menuMapper.selectMenuByUserId(user.getUserId());
            List<SimpleGrantedAuthority> authorities = null;
            if (!list.isEmpty()) {
                authorities = list.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
            }
            return new JwtUser(user.getLoginName(), user.getPassword(),  authorities);
        }
    }
}
