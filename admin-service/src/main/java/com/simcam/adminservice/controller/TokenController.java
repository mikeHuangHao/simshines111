package com.simcam.adminservice.controller;

import com.simcam.adminservice.form.LoginForm;
import com.simcam.adminservice.model.SysUser;
import com.simcam.adminservice.service.serviceImpl.AccessTokenServiceImpl;
import com.simcam.adminservice.service.serviceImpl.SysLoginServiceImpl;
import com.simcam.adminservice.util.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;


/**
 * @author hh
 */
@RestController
@RequestMapping("/api/auth")
public class TokenController
{
    @Autowired
    private AccessTokenServiceImpl tokenService;

    @Autowired
    private SysLoginServiceImpl sysLoginService;

    @RequestMapping("login")
    public Result<Object> login(LoginForm form){
        // 用户登录
        SysUser user = sysLoginService.login(form.getUsername(), form.getPassword());
        // 获取登录token
        Map<String, Object> token = tokenService.createToken(user);
        return Result.success(token);
    }

   /* @PostMapping("logout")
    public R logout(HttpServletRequest request)
    {
        String token=request.getHeader("token");
        SysUser user=tokenService.queryByToken(token);
        if (null != user)
        {
            sysLoginService.logout(user.getLoginName());
            tokenService.expireToken(user.getUserId());
        }
        return R.ok();
    }*/
}
