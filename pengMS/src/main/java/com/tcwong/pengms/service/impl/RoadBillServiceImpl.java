package com.tcwong.pengms.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tcwong.pengms.base.WebPageResponse;
import com.tcwong.pengms.constant.AddressTypeEnum;
import com.tcwong.pengms.constant.DeletedEnum;
import com.tcwong.pengms.constant.RoadBillStateEnum;
import com.tcwong.pengms.dao.AddressMapper;
import com.tcwong.pengms.dao.CostMapper;
import com.tcwong.pengms.dao.GoodsMapper;
import com.tcwong.pengms.dao.RoadBillMapper;
import com.tcwong.pengms.dto.address.AddressAddRequest;
import com.tcwong.pengms.dto.bill.RoadBillAddRequest;
import com.tcwong.pengms.dto.bill.RoadBillDeleteRequest;
import com.tcwong.pengms.dto.bill.RoadBillEditRequest;
import com.tcwong.pengms.dto.bill.RoadBillPageRequest;
import com.tcwong.pengms.dto.cost.CostAddRequest;
import com.tcwong.pengms.dto.goods.GoodsAddRequest;
import com.tcwong.pengms.model.*;
import com.tcwong.pengms.model.example.RoadBillExample;
import com.tcwong.pengms.service.RoadBillService;
import com.tcwong.pengms.utils.PengContext;
import com.tcwong.pengms.utils.SnowFlakeUtil;
import java.time.LocalDateTime;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Description 运单服务
 *
 * @author tcwong
 * @date 2021/3/22 Since 1.8
 */
@Service
public class RoadBillServiceImpl implements RoadBillService {
    @Resource private RoadBillMapper roadBillMapper;
    @Resource private GoodsMapper goodsMapper;
    @Resource private CostMapper costMapper;
    @Resource private AddressMapper addressMapper;

    /**
     * Description 运单添加
     *
     * @param request 运单添加请求
     * @return Integer 成功条数
     * @author tcwong
     * @date 2021/3/22
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Integer addRoadBill(RoadBillAddRequest request) {
        User user = PengContext.getUser();
        LocalDateTime nowDate = LocalDateTime.now();
        Long billNo = SnowFlakeUtil.getId();
        RoadBill roadBill =
                RoadBill.builder()
                        .billNo(billNo)
                        .state(RoadBillStateEnum.CREATED.getState())
                        .startTime(request.getStartTime())
                        .endTime(request.getEndTime())
                        .remark(request.getRemark())
                        .isDelete(DeletedEnum.UN_DELETED.getState())
                        .updateBy(user.getUsername())
                        .createBy(user.getUsername())
                        .updateTime(nowDate)
                        .createTime(nowDate)
                        .build();
        AddressAddRequest deliverAddressAdd = request.getDeliverAddressAdd();
        Address deliverAddress =
                Address.builder()
                        .billNo(billNo)
                        .companyName(deliverAddressAdd.getCompanyName())
                        .address(deliverAddressAdd.getAddress())
                        .person(deliverAddressAdd.getPerson())
                        .phone(deliverAddressAdd.getPhone())
                        .type(AddressTypeEnum.DELIVER.getType())
                        .isDelete(DeletedEnum.UN_DELETED.getState())
                        .updateBy(user.getUsername())
                        .createBy(user.getUsername())
                        .updateTime(nowDate)
                        .createTime(nowDate)
                        .build();
        AddressAddRequest receiverAddressAdd = request.getReceiverAddressAdd();
        Address receiverAddress =
                Address.builder()
                        .billNo(billNo)
                        .companyName(receiverAddressAdd.getCompanyName())
                        .address(receiverAddressAdd.getAddress())
                        .person(receiverAddressAdd.getPerson())
                        .phone(receiverAddressAdd.getPhone())
                        .type(AddressTypeEnum.RECEIVER.getType())
                        .isDelete(DeletedEnum.UN_DELETED.getState())
                        .updateBy(user.getUsername())
                        .createBy(user.getUsername())
                        .updateTime(nowDate)
                        .createTime(nowDate)
                        .build();
        CostAddRequest costAdd = request.getCostAdd();
        Cost cost =
                Cost.builder()
                        .billNo(billNo)
                        .insurance(costAdd.getInsurance())
                        .transport(costAdd.getTransport())
                        .extra(costAdd.getExtra())
                        .total(null)
                        .isDelete(DeletedEnum.UN_DELETED.getState())
                        .updateBy(user.getUsername())
                        .createBy(user.getUsername())
                        .updateTime(nowDate)
                        .createTime(nowDate)
                        .build();
        GoodsAddRequest goodsAdd = request.getGoodsAdd();
        Goods goods =
                Goods.builder()
                        .billNo(billNo)
                        .name(goodsAdd.getName())
                        .amount(goodsAdd.getAmount())
                        .weight(goodsAdd.getWeight())
                        .volume(goodsAdd.getVolume())
                        .isDelete(DeletedEnum.UN_DELETED.getState())
                        .updateBy(user.getUsername())
                        .createBy(user.getUsername())
                        .updateTime(nowDate)
                        .createTime(nowDate)
                        .build();
        addressMapper.insertSelective(deliverAddress);
        addressMapper.insertSelective(receiverAddress);
        costMapper.insertSelective(cost);
        goodsMapper.insertSelective(goods);
        return roadBillMapper.insertSelective(roadBill);
    }

    /**
     * Description 运单编辑
     *
     * @param request 运单编辑请求
     * @return Integer 成功条数
     * @author tcwong
     * @date 2021/3/22
     */
    @Override
    public Integer editRoadBill(RoadBillEditRequest request) {
        User user = PengContext.getUser();
        LocalDateTime nowDate = LocalDateTime.now();
        RoadBill roadBill =
                RoadBill.builder()
                        .id(request.getId())
                        .updateBy(user.getUsername())
                        .updateTime(nowDate)
                        .build();
        return roadBillMapper.updateByPrimaryKeySelective(roadBill);
    }

