package com.meifawu.meifawu.service.impl;

import com.meifawu.meifawu.dao.UserMapper;
import com.meifawu.meifawu.entity.User;
import com.meifawu.meifawu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    @Override
    public List<User> findAllUser() {
        return userMapper.findAllUser();
    }

    @Override
    public int addUser(User user) {
        return userMapper.addUser(user);
    }

//    @Override
//    public User validateStatus(String user_id, String password) {
//        return userMapper.validateStatus(user_id,password);
//    }
//
//    @Override
//    public User findUserById(String user_id) {
//        return userMapper.findUserById(user_id);
//    }


}
