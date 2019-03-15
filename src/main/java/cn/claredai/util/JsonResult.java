package cn.claredai.util;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author daixiaosong
 * @Date create in 15:36 2019/3/7
 */
@Getter
@Setter
public class JsonResult<T> {
    private int status;
    private String msg;
    private T data;

    public static final int STATUS_SUCCESS = 200;
    public static final int STATUS_ERROR = 201;
    public static final int NO_AUTHORITIES = 401;
    public static final int NO_PERMISSION = 403;

    public static final String MSG_SUCCESS = "请求成功";

    public static final String MSG_SYSTEM_ERROR = "系统异常";

    public JsonResult(){
        this.status = STATUS_SUCCESS;
        this.msg = MSG_SUCCESS;
    }

    public JsonResult(T data){
        this.status = STATUS_SUCCESS;
        this.msg = MSG_SUCCESS;
        this.data = data;
    }

    public JsonResult(int status){
        this.status = status;
        this.msg = MSG_SYSTEM_ERROR;
    }

    public JsonResult(int status, String msg){
        this.status = status;
        this.msg = msg;
    }

    public JsonResult(int status, String msg, T data){
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public static JsonResult success(){
        return new JsonResult();
    }

    public static JsonResult success(String msg){
        return new JsonResult(STATUS_SUCCESS, msg);
    }

    public static <T> JsonResult success(T data){
        return new JsonResult(data);
    }

    public static <T> JsonResult success(String msg, T data){
        return new JsonResult(STATUS_SUCCESS, msg, data);
    }

    public static JsonResult fail(){
        return new JsonResult(STATUS_ERROR);
    }

    public static JsonResult fail(String msg){
        return new JsonResult(STATUS_ERROR, msg);
    }

    public static JsonResult fail(int status, String msg){
        return new JsonResult(status, msg);
    }
}
