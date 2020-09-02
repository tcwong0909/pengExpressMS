package com.tcwong.pengms.service.impl;

import com.tcwong.pengms.base.WebPageResponse;
import com.tcwong.pengms.dao.CarriersMapper;
import com.tcwong.pengms.model.Carriers;
import com.tcwong.pengms.model.Goods;
import com.tcwong.pengms.service.CarriersService;
import com.tcwong.pengms.service.GoodsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class CarriersServiceImpl implements CarriersService {
    @Resource
    private CarriersMapper carriersMapper;
    @Resource
    private GoodsService goodsService;
    @Override
    public int addCarrier(Carriers carriers) {
//        Integer userid = ((User) (SecurityUtils.getSubject().getSession().getAttribute("user"))).getUserid();
//        carriers.setFkUserid(userid);
        Goods goods = carriers.getGoods();
        carriers.setAltertime(new Date());
        carriers.setCheckintime(new Date());
        carriers.setFinishedstate(1);
        int num1 = carriersMapper.insert(carriers);
        goods.setFkCarriersid(carriers.getCarriersid());
        int num2 = goodsService.addGoods(goods);
        return num2;

    }

    @Override
    public int editCarrier(Carriers carriers) {
        Goods goods = carriers.getGoods();
        carriers.setAltertime(new Date());
        carriersMapper.updateByPrimaryKeySelective(carriers);
        return goodsService.editGoods(goods);
    }

    @Override
    public WebPageResponse getAllByPage(Integer page, Integer size, String sendcompany, String receivecompany, Integer finishedstate) {
        if (page != null && size != null) {
            page = (page-1)*size;
        }

        return new WebPageResponse(carriersMapper.getTotal(sendcompany,receivecompany,finishedstate),
                carriersMapper.getAllByPage(page,size,sendcompany,receivecompany,finishedstate));
    }

    @Override
    public int deleteCarrier(String ids) {
        String[] carrierIds = ids.split(",");
        carriersMapper.deleteByIds(carrierIds);
        return carrierIds.length ;
    }

    @Override
    public int reveiveById(Integer id) {
        Carriers carriers = new Carriers();
        carriers.setCarriersid(id);
        carriers.setFinishedstate(3);
        return carriersMapper.updateByPrimaryKeySelective(carriers);
    }
}
