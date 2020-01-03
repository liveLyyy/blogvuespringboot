package com.live.blogvuespringboot.service.Impl;

import com.live.blogvuespringboot.config.JwtConfig;
import com.live.blogvuespringboot.mapper.RoleMapper;
import com.live.blogvuespringboot.mapper.UserMapper;
import com.live.blogvuespringboot.model.pojo.Login;
import com.live.blogvuespringboot.model.pojo.Role;
import com.live.blogvuespringboot.model.pojo.User;
import com.live.blogvuespringboot.service.UserService;
import com.live.blogvuespringboot.utils.DateUtil;
import com.live.blogvuespringboot.utils.JwtUtils;
import com.live.blogvuespringboot.utils.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtConfig jwtConfig;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private LoginServiceImpl loginService;
    @Autowired
    private HttpServletRequest request;

    @Override
    public Map login(User user) {
        Map<String, Object> map = new HashMap<>();
        try {
            UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(user.getName(), user.getPassword());
            final Authentication authentication = authenticationManager.authenticate(upToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            final UserDetails userDetails = this.loadUserByUsername(user.getName());
            final String token = jwtUtils.generateToken(userDetails);
            Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
            List<String> roles = new ArrayList<>();
            for (GrantedAuthority authority : authorities) {
                roles.add(authority.getAuthority());
            }
            map.put("token", jwtConfig.getPrefix() + token);
            map.put("name", user.getName());
            map.put("roles", roles);
            return map;
        } catch (AuthenticationException e) {
            e.printStackTrace();
            //认证失败，不返回token
            return null;
        }

    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userMapper.findUserByName(s);
        if (user == null) {
            ///查询不到用户时，判定这是个非法token，但是仍然返回 不抛异常
            return new org.springframework.security.core.userdetails.User("NORMAL", "NORMAL", null);
        }
        List<SimpleGrantedAuthority> list = new ArrayList<>();
        //用于添加用户的权限。将用户权限添加到authorities
        if (user.getStatus() == 1) {
            List<Role> roles = roleMapper.findUserRoles(user.getId());
            for (Role role : roles) {
                list.add(new SimpleGrantedAuthority(role.getName()));
            }
        } else {
            //该用户被禁用
            list.add(new SimpleGrantedAuthority("NORMAL"));
        }
        Login login = loginService.findLoginByUser(user);
        if (login == null) {
            loginService.saveLoginInfo(user);
        }else {
            login.setIp(RequestUtil.getIpAddress(request));
            login.setTime(DateUtil.getCurrentDate());
            loginService.updateLoginInfo(login);
        }
        return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), list);
    }
}
