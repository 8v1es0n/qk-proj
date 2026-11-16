package com.itheima.dto;

import lombok.Data;

@Data
public class UserDTO {
    /**
     * 姓名
     */
    private String name;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 状态，0:禁用，1:启用
     */
    private Integer status;

    /**
     * 部门id
     */
    private Integer deptId;

    /**
     * 当前页码
     */
    private Integer page = 1;

    /**
     * 每页记录数
     */
    private Integer pageASize = 10;
}
