package com.itheima.service.impl;


import com.itheima.mapper.ReportMapper;
import com.itheima.service.ReportService;
import com.itheima.vo.OverviewVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    private ReportMapper reportMapper;

    @Override
    public OverviewVO getOverview() {
        return reportMapper.getOverviewData();
    }
}