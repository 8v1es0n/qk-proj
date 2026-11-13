package com.itheima.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.common.PageResult;
import com.itheima.entity.Course;
import com.itheima.mapper.CourseMapper;
import com.itheima.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseMapper courseMapper;


    @Override
    public void addCourse(Course course) {
        LocalDateTime now = LocalDateTime.now().withNano(0);
        course.setCreateTime(now);
        course.setUpdateTime(now);

        courseMapper.insert(course);
    }

    @Override
    public void deleteCourse(Integer id) {
        courseMapper.deleteById(id);
    }

    @Override
    public PageResult<Course> queryCourse(String name, Integer subject, Integer target, Integer page, Integer pageSize) {
        Page<Course> coursePage = new Page<>(page, pageSize);

        LambdaQueryWrapper<Course> courseLambdaQueryWrapper = new LambdaQueryWrapper<>();
        courseLambdaQueryWrapper.like(StrUtil.isNotBlank(name), Course::getName, name)
                .eq(subject != null, Course::getSubject, subject)
                .eq(target != null, Course::getTarget, target);

        coursePage = courseMapper.selectPage(coursePage, courseLambdaQueryWrapper);
        return new PageResult<>(coursePage.getTotal(), coursePage.getRecords());
    }

    @Override
    public Course queryCourseById(Integer id) {
        return courseMapper.selectById(id);
    }

    @Override
    public void updateCourse(Course course) {
        course.setUpdateTime(LocalDateTime.now().withNano(0));
        courseMapper.updateById(course);
    }
}
