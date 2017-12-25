package cn.ibbidream.controller;

import cn.ibbidream.common.EasyUIDataGridResult;
import cn.ibbidream.pojo.TbContent;
import cn.ibbidream.service.ContentService;
import cn.ibbidream.utils.E3Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Description:
 * @author: ShiJia
 * @version: 1.0
 * @Company: http://www.ibbidream.cn
 * @date: 2017年12月24日
 */
@Controller
@RequestMapping(value = "/content/")
public class ContentController {

    @Autowired
    private ContentService contentService;

    @RequestMapping(value = "query/list")
    @ResponseBody
    public EasyUIDataGridResult getContentList(Integer  page,Integer rows,Long categoryId ){
        EasyUIDataGridResult result = contentService.getContentList(page, rows,categoryId);
        return result;
    }

    @RequestMapping(value = "save")
    @ResponseBody
    public E3Result addContent(TbContent content){

        E3Result result = contentService.addContent(content);

        return result;
    }

    @RequestMapping(value = "test")
    @ResponseBody
    public List<TbContent> getTestList(Long cid){

        List<TbContent> list = contentService.getContentList(cid);

        return list;
    }


}
