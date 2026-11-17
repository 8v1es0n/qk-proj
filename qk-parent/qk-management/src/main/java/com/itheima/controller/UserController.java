package com.itheima.controller;

import com.itheima.common.PageResult;
import com.itheima.common.Result;
import com.itheima.common.utils.OssFileUpload;
import com.itheima.dto.UserDTO;
import com.itheima.entity.User;
import com.itheima.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;


@Slf4j
@RestController
@RequestMapping("/users")
@Tag(name = "用户管理")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private OssFileUpload ossFileUpload;


    /**
     * 使用分页进行条件查询用户
     * @param userDTO   请求数据
     * @return      返回结果
     */
    @GetMapping
    public Result pageQueryUser(UserDTO userDTO){
        PageResult<User> pageResult = userService.pageQueryUser(userDTO);
        return Result.success(pageResult);
    }

    /**
     * 新增用户
     * @param user  用户信息
     * @return      返回结果
     */
    @PostMapping
    public Result addUser(@RequestBody User user){
        boolean flag = userService.addUser(user);
        if (flag){
            return Result.success();
        } else {
            ossFileUpload.delete(user.getImage());
            return Result.error("用户添加失败");
        }
    }

    /**
     * 根据多个id删除用户信息
     * @param ids   需要删除的用户id
     * @return      返回结果
     */
    @DeleteMapping("/{ids}")
    public Result deleteUserByIds(@PathVariable Set<Integer> ids){
        for (Integer id : ids) {
            User user = userService.getById(id);
            boolean flag = userService.removeById(id);
            if (flag && !user.getImage().isEmpty()){
                ossFileUpload.delete(user.getImage());
            } else {
                return Result.error("删除用户意外中断");
            }
        }
        return Result.success();
    }

    /**
     * 根据id查询用户信息
     * @param id    用户id
     * @return      返回结果
     */
    @GetMapping("/{id}")
    public Result queryUserById(@PathVariable Integer id){
        User user = userService.getById(id);
        return Result.success(user);
    }

    /**
     * 更新用户信息
     * @param user  用户信息
     * @return  返回结果
     */
    @PutMapping
    public Result updateUser(@RequestBody User user){
        userService.saveOrUpdate(user);
        return Result.success();
    }

    /**
     * 查询所用用户
     * @return  返回结果
     */
    @GetMapping("/list")
    public Result userList(){
        List<User> users = userService.list();
        return Result.success(users);
    }

    /**
     * 根据角色标识查询用户列表
     * @param roleLabel 角色标识
     * @return      返回结果
     */
    @GetMapping("/role/{roleLabel}")
    public Result queryByRoleLabel(@PathVariable String roleLabel){
        List<User> users = userService.queryByRoleLabel(roleLabel);
        return Result.success(users);
    }

    /**
     * 根据部门id查询用户
     * @param deptId    部门id
     * @return      返回结果
     */
    @GetMapping("/dept/{deptId}")
    public Result queryDeptId(@PathVariable Integer deptId){
        List<User> userList = userService.queryDeptId(deptId);
        return Result.success(userList);
    }
}