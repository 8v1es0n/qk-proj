package com.itheima.service;

import com.itheima.common.PageResult;
import com.itheima.entity.Role;

public interface RoleService {
    /**
     * 使用分页进行条件查询角色
     * @param name      角色名称
     * @param label     角色标识
     * @param page      当前页码
     * @param pageSize      每页记录数
     * @return  分页查询的结果
     */
    PageResult<Role> findRoles(String name, String label, Integer page, Integer pageSize);

    /**
     * 新增角色
     * @param role  角色信息
     * @return  返回结果
     */
    void addRole(Role role);

    /**
     * 根据id删除角色
     * @param id    角色id
     */
    void deleteRole(Integer id);

    /**
     * 根据id查询角色信息
     * @param id    角色id
     * @return      返回查询到的结果
     */
    Role queryRoleById(Integer id);

    /**
     * 根据部门id修改角色信息
     * @param role  角色信息
     */
    void updateRole(Role role);
}
