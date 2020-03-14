package com.wxw.engineer;

import com.wxw.engineer.util.RestResponse;
import com.wxw.engineer.util.RestStatus;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
@ResponseBody
@Log4j2
public class ExceptionAdvice
{


//    @Autowired
//    private InternationalizationUtil i18nUtil;

//    /**
//     * 处理BusinessException异常返回信息
//     *
//     * @param businessException
//     * @return
//     */
//    @ExceptionHandler(Exception.class)
//    @ResponseBody
//    public Result handleBusinessException(BusinessException businessException) {
//        String message = businessException.getMessage();
//        Integer errorCode = businessException.getCode();
//        if (StringUtils.isEmpty(errorCode.toString())) {
//            errorCode = SystemErrorCode.SYSTEM_ERROR;
//        }
//        String resultMessage = i18nUtil.i18n(errorCode+"",businessException.getArgs());
//        logger.info("业务异常:{}-{}-{}", errorCode, message, resultMessage);
//        return new Result(errorCode, resultMessage);
//    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public Object handle(MethodArgumentNotValidException e, HttpServletResponse response)
    {

        response.setStatus(500);
        String errMsg = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        log.error("运行时异常:", errMsg);
//        response.
        return new ResponseEntity(errMsg, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Object ExceptionHandle(Exception exception, HttpServletResponse response)
    {
        log.error("运行时异常:", exception);
//        response.setStatus(500);
//        response.
        return new RestResponse(RestStatus.INTERNAL_SERVER_ERROR, "", exception.getMessage());
    }
}
