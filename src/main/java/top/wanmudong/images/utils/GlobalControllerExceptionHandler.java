package top.wanmudong.images.utils;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import top.wanmudong.images.entity.Result;
import top.wanmudong.images.service.impl.ImagesServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wanmudong
 * @date 14:57 2019/3/1
 */
@ControllerAdvice
@Slf4j
public class GlobalControllerExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalControllerExceptionHandler.class);

    /**
     * 对抛出的未知异常进行统一返回
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Result defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {

        return Result.Error("未知错误，请稍后再试").put("data",e.getMessage());
    }

    /**
     * 检验参数时参数出现错误
     */
    @ResponseBody
    @ExceptionHandler(value = BindException.class)
    public Result defaultBindExceptionHandler(HttpServletRequest req, BindException e) {

        Map<String, String> messages = new HashMap<>();
        BindingResult result = e.getBindingResult();
        if (result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors();
            ObjectError error = errors.get(0);
            log.error(error.getDefaultMessage());
            return Result.Error("校验参数有误").put("data",error.getDefaultMessage());
        }
        return Result.Error("系统错误");
    }


}
