package com.tcwong.pengms.service;


import com.tcwong.pengms.model.Goods;

import java.util.List;

public interface GoodsService {
    List<Goods> getAll();

    int addGoods(Goods goods);

    int editGoods(Goods goods);

    int deleteGoods(String ids);
}
