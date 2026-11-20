package com.itheima.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 商机跟进记录实体类
 * 对应数据库表 business_track_record
 */
@Data
@TableName("business_track_record")
public class BusinessTrackRecord {

    /**
     * 跟进记录id, 主键
     */
    @TableId
    private Integer id;

    /**
     * 商机id，关联商机id
     */
    private Integer businessId;

    /**
     * 跟进人id，关联用户id
     */
    private Integer userId;

    /**
     * 跟进状态, 1:接通, 2:拒绝, 3:无人接听
     */
    private Integer trackStatus;

    /**
     * 沟通重点
     */
    private String keyItems;

    /**
     * 下次跟进时间
     */
    private LocalDateTime nextTime;

    /**
     * 沟通纪要
     */
    private String record;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 扩展 - 跟进人姓名（非数据库字段）
     */
    @TableField(exist = false)
    private String assignName;
}