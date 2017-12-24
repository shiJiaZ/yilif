package cn.ibbidream.service.Impl;

import cn.ibbidream.common.EasyUITreeNode;
import cn.ibbidream.mapper.TbItemCatMapper;
import cn.ibbidream.pojo.TbItemCat;
import cn.ibbidream.pojo.TbItemCatExample;
import cn.ibbidream.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @author: ShiJia
 * @version: 1.0
 * @Company: http://www.ibbidream.cn
 * @date: 2017年12月23日
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {

    @Autowired
    private TbItemCatMapper itemCatMapper;

    @Override
    public List<EasyUITreeNode> getItemCatTree(Long parentId) {

        //根据父节点查询节点列表

        /**
         * mybaties 逆向工程工具类
         */
        TbItemCatExample example = new TbItemCatExample();

        //创建查询条件
        TbItemCatExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);

        //执行查询
        List<TbItemCat> tbItemCats = itemCatMapper.selectByExample(example);

        List<EasyUITreeNode> list = new ArrayList<>();
        //创建存储对象
        for (TbItemCat cat:tbItemCats) {
            EasyUITreeNode easyUITreeNode = new EasyUITreeNode();
            easyUITreeNode.setId(cat.getId());
            easyUITreeNode.setText(cat.getName());
            easyUITreeNode.setState(cat.getIsParent()?"closed":"open");
            list.add(easyUITreeNode);
        }

        return list;
    }
}
