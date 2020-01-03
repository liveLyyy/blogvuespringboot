package com.live.blogvuespringboot.mapper;

import com.live.blogvuespringboot.model.pojo.Login;
import com.live.blogvuespringboot.model.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface LoginMapper {
    /*

     */
    Login findLoginByUser(User user);

    /*
        保存登录信息
     */
    void saveLogin(Login login);

    void updateLogin(Login login);
}
