package com.meifawu.meifawu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.meifawu.meifawu.entity.Menu;
import com.meifawu.meifawu.entity.Orders;
import com.meifawu.meifawu.entity.Publicity;
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
@CrossOrigin
@RestController
public class OrdersController {
    @Autowired
    IOrdersService iOrdersService;
    //查询所有订单信息
    @GetMapping("getAllOrder")
    public Result getAllOrder(){
        return Result.ok(iOrdersService.list());
    }

    //根据ID查询订单信息
    @GetMapping("getOrderById/{orderId}")
    public Result getOrderById(@PathVariable int orderId){
        QueryWrapper<Orders> ordersQueryWrapper = new QueryWrapper<>();
        ordersQueryWrapper.eq("order_id",orderId);
        if(iOrdersService.count(ordersQueryWrapper) != 0){
            return Result.ok(iOrdersService.list(ordersQueryWrapper));
        }
        else {
            return Result.error("查询失败");
        }
    }
    //根据昵称查询订单信息
    @GetMapping("getOrderByNickName/{nick_name}")
    public Result getOrderByNickName(@PathVariable String nick_name){
        QueryWrapper<Orders> ordersQueryWrapper = new QueryWrapper<>();
        ordersQueryWrapper.eq("nick_name",nick_name);
        if(iOrdersService.count(ordersQueryWrapper) != 0){
            return Result.ok(iOrdersService.list(ordersQueryWrapper));
        }
        else {
            return Result.error("查询失败");
        }
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

    @PostMapping("deleteOrderById/{orderId}")
    public Result deleteOrderById(@PathVariable int orderId){
        if(iOrdersService.removeById(orderId) == true){
            return Result.ok(orderId);
        }
        else {
            return Result.error("删除失败");
        }
    }

    //通过Id更新订单信息
    @PostMapping("updateOrderById")
    public Result updateOrderById(@RequestBody Orders orders){
//        QueryWrapper<Servers> serversQueryWrapper = new QueryWrapper<>();
//        serversQueryWrapper.eq("server_id",server_id);
        iOrdersService.updateById(orders);
        if(iOrdersService.updateById(orders) == true){
            return Result.ok(orders);
        }
        else {
            return Result.error("更新失败");
        }
    }

    //根据昵称进行模糊查询
    @GetMapping("getOrderByNickNameMoHu/{nickNameMoHU}")
    public Result getOrderByNickNameMoHu(@PathVariable String nickNameMoHU) {
        QueryWrapper<Orders> ordersQueryWrapper = new QueryWrapper<>();
        ordersQueryWrapper.like("nick_name", nickNameMoHU);
        if (iOrdersService.count(ordersQueryWrapper) != 0) {
            return Result.ok(iOrdersService.list(ordersQueryWrapper));
        } else {
            return Result.error("查询失败");
        }
    }

    //分页查询
    @GetMapping("getOrderByPage/{pageSize}/{currentPage}")
    public Result getOrderByPage ( @PathVariable int pageSize, @PathVariable int currentPage){
        //字段:查询每页的数量10 第几页
        Page<Orders> page = new Page<>(currentPage, pageSize);
        IPage<Orders> ordersPage = iOrdersService.page(page, null);
        return Result.ok(ordersPage);
    }

}

