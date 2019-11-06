package com.pengjunlee.controller;


import ch.qos.logback.core.util.ContextUtil;
import com.pengjunlee.domain.TmallGoodsEntity;
import com.pengjunlee.domain.TmallShopEntity;
import com.pengjunlee.service.TmallService;
import com.pengjunlee.service.UserService;
import com.pengjunlee.utils.ExcelUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author pengjunlee
 * @create 2019-09-03 9:27
 */
@RequestMapping("/api/v1/download")
@RestController
@CrossOrigin
public class ExcelController {

    @Autowired
    private TmallService tmallService;

    /**
     * 导出商品数据
     *
     * @return
     */
    @GetMapping(value = "/goods")
    public void batchDownload(HttpServletResponse response,@RequestParam Map<String, Object> map) {
        //获取数据
        List<TmallGoodsEntity> tmallGoodsEntities = tmallService.listGoodsByCond(map);

        //excel标题
        String[] title = {"商品ID", "商品名称", "参考价格", "预售", "订金", "付订金立减", "预售数量", "预售订单总额", "评论数", "库存"};

        //excel文件名
        String fileName = "商品数据" + System.currentTimeMillis() + ".xls";

        //sheet名
        String sheetName = "商品数据表";

        String[][] content = new String[tmallGoodsEntities.size()][title.length];
        try {
        for (int i = 0; i < tmallGoodsEntities.size(); i++) {
            content[i] = new String[title.length];
            TmallGoodsEntity goodsEntity = tmallGoodsEntities.get(i);
            content[i][0] = goodsEntity.getId().toString();
            content[i][1] = goodsEntity.getTitle();
            content[i][2] = goodsEntity.getPrice().toString();
            content[i][3] = goodsEntity.getPreSale() ? "是":"否";
            if(goodsEntity.getPreSale()){
                content[i][4] = goodsEntity.getPreSaleCash().toString();
                content[i][5] = goodsEntity.getPreSaleDiscount()!=null?goodsEntity.getPreSaleDiscount().toString():"0";
                content[i][6] = goodsEntity.getPreSaleCount().toString();
                content[i][7] = goodsEntity.getPreSaleTotal().toString();
            }else{
                content[i][4] = "-";
                content[i][5] = "-";
                content[i][6] = "-";
                content[i][7] = "-";
            }
            content[i][8] = goodsEntity.getRateCount().toString();
            content[i][9] = goodsEntity.getTotalQuantity().toString();

        }
        //创建HSSFWorkbook
        HSSFWorkbook wb = ExcelUtil.getHSSFWorkbook(sheetName, title, content, null);


        //响应到客户端

            this.setResponseHeader(response, fileName);
            OutputStream os = response.getOutputStream();
            wb.write(os);
            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //发送响应流方法
    public void setResponseHeader(HttpServletResponse response, String fileName) {
        try {
            try {
                fileName = new String(fileName.getBytes(), "ISO8859-1");
            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            response.setContentType("application/octet-stream;charset=ISO8859-1");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
