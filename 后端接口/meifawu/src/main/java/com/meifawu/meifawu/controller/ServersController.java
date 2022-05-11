package com.meifawu.meifawu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.meifawu.meifawu.entity.Orders;
import com.meifawu.meifawu.entity.Sales;
import com.meifawu.meifawu.entity.Servers;
import com.meifawu.meifawu.service.ISalesService;
import com.meifawu.meifawu.service.IServersService;
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
@Api(tags="服务员类")
@CrossOrigin
@RestController
public class ServersController {
    @Autowired
    IServersService iServersService;
    //查询所有服务员
    @GetMapping("getAllServer")
    public Result getAllServer(){
        return Result.ok(iServersService.list());
    }
    //添加服务员
    @PostMapping("addServer")
    public Result addServer(@RequestBody Servers servers){
        if(iServersService.save(servers) == true){
            return Result.ok(servers);
        }
        else {
            return Result.error("添加失败");
        }
    }
    //更新服务员信息
    @PostMapping("updateServer")
    public Result updateServer(@RequestBody Servers servers){
        if(iServersService.saveOrUpdate(servers) == true){
            return Result.ok(servers);
        }
        else {
            return Result.error("更新失败");
        }
    }

    //通过Id更新服务员信息
    @PostMapping("updateServerById")
    public Result updateServerById(@RequestBody Servers servers){
//        QueryWrapper<Servers> serversQueryWrapper = new QueryWrapper<>();
//        serversQueryWrapper.eq("server_id",server_id);
        iServersService.updateById(servers);
        if(iServersService.updateById(servers) == true){
            return Result.ok(servers);
        }
        else {
            return Result.error("更新失败");
        }
    }

    @PostMapping("deleteServerById/{serverId}")
    public Result deleteServerById(@PathVariable int serverId){
        if(iServersService.removeById(serverId) == true){
            return Result.ok(serverId);
        }
        else {
            return Result.error("删除失败");
        }
    }
    //根据服务员姓名进行模糊查询
    @GetMapping("getServerByNameMoHu/{serverNameMoHU}")
    public Result getServerByNameMoHu(@PathVariable String serverNameMoHU){
        QueryWrapper<Servers> serversQueryWrapper = new QueryWrapper<>();
        serversQueryWrapper.like("server_name",serverNameMoHU);
        if(iServersService.count(serversQueryWrapper) != 0){
            return Result.ok(iServersService.list(serversQueryWrapper));
        }
        else {
            return Result.error("查询失败");
        }

    }

    @GetMapping("getServerByPage/{pageSize}/{currentPage}")
        public Result getServerByPage ( @PathVariable int pageSize, @PathVariable int currentPage){
            //字段:查询每页的数量10 第几页
            Page<Servers> page = new Page<>(currentPage, pageSize);
            IPage<Servers> serverPage = iServersService.page(page, null);
            return Result.ok(serverPage);
        }

    }



