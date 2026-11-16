package com.itheima.controller;

import com.itheima.common.PageResult;
import com.itheima.common.Result;
import com.itheima.entity.Activity;
import com.itheima.service.ActivityService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/activities")
@Tag(name = "活动管理")
public class ActivityController {
    @Autowired
    private ActivityService activityService;


    /**
     * 使用分页进行条件查询活动
     * @param channel   渠道来源
     * @param type      活动类型
     * @param page      当前页码
     * @param pageSize  每页记录数
     * @return  返回查询到的结果
     */
    @GetMapping
    public Result pageQueryActivity(Integer channel, Integer type, Integer status,
                                    @RequestParam(defaultValue="1") Integer page,
                                    @RequestParam(defaultValue="10") Integer pageSize){
        PageResult<Activity> pageResult = activityService.pageQueryActivity(channel, type, status, page, pageSize);
        return Result.success(pageResult);
    }

    /**
     * 添加活动
     * @param activity  活动信息
     * @return  返回结果
     */
    @PostMapping
    public Result addActivity(@RequestBody Activity activity){
        return activityService.saveOrUpdate(activity) ? Result.success("添加成功") : Result.error("添加失败");
    }

    /**
     * 删除活动
     * @param id    活动id
     * @return      返回结果
     */
    @DeleteMapping("/{id}")
    public Result deleteActivity(@PathVariable Integer id){
        boolean flag = activityService.removeById(id);
        return flag ? Result.success("删除成功") : Result.error("删除失败");
    }

    /**
     * 根据id查询活动信息
     * @param id    活动id
     * @return      返回结果
     */
    @GetMapping("/{id}")
    public Result queryActivityById(@PathVariable Integer id){
        Activity activity = activityService.getById(id);
        return  activity == null ? Result.error("没有查询到活动信息") : Result.success(activity);
    }

    /**
     * 更新活动信息
     * @param activity  活动信息
     * @return      返回结果
     */
    @PutMapping
    public Result updateActivity(@RequestBody Activity activity){
        boolean flag = activityService.saveOrUpdate(activity);
        return flag ? Result.success("更新活动信息成功") : Result.error("更新活动信息失败");
    }

    /**
     * 根据活动类型查询
     * @param type  活动类型
     * @return  返回结果
     */
    @GetMapping("/type/{type}")
    public Result queryActivityByType(@PathVariable Integer type){
        List<Activity> activityList = activityService.queryActivityByType(type);
        return Result.success(activityList);
    }
}
