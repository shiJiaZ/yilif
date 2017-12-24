package cn.ibbidream.controller;

import cn.ibbidream.common.EasyUIDataGridResult;
import cn.ibbidream.pojo.TbItem;
import cn.ibbidream.service.ItemService;
import cn.ibbidream.utils.E3Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
     * 异步请求数据
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


    /**
     * 新增操作
     * @param item
     * @param desc
     * @return
     */
    @RequestMapping(value = "/save",method = {RequestMethod.POST})
    @ResponseBody
    public E3Result addItem(TbItem item,String desc){
        E3Result result = itemService.addItem(item, desc);
        return result;
    }
}