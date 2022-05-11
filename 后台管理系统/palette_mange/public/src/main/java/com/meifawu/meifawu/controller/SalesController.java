package com.meifawu.meifawu.controller;


import com.meifawu.meifawu.entity.Pricelist;
import com.meifawu.meifawu.entity.Sales;
import com.meifawu.meifawu.service.IPricelistService;
import com.meifawu.meifawu.service.ISalesService;
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
@Api(tags="销售类")
@RestController
public class SalesController {
    @Autowired
    ISalesService iSalesService;
    //查询所有销售信息
    @GetMapping("getAllSale")
    public Result getAllSale(){
        return Result.ok(iSalesService.list());
    }
    //添加销售
    @PostMapping("addSale")
    public Result addSale(@RequestBody Sales sales){
        if(iSalesService.save(sales) == true){
            return Result.ok(sales);
        }
        else {
            return Result.error("添加失败");
        }
    }
    //更新销售信息
    @PostMapping("updateSale")
    public Result updateSale(@RequestBody Sales sales){
        if(iSalesService.saveOrUpdate(sales) == true){
            return Result.ok(sales);
        }
        else {
            return Result.error("更新失败");
        }
    }

    @GetMapping("deleteSaleById/{saleId}")
    public Result deleteSaleById(@PathVariable String saleId){
        if(iSalesService.removeById(saleId) == true){
            return Result.ok(saleId);
        }
        else {
            return Result.error("删除失败");
        }
    }
}

