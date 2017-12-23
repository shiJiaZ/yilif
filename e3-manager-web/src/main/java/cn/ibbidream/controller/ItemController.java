package cn.ibbidream.controller;

import cn.ibbidream.common.EasyUIDataGridResult;
import cn.ibbidream.pojo.TbItem;
import cn.ibbidream.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.Serializable;

/**
 * @Description:
 * @author: ShiJia
 * @version: 1.0
 * @Company: http://www.ibbidream.cn
 * @date: 2017年12月22日
 */
@Controller
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @RequestMapping("/{item}")
    @ResponseBody
    public TbItem getItemById(@PathVariable long item){

        TbItem result = itemService.getItemById(item);

        return result;
    }

    /**
     * Ajax  异步请求数据
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public EasyUIDataGridResult  getItemList(Integer  page,Integer rows){

        EasyUIDataGridResult result = itemService.getItemList(page, rows);

        return result;
    }

}