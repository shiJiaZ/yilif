package cn.ibbidream.service;

import cn.ibbidream.common.EasyUIDataGridResult;
import cn.ibbidream.pojo.TbItem;
import cn.ibbidream.pojo.TbItemDesc;
import cn.ibbidream.utils.E3Result;

/**
 * @Description:
 * @author: ShiJia
 * @version: 1.0
 * @Company: http://www.ibbidream.cn
 * @date: 2017年12月22日
 */
public interface ItemService {
    TbItem getItemById(long id);

    EasyUIDataGridResult getItemList(Integer page, Integer rows);

    E3Result addItem(TbItem item,String desc);

    TbItemDesc getItemDescById(long id);

    E3Result updateItem(TbItem item,String desc);

    E3Result deleteItem(String[] ids);

    E3Result instock(String[] ids);

    E3Result reshelf(String[] ids);



}
