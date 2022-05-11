package com.meifawu.meifawu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.meifawu.meifawu.entity.Investrecord;
import com.meifawu.meifawu.entity.Menu;
import com.meifawu.meifawu.service.IInvestrecordService;
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
 * @author 孟子弘
 * @since 2021-12-28
 */
@Api(tags="充值记录类")
@RestController
@CrossOrigin
public class InvestrecordController {

    @Autowired
    IInvestrecordService iInvestrecordService;
    //查询所有充值记录信息
    @GetMapping("getAllInvestrecord")
    public Result getAllInvestrecord(){
        return Result.ok(iInvestrecordService.list());
    }
    //添加充值记录信息
    @PostMapping("addInvestrecord")
    public Result addInvestrecord(@RequestBody Investrecord investrecord){
        if(iInvestrecordService.save(investrecord) == true){
            return Result.ok(investrecord);
        }
        else {
            return Result.error("添加失败");
        }
    }
    //更新充值记录信息
    @PostMapping("updateInvestrecord")
    public Result updateInvestrecord(@RequestBody Investrecord investrecord){
        if(iInvestrecordService.saveOrUpdate(investrecord) == true){
            return Result.ok(investrecord);
        }
        else {
            return Result.error("更新失败");
        }
    }

    @PostMapping("deleteInvestrecordById/{investId}")
    public Result deleteMenuById(@PathVariable int investId){
        if(iInvestrecordService.removeById(investId) == true){
            return Result.ok(investId);
        }
        else {
            return Result.error("删除失败");
        }
    }

    //通过Id更新充值记录信息
    @PostMapping("updateInvestrecordById")
    public Result updateInvestrecordById(@RequestBody Investrecord investrecord){
//        QueryWrapper<Servers> serversQueryWrapper = new QueryWrapper<>();
//        serversQueryWrapper.eq("server_id",server_id);
        iInvestrecordService.updateById(investrecord);
        if(iInvestrecordService.updateById(investrecord) == true){
            return Result.ok(investrecord);
        }
        else {
            return Result.error("更新失败");
        }
    }

    //根据充值记录的用户名信息进行模糊查询
    @GetMapping("getInvestrecordByNameMoHu/{nickNameMoHu}")
    public Result getInvestrecordByNameMoHu(@PathVariable String nickNameMoHu) {
        QueryWrapper<Investrecord> investrecordQueryWrapper = new QueryWrapper<>();
        investrecordQueryWrapper.like("nick_name", nickNameMoHu);
        if (iInvestrecordService.count(investrecordQueryWrapper) != 0) {
            return Result.ok(iInvestrecordService.list(investrecordQueryWrapper));
        } else {
            return Result.error("查询失败");
        }
    }

    //分页查询
    @GetMapping("getInvestrecordByPage/{pageSize}/{currentPage}")
    public Result getInvestrecordByPage ( @PathVariable int pageSize, @PathVariable int currentPage){
        //字段:查询每页的数量10 第几页
        Page<Investrecord> page = new Page<>(currentPage, pageSize);
        IPage<Investrecord> investrecordPage = iInvestrecordService.page(page, null);
        return Result.ok(investrecordPage);
    }

}

