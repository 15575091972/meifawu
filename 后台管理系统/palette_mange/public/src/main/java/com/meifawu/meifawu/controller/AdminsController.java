package com.meifawu.meifawu.controller;


import com.meifawu.meifawu.entity.Admins;
import com.meifawu.meifawu.entity.Babers;
import com.meifawu.meifawu.service.IAdminsService;
import com.meifawu.meifawu.service.IBarbersService;
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
 * @since 2021-12-20
 */
@Api(tags="管理员类")
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

    @GetMapping("deleteAdminById/{adminId}")
    public Result deleteAdminById(@PathVariable String adminId){
        if(iAdminsService.removeById(adminId) == true){
            return Result.ok(adminId);
        }
        else {
            return Result.error("删除失败");
        }
    }

}

