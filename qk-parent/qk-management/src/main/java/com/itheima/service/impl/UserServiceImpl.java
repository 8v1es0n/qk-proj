package com.itheima.service.impl;

import cn.hutool.crypto.digest.DigestUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.common.PageResult;
import com.itheima.dto.UserDTO;
import com.itheima.entity.User;
import com.itheima.mapper.UserMapper;
import com.itheima.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public PageResult<User> pageQueryUser(UserDTO userDTO) {
        Page<User> userPage = new Page<>(userDTO.getPage(), userDTO.getPageASize());

        IPage<User> userIPage = userMapper.pageQueryUser(userPage, userDTO);
        return new PageResult<User>(userIPage.getTotal(), userIPage.getRecords());
    }

    @Override
    public void addUser(User user) {
        String Confusion =  user.getPassword() + "qk";
        user.setPassword(DigestUtil.md5Hex(Confusion));
        userMapper.insert(user);
    }

    @Override
    public List<User> queryByRoleLabel(String roleLabel) {
        return userMapper.queryByRoleLabel(roleLabel);
    }
}
