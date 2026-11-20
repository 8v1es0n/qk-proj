package com.itheima.service;

import com.itheima.vo.OverviewVO;

public interface ReportService {
    /**
     * 获取首页概览数据 - /report/overview
     */
    OverviewVO getOverview();
}
