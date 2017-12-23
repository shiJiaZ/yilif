package cn.ibbidream.service.Impl;

import cn.ibbidream.common.EasyUIDataGridResult;
import cn.ibbidream.mapper.TbItemMapper;
import cn.ibbidream.pojo.TbItem;
import cn.ibbidream.pojo.TbItemExample;
import cn.ibbidream.service.ItemService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @author: ShiJia
 * @version: 1.0
 * @Company: http://www.ibbidream.cn
 * @date: 2017年12月22日
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private TbItemMapper itemMapper;

    @Override
    public TbItem getItemById(long id) {

        TbItem item = itemMapper.selectByPrimaryKey(id);

        return item;
    }

    @Override
    public EasyUIDataGridResult getItemList(Integer page, Integer rows) {

        //设置分页信息
        PageHelper.startPage(page,rows);

        ////执行查询
        TbItemExample example = new TbItemExample();
        List<TbItem> list = itemMapper.selectByExample(example);

        //取分页信息  把所有信息给分页，然后分页给你信息
        PageInfo<TbItem> pageInfo = new PageInfo<>(list);

        //创建返回结果对象   把分页好的信息返回去
        EasyUIDataGridResult result = new EasyUIDataGridResult();
        result.setTotal(pageInfo.getTotal());
        result.setRows(list);

        return result;
    }
}
