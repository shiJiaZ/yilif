package cn.ibbidream.service;

import cn.ibbidream.common.EasyUITreeNode;
import cn.ibbidream.pojo.TbContentCategory;
import cn.ibbidream.utils.E3Result;

import java.util.List;

/**
 * @Description:
 * @author: ShiJia
 * @version: 1.0
 * @Company: http://www.ibbidream.cn
 * @date: 2017年12月24日
 */
public interface ContentCategoryService {

    List<EasyUITreeNode> getCateGoryList(Long parentId);
    E3Result addCategory(TbContentCategory category);
    E3Result updateCategory(TbContentCategory category);
    E3Result deleteCategory(Long id);
}
