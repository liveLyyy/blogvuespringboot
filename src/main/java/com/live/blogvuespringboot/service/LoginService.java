package com.live.blogvuespringboot.service;

import com.live.blogvuespringboot.model.pojo.Login;
import com.live.blogvuespringboot.model.pojo.User;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;


public interface LoginService {
    Login findLoginByUser(User user);

    void updateLoginInfo(Login login);
}
