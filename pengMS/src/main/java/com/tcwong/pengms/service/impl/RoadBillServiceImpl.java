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
import com.tcwong.pengms.dto.address.AddressResponse;
import com.tcwong.pengms.dto.bill.*;
import com.tcwong.pengms.dto.cost.CostAddRequest;
import com.tcwong.pengms.dto.cost.CostResponse;
import com.tcwong.pengms.dto.goods.GoodsAddRequest;
import com.tcwong.pengms.model.*;
import com.tcwong.pengms.model.example.AddressExample;
import com.tcwong.pengms.model.example.CostExample;
import com.tcwong.pengms.model.example.RoadBillExample;
import com.tcwong.pengms.service.RoadBillService;
import com.tcwong.pengms.utils.PengContext;
import com.tcwong.pengms.utils.SnowFlakeUtil;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
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

        BigDecimal totalCost = new BigDecimal(0);
        if (ObjectUtil.isNotNull(costAdd.getExtra())) {
            totalCost = totalCost.add(costAdd.getExtra());
        }
        if (ObjectUtil.isNotNull(costAdd.getInsurance())) {
            totalCost = totalCost.add(costAdd.getInsurance());
        }
        if (ObjectUtil.isNotNull(costAdd.getTransport())) {
            totalCost = totalCost.add(costAdd.getTransport());
        }
        Cost cost =
                Cost.builder()
                        .billNo(billNo)
                        .insurance(costAdd.getInsurance())
                        .transport(costAdd.getTransport())
                        .extra(costAdd.getExtra())
                        .total(totalCost)
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
        if (ObjectUtil.isNotEmpty(roadBillPageInfo.getList())) {
            List<RoadBill> billList = roadBillPageInfo.getList();
            List<Long> billNoList =
                    billList.stream().map(RoadBill::getBillNo).collect(Collectors.toList());
            AddressExample addressExample = new AddressExample();
            addressExample.createCriteria().andBillNoIn(billNoList);
            List<Address> addressList = addressMapper.selectByExample(addressExample);
            CostExample costExample = new CostExample();
            costExample.createCriteria().andBillNoIn(billNoList);
            List<Cost> costList = costMapper.selectByExample(costExample);
            return new WebPageResponse(
                    roadBillPageInfo.getTotal(),
                    convertBillResponse(billList, addressList, costList));
        }
        return new WebPageResponse(roadBillPageInfo.getTotal(), roadBillPageInfo.getList());
    }

    private List<?> convertBillResponse(
            List<RoadBill> billList, List<Address> addressList, List<Cost> costList) {
        Map<Long, List<Address>> addressMap =
                addressList.stream().collect(Collectors.groupingBy(Address::getBillNo));
        Map<Long, List<Cost>> costMap =
                costList.stream().collect(Collectors.groupingBy(Cost::getBillNo));
        List<RoadBillResponse> billResponseList = new ArrayList<>();
        billList.forEach(
                roadBill -> {
                    AddressResponse deliverAddress = null;
                    AddressResponse receiverAddress = null;
                    CostResponse cost = null;
                    if (ObjectUtil.isNotEmpty(addressMap.get(roadBill.getBillNo()))) {
                        List<Address> addressItemList = addressMap.get(roadBill.getBillNo());
                        for (Address address : addressItemList) {
                            if (AddressTypeEnum.DELIVER.getType().equals(address.getType())) {
                                deliverAddress =
                                        AddressResponse.builder()
                                                .address(address.getAddress())
                                                .companyName(address.getCompanyName())
                                                .person(address.getPerson())
                                                .phone(address.getPhone())
                                                .build();
                            }
                            if (AddressTypeEnum.RECEIVER.getType().equals(address.getType())) {
                                receiverAddress =
                                        AddressResponse.builder()
                                                .address(address.getAddress())
                                                .companyName(address.getCompanyName())
                                                .person(address.getPerson())
                                                .phone(address.getPhone())
                                                .build();
                            }
                        }
                    }
                    if (ObjectUtil.isNotEmpty(costMap.get(roadBill.getBillNo()))) {
                        List<Cost> costItemList = costMap.get(roadBill.getBillNo());
                        Cost costItem = costItemList.get(0);
                        cost =
                                CostResponse.builder()
                                        .insurance(costItem.getInsurance())
                                        .extra(costItem.getExtra())
                                        .transport(costItem.getTransport())
                                        .total(costItem.getTotal())
                                        .build();
                    }
                    RoadBillResponse roadBillResponse =
                            RoadBillResponse.builder()
                                    .id(roadBill.getId())
                                    .billNo(roadBill.getBillNo())
                                    .startTime(roadBill.getStartTime())
                                    .endTime(roadBill.getEndTime())
                                    .state(roadBill.getState())
                                    .stateName(RoadBillStateEnum.getMessage(roadBill.getState()))
                                    .remark(roadBill.getRemark())
                                    .updateBy(roadBill.getUpdateBy())
                                    .createBy(roadBill.getCreateBy())
                                    .updateTime(roadBill.getUpdateTime())
                                    .createTime(roadBill.getCreateTime())
                                    .cost(cost)
                                    .deliverAddress(deliverAddress)
                                    .receiverAddress(receiverAddress)
                                    .build();
                    billResponseList.add(roadBillResponse);
                });
        return billResponseList;
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
