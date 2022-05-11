package com.meifawu.meifawu.controller;


import com.meifawu.meifawu.entity.Babers;
import com.meifawu.meifawu.entity.Menu;
import com.meifawu.meifawu.service.IBarbersService;
import com.meifawu.meifawu.service.IMenuService;
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
@Api(tags="点餐类")
@RestController
public class MenuController {
    @Autowired
    IMenuService iMenuService;
    //查询所有点餐信息
    @GetMapping("getAllMenu")
    public Result getAllMenu(){
        return Result.ok(iMenuService.list());
    }
    //添加点餐
    @PostMapping("addMenu")
    public Result addMenu(@RequestBody Menu menu){
        if(iMenuService.save(menu) == true){
            return Result.ok(menu);
        }
        else {
            return Result.error("添加失败");
        }
    }
    //更新点餐信息
    @PostMapping("updateMenu")
    public Result updateMenu(@RequestBody Menu menu){
        if(iMenuService.saveOrUpdate(menu) == true){
            return Result.ok(menu);
        }
        else {
            return Result.error("更新失败");
        }
    }

    @GetMapping("deleteMenuById/{menuId}")
    public Result deleteMenuById(@PathVariable String menuId){
        if(iMenuService.removeById(menuId) == true){
            return Result.ok(menuId);
        }
        else {
            return Result.error("删除失败");
        }
    }
}

