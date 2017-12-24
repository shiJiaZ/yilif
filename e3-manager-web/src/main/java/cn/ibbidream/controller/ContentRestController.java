package cn.ibbidream.controller;

import cn.ibbidream.pojo.TbContent;
import cn.ibbidream.service.ContentService;
import cn.ibbidream.utils.E3Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description:
 * @author: ShiJia
 * @version: 1.0
 * @Company: http://www.ibbidream.cn
 * @date: 2017年12月24日
 */
@Controller
public class ContentRestController {

    @Autowired
    private ContentService contentService;

    @RequestMapping(value = "/rest/content/edit")
    @ResponseBody
    public E3Result toContentEdit(TbContent content){
        E3Result result = contentService.toContentEdit(content);
        return result;
    }

    @RequestMapping(value = "/content/delete")
    @ResponseBody
    public E3Result delete(String ids){
        String[] split = ids.split(",");
        E3Result result = contentService.deleteContent(split);
        return result;
    }

}
