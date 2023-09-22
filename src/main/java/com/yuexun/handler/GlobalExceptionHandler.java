package com.yuexun.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import com.yuexun.exception.ResponseException;
import com.yuexun.result.ResponseResult;
import com.yuexun.result.ResultCodeEnum;

import java.util.ArrayList;
import java.util.List;

/**
 * @apiNote 全局异常处理类
 * @author zzd
 * @date 2023-03-01 16:11
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * @apiNote 全局异常
     * @param e : 异常
     * @return {@link ResponseResult }
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseResult error(Exception e) throws Exception {
        e.printStackTrace();
        //针对于捕捉不到AccessDeniedHandler的情况，直接向上抛出
        if ("不允许访问".equals(e.getMessage())) {
            throw e;
        }
        return ResponseResult.error();
    }

    /**
     * @apiNote 指定异常
     * @param e : 异常
     * @return {@link ResponseResult }
     */
    @ExceptionHandler(ResponseException.class)
    @ResponseBody
    public ResponseResult error(ResponseException e) {
        e.printStackTrace();
        return ResponseResult.error(e.getCode(), e.getMessage());
    }

    /**
     * @apiNote 参数校验异常
     * @param e e
     * @return {@link ResponseResult }
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseResult error(MethodArgumentNotValidException e) {
        log.error("MethodArgumentNotValidException: ", e);
        return handleBindingResult(e.getBindingResult());
    }

    private ResponseResult handleBindingResult(BindingResult result) {
        //把异常处理为对外暴露的提示
        List<String> list = new ArrayList<>();
        //是不是包含错误
        if (result.hasErrors()) {
            List<ObjectError> allErrors = result.getAllErrors();
            for (ObjectError objectError : allErrors) {
                String message = objectError.getDefaultMessage();
                list.add(message);
            }
        }
        if (list.size() == 0) {
            return ResponseResult.error(ResultCodeEnum.PARAM_NOT_COMPLETE);
        }
        return ResponseResult.error(ResultCodeEnum.PARAM_NOT_COMPLETE.getCode(), list.toString());
    }


    /**
     * @apiNote 处理非法参数异常
     * @param e e
     * @return {@link ResponseResult }
     */
    @ExceptionHandler(value = IllegalArgumentException.class)
    @ResponseBody
    public ResponseResult handleIllegalArgumentException(IllegalArgumentException e) {
        e.printStackTrace();
        return ResponseResult.error(e.getMessage());
    }

}
