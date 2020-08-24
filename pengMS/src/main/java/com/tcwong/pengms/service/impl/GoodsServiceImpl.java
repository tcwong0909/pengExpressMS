package com.tcwong.pengms.service.impl;

import com.tcwong.pengms.dao.GoodsMapper;
import com.tcwong.pengms.model.Goods;
import com.tcwong.pengms.service.GoodsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {
    @Resource
    private GoodsMapper goodsMapper;
    @Override
    public List<Goods> getAll() {
        return goodsMapper.selectByExample(null);
    }

    @Override
    public int addGoods(Goods goods) {
        return goodsMapper.insert(goods);
    }

    @Override
    public int editGoods(Goods goods) {
        return goodsMapper.updateByPrimaryKeySelective(goods);
    }

    @Override
    public int deleteGoods(String ids) {
        String[] fkIds = ids.split(",");
        goodsMapper.deleteByFkIds(fkIds);
        return fkIds.length;

    }
}
