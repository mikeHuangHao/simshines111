package com.simshine.service;

import com.simshine.mode.UserModel;

import java.util.Map;

/**
 * @ClassName UserService
 * @Description TODO
 * @Author hh
 * @Date 2019/9/16 0016 11:26
 * @Version 1.0
 **/
public interface UserService {

    /**
     * 登录校验
     * @author zifangsky
     * @date 2019/9/16 10:48
     * @since 1.0.0
     * @param username 用户名
     * @param password 密码
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    Map<String,Object> checkLogin(String username, String password);

    /**
     * 注册
     * @author zifangsky
     * @date 2019/9/16 10:48
     * @since 1.0.0
     * @param user 用户详情
     * @return boolean
     */
    boolean register(UserModel user);
}
