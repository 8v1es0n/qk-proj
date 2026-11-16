package com.itheima.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.common.PageResult;
import com.itheima.dto.UserDTO;
import com.itheima.entity.User;

import java.util.List;


public interface UserService extends IService<User> {
    /**
     * 使用分页进行条件查询用户
     * @param userDTO   请求数据
     * @return      返回结果
     */
    PageResult<User> pageQueryUser(UserDTO userDTO);

    /**
     * 新增用户
     * @param user  用户信息
     */
    void addUser(User user);

    /**
     * 根据角色标识查询用户列表
     * @param roleLabel 角色标识
     * @return      返回结果
     */
    List<User> queryByRoleLabel(String roleLabel);
}
