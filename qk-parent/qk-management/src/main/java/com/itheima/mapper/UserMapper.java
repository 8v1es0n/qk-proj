package com.itheima.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.dto.UserDTO;
import com.itheima.entity.User;
import com.itheima.vo.LoginResultVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    IPage<User> pageQueryUser(Page<User> userPage, UserDTO userDTO);

    @Select("SELECT u.* FROM sys_user u INNER JOIN role r ON u.role_id = r.id WHERE r.label = #{roleLabel}")
    List<User> queryByRoleLabel(String roleLabel);

    @Select("select u.*, r.label as role_label from sys_user u left join role r on u.role_id = r.id where u.username = #{username}")
    LoginResultVO queryByUsername(String username);
}
