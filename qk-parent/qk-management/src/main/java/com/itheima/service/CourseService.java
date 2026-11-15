package com.itheima.service;

import com.itheima.common.PageResult;
import com.itheima.entity.Course;

import java.util.List;

public interface CourseService {
    /**
     * 新增课程
     * @param course 课程信息
     */
    void addCourse(Course course);

    /**
     * 删除课程
     * @param id    课程id
     */
    void deleteCourse(Integer id);

    /**
     * \使用分页进行条件查询课程
     * @param name      课程名称
     * @param subject   课程学科
     * @param target    适合人群
     * @param page      当前页码
     * @param pageSize  每页记录数
     * @return      分页查询的结果
     */
    PageResult<Course> queryCourse(String name, Integer subject, Integer target, Integer page, Integer pageSize);

    /**
     * 根据id查询课程信息
     * @param id    课程id
     * @return      返回查询到的结果
     */
    Course queryCourseById(Integer id);

    /**
     * 根据课程id修改课程信息
     * @param course  课程信息
     */
    void updateCourse(Course course);

    /**
     * 根据课程学科查询课程
     * @param subject 课程所属的学科
     * @return  返回结果
     */
    List<Course> queryBySubject(Integer subject);
}
