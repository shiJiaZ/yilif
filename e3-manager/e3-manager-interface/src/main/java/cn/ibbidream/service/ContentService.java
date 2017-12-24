package cn.ibbidream.service;

import cn.ibbidream.common.EasyUIDataGridResult;
import cn.ibbidream.pojo.TbContent;
import cn.ibbidream.utils.E3Result;

import java.util.List;

/**
 * @Description:
 * @author: ShiJia
 * @version: 1.0
 * @Company: http://www.ibbidream.cn
 * @date: 2017年12月24日
 */
public interface ContentService {

    EasyUIDataGridResult getContentList(Integer page, Integer rows,Long categoryId);
    E3Result addContent(TbContent content);
    E3Result toContentEdit(TbContent content);
    E3Result deleteContent(String[] ids);
    List<TbContent> getContentList(Long cid);
}
