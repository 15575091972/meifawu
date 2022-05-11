package com.meifawu.meifawu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.meifawu.meifawu.entity.Admins;
import com.meifawu.meifawu.service.IAdminsService;
import com.meifawu.meifawu.utils.MD5Util;
import com.meifawu.meifawu.utils.Result;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 刘诚可
 * @since 2021-12-20
 */
@Api(tags="管理员类")
@CrossOrigin
@RestController
public class AdminsController {
    @Autowired
    IAdminsService iAdminsService;
    //查询所有管理员
    @GetMapping("getAllAdmin")
    public Result getAllAdmin(){
        return Result.ok(iAdminsService.list());
    }


    //添加管理员
    @PostMapping("addAdmin")
    public Result addAdmin(@RequestBody Admins admins){
        //将密码进行MD5加密
        admins.setAdminPassword(MD5Util.MD5JiaMi(admins.getAdminPassword()));
        if(iAdminsService.save(admins) == true){
            return Result.ok(admins);
        }
        else {
            return Result.error("添加失败");
        }
    }
    //更新管理员信息
    @PostMapping("updateAdmin")
    public Result updateAdmin(@RequestBody Admins admins){
        if(iAdminsService.saveOrUpdate(admins) == true){
            return Result.ok(admins);
        }
        else {
            return Result.error("更新失败");
        }
    }
    //管理员账户登录接口（验证用户名和密码是否正确）
    @PostMapping("validateStatus/{admin_name}/{admin_password}")
    public Result validateStatus( @PathVariable String admin_name,@PathVariable String admin_password){
        //将前端的密码进行MD5加密
        String password_jiami= MD5Util.MD5JiaMi(admin_password);
        System.out.println(admin_name);
        System.out.println(admin_password);
        QueryWrapper<Admins> adminsQueryWrapper =new QueryWrapper<>();
        adminsQueryWrapper.eq("admin_name",admin_name).eq("admin_password",password_jiami);
        //打印查询到的返回值
        System.out.println("查询到"+iAdminsService.count(adminsQueryWrapper)+"条数据");
        if(iAdminsService.count(adminsQueryWrapper)!=0){
            System.out.println(admin_name);
            System.out.println(password_jiami);
            System.out.println("登录成功！");
            return Result.ok(admin_name);
            }
        else{
            System.out.println("用户名或密码错误！");
            return Result.error(-1,"用户名或密码错误！");
        }
    }

    @PostMapping("deleteAdminById/{adminId}")
    public Result deleteAdminById(@PathVariable int adminId){
        if(iAdminsService.removeById(adminId) == true){
            return Result.ok(adminId);
        }
        else {
            return Result.error("删除失败");
        }
    }

}

