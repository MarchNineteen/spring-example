package com.wyb.exception.handler;

import com.alibaba.fastjson.JSONObject;
import com.wyb.exception.exception.BizException;
import com.wyb.exception.exception.NoAuth;
import com.wyb.exception.result.BizResultEnum;
import com.wyb.exception.result.WebResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Kunzite
 */
@Slf4j
@ControllerAdvice
public class GlobExceptionHandler {

    @ExceptionHandler(BizException.class)
    @ResponseBody
    public WebResult<String> handleUnexpectedServerError(BizException ex) {
        log.error("<=====================biz业务处理异常========================>");
        ex.printStackTrace();
        log.error("exception detail info :" + ex);
        log.error("exception detail info :" + JSONObject.toJSONString(ex));
        log.error("<=====================biz业务处理异常========================>");
        WebResult<String> webResult = new WebResult<String>(BizResultEnum.SERVER_EXCEPTION);
        webResult.setData(ex.getLocalizedMessage());
        webResult.setMessage(ex.getMessage());
        webResult.setResultCode(ex.getCode());
        return webResult;
    }



    @ExceptionHandler(NoAuth.class)
    @ResponseBody
    public WebResult<String> NoAuthError(NoAuth ex) {
        log.error("<=====================用户未登录异常========================>");
        ex.printStackTrace();
        log.error("exception detail info :" + ex);
        log.error("exception detail info :" + JSONObject.toJSONString(ex));
        log.error("<=====================用户未登录异常========================>");
        WebResult<String> webResult = new WebResult<String>(BizResultEnum.SESSION_MISS);
        return webResult;
    }

    /**
     * @param ex
     * @return
     * @Description: 全局异常控制，记录日志
     * 任何一个方法发生异常，一定会被这个方法拦截到。然后，输出日志。封装Map并返回给页面显示错误信息：
     * 特别注意：返回给页面错误信息只在开发时才使用，上线后，要把错误页面去掉，只打印log日志即可，防止信息外漏
     */
    @ResponseBody
    @ExceptionHandler(Throwable.class)
    public WebResult<String> handleInvalidRequestError(Throwable ex) {
        log.error("<=====================error 服务器内部异常========================>");
        ex.printStackTrace();
        log.error("exception detail info :" + ex);
        log.error("exception detail info :" + JSONObject.toJSONString(ex));
        log.error("<=====================error 服务器内部异常========================>");
        WebResult<String> webResult = new WebResult<String>(BizResultEnum.ILLEGAL_ARGUMENT);
        String msg = "服务器内部异常";
        webResult.setMessage(msg);
        webResult.setData(ex.getLocalizedMessage());
        webResult.setResultCode(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));

        return webResult;
    }


//    /**
//     * @param ex
//     * @return
//     * @Description: 全局异常控制，记录日志
//     * 任何一个方法发生异常，一定会被这个方法拦截到。然后，输出日志。封装Map并返回给页面显示错误信息：
//     * 特别注意：返回给页面错误信息只在开发时才使用，上线后，要把错误页面去掉，只打印log日志即可，防止信息外漏
//     */
//    @ExceptionHandler(Exception.class)
//    public ModelAndView handleInvalidRequestError(Exception ex) {
//        log.error("<=====================error 服务器内部异常========================>");
//        ex.printStackTrace();
//        log.error("exception detail :" + ex);
//        log.error("exception detail :" + JSONObject.toJSONString(ex));
//        log.error("<=====================error 服务器内部异常========================>");
//        WebResult<String> webResult = new WebResult<>(BizResultEnum.ILLEGAL_ARGUMENT);
//        String msg = ex.getMessage();
//        webResult.setMessage(msg);
//        webResult.setData(ex.getLocalizedMessage());
//        webResult.setResultCode(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
//        ModelAndView mode = new ModelAndView("error-page");
//        mode.addObject("webResult", webResult);
//        return mode;
//    }
}
