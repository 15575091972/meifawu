package com.meifawu.meifawu.controller;


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

    @GetMapping("deleteServerById/{serverId}")
    public Result deleteServerById(@PathVariable String serverId){
        if(iServersService.removeById(serverId) == true){
            return Result.ok(serverId);
        }
        else {
            return Result.error("删除失败");
        }
    }
}

