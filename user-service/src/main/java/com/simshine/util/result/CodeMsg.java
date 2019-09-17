package com.simshine.util.result;

public class CodeMsg {

    private int code;

    private String msg;

    public static CodeMsg SUCCESS = new CodeMsg(0 , "成功");
    public static CodeMsg SERVER_ERROR = new CodeMsg(500100, "服务端异常");
    public static CodeMsg BIND_ERROR = new CodeMsg(500101, "参数校验异常");

    //登录相关
    public static CodeMsg PASSWORD_EMPTY = new CodeMsg(500211, "登录密码不能为空");
    public static CodeMsg LOGIN = new CodeMsg(500212, "请先登录");
    public static CodeMsg LOGIN_ERROR = new CodeMsg(500229, "登录失败");

    public static CodeMsg REGISTER_SUCCESS = new CodeMsg(500312, "注册成功");
    public static CodeMsg REGISTER_ERROR = new CodeMsg(500313, "注册失败");


    public CodeMsg( ) {
    }

    public CodeMsg(int code , String msg){
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "CodeMsg{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }
}
