package com.meifawu.meifawu.controller;


import com.meifawu.meifawu.entity.Menu;
import com.meifawu.meifawu.entity.Orders;
import com.meifawu.meifawu.service.IMenuService;
import com.meifawu.meifawu.service.IOrdersService;
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
@Api(tags="订单类")
@RestController
public class OrdersController {
    @Autowired
    IOrdersService iOrdersService;
    //查询所有订单信息
    @GetMapping("getAllOrder")
    public Result getAllOrder(){
        return Result.ok(iOrdersService.list());
    }
    //添加订单
    @PostMapping("addOrder")
    public Result addOrder(@RequestBody Orders orders){
        if(iOrdersService.save(orders) == true){
            return Result.ok(orders);
        }
        else {
            return Result.error("添加失败");
        }
    }
    //更新订单信息
    @PostMapping("updateOrder")
    public Result updateOrder(@RequestBody Orders orders){
        if(iOrdersService.saveOrUpdate(orders) == true){
            return Result.ok(orders);
        }
        else {
            return Result.error("更新失败");
        }
    }

    @GetMapping("deleteOrderById/{orderId}")
    public Result deleteOrderById(@PathVariable String orderId){
        if(iOrdersService.removeById(orderId) == true){
            return Result.ok(orderId);
        }
        else {
            return Result.error("删除失败");
        }
    }
}

