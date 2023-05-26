package com.fw.springbootmall.service.impl;

import com.fw.springbootmall.dao.UserDao;
import com.fw.springbootmall.dto.UserRegisterRequest;
import com.fw.springbootmall.model.User;
import com.fw.springbootmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User getUserById(Integer userId) {
        return userDao.getUserById(userId);
    }

    @Override
    public Integer register(UserRegisterRequest userRegisterRequest) {

        return userDao.createUser(userRegisterRequest);

    }
}
