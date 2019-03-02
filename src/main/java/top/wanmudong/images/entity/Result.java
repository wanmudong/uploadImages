package top.wanmudong.images.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;


import java.io.Serializable;
import java.util.HashMap;

/**
 * @author wanmudong
 * @date 18:41 2019/2/27
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result  extends HashMap<String,Object> implements Serializable {

    public Result(boolean success,String message,  Object data) {
        this.put("success", success);
        this.put("message", message);
        this.put("data",data);
    }

    public static Result OK(){
        return new Result(true,"操作成功",null);
    }


    public static Result OK(Object data){
        return new Result(true,"操作成功",data);
    }

    public static Result Error(String message){
        return new Result(false,message,null);
    }
    @Override
    public Result put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
