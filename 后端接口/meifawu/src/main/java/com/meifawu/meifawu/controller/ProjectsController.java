package com.meifawu.meifawu.controller;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.meifawu.meifawu.entity.Orders;
import com.meifawu.meifawu.entity.Projects;
import com.meifawu.meifawu.entity.Timeslotcount;
import com.meifawu.meifawu.service.IProjectsService;
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
 * @since 2021-12-24
 */
@Api(tags="服务项目类")
@CrossOrigin
@RestController
public class ProjectsController {
    @Autowired
    IProjectsService iProjectsService;
    //查询所有价目表信息
    @GetMapping("getAllProject")
    public Result getAllProject(){
        return Result.ok(iProjectsService.list());
    }

    //添加服务项目
    @PostMapping("addProject")
    public Result addProject(@RequestBody Projects projects){
        if(iProjectsService.save(projects) == true){
            return Result.ok(projects);
        }
        else {
            return Result.error("添加失败");
        }
    }

    //更新服务项目信息
    @PostMapping("updateProject")
    public Result updateProject(@RequestBody Projects projects){
        if(iProjectsService.saveOrUpdate(projects) == true){
            return Result.ok(projects);
        }
        else {
            return Result.error("更新失败");
        }
    }

    @PostMapping("deleteProjectById/{project_id}")
    public Result deleteProjectById(@PathVariable int project_id){
        if(iProjectsService.removeById(project_id) == true){
            return Result.ok(project_id);
        }
        else {
            return Result.error("删除失败");
        }
    }

    //通过Id更新服务项目信息
    @PostMapping("updateProjectById")
    public Result updateProjectById(@RequestBody Projects projects){
//        QueryWrapper<Servers> serversQueryWrapper = new QueryWrapper<>();
//        serversQueryWrapper.eq("server_id",server_id);
        iProjectsService.updateById(projects);
        if(iProjectsService.updateById(projects) == true){
            return Result.ok(projects);
        }
        else {
            return Result.error("更新失败");
        }
    }

    @GetMapping("getProjectByPage/{pageSize}/{currentPage}")
    public Result getProjectByPage ( @PathVariable int pageSize, @PathVariable int currentPage){
        //字段:查询每页的数量10 第几页
        Page<Projects> page = new Page<>(currentPage, pageSize);
        IPage<Projects> projectsPage = iProjectsService.page(page, null);
        return Result.ok(projectsPage);
    }

}

