package com.itheima.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.common.PageResult;
import com.itheima.entity.Activity;
import com.itheima.mapper.ActivityMapper;
import com.itheima.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityServiceImpl extends ServiceImpl<ActivityMapper, Activity> implements ActivityService {
    @Autowired
    private ActivityMapper activityMapper;


    @Override
    public PageResult<Activity> pageQueryActivity(Integer channel, Integer type, Integer page, Integer pageSize) {
        Page<Activity> activityPage = new Page<>(page, pageSize);
        LambdaQueryWrapper<Activity> activityLambdaQueryWrapper = new LambdaQueryWrapper<>();
        activityLambdaQueryWrapper.eq(channel != null, Activity::getChannel, channel)
                .eq(type != null, Activity::getType, type);

        activityPage = activityMapper.selectPage(activityPage, activityLambdaQueryWrapper);
        return new PageResult<>(activityPage.getTotal(), activityPage.getRecords());
    }

    @Override
    public List<Activity> queryActivityByType(Integer type) {
        LambdaQueryWrapper<Activity> activityLambdaQueryWrapper = new LambdaQueryWrapper<>();
        activityLambdaQueryWrapper.eq(type != null, Activity::getType, type);
        return activityMapper.selectList(activityLambdaQueryWrapper);
    }

}
