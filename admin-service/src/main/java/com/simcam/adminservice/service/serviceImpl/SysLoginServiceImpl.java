package com.simcam.adminservice.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.simcam.adminservice.mapper.SysLoginMapper;
import com.simcam.adminservice.model.SysUser;
import com.simcam.adminservice.service.SysLoginService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Administrator
 */
@Service
public class SysLoginServiceImpl implements SysLoginService{

    @Autowired
    private SysLoginMapper sysLoginMapper;

    /**
     * 登录
     */
    @Override
    public SysUser login(String username, String password){
        // 用户名或密码为空 错误
        if (StringUtils.isAnyBlank(username, password)){
            return null;
        }
        // 查询用户信息
        QueryWrapper<SysUser> qw = new QueryWrapper<>();
        qw.eq("login_name",username);
        SysUser user = sysLoginMapper.selectOne(qw);
        return user;
    }
}