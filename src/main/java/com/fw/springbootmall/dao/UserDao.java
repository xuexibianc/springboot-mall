package com.fw.springbootmall.dao;

import com.fw.springbootmall.dto.UserRegisterRequest;
import com.fw.springbootmall.model.User;

public interface UserDao {

    User getUserById(Integer userId);

    Integer createUser(UserRegisterRequest userRegisterRequest);

}
