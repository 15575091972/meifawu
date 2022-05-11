package com.meifawu.meifawu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.meifawu.meifawu.entity.Advice;
import com.meifawu.meifawu.entity.Assess;
import com.meifawu.meifawu.service.IAdviceService;
import com.meifawu.meifawu.service.IAssessService;
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
 * @since 2021-12-30
 */
@Api(tags="评价类")
@RestController
@CrossOrigin
public class AssessController {
    @Autowired
    IAssessService iAssessService;
    //查询所有投诉建议
    @GetMapping("getAllAssess")
    public Result getAllAssess(){
        return Result.ok(iAssessService.list());
    }
    //添加投诉建议
    @PostMapping("addAssess")
    public Result addAssess(@RequestBody Assess assess){
        if(iAssessService.save(assess) == true){
            return Result.ok(assess);
        }
        else {
            return Result.error("添加失败");
        }
    }
    //更新投诉建议信息
    @PostMapping("updateAssess")
    public Result updateAssess(@RequestBody Assess assess){
        if(iAssessService.saveOrUpdate(assess) == true){
            return Result.ok(assess);
        }
        else {
            return Result.error("更新失败");
        }
    }

    @PostMapping("deleteAssessById/{assessId}")
    public Result deleteAssessById(@PathVariable int assessId){
        if(iAssessService.removeById(assessId) == true){
            return Result.ok(assessId);
        }
        else {
            return Result.error("删除失败");
        }
    }

    //通过Id更新意见类信息
    @PostMapping("updateAssessById")
    public Result updateAssessById(@RequestBody Assess assess){
//        QueryWrapper<Servers> serversQueryWrapper = new QueryWrapper<>();
//        serversQueryWrapper.eq("server_id",server_id);
        iAssessService.updateById(assess);
        if(iAssessService.updateById(assess) == true){
            return Result.ok(assess);
        }
        else {
            return Result.error("更新失败");
        }
    }

    //根据昵称进行模糊查询
    @GetMapping("getAssessByNickNameMoHu/{nickNameMoHU}")
    public Result getAssessByNickNameMoHu(@PathVariable String nickNameMoHU){
        QueryWrapper<Assess> assessQueryWrapper = new QueryWrapper<>();
        assessQueryWrapper.like("nick_name",nickNameMoHU);
        if(iAssessService.count(assessQueryWrapper) != 0){
            return Result.ok(iAssessService.list(assessQueryWrapper));
        }
        else {
            return Result.error("查询失败");
        }
    }

    //分页查询
    @GetMapping("getAssessByPage/{pageSize}/{currentPage}")
    public Result getAssessByPage ( @PathVariable int pageSize, @PathVariable int currentPage){
        //字段:查询每页的数量10 第几页
        Page<Assess> page = new Page<>(currentPage, pageSize);
        IPage<Assess> assessPage = iAssessService.page(page, null);
        return Result.ok(assessPage);
    }

}

