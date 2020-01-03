package com.live.blogvuespringboot.mapper;

import com.live.blogvuespringboot.model.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {
    /*
    根据用户名查找
     */
    User findUserByName(String username);
}
