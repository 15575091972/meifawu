package com.meifawu.meifawu.controller;



import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.meifawu.meifawu.entity.Servers;
import com.meifawu.meifawu.entity.Store;
import com.meifawu.meifawu.service.IStoreService;
import com.meifawu.meifawu.utils.Result;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 孟子弘
 * @since 2021-12-28
 */
@Api(tags="门店类")
@RestController
@CrossOrigin
public class StoreController {
    @Autowired
    IStoreService iStoreService;
    //查询所有服务员
    @GetMapping("getAllStore")
    public Result getAllServer(){
        return Result.ok(iStoreService.list());
    }
    //添加服务员
    @PostMapping("addStore")
    public Result addServer(@RequestBody Store store){
        if(iStoreService.save(store) == true){
            return Result.ok(store);
        }
        else {
            return Result.error("添加失败");
        }
    }
    //更新服务员信息
    @PostMapping("updateStore")
    public Result updateStore(@RequestBody Store store){
        if(iStoreService.saveOrUpdate(store) == true){
            return Result.ok(store);
        }
        else {
            return Result.error("更新失败");
        }
    }

    //通过Id更新服务员信息
    @PostMapping("updateStoreById")
    public Result updateStoreById(@RequestBody Store store){
//        QueryWrapper<Servers> serversQueryWrapper = new QueryWrapper<>();
//        serversQueryWrapper.eq("server_id",server_id);
        iStoreService.updateById(store);
        if(iStoreService.updateById(store) == true){
            return Result.ok(store);
        }
        else {
            return Result.error("更新失败");
        }
    }

    @PostMapping("deleteStoreById/{storeId}")
    public Result deleteStoreById(@PathVariable int storeId){
        if(iStoreService.removeById(storeId) == true){
            return Result.ok(storeId);
        }
        else {
            return Result.error("删除失败");
        }
    }
    //分页查询
    @GetMapping("getStoreByPage/{pageSize}/{currentPage}")
    public Result getStoreByPage ( @PathVariable int pageSize, @PathVariable int currentPage){
        //字段:查询每页的数量10 第几页
        Page<Store> page = new Page<>(currentPage, pageSize);
        IPage<Store> storePage = iStoreService.page(page, null);
        return Result.ok(storePage);
    }

}

