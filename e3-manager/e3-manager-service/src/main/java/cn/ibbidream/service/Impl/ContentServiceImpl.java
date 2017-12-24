package cn.ibbidream.service.Impl;

import cn.ibbidream.common.EasyUIDataGridResult;
import cn.ibbidream.jedis.JedisClient;
import cn.ibbidream.jedis.JedisClientPool;
import cn.ibbidream.mapper.TbContentMapper;
import cn.ibbidream.pojo.TbContent;
import cn.ibbidream.pojo.TbContentExample;
import cn.ibbidream.service.ContentService;
import cn.ibbidream.utils.E3Result;
import cn.ibbidream.utils.JsonUtils;
import com.alibaba.dubbo.common.utils.StringUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Autowired
    private JedisClient jedis;

    @Value("${CONTENT_KEY}")
    private String CONTENT_KEY;

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

        //数据变动 删除缓存
        jedis.hdel(CONTENT_KEY,content.getCategoryId().toString());

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

        //数据变动 删除缓存
        jedis.hdel(CONTENT_KEY,content.getCategoryId().toString());

        return E3Result.ok();
    }

    @Override
    public E3Result deleteContent(String[] ids) {

        for (String i:ids) {
            long id = Long.parseLong(i);

            TbContent content = contentMapper.selectByPrimaryKey(id);
            //数据变动 删除缓存
            jedis.hdel(CONTENT_KEY,content.getCategoryId().toString());

            contentMapper.deleteByPrimaryKey(id);
        }

        return E3Result.ok();
    }

    @Override
    public List<TbContent> getContentList(Long cid) {
        // 1、从缓存中取数据，如果有则直接返回
        try {
            String json = jedis.hget(CONTENT_KEY, cid + "");
            //判断json是否为空
            if(StringUtils.isNotEmpty(json)){
                //把json转换成list
                List<TbContent> list = JsonUtils.jsonToList(json, TbContent.class);
                return list;
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        TbContentExample example = new TbContentExample();
        TbContentExample.Criteria criteria = example.createCriteria();
        // 根据cid 查询列表
        criteria.andCategoryIdEqualTo(cid);

        List<TbContent> list = contentMapper.selectByExampleWithBLOBs(example);

        // 执行到这里，证明缓存中没有，将查询结果放到缓存中
        try {
            jedis.hset(CONTENT_KEY,cid + "", JsonUtils.objectToJson(list));
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

}
