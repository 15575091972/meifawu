package com.meifawu.meifawu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.meifawu.meifawu.entity.Orders;
import com.meifawu.meifawu.entity.Store;
import com.meifawu.meifawu.entity.User;
import com.meifawu.meifawu.service.ITimeslotcountService;
import com.meifawu.meifawu.service.IUserService;
import com.meifawu.meifawu.utils.Result;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 刘诚可
 * @since 2021-12-28
 */
@Api(tags="用户类")
@RestController
@CrossOrigin
public class UserController {
    @Autowired
    IUserService iUserService;

    @GetMapping("findAllUser")
    public Result findAllUser(){
        return Result.ok(iUserService.findAllUser());
    }

    @PostMapping("deleteUserById/{user_id}")
    public Result deleteUserById(@PathVariable int user_id){
        if(iUserService.deleteUserById(user_id) >0){
            return Result.ok(user_id);
        }
        else {
            return Result.error("删除失败");
        }
    }

    @PostMapping("addUser")
    public Result addUser(@RequestBody  User user){
        if(iUserService.addUser(user)>0){
            return Result.ok(user);
        }
        else return Result.error(-1,"增加失败！");
    }

    @GetMapping("findUserByNickName/{nick_name}")
    public Result findUserByNickName(@PathVariable String nick_name){
        if(iUserService.findUserByNickName(nick_name)!=null){
            return Result.ok(iUserService.findUserByNickName(nick_name));
        }
        else return Result.error(-1,"查询失败！");
    }

    //根据昵称更新用户余额
    @PostMapping("updateUserBalanceByNickName")
    public Result updateUserBalanceByNickName(@RequestBody  User user){
        if(iUserService.updateUserBalanceByNickName(user)>0){
            return Result.ok(user);
        }else {
            return Result.error(-1,"更新失败！");
        }
    }

    @GetMapping("validateStatus/{nick_name}")
    public Result validateStatus( @PathVariable String nick_name){
        if(iUserService.validateStatus(nick_name)!=null){
            System.out.println(nick_name);
            System.out.println("登录成功！");
            return Result.ok(iUserService.validateStatus(nick_name));
        }
        else{
            System.out.println("登录失败！");
            return Result.error(-1,"登录失败！");
        }
    }
    //根据昵称更新用户状态
    @PostMapping("updateUserByName")
    public Result updateUserByName(@RequestBody  User user){
        if(iUserService.updateUserByName(user)>0){
            return Result.ok(user);
        }else {
            return Result.error(-1,"更新失败！");
        }
    }

    //分页查询
    @GetMapping("getUserByPage/{pageSize}/{currentPage}")
    public Result getUserByPage ( @PathVariable int pageSize, @PathVariable int currentPage){
        //字段:查询每页的数量10 第几页
        Page<User> page = new Page<>(currentPage, pageSize);
        IPage<User> userPage = iUserService.page(page, null);
        return Result.ok(userPage);
    }
}

