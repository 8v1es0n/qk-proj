package com.itheima.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.common.PageResult;
import com.itheima.common.utils.JwtUtil;
import com.itheima.dto.UserDTO;
import com.itheima.entity.User;
import com.itheima.mapper.UserMapper;
import com.itheima.service.UserService;
import com.itheima.vo.LoginResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserMapper userMapper;


    @Override
    public PageResult<User> pageQueryUser(UserDTO userDTO) {
        Page<User> userPage = new Page<>(userDTO.getPage(), userDTO.getPageASize());

        IPage<User> userIPage = userMapper.pageQueryUser(userPage, userDTO);
        return new PageResult<>(userIPage.getTotal(), userIPage.getRecords());
    }

    @Override
    public List<User> queryByRoleLabel(String roleLabel) {
        return userMapper.queryByRoleLabel(roleLabel);
    }

    @Override
    public List<User> queryDeptId(Integer deptId) {
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.eq(deptId != null, User::getDeptId, deptId);
        return userMapper.selectList(userLambdaQueryWrapper);
    }

    @Override
    public LoginResultVO login(String username, String password) {
        LoginResultVO vo = userMapper.queryByUsername(username);
        // String pw = password + "qk";
        if (ObjectUtil.isEmpty(vo)){
            throw new RuntimeException("用户不存在");
        } else if (!StrUtil.equals(vo.getPassword(), DigestUtil.md5Hex(password))) {
            throw new RuntimeException("用户名或密码错误");
        } else if (vo.getStatus() == 0) {
            throw new RuntimeException("用户被冻结");
        }else {
            // 生成token
            HashMap<String, Object> claims  = new HashMap<>();
            claims.put("id", vo.getId());
            claims.put("username", vo.getUsername());
            String token = JwtUtil.generateToken(claims);
            // 构造登录结果
            vo.setToken(token);
            // 避免密码泄露
            vo.setPassword("");
            return vo;
        }
    }

    @Override
    public boolean addUser(User user) {
        String pw = user.getPassword() + "qk";
        user.setPassword(DigestUtil.md5Hex(pw));
        int insert = userMapper.insert(user);
        return insert != 0;
    }
}
