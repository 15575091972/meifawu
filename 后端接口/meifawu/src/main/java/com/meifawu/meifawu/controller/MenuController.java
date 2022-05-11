package com.meifawu.meifawu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.meifawu.meifawu.entity.BarberState;
import com.meifawu.meifawu.entity.Barbers;
import com.meifawu.meifawu.entity.Menu;
import com.meifawu.meifawu.service.IMenuService;
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
@Api(tags="点餐类")
@CrossOrigin
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

    @PostMapping("deleteMenuById/{menuId}")
    public Result deleteMenuById(@PathVariable int menuId){
        if(iMenuService.removeById(menuId) == true){
            return Result.ok(menuId);
        }
        else {
            return Result.error("删除失败");
        }
    }

    //通过Id更新服务点餐信息
    @PostMapping("updateMenuById")
    public Result updateMenuById(@RequestBody Menu menu){
//        QueryWrapper<Servers> serversQueryWrapper = new QueryWrapper<>();
//        serversQueryWrapper.eq("server_id",server_id);
        iMenuService.updateById(menu);
        if(iMenuService.updateById(menu) == true){
            return Result.ok(menu);
        }
        else {
            return Result.error("更新失败");
        }
    }

    //根据要点餐的用户姓名进行模糊查询
    @GetMapping("getMenuByNameMoHu/{userNameMoHU}")
    public Result getMenuByNameMoHu(@PathVariable String userNameMoHU) {
        QueryWrapper<Menu> menuQueryWrapper = new QueryWrapper<>();
        menuQueryWrapper.like("user_name", userNameMoHU);
        if (iMenuService.count(menuQueryWrapper) != 0) {
            return Result.ok(iMenuService.list(menuQueryWrapper));
        } else {
            return Result.error("查询失败");
        }
    }

    //分页查询
    @GetMapping("getMenuByPage/{pageSize}/{currentPage}")
    public Result getMenuByPage ( @PathVariable int pageSize, @PathVariable int currentPage){
        //字段:查询每页的数量10 第几页
        Page<Menu> page = new Page<>(currentPage, pageSize);
        IPage<Menu> menuPage = iMenuService.page(page, null);
        return Result.ok(menuPage);
    }

}

