package com.tcwong.pengms.controller;

import com.tcwong.pengms.base.WebResponse;
import com.tcwong.pengms.model.Goods;
import com.tcwong.pengms.service.GoodsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 货物
 */
@RestController
@RequestMapping("/pengms")
public class GoodsController {

    @Resource
    private GoodsService goodsService;

    @GetMapping("/goodslist")
    public WebResponse getGoods(){
        List<Goods> goodsList = goodsService.getAll();
        return WebResponse.success(goodsList);
    }
}
