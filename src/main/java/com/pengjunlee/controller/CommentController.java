package com.pengjunlee.controller;

import com.pengjunlee.domain.*;
import com.pengjunlee.service.CommentService;
import com.pengjunlee.utils.TmallUtil;
import com.pengjunlee.utils.PageUtil;
import com.pengjunlee.utils.RedisUtil;
import org.apdplat.word.WordSegmenter;
import org.apdplat.word.segmentation.Word;
import org.apdplat.word.segmentation.WordRefiner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @author pengjunlee
 * @create 2019-09-04 11:00
 */
@RequestMapping("/api/v1/comment")
@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private RedisUtil redisUtil;

    private static final String RATEWORD_PREFIX = "RATEWORD_";


    @GetMapping(value = "/goods")
    public Object listGoods(@RequestParam("page") int page) {
        PageUtil pageUtil = commentService.pageCommentGoods(page);
        BaseResponse<Object> ret = new BaseResponse<Object>(pageUtil);
        ret.setCode(0);
        ret.setMessage("数据加载成功");
        return ret;
    }

    @GetMapping(value = "/{goodsId}")
    public Object lisComments(@PathVariable(name = "goodsId") String goodsId, @RequestParam("page") int page) {
        PageUtil pageUtil = commentService.pageCommentByGoods(goodsId, page);
        BaseResponse<Object> ret = new BaseResponse<Object>(pageUtil);
        ret.setCode(0);
        ret.setMessage("数据加载成功");
        return ret;
    }


    @GetMapping(value = "/rateword/{goodsId}")
    public Object lisRatewords(@PathVariable(name = "goodsId") String goodsId) {

        String key = RATEWORD_PREFIX + goodsId;


        List<RateWord> values = null;
        if (redisUtil.hasKey(key)) {
            Object o = redisUtil.get(key);
            if (o != null) {
                values = (List<RateWord>) o;
            }
        } else {
            List<CommentEntity> CommentsList = commentService.listCommentByGoods(goodsId);
            Map<String, RateWord> map = new HashMap<String, RateWord>();
            for (CommentEntity comment : CommentsList) {
                String rateContent = comment.getRateContent();
                if (TmallUtil.isValidComment(rateContent)) {
                    processRateword(rateContent, map);
                }
                CommentAppendEntity appendComment = comment.getAppendComment();
                if (null != appendComment) {
                    processRateword(appendComment.getContent(), map);
                }
            }
            List<RateWord> list = new ArrayList<>(map.values());
            Collections.sort(list);
            if (list.size() > 100) {
                values = new ArrayList<RateWord>(list.subList(0, 100));
            }
            redisUtil.set(key, values);
        }
        BaseResponse<Object> ret = new BaseResponse<Object>();

        Map<String, Object> data = new HashMap<>();
        data.put("ratewords", values);
        ret.setData(data);
        ret.setCode(0);
        ret.setMessage("数据加载成功");
        return ret;
    }

    private void processRateword(String rateContent, Map<String, RateWord> map) {
        List<Word> words = WordSegmenter.seg(rateContent);
        words = WordRefiner.refine(words);
        for (Word word : words) {
            RateWord rateWord = map.get(word.getText());
            if (rateWord == null) {
                rateWord = new RateWord(word.getText());
            } else {
                rateWord.addWeight(1);
            }
            map.put(word.getText(), rateWord);
        }
    }
}
