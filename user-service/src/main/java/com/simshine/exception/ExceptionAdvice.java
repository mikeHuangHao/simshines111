package com.simshine.exception;

import com.simshine.util.result.CodeMsg;
import com.simshine.util.result.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

/**
 * 捕获全局异常
 * @author Administrator
 */
@ControllerAdvice
public class ExceptionAdvice {

    /**
     * 拦截捕捉自定义异常 ConstraintViolationException.class
     *
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = ConstraintViolationException.class)
    public Result<Object> ConstraintViolationExceptionHandler(HttpServletResponse response, ConstraintViolationException ex) throws IOException {
        Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
        Iterator<ConstraintViolation<?>> iterator = constraintViolations.iterator();
        //List<String> msgList = new ArrayList<>();
        while (iterator.hasNext()) {
            ConstraintViolation<?> cvl = iterator.next();
            //msgList.add(cvl.getMessageTemplate());
            return Result.error(new CodeMsg(500280, cvl.getMessageTemplate()));
        }
        return  Result.error(new CodeMsg(500280, ""));
    }
}