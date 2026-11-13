package com.itheima.controller;

import com.itheima.common.PageResult;
import com.itheima.common.Result;
import com.itheima.entity.Course;
import com.itheima.service.CourseService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/courses")
@Tag(name = "课程管理")
public class CourseController {

    @Autowired
    private CourseService courseService;


    /**
     * 新增课程
     * @param course 课程信息
     * @return 返回结果
     */
    @PostMapping
    public Result addCourse(@RequestBody Course course){
        courseService.addCourse(course);
        return Result.success();
    }

    /**
     * 删除课程
     * @param id    课程id
     * @return      返回结果
     */
    @DeleteMapping("/{id}")
    public Result deleteCourse(@PathVariable Integer id){
        courseService.deleteCourse(id);
        log.info("课程id为 {} 的课程删除成功", id);
        return Result.success();
    }

    /**
     * 使用分页进行条件查询课程
     * @param name      课程名称
     * @param subject   课程学科
     * @param target    适合人群
     * @param page      当前页码
     * @param pageSize  每页记录数
     * @return      分页查询的结果
     */
    @GetMapping
    public Result queryCourse(String name, Integer subject, Integer target,
                              @RequestParam(defaultValue="1") Integer page,
                              @RequestParam(defaultValue="10") Integer pageSize){
        PageResult<Course> pageResult = courseService.queryCourse(name, subject, target, page, pageSize);
        return Result.success(pageResult);
    }

    /**
     * 根据id查询课程信息
     * @param id    课程id
     * @return      返回查询到的结果
     */
    @GetMapping("/{id}")
    public Result queryCourseById(@PathVariable Integer id){
        Course course = courseService.queryCourseById(id);
        return Result.success(course);
    }

    /**
     * 根据课程id修改课程信息
     * @param course  课程信息
     * @return  返回结果
     */
    @PutMapping
    public Result updateCourse(@RequestBody Course course){
        courseService.updateCourse(course);
        return Result.success();
    }
}
