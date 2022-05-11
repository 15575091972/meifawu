package com.meifawu.meifawu.dao;

import com.meifawu.meifawu.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface UserMapper {
    //查询所有用户，返回一个列表
    public List<User> findAllUser();
    //添加用户
    public int addUser(User user);
//    //验证用户名和密码,返回一个对象
//    public User validateStatus(String user_id,String password);
//    //查询单个用户信息
//    public User findUserById(String user_id);
}
