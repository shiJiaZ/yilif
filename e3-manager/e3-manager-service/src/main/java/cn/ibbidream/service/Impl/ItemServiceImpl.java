package cn.ibbidream.service.Impl;

import cn.ibbidream.mapper.TbItemMapper;
import cn.ibbidream.pojo.TbItem;
import cn.ibbidream.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @author: ShiJia
 * @version: 1.0
 * @Company: http://www.ibbidream.cn
 * @date: 2017年12月22日
 */
@Service(value = "itemService")
public class ItemServiceImpl implements ItemService {

    @Autowired
    private TbItemMapper itemMapper;

    @Override
    public TbItem getItemById(long id) {

        TbItem item = itemMapper.selectByPrimaryKey(id);

        return item;
    }
}
