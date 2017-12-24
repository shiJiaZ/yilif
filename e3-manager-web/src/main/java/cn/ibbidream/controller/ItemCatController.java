package cn.ibbidream.controller;

import cn.ibbidream.common.EasyUITreeNode;
import cn.ibbidream.service.ItemCatService;
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
 * @date: 2017年12月23日
 */
@Controller
@RequestMapping(value = "/item/cat")
public class ItemCatController {

    @Autowired
    private ItemCatService itemCatService;

    @RequestMapping("/list")
    @ResponseBody
    public List<EasyUITreeNode> getItemCatTree(@RequestParam(value = "id",defaultValue = "0") Long parentId){

        List<EasyUITreeNode> itemCatTree = itemCatService.getItemCatTree(parentId);

        return itemCatTree;

    }

}
