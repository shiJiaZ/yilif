package cn.ibbidream.service.Impl;

import cn.ibbidream.common.EasyUIDataGridResult;
import cn.ibbidream.mapper.TbContentMapper;
import cn.ibbidream.pojo.TbContent;
import cn.ibbidream.pojo.TbContentExample;
import cn.ibbidream.service.ContentService;
import cn.ibbidream.utils.E3Result;
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
 * @date: 2017年12月24日
 */
@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private TbContentMapper contentMapper;

    @Override
    public EasyUIDataGridResult getContentList(Integer page, Integer rows, Long categoryId) {
        //设置分页信息
        PageHelper.startPage(page,rows);

        //执行查询
        //添加条件
        TbContentExample example = new TbContentExample();
        TbContentExample.Criteria criteria = example.createCriteria();
        criteria.andCategoryIdEqualTo(categoryId);
        List<TbContent> list = contentMapper.selectByExampleWithBLOBs(example);

        //取分页信息  把所有信息给分页，然后分页给你信息
        PageInfo<TbContent> pageInfo = new PageInfo<TbContent>(list);

        //创建返回结果对象   把分页好的信息返回去
        EasyUIDataGridResult result = new EasyUIDataGridResult();
        result.setTotal(pageInfo.getTotal());
        result.setRows(list);
        return result;
    }

    @Override
    public E3Result addContent(TbContent content) {
        Date date = new Date();
        //补充内容
        content.setCreated(date);
        content.setUpdated(date);
        //保存
        contentMapper.insert(content);
        return E3Result.ok();
    }

    @Override
    public E3Result toContentEdit(TbContent content) {

        //根据ID查询数据
        TbContent tbContent = contentMapper.selectByPrimaryKey(content.getId());

        //更新必要的信息
        content.setUpdated(new Date());
        content.setCreated(tbContent.getCreated());
        //保存信息
        contentMapper.updateByPrimaryKeyWithBLOBs(content);
        return E3Result.ok();
    }

    @Override
    public E3Result deleteContent(String[] ids) {

        for (String i:ids) {
            long id = Long.parseLong(i);
            contentMapper.deleteByPrimaryKey(id);
        }

        return E3Result.ok();
    }

}
