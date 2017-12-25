package cn.ibbidream.search.Exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description:
 * @author: ShiJia
 * @version: 1.0
 * @Company: http://www.ibbidream.cn
 * @date: 2017年12月25日
 */
@Controller
public class GlobalHandlerExceptionResolver implements HandlerExceptionResolver {
    Logger logger = LoggerFactory.getLogger(GlobalHandlerExceptionResolver.class);

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object obj, Exception ex) {

        //写日志文件
        logger.error("系统发生异常",ex);
        //发邮件
        //Jmail
        //需要购买短信，调用第三方接口
        //展示错误页面
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message","系统发生异常，请稍后重试");
        modelAndView.setViewName("error/exception");
        return modelAndView;
    }

}
