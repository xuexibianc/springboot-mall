package com.fw.springbootmall.service.impl;

import com.fw.springbootmall.dao.UserDao;
import com.fw.springbootmall.dto.UserLoginRequest;
import com.fw.springbootmall.dto.UserRegisterRequest;
import com.fw.springbootmall.model.User;
import com.fw.springbootmall.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class UserServiceImpl implements UserService {

    private final static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;

    @Override
    public User getUserById(Integer userId) {
        return userDao.getUserById(userId);
    }

    @Override
    public Integer register(UserRegisterRequest userRegisterRequest) {
        // 检查注册的Email
       User user =  userDao.getUserByEmail(userRegisterRequest.getEmail());

       if (user != null){
           log.warn("该 email {} 已经被注册",userRegisterRequest.getEmail());
           throw  new ResponseStatusException(HttpStatus.BAD_REQUEST);
       }
       //创建账号
        return userDao.createUser(userRegisterRequest); //很贴近要做的事情

    }

    @Override
    public User login(UserLoginRequest userLoginRequest) {

        User user = userDao.getUserByEmail(userLoginRequest.getEmail());

        if (user == null){
            log.warn("该 email {} 尚未注册",userLoginRequest.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        if (user.getPassword().equals(userLoginRequest.getPassword())){
            return user;
        }else {
            log.warn("email {} 的密码不正确",userLoginRequest.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }


}
