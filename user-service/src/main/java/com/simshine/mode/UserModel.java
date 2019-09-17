package com.simshine.mode;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @ClassName UserModel
 * @Description 用户
 * @Author hh
 * @Date 2019/9/16 0016 11:17
 * @Version 1.0
 **/
@TableName("simcam_user")
@Data
public class UserModel {

    /**
     * 用户唯一值
     */
    @TableId
    private String userId;
    /**
     * 用户名称
     */
    @NotBlank(message = "用户名称不能为空",groups = {RegisterGroup.class})
    private String userName;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空",groups = {LoginGroup.class,RegisterGroup.class})
    private String pwd;
    /**
     * 手机/邮箱
     */
    @NotBlank(message = "用户名称不能为空",groups = {LoginGroup.class,RegisterGroup.class})
    private String phone;
    /**
     * APPID
     */
    private String registerId;
    /**
     * 图片
     */
    private String img;
    /**
     * 1:有效，0无效
     */
    private Integer	status;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 场景：主账号下面有多个字账号，  p_id代表主账号的唯一标识，p_id=0  or null 说明此数据没有子账号
     */
    private Integer pId;
    /**
     * 来电电话
     */
    private String callPhone;
    /**
     * 修改时间
     */
    private Date updateTime;
    /**
     * 异步情况，1 异步了部分，2 异步完成了
     */
    private String async;
    /**
     * 类型 0 SIMCAM 1 门铃 2 大数据  9 其他
     */
    @NotNull(message = "类型不能为空",groups = {RegisterGroup.class})
    private Integer type;
    /**
     * 邮箱
     */
    private String email;

    /**
     * 登录分组
     */
    public interface LoginGroup{}

    /**
     * 注册分组
     */
    public interface RegisterGroup{}

}
