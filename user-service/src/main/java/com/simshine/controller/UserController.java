package com.simshine.controller;

import cn.hutool.core.util.IdUtil;
import com.simshine.common.Constants;
import com.simshine.mode.UserModel;
import com.simshine.service.UserService;
import com.simshine.util.RedisUtils;
import com.simshine.util.result.CodeMsg;
import com.simshine.util.result.ResponseUtil;
import com.simshine.util.result.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName UserController
 * @Description TODO
 * @Author hh
 * @Date 2019/9/16 0016 11:09
 * @Version 1.0
 **/
@RestController
@Validated
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisUtils redis;

    /**
     * 12小时后过期
     */
    private final static long   EXPIRE        = 12 * 60 * 60;

    /**
     * 登录验证
     * @author hh
     * @date
     * @since 1.0.0
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    @PostMapping("/check")
    @ResponseBody
    public Result<Object> check(@Validated(UserModel.LoginGroup.class) UserModel userModel ,HttpServletRequest request ,
                                    BindingResult resultData) throws IOException {
        String validation = ResponseUtil.isParameter(resultData);
        if(StringUtils.isNotBlank(validation)){
            return Result.error(new CodeMsg(500101,validation));
        }
        //1. 登录验证
        Map<String,Object> checkMap = userService.checkLogin(userModel.getPhone(), userModel.getPwd());
        Boolean loginResult = (Boolean) checkMap.get("result");
        UserService sysUser = (UserService) checkMap.get("user");
        //登录验证通过
        if(loginResult != null && loginResult){
            //2. session中添加用户信息
            String token = IdUtil.simpleUUID();
            redis.set("app_access_token_" + token, sysUser, EXPIRE);
            Result.success(token);
        }else{
            return Result.error(CodeMsg.LOGIN_ERROR);
        }
        return Result.success("成功");
    }

    /**
     * 用户注册
     * @param userModel
     * @param result
     * @return
     */
    @PostMapping("register")
    public Result<Object> register(@Validated(UserModel.RegisterGroup.class) UserModel userModel , BindingResult result){
        try {
            String validation = ResponseUtil.isParameter(result);
            if(StringUtils.isNotBlank(validation)){
                return Result.error(new CodeMsg(500101,validation));
            }
            boolean register = userService.register(userModel);
            if (register) {
                return Result.success(CodeMsg.REGISTER_SUCCESS);
            } else {
                return Result.error(CodeMsg.REGISTER_ERROR);
            }
        }catch (Exception e){
            e.printStackTrace();
            return Result.error(CodeMsg.SERVER_ERROR);
        }
    }

}
