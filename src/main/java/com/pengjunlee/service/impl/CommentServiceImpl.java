package com.pengjunlee.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pengjunlee.domain.CommentEntity;
import com.pengjunlee.domain.CommentGoodsEntity;
import com.pengjunlee.domain.TmallGoodsEntity;
import com.pengjunlee.service.CommentService;
import com.pengjunlee.service.mapper.CommentMapper;
import com.pengjunlee.utils.PageUtil;
import com.pengjunlee.utils.TmallUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @author pengjunlee
 * @create 2019-09-03 17:19
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, CommentGoodsEntity> implements CommentService {

    @Resource
    private CommentMapper CommentMapper;

    @Autowired
    private MongoTemplate mongoTemplate;


    @Override
    public PageUtil pageCommentGoodsByCond(Map<String, Object> map) {
        Integer offset = TmallUtil.getOffsetFromParams(map);
        map.put("offset", offset);
        List<CommentGoodsEntity> commentGoodsEntities = CommentMapper.pageCommentGoods(map);
        int count = CommentMapper.countCommentGoods(map);
        return new PageUtil(commentGoodsEntities, count);
    }


    @Override
    public List<CommentEntity> listCommentByGoods(Long goodsId) {
        // 获取数据
        Query query = new Query(Criteria.where("goodsId").is(goodsId));
        List<CommentEntity> commentEntities = mongoTemplate.find(query, CommentEntity.class, "tmallRateEntity");
        return commentEntities;
    }

    @Override
    public PageUtil pageCommentByGoods(Map<String, Object> map) {
        // 获取数据条数
        Query countQuery = getCommentQueryFromMap(map, false);
        int count = mongoTemplate.find(countQuery, CommentEntity.class, "tmallRateEntity").size();

        Query pageQuery = getCommentQueryFromMap(map, true);
        Integer offset = TmallUtil.getOffsetFromParams(map);
        pageQuery.skip(offset);
        pageQuery.limit(new Integer(map.get("limit").toString()));
        List<CommentEntity> commentEntities = mongoTemplate.find(pageQuery, CommentEntity.class, "tmallRateEntity");
        return new PageUtil(commentEntities, count);
    }

    private Query getCommentQueryFromMap(Map<String, Object> map, boolean sorted) {
        // 获取数据条数
        Query query = new Query(Criteria.where("goodsId").is(Long.parseLong(map.get("goodsId").toString())));
        if (map.get("content") != null && map.get("content").toString().trim() !="") {
            Pattern pattern = Pattern.compile("^.*" + map.get("content").toString().trim() + ".*$", Pattern.CASE_INSENSITIVE);
            query.addCriteria(new Criteria().orOperator(Criteria.where("content").regex(pattern), Criteria.where("appendComment.content").regex(pattern)));
        }
        if (map.get("appendComment") != null && map.get("appendComment").toString().trim() != "") {
            if(map.get("appendComment").toString().equals("1")){
                query.addCriteria(Criteria.where("appendComment").ne(null));
            }else{
                query.addCriteria(Criteria.where("appendComment").is(null));
            }

        }
        if (sorted) {
            String sort_by = map.get("sortBy") == null ? "rateDate" : map.get("sortBy").toString();
            String sort_order = map.get("sortOrder") == null ? "DESC" : map.get("sortOrder").toString();
            query.with(new Sort(Sort.Direction.valueOf(sort_order), sort_by));
        }
        return query;
    }
}
