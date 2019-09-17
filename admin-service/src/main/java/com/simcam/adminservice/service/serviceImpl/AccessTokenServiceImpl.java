package com.simcam.adminservice.service.serviceImpl;

import cn.hutool.core.util.IdUtil;
import com.simcam.adminservice.constants.Constants;
import com.simcam.adminservice.model.SysUser;
import com.simcam.adminservice.service.AccessTokenService;
import com.simcam.adminservice.util.RedisUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator
 */
@Service
public class AccessTokenServiceImpl implements AccessTokenService{
    @Autowired
    private RedisUtils redis;

    /**
     * 12小时后过期
     */
    private final static long   EXPIRE        = 12 * 60 * 60;

    private final static String ACCESS_TOKEN  = Constants.ACCESS_TOKEN;

    private final static String ACCESS_USERID = Constants.ACCESS_USERID;

    public SysUser queryByToken(String token)
    {
        return redis.get(ACCESS_TOKEN + token, SysUser.class);
    }

    @Override
    public Map<String, Object> createToken(SysUser sysUser){
        // 生成token
        String token = IdUtil.fastSimpleUUID();
        // 保存或更新用户token
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userId", sysUser.getUserId());
        map.put("token", token);
        map.put("expire", EXPIRE);
        // expireToken(userId);
        redis.set(ACCESS_TOKEN + token, sysUser, EXPIRE);
        redis.set(ACCESS_USERID + sysUser.getUserId(), token, EXPIRE);
        return map;
    }

    public void expireToken(long userId){
        String token = redis.get(ACCESS_USERID + userId);
        if (StringUtils.isNotBlank(token))
        {
            redis.delete(ACCESS_USERID + userId);
            redis.delete(ACCESS_TOKEN + token);
        }
    }
}