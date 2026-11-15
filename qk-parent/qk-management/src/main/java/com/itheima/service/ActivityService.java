package com.itheima.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.common.PageResult;
import com.itheima.entity.Activity;

import java.util.List;

public interface ActivityService extends IService<Activity> {
    /**
     * 使用分页进行条件查询活动
     * @param channel   渠道来源
     * @param type      活动类型
     * @param page      当前页码
     * @param pageSize  每页记录数
     * @return  返回查询到的结果
     */
    PageResult<Activity> pageQueryActivity(Integer channel, Integer type, Integer page, Integer pageSize);

    /**
     * 根据活动类型查询
     * @return  返回结果
     */
    List<Activity> queryActivityByType(Integer type);
}
