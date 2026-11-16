package com.itheima.controller;

import com.itheima.common.PageResult;
import com.itheima.common.Result;
import com.itheima.entity.Role;
import com.itheima.service.RoleService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
@Tag(name = "角色管理")
public class RoleController {
    @Autowired
    private RoleService roleService;


    /**
     * 新增角色
     * @param role  角色信息
     * @return  返回结果
     */
    @PostMapping
    public Result addRole(@RequestBody Role role){
        roleService.addRole(role);
        return Result.success();
    }

    /**
     * 使用分页进行条件查询角色
     * @param name      角色名称
     * @param label     角色标识
     * @param page      当前页码
     * @param pageSize      每页记录数
     * @return  分页查询的结果
     */
    @GetMapping
    public Result listRoles(String name, String label,
                            @RequestParam(defaultValue="1") Integer page,
                            @RequestParam(defaultValue="10") Integer pageSize){
        PageResult<Role> pageResult = roleService.findRoles(name, label, page, pageSize);
        return Result.success(pageResult);
    }

    /**
     * 根据id删除角色
     * @param id    角色id
     * @return      返回结果
     */
    @DeleteMapping("/{id}")
    public Result deleteRole(@PathVariable Integer id){
        roleService.deleteRole(id);
        return Result.success();
    }

    /**
     * 根据id查询角色信息
     * @param id    角色id
     * @return      返回查询到的结果
     */
    @GetMapping("/{id}")
    public Result queryRoleById(@PathVariable Integer id){
        Role role = roleService.queryRoleById(id);
        return Result.success(role);
    }

    /**
     * 根据角色id修改角色信息
     * @param role  角色信息
     * @return  返回结果
     */
    @PutMapping
    public Result updateRole(@RequestBody Role role){
        roleService.updateRole(role);
        return Result.success();
    }

    /**
     * 查询所有角色
     * @return  返回结果
     */
    @GetMapping("/list")
    public Result queryRoleList(){
        List<Role> roleList = roleService.queryRoleList();
        return Result.success(roleList);
    }
}
