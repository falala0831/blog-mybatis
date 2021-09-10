package com.bigbone.blog.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常处理
 */
@ControllerAdvice
public class ExceptionInterceptor {
    //将异常记录到日志
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * @Description 处理错误信息
     * @param httpServletRequest
     * @param e
     * @return 返回错误信息页面
     * @throws Exception
     */
    @ExceptionHandler(Exception.class)
    public ModelAndView exception(HttpServletRequest httpServletRequest, Exception e) throws Exception {
        //记录的异常格式为{请求url，异常}
        logger.error("Request URL : {}, Exception : {}", httpServletRequest.getRequestURL(), e);

        //有定义ResponseStatus时交给Spring处理，即标识状态码时不拦截
        if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null) {
            throw e;
        }
        //剩余异常返回给error页面
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("url", httpServletRequest.getRequestURL());
        modelAndView.addObject("exception", e);
        modelAndView.setViewName("error/error");
        return modelAndView;
    }
}
