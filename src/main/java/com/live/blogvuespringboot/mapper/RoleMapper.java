package com.live.blogvuespringboot.mapper;

import com.live.blogvuespringboot.model.pojo.Role;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface RoleMapper {
    /**
     * 根据用户id查询角色
     * @param userId
     * @return
     */
    List<Role> findUserRoles(Long userId);
}
