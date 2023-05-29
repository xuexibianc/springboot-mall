package com.fw.springbootmall.service;

import com.fw.springbootmall.dto.UserLoginRequest;
import com.fw.springbootmall.dto.UserRegisterRequest;
import com.fw.springbootmall.model.User;

public interface UserService {

    User getUserById(Integer userId);

    Integer register(UserRegisterRequest userRegisterRequest);

    User login(UserLoginRequest userLoginRequest);

}


