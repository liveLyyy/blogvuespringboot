package com.live.blogvuespringboot.service;

import com.live.blogvuespringboot.model.pojo.User;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Map;

public interface UserService {
  Map login(User user);
}
