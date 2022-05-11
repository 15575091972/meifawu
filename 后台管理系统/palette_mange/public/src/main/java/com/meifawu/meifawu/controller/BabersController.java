package com.meifawu.meifawu.controller;
import com.meifawu.meifawu.entity.Babers;
import com.meifawu.meifawu.service.IBarbersService;
import com.meifawu.meifawu.utils.Result;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 刘诚可
 * @since 2021-12-12
 */
@Api(tags="理发师类")
@RestController
public class BabersController {
    @Autowired
    IBarbersService iBabersService;
    //查询所有理发师
    @GetMapping("getAllBarber")
    public Result getAllBarber(){
        return Result.ok(iBabersService.list());
    }
    //添加理发师
    @PostMapping("addBaber")
    public Result addStudent(@RequestBody Babers babers){
        if(iBabersService.save(babers) == true){
            return Result.ok(babers);
        }
        else {
            return Result.error("添加失败");
        }
    }
    //更新理发师信息
    @PostMapping("updateBaber")
    public Result updateBaber(@RequestBody Babers babers){
        if(iBabersService.saveOrUpdate(babers) == true){
            return Result.ok(babers);
        }
        else {
            return Result.error("更新失败");
        }
    }

    @GetMapping("deleteBaberById/{barberId}")
    public Result deleteBaberById(@PathVariable String barberId){
        if(iBabersService.removeById(barberId) == true){
            return Result.ok(barberId);
        }
        else {
            return Result.error("删除失败");
        }
    }
}

