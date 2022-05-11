package com.meifawu.meifawu.controller;
import com.meifawu.meifawu.entity.User;
import com.meifawu.meifawu.service.UserService;
import com.meifawu.meifawu.utils.MD5Util;
import com.meifawu.meifawu.utils.Result;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags="用户类")
@RestController
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("findAllUser")
    public Result findAllUser(){
        System.out.println("testrwe");
        return Result.ok(userService.findAllUser());
    }
//    @PostMapping("addUser")
//    public Result addUser(@RequestBody User user){
//        //将密码进行MD5加密
//        user.setPassword(MD5Util.MD5JiaMi(user.getPassword()));
//        if(userService.addUser(user)>0){
//            System.out.println(user.getUser_id());
//            System.out.println(user.getUser_name());
//            System.out.println(user.getPassword());
//            System.out.println(user.getUser_category());
//            return Result.ok(user);
//        }
//        else return Result.error(-1,"增加失败！");
//    }


    @PostMapping("addUser")
    public Result addUser(@RequestBody  User user){
        if(userService.addUser(user)>0){
            return Result.ok(user);
        }
        else return Result.error(-1,"增加失败！");
    }
//    @PostMapping("validateStatus/{user_id}/{password}")
//    public Result validateStatus( @PathVariable String user_id,@PathVariable String password){
//        //将前端的密码进行MD5加密
//        String password_jiami=MD5Util.MD5JiaMi(password);
//        if(userService.validateStatus(user_id,password_jiami)!=null){
//            System.out.println(user_id);
//            System.out.println(password_jiami);
//            System.out.println("登录成功！");
//            return Result.ok(userService.validateStatus(user_id,password_jiami));
//        }
//        else{
//            System.out.println("用户名或密码错误！");
//            return Result.error(-1,"用户名或密码错误！");
//        }
//    }

//    @GetMapping("findUserById/{user_id}")
//    public Result findUserById(@PathVariable String user_id){
//        if(userService.findUserById(user_id)!=null){
//            return Result.ok(userService.findUserById(user_id));
//        }
//       else {
//          return  Result.error(-1,"查询错误！");
//        }
//    }
}
