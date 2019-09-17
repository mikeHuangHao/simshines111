package com.simcam.adminservice.util.result;


import lombok.Data;
import org.apache.commons.lang3.StringUtils;

/**
 * 自定义泛型
 * @param <T>
 */
@Data
public class Result<T> {

    private int code;
    private String msg;
    private T data;
    private String attach;

    private Result(T data,String attach) {
        if(StringUtils.isNotBlank(attach)){
        }else{
            this.data = data;
        }
        this.attach = attach;
    }

    private Result(CodeMsg codeMsg) {
        if(codeMsg != null){
            this.msg = codeMsg.getMsg();
            this.code = codeMsg.getCode();
        }
    }

    /**
     * 成功的时候调用
     * @param data
     * @param <T>
     * @return
     */
    //其中<T>是为了定义当前我有一个 范型变量类型，类型名使用T来表示，而第二部分T，表示method这个函数的返回值类型为T，其中的<T>只是为了在函
    //其中<T.是定义一个泛型变量的一个列席，类型名用 T 来表示
    //第一个T 表示定义一个泛型类型的变量名字为T 第二个表示返回类型用T表示 ， 三个表示传入的类型 Object任何
    public static <T> Result<T> success(T data){
        return new Result<T>(data,"");
    }

    /**
     * 成功的时候调用 附加数据
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Result<T> successAdd(T data,String attach){
        return new Result<T>(data,attach);
    }

    /**
     * 失败时候调用
     * @param codeMsg
     * @param <T>
     * @return
     */
    public static  <T> Result<T> error(CodeMsg codeMsg){
        return new Result<T>(codeMsg);
    }

}
