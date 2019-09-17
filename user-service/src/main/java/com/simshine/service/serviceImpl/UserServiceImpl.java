package com.simshine.service.serviceImpl;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.IdUtil;
import cn.hutool.log.Log;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.simshine.mapper.UserMapper;
import com.simshine.mode.UserModel;
import com.simshine.service.UserService;
import com.simshine.util.EncryptUtils;
import com.simshine.util.Md5Util;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName UserServiceImpl
 * @Description TODO
 * @Author hh
 * @Date 2019/9/16 0016 11:27
 * @Version 1.0
 **/
@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    /**
     * 用户注册
     * @param user 用户详情
     * @return
     */
    @Override
    public boolean register(UserModel user) {
        if(StringUtils.isNoneBlank(user.getPhone()) && StringUtils.isNoneBlank(user.getPwd())){
            Date current = new Date();
            //密码加密存储
            user.setUserId(IdUtil.objectId());
            user.setPwd(EncryptUtils.sha256Crypt(user.getPwd(),null));
            user.setCreateTime(current);
            user.setStatus(1);
            userMapper.insert(user);
            LOGGER.info("用户注册 --> " + user);
            return true;
        }
        return false;
    }

    /**
     * 用户登录
     * @param username 用户名
     * @param password 密码
     * @return
     */
    @Override
    public Map<String,Object> checkLogin(String username, String password) {
        //返回信息
        Map<String,Object> result = new HashMap<>(2);
        LOGGER.info(MessageFormat.format("用户登录 --> username:{0},password:{1}",username,password));

        QueryWrapper<UserModel> qw = new QueryWrapper<>();
        qw.eq("user_name",username);
        UserModel userModel = userMapper.selectOne(qw);
        if(null == userModel){
            return result;
        }
        result.put("result", EncryptUtils.checkSha256Crypt(password, userModel.getPwd()));
        result.put("user", userModel);
        return result;
    }
}
