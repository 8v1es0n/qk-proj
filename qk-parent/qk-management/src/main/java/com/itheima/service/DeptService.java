package com.itheima.service;

import com.itheima.common.PageResult;
import com.itheima.entity.Dept;

import java.util.List;


public interface DeptService {

    /**
     * 新增部门
     * @param dept 部门信息
     */
    void addDept(Dept dept);

    /**
     * 使用分页进行条件查询部门
     * @param name      部门名称
     * @param status    部门状态
     * @param page      当前页码
     * @param pageSize  每页记录数
     * @return  分页查询的结果
     */
    PageResult<Dept> findDepts(String name, Integer status, Integer page, Integer pageSize);

    /**
     * 根据id查询部门信息
     * @param id    部门id
     * @return      返回查询到的部门信息
     */
    Dept queryById(Integer id);

    /**
     * 根据部门id修改部门信息
     * @param dept  部门信息
     */
    void updateDept(Dept dept);

    /**
     * 根据id删除部门
     * @param id    部门id
     */
    void deleteDept(Integer id);

    /**
     * 查询所有部门信息
     * @return  返回所有结果
     */
    List<Dept> listDept();

}
