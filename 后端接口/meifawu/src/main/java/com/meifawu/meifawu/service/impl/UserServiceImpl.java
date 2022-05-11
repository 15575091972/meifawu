package com.meifawu.meifawu.service.impl;

import com.meifawu.meifawu.dao.UserMapper;
import com.meifawu.meifawu.entity.User;
import com.meifawu.meifawu.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 刘诚可
 * @since 2021-12-28
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
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

    @Override
    public User findUserByNickName(String nick_name) {
        return userMapper.findUserByNickName(nick_name);
    }

    @Override
    public User validateStatus(String nick_name) {
        return userMapper.validateStatus(nick_name);
    }

    @Override
    public int updateUserByName(User user) {
        return userMapper.updateUserByName(user);
    }

    @Override
    public int deleteUserById(int user_id) {
        return  userMapper.deleteUserById(user_id);
    }

    @Override
    public int updateUserBalanceByNickName(User user) {
        return userMapper.updateUserBalanceByNickName(user);
    }


}
