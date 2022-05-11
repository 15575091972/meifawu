package com.meifawu.meifawu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.meifawu.meifawu.entity.Orders;
import com.meifawu.meifawu.entity.Pricelist;
import com.meifawu.meifawu.entity.Projects;
import com.meifawu.meifawu.service.IOrdersService;
import com.meifawu.meifawu.service.IPricelistService;
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
@Api(tags="价目表类")
@CrossOrigin
@RestController
public class PricelistController {
    @Autowired
    IPricelistService iPricelistService;
    //查询所有价目表信息
    @GetMapping("getAllPricelist")
    public Result getAllPricelist(){
        return Result.ok(iPricelistService.list());
    }

    //根据服务项目和理发师等级得出订单金额
    @GetMapping("getPriceByBarberLevelAndProject/{barberLevel}/{project}")
    public Result getPriceByBarberLevelAndProject(@PathVariable String barberLevel,@PathVariable String project){
        QueryWrapper<Pricelist> pricelistQueryWrapper = new QueryWrapper<>();
        pricelistQueryWrapper.select("pricelist_price").eq("pricelist_level",barberLevel).eq("pricelist_way",project);
        System.out.println(iPricelistService.count(pricelistQueryWrapper));
        //打印查询到的返回值
        System.out.println("查询到"+iPricelistService.list(pricelistQueryWrapper)+"条数据");
        if(iPricelistService.count(pricelistQueryWrapper)!=0){
            System.out.println("查找到数据！");
            return Result.ok(iPricelistService.listObjs(pricelistQueryWrapper));
        }else{
            return Result.error("未查找到数据！");
        }
    }
    //添加价目表
    @PostMapping("addPricelist")
    public Result addPricelist(@RequestBody Pricelist pricelist){
        if(iPricelistService.save(pricelist) == true){
            return Result.ok(pricelist);
        }
        else {
            return Result.error("添加失败");
        }
    }
    //更新价目表信息
    @PostMapping("updatePricelist")
    public Result updatePricelist(@RequestBody Pricelist pricelist){
        if(iPricelistService.saveOrUpdate(pricelist) == true){
            return Result.ok(pricelist);
        }
        else {
            return Result.error("更新失败");
        }
    }

    @PostMapping("deletePricelistById/{pricelistId}")
    public Result deletePricelistById(@PathVariable int pricelistId){
        if(iPricelistService.removeById(pricelistId) == true){
            return Result.ok(pricelistId);
        }
        else {
            return Result.error("删除失败");
        }
    }

    //通过Id更新价目表信息
    @PostMapping("updatePricelistById")
    public Result updatePricelistById(@RequestBody Pricelist pricelist){
//        QueryWrapper<Servers> serversQueryWrapper = new QueryWrapper<>();
//        serversQueryWrapper.eq("server_id",server_id);
        iPricelistService.updateById(pricelist);
        if(iPricelistService.updateById(pricelist) == true){
            return Result.ok(pricelist);
        }
        else {
            return Result.error("更新失败");
        }
    }

    @GetMapping("getPricelistByPage/{pageSize}/{currentPage}")
    public Result getPricelistByPage ( @PathVariable int pageSize, @PathVariable int currentPage){
        //字段:查询每页的数量10 第几页
        Page<Pricelist> page = new Page<>(currentPage, pageSize);
        IPage<Pricelist> pricelistPage = iPricelistService.page(page, null);
        return Result.ok(pricelistPage);
    }

}

