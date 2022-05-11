package com.meifawu.meifawu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.meifawu.meifawu.entity.Orders;
import com.meifawu.meifawu.entity.Projects;
import com.meifawu.meifawu.entity.Publicity;
import com.meifawu.meifawu.service.IPublicityService;
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
 * @since 2021-12-23
 */
@Api(tags="公示类")
@CrossOrigin
@RestController
public class PublicityController {
    @Autowired
    IPublicityService iPublicityService;
    //查询所有公示信息
    @GetMapping("getAllPublicity")
    public Result getAllPublicity(){
        return Result.ok(iPublicityService.list());
    }
    //添加公示
    @PostMapping("addPublicity")
    public Result addPublicity(@RequestBody Publicity publicity){
        if(iPublicityService.save(publicity) == true){
            return Result.ok(publicity);
        }
        else {
            return Result.error("添加失败");
        }
    }

    @GetMapping("getPublicityById/{publicityId}")
    public Result getPublicityById(@PathVariable int publicityId){
        QueryWrapper<Publicity> publicityQueryWrapper = new QueryWrapper<>();
        publicityQueryWrapper.eq("publicity_id",publicityId);
        if(iPublicityService.count(publicityQueryWrapper) != 0){
            return Result.ok(iPublicityService.list(publicityQueryWrapper));
        }
        else {
            return Result.error("查询失败");
        }
    }

    //更新公示信息
    @PostMapping("updatePublicity")
    public Result updatePublicity(@RequestBody Publicity publicity){
        if(iPublicityService.saveOrUpdate(publicity) == true){
            return Result.ok(publicity);
        }
        else {
            return Result.error("更新失败");
        }
    }

    @PostMapping("deletePublicityById/{publicity_id}")
    public Result deleteProjectById(@PathVariable int publicity_id){
        if(iPublicityService.removeById(publicity_id) == true){
            return Result.ok(publicity_id);
        }
        else {
            return Result.error("删除失败");
        }
    }

    //通过Id更新公示信息
    @PostMapping("updatePublicityById")
    public Result updatePublicityById(@RequestBody Publicity publicity){
//        QueryWrapper<Servers> serversQueryWrapper = new QueryWrapper<>();
//        serversQueryWrapper.eq("server_id",server_id);
        iPublicityService.updateById(publicity);
        if(iPublicityService.updateById(publicity) == true){
            return Result.ok(publicity);
        }
        else {
            return Result.error("更新失败");
        }
    }

    //根据昵称进行模糊查询
    @GetMapping("getPublicityByNameMoHu/{publicityNameMoHU}")
    public Result getPublicityByNameMoHu(@PathVariable String publicityNameMoHU) {
        QueryWrapper<Publicity> publicityQueryWrapper = new QueryWrapper<>();
        publicityQueryWrapper.like("publicity_name", publicityNameMoHU);
        if (iPublicityService.count(publicityQueryWrapper) != 0) {
            return Result.ok(iPublicityService.list(publicityQueryWrapper));
        } else {
            return Result.error("查询失败");
        }
    }

    //分页查询
    @GetMapping("getPublicityByPage/{pageSize}/{currentPage}")
    public Result getPublicityByPage ( @PathVariable int pageSize, @PathVariable int currentPage){
        //字段:查询每页的数量10 第几页
        Page<Publicity> page = new Page<>(currentPage, pageSize);
        IPage<Publicity> publicityPage = iPublicityService.page(page, null);
        return Result.ok(publicityPage);
    }

}

