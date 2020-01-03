package com.live.blogvuespringboot.service.Impl;

import com.live.blogvuespringboot.mapper.LoginMapper;
import com.live.blogvuespringboot.model.pojo.Login;
import com.live.blogvuespringboot.model.pojo.User;
import com.live.blogvuespringboot.service.LoginService;
import com.live.blogvuespringboot.utils.DateUtil;
import com.live.blogvuespringboot.utils.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    LoginMapper loginMapper;
    @Autowired
    HttpServletRequest request;

    @Override
    public Login findLoginByUser(User user) {
        return loginMapper.findLoginByUser(user);
    }

    @Override
    public void updateLoginInfo(Login login) {
        loginMapper.updateLogin(login);
    }

    /**
     * 保存登录信息
     *
     * @param user
     */
    @Transactional
    public void saveLoginInfo(User user) {
        Login login = new Login();
        login.setUser(user);//绑定用户
        login.setIp(RequestUtil.getIpAddress(request));//获取操作ip
        login.setTime(DateUtil.getCurrentDate());//操作时间
        loginMapper.saveLogin(login);
    }
}
