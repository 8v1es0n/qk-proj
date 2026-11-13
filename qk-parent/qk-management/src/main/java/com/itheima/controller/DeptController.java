package com.itheima.controller;

import com.itheima.common.PageResult;
import com.itheima.common.Result;
import com.itheima.entity.Dept;
import com.itheima.service.DeptService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/depts")
@Tag(name = "部门管理")
public class DeptController {
    @Autowired
    private DeptService deptService;


    /**
     * 新增部门
     * @param dept 部门信息
     * @return 返回结果
     */
    @PostMapping
    public Result addDept(@RequestBody Dept dept){
        deptService.addDept(dept);
        return Result.success();
    }

    /**
     * 使用分页进行条件查询部门
     * @param name      部门名称
     * @param status    部门状态
     * @param page      当前页码
     * @param pageSize  每页记录数
     * @return  分页查询的结果
     */
    @GetMapping
    public Result listDepts(String name, Integer status,
                            @RequestParam(defaultValue="1") Integer page,
                            @RequestParam(defaultValue="10") Integer pageSize){
        PageResult<Dept> pageResult  = deptService.findDepts(name, status, page, pageSize);
        return Result.success(pageResult);
    }

    /**
     * 根据id查询部门信息
     * @param id    部门id
     * @return      返回查询到的部门信息
     */
    @GetMapping("/{id}")
    public Result queryById(@PathVariable Integer id){
        Dept dept = deptService.queryById(id);
        return Result.success(dept);
    }

    /**
     * 根据部门id修改部门信息
     * @param dept  部门信息
     * @return  返回结果
     */
    @PutMapping
    public Result updateDept(@RequestBody Dept dept){
        deptService.updateDept(dept);
        return Result.success();
    }

    /**
     * 根据id删除部门
     * @param id    部门id
     * @return      返回结果
     */
    @DeleteMapping("/{id}")
    public Result deleteDept(@PathVariable Integer id){
        deptService.deleteDept(id);
        return Result.success();
    }

    /**
     * 查询所有部门信息
     * @return  返回所有结果
     */
    @GetMapping("/list")
    public Result listDept(){
        List<Dept> deptList = deptService.listDept();
        return Result.success(deptList);
    }
}
