package com.tcwong.pengms.controller;

import com.tcwong.pengms.base.WebResponse;
import com.tcwong.pengms.model.Goods;
import com.tcwong.pengms.service.GoodsService;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description 货物管理
 *
 * @author tcwong
 * @date 2021/3/22 Since 1.8
 */
@RestController
@RequestMapping("/pengms/goods/")
public class GoodsController {

    @Resource private GoodsService goodsService;

    /**
     * Description 货物列表
     *
     * @return
     * @author tcwong
     * @date 2021/3/22
     */
    @GetMapping("/list")
    public WebResponse getGoods() {
        List<Goods> goodsList = goodsService.getAll();
        return WebResponse.success(goodsList);
    }
}
