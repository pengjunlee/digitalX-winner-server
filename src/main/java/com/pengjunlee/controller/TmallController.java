package com.pengjunlee.controller;

import com.pengjunlee.domain.BaseResponse;
import com.pengjunlee.service.TmallService;
import com.pengjunlee.utils.PageUtil;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


/**
 * @author pengjunlee
 * @create 2019-09-03 10:47
 */

@RestController
@RequestMapping("/api/v1/tmall")
public class TmallController {

    @Autowired
    private TmallService tmallService;

    @GetMapping("/shop/list")
    @RequiresRoles(value = {"admin"})
    public Object shopList(@RequestParam Map<String, Object> params) {
        PageUtil pageUtil = tmallService.pageShopByCond(params);
        BaseResponse<Object> ret = new BaseResponse<Object>(pageUtil);
        ret.setCode(0);
        ret.setMessage("店铺数据加载成功");
        return ret;
    }

    @GetMapping("/shop/update/{shopId}")
    @RequiresRoles(value = {"admin"})
    public Object shopUpdate(@PathVariable(name = "shopId") Long shopId) {
        tmallService.updateShopById(shopId);
        BaseResponse<Object> ret = new BaseResponse<Object>();
        ret.setCode(0);
        ret.setMessage("店铺数据更新成功");
        return ret;
    }

    @GetMapping("/goods/list")
    @RequiresRoles(value = {"admin"})
    public Object goodsList(@RequestParam Map<String, Object> params) {
        System.out.println(params);
        PageUtil pageUtil = tmallService.pageGoodsByCond(params);
        BaseResponse<Object> ret = new BaseResponse<Object>(pageUtil);
        ret.setCode(0);
        ret.setMessage("宝贝数据加载成功");
        return ret;
    }


}