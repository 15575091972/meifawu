package com.meifawu.meifawu.controller;


import com.meifawu.meifawu.entity.Orders;
import com.meifawu.meifawu.entity.Pricelist;
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
@RestController
public class PricelistController {
    @Autowired
    IPricelistService iPricelistService;
    //查询所有价目表信息
    @GetMapping("getAllPricelist")
    public Result getAllPricelist(){
        return Result.ok(iPricelistService.list());
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

    @GetMapping("deletePricelistById/{pricelistId}")
    public Result deletePricelistById(@PathVariable String pricelistId){
        if(iPricelistService.removeById(pricelistId) == true){
            return Result.ok(pricelistId);
        }
        else {
            return Result.error("删除失败");
        }
    }
}

