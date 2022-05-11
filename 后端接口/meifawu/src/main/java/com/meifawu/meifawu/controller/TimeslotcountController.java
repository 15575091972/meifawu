package com.meifawu.meifawu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.meifawu.meifawu.entity.Publicity;
import com.meifawu.meifawu.entity.Servers;
import com.meifawu.meifawu.entity.Timeslotcount;
import com.meifawu.meifawu.service.ITimeslotcountService;
import com.meifawu.meifawu.utils.Result;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.time.LocalDate;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 孟子弘
 * @since 2021-12-24
 */
@Api(tags="时间段数量类")
@CrossOrigin
@RestController
public class TimeslotcountController {
    @Autowired
    ITimeslotcountService iTimeslotcountService;

    @GetMapping("getAllTimeslotcount")
    public Result getAllTimeslotcount(){
        return Result.ok(iTimeslotcountService.list());
    }

    //添加时间段数量信息
    @PostMapping("addTimeslotcount")
    public Result addTimeslotcount(@RequestBody Timeslotcount timeslotcount){
        if(iTimeslotcountService.save(timeslotcount) == true){
            return Result.ok(timeslotcount);
        }
        else {
            return Result.error("添加失败");
        }
    }

    @GetMapping("getTimeslotcountByDate/{the_date}")
    public Result getTimeslotcountByDate(@PathVariable LocalDate the_date){
        QueryWrapper<Timeslotcount> timeslotcountQueryWrapper = new QueryWrapper<>();
        timeslotcountQueryWrapper.eq("the_date",the_date);
        if(iTimeslotcountService.count(timeslotcountQueryWrapper) != 0){
            return Result.ok(iTimeslotcountService.list(timeslotcountQueryWrapper));
        }
        else {
            return Result.error("查询失败");
        }
    }

    //更新时间段数量信息
    @PostMapping("updateTimeslotcount")
    public Result updateTimeslotcount(@RequestBody Timeslotcount timeslotcount){
        if(iTimeslotcountService.saveOrUpdate(timeslotcount) == true){
            return Result.ok(timeslotcount);
        }
        else {
            return Result.error("更新失败");
        }
    }

    @PostMapping("deleteTimeslotcountById/{the_date}")
    public Result deleteTimeslotcountById(@PathVariable LocalDate the_date){
        if(iTimeslotcountService.removeById(the_date) == true){
            return Result.ok(the_date);
        }
        else {
            return Result.error("删除失败");
        }
    }

    //通过Id更新公示信息
    @PostMapping("updateTimeslotcountById")
    public Result updateTimeslotcountById(@RequestBody Timeslotcount timeslotcount){
//        QueryWrapper<Servers> serversQueryWrapper = new QueryWrapper<>();
//        serversQueryWrapper.eq("server_id",server_id);
        iTimeslotcountService.updateById(timeslotcount);
        if(iTimeslotcountService.updateById(timeslotcount) == true){
            return Result.ok(timeslotcount);
        }
        else {
            return Result.error("更新失败");
        }
    }

    @GetMapping("getTimeslotcountByPage/{pageSize}/{currentPage}")
    public Result getTimeslotcountByPage ( @PathVariable int pageSize, @PathVariable int currentPage){
        //字段:查询每页的数量10 第几页
        Page<Timeslotcount> page = new Page<>(currentPage, pageSize);
        IPage<Timeslotcount> timeslotcountPage = iTimeslotcountService.page(page, null);
        return Result.ok(timeslotcountPage);
    }

}

