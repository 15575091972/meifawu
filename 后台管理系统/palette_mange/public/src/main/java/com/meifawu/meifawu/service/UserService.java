package com.meifawu.meifawu.service;

import com.meifawu.meifawu.entity.User;

import java.util.List;

public interface UserService {
    public List<User> findAllUser();
    public int addUser(User user);
//    public User validateStatus(String user_id,String password);
//    public User findUserById(String user_id);
}
