package com.itheima.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.common.PageResult;
import com.itheima.entity.Dept;
import com.itheima.mapper.DeptMapper;
import com.itheima.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptMapper deptMapper;


    @Override
    public void addDept(Dept dept) {
        // 统一截断微秒
        LocalDateTime now = LocalDateTime.now().withNano(0);
        // 获取创建时间与更新时间
        dept.setCreateTime(now);
        dept.setUpdateTime(now);

        // 执行新增操作
        deptMapper.insert(dept);
    }

    @Override
    public PageResult<Dept> findDepts(String name, Integer status, Integer page, Integer pageSize) {
        //分页插件使用步骤
        //1.设置分页参数
        Page<Dept> deptPage = new Page<>(page, pageSize);

        //2. 设置条件参数
        LambdaQueryWrapper<Dept> deptLambdaQueryWrapper = new LambdaQueryWrapper<>();
        deptLambdaQueryWrapper.like(StrUtil.isNotBlank(name), Dept::getName, name)
                .eq(status != null, Dept::getStatus, status);

        //3. 执行分页查询
        deptPage = deptMapper.selectPage(deptPage,deptLambdaQueryWrapper);

        //4. 返回结果
        return new PageResult<>(deptPage.getTotal(), deptPage.getRecords());
    }

    @Override
    public Dept queryById(Integer id) {
        return deptMapper.selectById(id);
    }

    @Override
    public void updateDept(Dept dept) {
        // 修改更新时间
        dept.setUpdateTime(LocalDateTime.now().withNano(0));
        deptMapper.updateById(dept);
    }

    @Override
    public void deleteDept(Integer id) {
        deptMapper.deleteById(id);
    }

    @Override
    public List<Dept> listDept() {
        LambdaQueryWrapper<Dept> deptLambdaQueryWrapper = new LambdaQueryWrapper<>();
        deptLambdaQueryWrapper.eq(Dept::getStatus, 1);
        List<Dept> deptList = deptMapper.selectList(deptLambdaQueryWrapper);
        return deptList;
    }
}
