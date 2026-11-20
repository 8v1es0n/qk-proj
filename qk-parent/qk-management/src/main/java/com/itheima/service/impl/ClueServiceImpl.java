package com.itheima.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.aspect.anno.LogOperation;
import com.itheima.common.PageResult;
import com.itheima.common.utils.UserHolder;
import com.itheima.dto.CluePoolDTO;
import com.itheima.dto.ClueQueryDTO;
import com.itheima.dto.MarkFalseClueDTO;
import com.itheima.entity.Business;
import com.itheima.entity.Clue;
import com.itheima.entity.ClueTrackRecord;
import com.itheima.mapper.BusinessMapper;
import com.itheima.mapper.ClueMapper;
import com.itheima.mapper.ClueTrackRecordMapper;
import com.itheima.service.ClueService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClueServiceImpl extends ServiceImpl<ClueMapper, Clue> implements ClueService {
    @Autowired
    private ClueMapper clueMapper;
    @Autowired
    private ClueTrackRecordMapper clueTrackRecordMapper;
    @Autowired
    private BusinessMapper businessMapper;


    @Override
    public PageResult<Clue> listClues(ClueQueryDTO clueQueryDTO) {
        Page<Clue> cluePage = new Page<>(clueQueryDTO.getPage(), clueQueryDTO.getPageSize());
        cluePage = clueMapper.listClues(cluePage, clueQueryDTO);
        return new PageResult<>(cluePage.getTotal(), cluePage.getRecords());
    }

    @Override
    public void assignClue(Integer clueId, Integer userId) {
        // 查询clueId 线索
        Clue clue = getById(clueId);
        // 将线索的userId修改为userId
        clue.setUserId(userId);
        //将线索的status修改为2
        clue.setStatus(2);
        // 更新线索
        saveOrUpdate(clue);
    }

    @Override
    public Clue getClueInfoById(Integer id) {
        // 1. 查主表（MP 一行代码搞定）
        Clue clue = clueMapper.selectById(id);

        if (clue != null) {
            // 2. 查子表（MP 同样一行代码搞定）
            List<ClueTrackRecord> records = clueTrackRecordMapper.queryCTR(id);
            // 3. 组装结果
            clue.setTrackRecords(records);
        }
        return clue;
    }

    @Override
    @Transactional
    public void trackClue(Clue clue, String token) {
        // 更新线索状态为“跟进中”
        clue.setStatus(3);
        // 更新时间
        clue.setUpdateTime(LocalDateTime.now());
        // 更新数据库
        this.updateById(clue);


        // 新增线索记录数据
        ClueTrackRecord trackRecord = new ClueTrackRecord();
        trackRecord.setClueId(clue.getId());
        BeanUtils.copyProperties(clue, trackRecord);
        // 获取token中的id信息 (方式一)
        // trackRecord.setUserId(JwtUtil.parseToken(token).get("id", Integer.class));

        // 从当前请求的线程中获取 (方式二)
        trackRecord.setUserId(UserHolder.getCurrentUser());

        trackRecord.setType(1);
        trackRecord.setCreateTime(LocalDateTime.now());
        clueTrackRecordMapper.insert(trackRecord);
    }

    @Override
    @Transactional
    public void convertToBusiness(Integer id) {
        // 1. 修改线索信息
        Clue clue = this.getById(id);
        clue.setStatus(5); //转为商机
        this.updateById(clue);

        // 2. 创建商机信息
        Business business = BeanUtil.copyProperties(clue, Business.class);
        business.setUserId(null); //  设置商机归属人为空
        business.setNextTime(null); //  设置下次联系时间为空
        business.setStatus(1);
        business.setClueId(clue.getId());

        // 3. 插入商机记录
        businessMapper.insert(business);
    }

    @Override
    @LogOperation
    public void markFalseClue(Integer id, MarkFalseClueDTO mfc) {
        // 1. 修改线索状态为"伪线索"
        Clue clue = new Clue();
        clue.setId(id);
        clue.setStatus(4); // 状态值 4 表示伪线索
        clue.setUpdateTime(LocalDateTime.now());
        this.updateById(clue);

        // 2. 添加跟进记录
        ClueTrackRecord trackRecord = new ClueTrackRecord();
        trackRecord.setClueId(id);
        trackRecord.setUserId(UserHolder.getCurrentUser()); // 当前登录用户ID
        trackRecord.setType(0); // 类型 0 表示伪线索
        trackRecord.setFalseReason(mfc.getReason());
        trackRecord.setRecord(mfc.getRemark());
        trackRecord.setCreateTime(LocalDateTime.now());
        clueTrackRecordMapper.insert(trackRecord);
    }

    @Override
    public PageResult<Clue> getPoolClues(CluePoolDTO cluePoolDTO) {
        Page<Clue> page = new Page<>(cluePoolDTO.getPage(), cluePoolDTO.getPageSize());
        page = baseMapper.getPoolClues(page, cluePoolDTO);
        return new PageResult<>(page.getTotal(), page.getRecords());
    }
}