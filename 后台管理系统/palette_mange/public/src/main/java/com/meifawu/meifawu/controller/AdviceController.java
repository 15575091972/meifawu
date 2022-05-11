package com.meifawu.meifawu.controller;


import com.meifawu.meifawu.entity.Admins;
import com.meifawu.meifawu.entity.Advice;
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

    @GetMapping("deleteAdviceById/{adviceId}")
    public Result deleteAdviceById(@PathVariable String adviceId){
        if(iAdviceService.removeById(adviceId) == true){
            return Result.ok(adviceId);
        }
        else {
            return Result.error("删除失败");
        }
    }
}

