package com.simcam.adminservice.util.result;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.io.IOException;
import java.util.Iterator;

public class ResponseUtil {

    /**
     * 将JSON对象写入到response
     * @param response
     * @param object
     * @throws IOException
     */
   /* public static void writeJson(HttpServletResponse response , Object object) throws IOException {
        Gson gson = new Gson();
        String result = gson.toJson(object);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(result);
    }*/

    /**
     * validate检验参数
     * @param result
     * @throws IOException
     */
    public static String isParameter( BindingResult result) throws IOException {
        if(result.hasErrors()) {
            Iterator<ObjectError> iterator =  result.getAllErrors().iterator();
            while (iterator.hasNext()) {
                ObjectError objectError = iterator.next();
                //System.out.println("[错误信息]" + objectError.getCode() + "," + objectError.getDefaultMessage());
                //System.out.print(result.getAllErrors());
                //Result.error(new CodeMsg(500280,objectError.getDefaultMessage()));
                return objectError.getDefaultMessage();
            }
        }
        return "";
    }

}
