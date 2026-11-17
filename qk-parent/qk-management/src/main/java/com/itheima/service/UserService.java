package com.itheima.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.common.PageResult;
import com.itheima.dto.UserDTO;
import com.itheima.entity.User;
import com.itheima.vo.LoginResultVO;

import java.util.List;


public interface UserService extends IService<User> {
    /**
     * 使用分页进行条件查询用户
     * @param userDTO   请求数据
     * @return      返回结果
     */
    PageResult<User> pageQueryUser(UserDTO userDTO);

    /**
     * 根据角色标识查询用户列表
     * @param roleLabel 角色标识
     * @return      返回结果
     */
    List<User> queryByRoleLabel(String roleLabel);

    /**
     * 根据部门id查询用户
     * @param deptId    部门id
     * @return      返回结果
     */
    List<User> queryDeptId(Integer deptId);

    /**
     * 用户登录
     * @param username  用户名
     * @param password  密码
     * @return  返回登陆结果
     */
    LoginResultVO login(String username, String password);

    /**
     * 新增用户
     * @param user  用户信息
     * @return      返回结果
     */
    boolean addUser(User user);
}
