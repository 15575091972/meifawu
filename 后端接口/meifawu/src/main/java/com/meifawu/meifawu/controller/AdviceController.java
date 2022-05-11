package com.meifawu.meifawu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.meifawu.meifawu.entity.Admins;
import com.meifawu.meifawu.entity.Advice;
import com.meifawu.meifawu.entity.Servers;
import com.meifawu.meifawu.entity.Store;
import com.meifawu.meifawu.service.IAdminsService;
import com.meifawu.meifawu.service.IAdviceService;
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
@Api(tags="投诉建议类")
@CrossOrigin
@RestController
public class AdviceController {
    @Autowired
    IAdviceService iAdviceService;
    //查询所有投诉建议
    @GetMapping("getAllAdvice")
    public Result getAllAdvice(){
        return Result.ok(iAdviceService.list());
    }
    //添加投诉建议
    @PostMapping("addAdvice")
    public Result addAdvice(@RequestBody Advice advice){
        if(iAdviceService.save(advice) == true){
            return Result.ok(advice);
        }
        else {
            return Result.error("添加失败");
        }
    }
    //更新投诉建议信息
    @PostMapping("updateAdvice")
    public Result updateAdvice(@RequestBody Advice advice){
        if(iAdviceService.saveOrUpdate(advice) == true){
            return Result.ok(advice);
        }
        else {
            return Result.error("更新失败");
        }
    }

    @PostMapping("deleteAdviceById/{adviceId}")
    public Result deleteAdviceById(@PathVariable int adviceId){
        if(iAdviceService.removeById(adviceId) == true){
            return Result.ok(adviceId);
        }
        else {
            return Result.error("删除失败");
        }
    }

    //通过Id更新意见类信息
    @PostMapping("updateAdviceById")
    public Result updateAdviceById(@RequestBody Advice advice){
//        QueryWrapper<Servers> serversQueryWrapper = new QueryWrapper<>();
//        serversQueryWrapper.eq("server_id",server_id);
        iAdviceService.updateById(advice);
        if(iAdviceService.updateById(advice) == true){
            return Result.ok(advice);
        }
        else {
            return Result.error("更新失败");
        }
    }

    //根据昵称进行模糊查询
    @GetMapping("getAdviceByNickNameMoHu/{nickNameMoHU}")
    public Result getAdviceByNickNameMoHu(@PathVariable String nickNameMoHU){
        QueryWrapper<Advice> adviceQueryWrapper = new QueryWrapper<>();
        adviceQueryWrapper.like("nick_name",nickNameMoHU);
        if(iAdviceService.count(adviceQueryWrapper) != 0){
            return Result.ok(iAdviceService.list(adviceQueryWrapper));
        }
        else {
            return Result.error("查询失败");
        }
    }

    //分页查询
    @GetMapping("getAdviceByPage/{pageSize}/{currentPage}")
    public Result getAdviceByPage ( @PathVariable int pageSize, @PathVariable int currentPage){
        //字段:查询每页的数量10 第几页
        Page<Advice> page = new Page<>(currentPage, pageSize);
        IPage<Advice> advicePage = iAdviceService.page(page, null);
        return Result.ok(advicePage);
    }

}

