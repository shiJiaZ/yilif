package cn.ibbidream.service.Impl;

import cn.ibbidream.common.EasyUIDataGridResult;
import cn.ibbidream.mapper.TbItemDescMapper;
import cn.ibbidream.mapper.TbItemMapper;
import cn.ibbidream.pojo.TbItem;
import cn.ibbidream.pojo.TbItemDesc;
import cn.ibbidream.pojo.TbItemExample;
import cn.ibbidream.service.ItemService;
import cn.ibbidream.utils.E3Result;
import cn.ibbidream.utils.IDUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    @Autowired
    private TbItemDescMapper itemDescMapper;

    @Override
    public TbItem getItemById(long id) {

        TbItem item = itemMapper.selectByPrimaryKey(id);

        return item;
    }

    @Override
    public EasyUIDataGridResult getItemList(Integer page, Integer rows) {

        //设置分页信息
        PageHelper.startPage(page,rows);

        //执行查询
        //添加条件
        TbItemExample example = new TbItemExample();
        TbItemExample.Criteria criteria = example.createCriteria();
        criteria.andStatusNotEqualTo((byte) 3);

        List<TbItem> list = itemMapper.selectByExample(example);

        //取分页信息  把所有信息给分页，然后分页给你信息
        PageInfo<TbItem> pageInfo = new PageInfo<>(list);

        //创建返回结果对象   把分页好的信息返回去
        EasyUIDataGridResult result = new EasyUIDataGridResult();
        result.setTotal(pageInfo.getTotal());
        result.setRows(list);

        return result;
    }

    @Override
    public E3Result addItem(TbItem item, String desc) {
        Date date = new Date();
        // 1、生成商品id
        long itemId = IDUtils.genItemId();
        // 2、补全TbItem对象的属性
        item.setId(itemId);
        item.setCreated(date);
        item.setUpdated(date);
        //商品状态，1-正常，2-下架，3-删除
        item.setStatus((byte) 1);
        // 3、向商品表插入数据
        itemMapper.insert(item);
        // 4、创建一个TbItemDesc对象
        TbItemDesc tbItemDesc = new TbItemDesc();
        // 5、补全TbItemDesc的属性
        tbItemDesc.setItemId(itemId);
        tbItemDesc.setItemDesc(desc);
        tbItemDesc.setCreated(date);
        tbItemDesc.setUpdated(date);
        // 6、向商品描述表插入数据
        itemDescMapper.insert(tbItemDesc);
        // 7、E3Result.ok()
        return E3Result.ok();
    }

    @Override
    public TbItemDesc getItemDescById(long id) {
        TbItemDesc tbItemDesc = itemDescMapper.selectByPrimaryKey(id);
        return tbItemDesc;
    }

    @Override
    public E3Result updateItem(TbItem item,String desc) {
        // 1、item更新数据
        item.setUpdated(new Date());
        itemMapper.updateByPrimaryKeySelective(item);

        // 2、item_desc 更新数据
        TbItemDesc tbItemDesc = itemDescMapper.selectByPrimaryKey(item.getId());
        tbItemDesc.setItemDesc(desc);
        tbItemDesc.setUpdated(new Date());
        itemDescMapper.updateByPrimaryKeySelective(tbItemDesc);
        // 3、E3Result.ok()
        return E3Result.ok();
    }

    @Override
    public E3Result deleteItem(String[] ids) {
        for (String i:ids) {
            long id = Long.parseLong(i);

            System.out.println(id);
            TbItem item = itemMapper.selectByPrimaryKey(id);
            //商品状态，1-正常，2-下架，3-删除
            item.setStatus((byte) 3);
            itemMapper.updateByPrimaryKeySelective(item);
        }
        return E3Result.ok();
    }

    @Override
    public E3Result instock(String[] ids) {
        for (String i:ids) {
            long id = Long.parseLong(i);

            System.out.println(id);
            TbItem item = itemMapper.selectByPrimaryKey(id);
            //商品状态，1-正常，2-下架，3-删除
            item.setStatus((byte) 2);
            itemMapper.updateByPrimaryKeySelective(item);
        }
        return E3Result.ok();
    }

    @Override
    public E3Result reshelf(String[] ids) {
        for (String i:ids) {
            long id = Long.parseLong(i);

            System.out.println(id);
            TbItem item = itemMapper.selectByPrimaryKey(id);
            //商品状态，1-正常，2-下架，3-删除
            item.setStatus((byte) 1);
            itemMapper.updateByPrimaryKeySelective(item);
        }
        return E3Result.ok();
    }
}
