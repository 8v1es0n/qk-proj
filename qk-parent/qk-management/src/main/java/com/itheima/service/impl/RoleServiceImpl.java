package com.itheima.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.common.PageResult;
import com.itheima.entity.Role;
import com.itheima.mapper.RoleMapper;
import com.itheima.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;


    @Override
    public void addRole(Role role) {
        LocalDateTime now = LocalDateTime.now().withNano(0);
        role.setCreateTime(now);
        role.setUpdateTime(now);
        roleMapper.insert(role);
    }

    @Override
    public void deleteRole(Integer id) {
        roleMapper.deleteById(id);
    }

    @Override
    public Role queryRoleById(Integer id) {
        return roleMapper.selectById(id);
    }

    @Override
    public void updateRole(Role role) {
        role.setUpdateTime(LocalDateTime.now().withNano(0));
        roleMapper.updateById(role);
    }

    @Override
    public List<Role> queryRoleList() {
        return roleMapper.selectList(null);
    }

    @Override
    public PageResult<Role> findRoles(String name, String label, Integer page, Integer pageSize) {
        Page<Role> rolePage = new Page<>(page, pageSize);
        LambdaQueryWrapper<Role> roleLambdaQueryWrapper = new LambdaQueryWrapper<>();
        roleLambdaQueryWrapper.like(StrUtil.isNotBlank(name), Role::getName, name)
                .like(StrUtil.isNotBlank(label), Role::getLabel, label);

        rolePage = roleMapper.selectPage(rolePage, roleLambdaQueryWrapper);
        return new PageResult<>(rolePage.getTotal(), rolePage.getRecords());
    }


}
