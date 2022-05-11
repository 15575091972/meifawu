package com.meifawu.meifawu.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.meifawu.meifawu.entity.Pricelist;
import com.meifawu.meifawu.entity.Publicity;
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
@CrossOrigin
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

    @PostMapping("deleteSaleById/{saleId}")
    public Result deleteSaleById(@PathVariable int saleId){
        if(iSalesService.removeById(saleId) == true){
            return Result.ok(saleId);
        }
        else {
            return Result.error("删除失败");
        }
    }

    //通过Id更新销售信息
    @PostMapping("updateSaleById")
    public Result updateSaleById(@RequestBody Sales sales){
//        QueryWrapper<Servers> serversQueryWrapper = new QueryWrapper<>();
//        serversQueryWrapper.eq("server_id",server_id);
        iSalesService.updateById(sales);
        if(iSalesService.updateById(sales) == true){
            return Result.ok(sales);
        }
        else {
            return Result.error("更新失败");
        }
    }

    //分页查询
    @GetMapping("getSaleByPage/{pageSize}/{currentPage}")
    public Result getSaleByPage ( @PathVariable int pageSize, @PathVariable int currentPage){
        //字段:查询每页的数量10 第几页
        Page<Sales> page = new Page<>(currentPage, pageSize);
        IPage<Sales> salesPage = iSalesService.page(page, null);
        return Result.ok(salesPage);
    }

}

