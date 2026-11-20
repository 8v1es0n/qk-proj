package com.itheima.entity;

import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDateTime;

/**
 * 操作日志实体类
 * 对应数据库表 operate_log
 */
@Data
@TableName("operate_log")
public class OperateLog {

    /**
     * ID
     */
    @TableId
    private Integer id;

    /**
     * 操作用户ID
     */
    private Integer operateUserId;

    /**
     * 操作时间
     */
    private LocalDateTime operateTime;

    /**
     * 类名称
     */
    private String className;

    /**
     * 方法名称
     */
    private String methodName;

    /**
     * 方法参数
     */
    private String methodParams;

    /**
     * 返回值
     */
    private String returnValue;

    /**
     * 耗时
     */
    private Long costTime;
}