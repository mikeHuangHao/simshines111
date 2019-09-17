package com.simcam.adminservice.service;

import com.simcam.adminservice.model.SysUser;

public interface SysLoginService {

    public SysUser login(String username, String password);

}
