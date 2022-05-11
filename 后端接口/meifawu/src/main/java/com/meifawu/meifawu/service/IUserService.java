package com.meifawu.meifawu.service;

import com.meifawu.meifawu.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 刘诚可
 * @since 2021-12-28
 */
public interface IUserService extends IService<User> {
    public List<User> findAllUser();
    public int addUser(User user);
    public User findUserByNickName(String nick_name);
    public User validateStatus(String nick_name);
    public int updateUserByName(User user);
    public  int deleteUserById(int user_id);
    public  int updateUserBalanceByNickName(User user);
}