    /**
     * Description 运单分页查询
     *
     * @param request 运单分页查询参数
     * @return WebPageResponse 运单分页结果集
     * @author tcwong
     * @date 2021/3/22
     */
    @Override
    public WebPageResponse pageRoadBill(RoadBillPageRequest request) {
        RoadBillExample roadBillExample = new RoadBillExample();
        roadBillExample.setOrderByClause("id DESC");
        RoadBillExample.Criteria criteria = roadBillExample.createCriteria();
        if (ObjectUtil.isNotEmpty(request.getState())) {
            criteria.andStateEqualTo(request.getState());
        }
        PageHelper.startPage(request.getPageNum(), request.getPageSize());
        List<RoadBill> roadBillList = roadBillMapper.selectByExample(roadBillExample);
        PageInfo<RoadBill> roadBillPageInfo = new PageInfo<>(roadBillList);
        return new WebPageResponse(roadBillPageInfo.getTotal(), roadBillPageInfo.getList());
    }

    /**
     * Description 运单删除
     *
     * @param request 运单删除请求
     * @return Integer 成功条数
     * @author tcwong
     * @date 2021/3/22
     */
    @Override
    public Integer deleteRoadBill(RoadBillDeleteRequest request) {
        User user = PengContext.getUser();
        LocalDateTime nowDate = LocalDateTime.now();
        RoadBill roadBill =
                RoadBill.builder()
                        .isDelete(DeletedEnum.DONE_DELETED.getState())
                        .updateBy(user.getUsername())
                        .updateTime(nowDate)
                        .build();
        RoadBillExample roadBillExample = new RoadBillExample();
        RoadBillExample.Criteria criteria = roadBillExample.createCriteria();
        criteria.andIdIn(request.getIdList()).andIsDeleteEqualTo(DeletedEnum.UN_DELETED.getState());
        return roadBillMapper.updateByExample(roadBill, roadBillExample);
    }
}
