package cn.ibbidream.controller;

import cn.ibbidream.pojo.TbItem;
import cn.ibbidream.pojo.TbItemDesc;
import cn.ibbidream.service.ItemService;
import cn.ibbidream.utils.E3Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
public class ItemRestController {


    @Autowired
    private ItemService itemService;

    @RequestMapping(value = "/rest/page/item-edit",method = {RequestMethod.GET})
    public String toItemEdit(){
        return "item-edit";
    }

    @RequestMapping(value = "/rest/item/query/item/desc/{id}")
    @ResponseBody
    public E3Result getItemById(@PathVariable(value = "id") Long id){
        TbItemDesc itemDesc = itemService.getItemDescById(id);
        return E3Result.ok(itemDesc);
    }

    @RequestMapping(value = "/rest/item/param/item/query/{descId}")
    @ResponseBody
    public TbItemDesc getItemDescById(@PathVariable(value = "descId")Long descId){
        return null;
    }

    @RequestMapping(value = "/rest/item/update")
    @ResponseBody
    public E3Result update(TbItem item,String desc){
        E3Result result = itemService.updateItem(item,desc);
        System.out.println(result);
        return result;
    }

    @RequestMapping(value = "/rest/item/delete")
    @ResponseBody
    public E3Result delete(String ids){
        String[] split = ids.split(",");
        E3Result result = itemService.deleteItem(split);
        return result;
    }

    @RequestMapping(value = "/rest/item/instock")
    @ResponseBody
    public E3Result instock(String ids){
        String[] split = ids.split(",");
        E3Result result = itemService.instock(split);
        return result;
    }

    @RequestMapping(value = "/rest/item/reshelf")
    @ResponseBody
    public E3Result reshelf(String ids){
        String[] split = ids.split(",");
        E3Result result = itemService.reshelf(split);
        return result;
    }
}