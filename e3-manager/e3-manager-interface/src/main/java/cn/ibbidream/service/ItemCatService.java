package cn.ibbidream.service;

import cn.ibbidream.common.EasyUITreeNode;

import java.util.List;

/**
 * @Description:
 * @author: ShiJia
 * @version: 1.0
 * @Company: http://www.ibbidream.cn
 * @date: 2017年12月23日
 */

public interface ItemCatService {

    List<EasyUITreeNode> getItemCatTree(Long parentId);

}
