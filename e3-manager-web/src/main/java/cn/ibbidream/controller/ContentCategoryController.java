package cn.ibbidream.controller;

import cn.ibbidream.common.EasyUITreeNode;
import cn.ibbidream.pojo.TbContentCategory;
import cn.ibbidream.service.ContentCategoryService;
import cn.ibbidream.utils.E3Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
@RequestMapping(value = "/content/category/")
public class ContentCategoryController {

    @Autowired
    private ContentCategoryService categoryService;

    @RequestMapping(value = "list")
    @ResponseBody
    public List<EasyUITreeNode> getCategoryList (@RequestParam(value = "id",defaultValue = "0") Long parentId){

        List<EasyUITreeNode> cateGoryList = categoryService.getCateGoryList(parentId);
        return cateGoryList;
    }

    @RequestMapping(value = "create")
    @ResponseBody
    public E3Result addCategory(TbContentCategory category){
        E3Result result = categoryService.addCategory(category);
        return result;
    }

    @RequestMapping(value = "update")
    @ResponseBody
    public E3Result updateCategory(TbContentCategory category){
        E3Result result = categoryService.updateCategory(category);
        return result;
    }

    @RequestMapping(value = "delete")
    @ResponseBody
    public E3Result deleteCategory(Long id){
        E3Result result = categoryService.deleteCategory(id);
        return result;
    }

}
