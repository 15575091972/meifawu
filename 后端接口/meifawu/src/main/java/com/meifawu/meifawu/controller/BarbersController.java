package com.meifawu.meifawu.controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.meifawu.meifawu.entity.Admins;
import com.meifawu.meifawu.entity.Advice;
import com.meifawu.meifawu.entity.Barbers;
import com.meifawu.meifawu.entity.Publicity;
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
@CrossOrigin
@RestController
public class BarbersController {
    @Autowired
    IBarbersService iBabersService;

    //查询所有理发师
    @GetMapping("getAllBarber")
    public Result getAllBarber() {
        return Result.ok(iBabersService.list());
    }
    //根据理发师ID查询理发师的信息
    @GetMapping("getBarberById/{barberId}")
    public Result getBarberById(@PathVariable int barberId){
        QueryWrapper<Barbers> barbersQueryWrapper = new QueryWrapper<>();
        barbersQueryWrapper.eq("barber_id",barberId);
        if(iBabersService.count(barbersQueryWrapper) != 0){
            return Result.ok(iBabersService.list(barbersQueryWrapper));
        }
        else {
            return Result.error("查询失败");
        }
    }

    //查询所有理发师的数量
    @GetMapping("getAllBarberForCount")
    public Result getAllBarberForCount() {
        return Result.ok(iBabersService.count());
    }

    //根据理发师的名字查询
    @GetMapping("getBarberByName/{barberName}")
    public Result getBarberByName(@PathVariable String barberName) {

        QueryWrapper<Barbers> barbersQueryWrapper = new QueryWrapper<>();
        barbersQueryWrapper.eq("barber_name", barberName);
        //打印查询到的返回值
        System.out.println("查询到" + iBabersService.count(barbersQueryWrapper) + "条数据");
        if (iBabersService.count(barbersQueryWrapper) != 0) {
            System.out.println("查找到数据！");
            return Result.ok(iBabersService.list(barbersQueryWrapper));
        } else {
            return Result.error("未查找到数据！");
        }

    }

    //添加理发师
    @PostMapping("addBaber")
    public Result addStudent(@RequestBody Barbers babers) {
        if (iBabersService.save(babers) == true) {
            return Result.ok(babers);
        } else {
            return Result.error("添加失败");
        }
    }

    //更新理发师信息
    @PostMapping("updateBaber")
    public Result updateBaber(@RequestBody Barbers babers) {
        if (iBabersService.saveOrUpdate(babers) == true) {
            return Result.ok(babers);
        } else {
            return Result.error("更新失败");
        }
    }

    @PostMapping("deleteBaberById/{barberId}")
    public Result deleteBaberById(@PathVariable int barberId) {
        if (iBabersService.removeById(barberId) == true) {
            return Result.ok(barberId);
        } else {
            return Result.error("删除失败");
        }
    }

    //通过Id更新理发师信息
    @PostMapping("updateBarberById")
    public Result updateBarberById(@RequestBody Barbers barbers) {
//        QueryWrapper<Servers> serversQueryWrapper = new QueryWrapper<>();
//        serversQueryWrapper.eq("server_id",server_id);
        iBabersService.updateById(barbers);
        if (iBabersService.updateById(barbers) == true) {
            return Result.ok(barbers);
        } else {
            return Result.error("更新失败");
        }
    }

    //根据理发师姓名进行模糊查询
    @GetMapping("getBarberByNameMoHu/{barberNameMoHU}")
    public Result getBarberByNameMoHu(@PathVariable String barberNameMoHU) {
        QueryWrapper<Barbers> barbersQueryWrapper = new QueryWrapper<>();
        barbersQueryWrapper.like("barber_name", barberNameMoHU);
        if (iBabersService.count(barbersQueryWrapper) != 0) {
            return Result.ok(iBabersService.list(barbersQueryWrapper));
        } else {
            return Result.error("查询失败");
        }
    }

    //分页查询
    @GetMapping("getBarberByPage/{pageSize}/{currentPage}")
    public Result getBarberByPage ( @PathVariable int pageSize, @PathVariable int currentPage){
        //字段:查询每页的数量10 第几页
        Page<Barbers> page = new Page<>(currentPage, pageSize);
        IPage<Barbers> barbersPage = iBabersService.page(page, null);
        return Result.ok(barbersPage);
    }

}

