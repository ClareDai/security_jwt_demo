package cn.claredai.exception.handler;

import cn.claredai.exception.BadRequestException;
import cn.claredai.util.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.*;

/**
 * @Author daixiaosong
 * @Date create in 14:08 2019/3/8
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 处理所有不可知的异常
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity handleException(Exception e){
        // 打印堆栈信息
        log.error("统一异常处理", e);
        return buildResponseEntity(JsonResult.fail(BAD_REQUEST.value(),e.getMessage()));
    }

    /**
     * 处理 接口无权访问异常AccessDeniedException
     * @param e
     * @return
     */
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity handleAccessDeniedException(AccessDeniedException e){
        // 打印堆栈信息
        log.error("无权访问", e);
        return buildResponseEntity(JsonResult.fail(FORBIDDEN.value(),e.getMessage()));
    }

    /**
     * 处理未登录异常AuthenticationException
     */
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity handleAuthenticationException(AuthenticationException e){
        // 打印堆栈信息
        log.error("未登录", e);
        return buildResponseEntity(JsonResult.fail(UNAUTHORIZED.value(),e.getMessage()));
    }

    /**
     * 处理自定义异常
     * @param e
     * @return
     */
    @ExceptionHandler(value = BadRequestException.class)
    public ResponseEntity badRequestException(BadRequestException e) {
        // 打印堆栈信息
        log.error("自定义异常", e);
        return buildResponseEntity(JsonResult.fail(e.getStatus(), e.getMessage()));
    }

    /**
     * 处理所有接口数据验证异常
     * @param e
     * @returns
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        // 打印堆栈信息
        log.error("接口数据验证异常", e);
        String[] str = e.getBindingResult().getAllErrors().get(0).getCodes()[1].split("\\.");
        StringBuffer msg = new StringBuffer(str[1]+":");
        msg.append(e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        return buildResponseEntity(JsonResult.fail(BAD_REQUEST.value(), e.getMessage()));
    }

    /**
     * 统一返回
     * @param jsonResult
     * @return
     */
    private ResponseEntity<JsonResult> buildResponseEntity(JsonResult jsonResult) {
        return new ResponseEntity(jsonResult, HttpStatus.valueOf(jsonResult.getStatus()));
    }

}
