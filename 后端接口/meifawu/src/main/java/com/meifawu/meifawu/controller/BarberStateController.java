package com.meifawu.meifawu.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.meifawu.meifawu.entity.BarberState;
import com.meifawu.meifawu.entity.Barbers;
import com.meifawu.meifawu.entity.User;
import com.meifawu.meifawu.service.IBarberStateService;
import com.meifawu.meifawu.utils.Result;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 孟子弘
 * @since 2021-12-28
 */
@Api(tags="理发师状态类")
@RestController
@CrossOrigin
public class BarberStateController {
    @Autowired
    IBarberStateService iBarberStateService;
    //查询所有理发师状态信息
    @GetMapping("getAllBarberState")
    public Result getAllBarberState(){
        return Result.ok(iBarberStateService.list());
    }
    //根据ID查询理发师状态信息
    @GetMapping("getBarberStateById/{stateId}")
    public Result getBarberStateById(@PathVariable int stateId){
        QueryWrapper<BarberState> barberStateQueryWrapper = new QueryWrapper<>();
        barberStateQueryWrapper.eq("state_id",stateId);
        if(iBarberStateService.count(barberStateQueryWrapper) != 0){
            return Result.ok(iBarberStateService.list(barberStateQueryWrapper));
        }
        else {
            return Result.error("查询失败");
        }
    }
    //根据理发师名称查询理发师状态信息
    @GetMapping("getBarberStateByBarberName/{barber_name}")
    public Result getBarberStateByBarberName(@PathVariable String barber_name){
        QueryWrapper<BarberState> barberStateQueryWrapper = new QueryWrapper<>();
        barberStateQueryWrapper.eq("barber_name",barber_name);
        if(iBarberStateService.count(barberStateQueryWrapper) != 0){
            return Result.ok(iBarberStateService.list(barberStateQueryWrapper));
        }
        else {
            return Result.error("查询失败");
        }
    }

    //根据理发师名称和日期查询理发师状态信息
    @GetMapping("getBarberStateByBarberName/{barber_name}/{theDate}")
    public Result getBarberStateByBarberName(@PathVariable String barber_name,@PathVariable String theDate){
        //转换日期
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dateStr = theDate;
        LocalDate date = LocalDate.parse(dateStr, fmt);

        QueryWrapper<BarberState> barberStateQueryWrapper = new QueryWrapper<>();
        barberStateQueryWrapper.eq("barber_name",barber_name).eq("the_date",date);
        if(iBarberStateService.count(barberStateQueryWrapper) != 0){
            return Result.ok(iBarberStateService.list(barberStateQueryWrapper));
        }
        else {
            return Result.error("查询失败");
        }
    }

    //添加理发师状态
    @PostMapping("addBarberState")
    public Result addBarberState(@RequestBody BarberState barberState){
        if(iBarberStateService.save(barberState) == true){
            return Result.ok(barberState);
        }
        else {
            return Result.error("添加失败");
        }
    }
    //更新理发师状态信息
    @PostMapping("updateBarberState")
    public Result updateBarberState(@RequestBody BarberState barberState){
        if(iBarberStateService.saveOrUpdate(barberState) == true){
            return Result.ok(barberState);
        }
        else {
            return Result.error("更新失败");
        }
    }

    @PostMapping("deleteBarberStateById/{stateId}")
    public Result deleteOrderById(@PathVariable int stateId){
        if(iBarberStateService.removeById(stateId) == true){
            return Result.ok(stateId);
        }
        else {
            return Result.error("删除失败");
        }
    }

    //通过Id更新理发师状态信息
    @PostMapping("updateBarberStateById")
    public Result updateOrderById(@RequestBody BarberState barberState){
//        QueryWrapper<Servers> serversQueryWrapper = new QueryWrapper<>();
//        serversQueryWrapper.eq("server_id",server_id);
        iBarberStateService.updateById(barberState);
        if(iBarberStateService.updateById(barberState) == true){
            return Result.ok(barberState);
        }
        else {
            return Result.error("更新失败");
        }
    }

    //根据理发师姓名进行模糊查询
    @GetMapping("getBarberStateByNameMoHu/{barberNameMoHU}")
    public Result getBarberStateByNameMoHu(@PathVariable String barberNameMoHU) {
        QueryWrapper<BarberState> barberStateQueryWrapper = new QueryWrapper<>();
        barberStateQueryWrapper.like("barber_name", barberNameMoHU);
        if (iBarberStateService.count(barberStateQueryWrapper) != 0) {
            return Result.ok(iBarberStateService.list(barberStateQueryWrapper));
        } else {
            return Result.error("查询失败");
        }
    }

    //分页查询
    @GetMapping("getBarberStateByPage/{pageSize}/{currentPage}")
    public Result getBarberStateByPage ( @PathVariable int pageSize, @PathVariable int currentPage){
        //字段:查询每页的数量10 第几页
        Page<BarberState> page = new Page<>(currentPage, pageSize);
        IPage<BarberState> barberStatePage = iBarberStateService.page(page, null);
        return Result.ok(barberStatePage);
    }

    //级联查询 查看所有美发师的详细信息以及空闲状态
    @GetMapping("findAllBarberState")
    public List<BarberState> findAllBarberState(){
        return iBarberStateService.findAllBarberState();
    }
    //查看当天工作的美发师的详细信息以及空闲状态
    @GetMapping("findAllBarberStateByDate/{theDate}")
    public List<BarberState> findAllBarberStateByDate(@PathVariable
//                                                                  (iso = DateTimeFormat.ISO.DATE)
                                                                  String theDate) {
        //转换日期
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dateStr = theDate;
        LocalDate date = LocalDate.parse(dateStr, fmt);

        return iBarberStateService.findAllBarberStateByDate(date);
    }
    //查看店内参与美发的美发师数量
    @GetMapping("findAllBarberStateForCount/{theDate}")
    public Result findAllBarberStateForCount(@PathVariable String theDate) {
        //转换日期
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dateStr = theDate;
        LocalDate date = LocalDate.parse(dateStr, fmt);

        QueryWrapper<BarberState> barberStateQueryWrapper = new QueryWrapper<>();
        barberStateQueryWrapper.eq("the_date",date);
        if(iBarberStateService.count(barberStateQueryWrapper)>0){
            return Result.ok(iBarberStateService.count(barberStateQueryWrapper));
        }
       else {
            return Result.error("查询失败");
        }
    }


    //根据昵称更新用户余额
    @PostMapping("updateStateByBarberName")
    public Result updateStateByBarberName(@RequestBody BarberState barberState){
        if(iBarberStateService.updateStateByBarberName(barberState)>0){
            return Result.ok(barberState);
        }else {
            return Result.error(-1,"更新失败！");
        }
    }
}

