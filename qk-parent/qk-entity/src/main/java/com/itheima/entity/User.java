package com.itheima.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 用户实体类
 */
@Data
@TableName("sys_user")
public class User {
    /**
     * id, 主键
     */
    @TableId
    private Integer id;

    /**
     * 用户名，唯一
     */
    private String username;

    /**
     * 密码
     */
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // 只在给前端返回时忽略该字段
    private String password;

    /**
     * 姓名
     */
    private String name;

    /**
     * 手机号，唯一
     */
    private String phone;

    /**
     * 邮箱，唯一
     */
    private String email;

    /**
     * 性别，1: 男，2: 女
     */
    private Integer gender;

    /**
     * 状态，1: 正常，0: 停用
     */
    private Integer status;

    /**
     * 部门id，关联部门表主键
     */
    private Integer deptId;

    /**
     * 角色id，关联角色表主键
     */
    private Integer roleId;

    /**
     * 头像url
     */
    private String image;

    /**
     * 备注，50字以内
     */
    private String remark;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    //扩展属性
    @TableField(exist = false)//表示这个字段在数据库中不存在
    private String deptName; //部门名称

    @TableField(exist = false)//表示这个字段在数据库中不存在
    private String roleName; //角色名称
}