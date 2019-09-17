package com.simcam.adminservice.service;


import com.simcam.adminservice.model.SysUser;

import java.util.Map;

/**
 * @author Administrator
 */
public interface AccessTokenService {

    Map<String, Object> createToken(SysUser sysUser);

}
