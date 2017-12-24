package cn.ibbidream.service.Impl;

import cn.ibbidream.common.EasyUITreeNode;
import cn.ibbidream.mapper.TbContentCategoryMapper;
import cn.ibbidream.pojo.TbContentCategory;
import cn.ibbidream.pojo.TbContentCategoryExample;
import cn.ibbidream.service.ContentCategoryService;
import cn.ibbidream.utils.E3Result;
import cn.ibbidream.utils.IDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Description:
 * @author: ShiJia
 * @version: 1.0
 * @Company: http://www.ibbidream.cn
 * @date: 2017年12月24日
 */
@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {

    @Autowired
    private TbContentCategoryMapper categoryMapper;

    @Override
    public List<EasyUITreeNode> getCateGoryList(Long parentId) {

        //查询条件
        TbContentCategoryExample example = new TbContentCategoryExample();
        TbContentCategoryExample.Criteria criteria = example.createCriteria();

        criteria.andParentIdEqualTo(parentId);

        //查询
        List<TbContentCategory> categoryList = categoryMapper.selectByExample(example);

        //创建List<EasyUITreeNode> node
        List<EasyUITreeNode> nodes = new ArrayList<>();

        for (TbContentCategory category:categoryList) {

            EasyUITreeNode node = new EasyUITreeNode();
            //{id:1,text:节点名称,state:open(closed)
            node.setId(category.getId());
            node.setState(category.getIsParent()?"closed":"open");
            node.setText(category.getName());
            nodes.add(node);
        }

        return nodes;
    }

    @Override
    public E3Result addCategory(TbContentCategory category) {

        //补全TbContentCategory对象的属性
        category.setIsParent(false);
        //排列序号，表示同级类目的展现次序，如数值相等则按名称次序排列。取值范围:大于零的整数
        category.setSortOrder(1);
        //状态。可选值:1(正常),2(删除)
        category.setStatus(1);
        category.setCreated(new Date());
        category.setUpdated(new Date());
        //插入数据
        categoryMapper.insert(category);
        // 3、判断父节点的isparent是否为true，不是true需要改为true。
        TbContentCategory categoryParent = categoryMapper.selectByPrimaryKey(category.getParentId());
        if(!categoryParent.getIsParent()){
            categoryParent.setIsParent(true);
            //更新父节点
            categoryMapper.updateByPrimaryKey(categoryParent);
        }
        return E3Result.ok(category);
    }

    @Override
    public E3Result updateCategory(TbContentCategory category) {
        //根据ID查询
        TbContentCategory result = categoryMapper.selectByPrimaryKey(category.getId());
        //更新数据
        result.setUpdated(new Date());
        result.setName(category.getName());
        //保存数据
        categoryMapper.updateByPrimaryKey(result);
        return E3Result.ok();
    }

    @Override
    public E3Result deleteCategory(Long id) {

        //根据id查找自己
        TbContentCategory category = categoryMapper.selectByPrimaryKey(id);
        //根据parentID找出父亲
        TbContentCategory parentCategory = categoryMapper.selectByPrimaryKey(category.getParentId());

        //递归删除
        deleteContgoryById(id);

        //判断是否有其余子节点，没有将isParent 改为false
        TbContentCategoryExample example = new TbContentCategoryExample();
        TbContentCategoryExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentCategory.getId());

        List<TbContentCategory> categoryList = categoryMapper.selectByExample(example);
        if(categoryList.size()==0){
            parentCategory.setIsParent(false);
            categoryMapper.updateByPrimaryKey(parentCategory);
        }

        return E3Result.ok();
    }

    public void deleteContgoryById(Long id){
        TbContentCategoryExample example = new TbContentCategoryExample();
        TbContentCategoryExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(id);
        List<TbContentCategory> categoryList = categoryMapper.selectByExample(example);
        for (TbContentCategory catgory:categoryList) {
            deleteContgoryById(catgory.getId());
        }
        //自杀
        categoryMapper.deleteByPrimaryKey(id);

    }

}
