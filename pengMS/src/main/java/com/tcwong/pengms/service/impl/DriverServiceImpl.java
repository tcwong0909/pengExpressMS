package com.tcwong.pengms.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tcwong.pengms.base.WebPageResponse;
import com.tcwong.pengms.constant.DeletedEnum;
import com.tcwong.pengms.dao.DriverMapper;
import com.tcwong.pengms.dto.driver.DriverAddRequest;
import com.tcwong.pengms.dto.driver.DriverDeleteRequest;
import com.tcwong.pengms.dto.driver.DriverEditRequest;
import com.tcwong.pengms.dto.driver.DriverPageRequest;
import com.tcwong.pengms.model.Driver;
import com.tcwong.pengms.model.DriverExample;
import com.tcwong.pengms.model.User;
import com.tcwong.pengms.service.DriverService;
import com.tcwong.pengms.utils.PengContext;
import java.time.LocalDateTime;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * Description 司机服务
 *
 * @author tcwong
 * @date 2021/3/20 Since 1.8
 */
@Service
public class DriverServiceImpl implements DriverService {

    @Resource private DriverMapper driverMapper;
    /**
     * Description 添加司机
     *
     * @param request 司机添加请求
     * @return Integer 成功条数
     * @author tcwong
     * @date 2021/3/20
     */
    @Override
    public Integer addDriver(DriverAddRequest request) {
        User user = PengContext.getUser();
        LocalDateTime nowDate = LocalDateTime.now();
        Driver driver =
                Driver.builder()
                        .name(request.getName())
                        .sex(request.getSex())
                        .birthTime(request.getBirthTime())
                        .phoneNo(request.getPhoneNo())
                        .identityNo(request.getIdentityNo())
                        .state(request.getState())
                        .remark(request.getRemark())
                        .checkTime(nowDate)
                        .isDelete(DeletedEnum.UN_DELETED.getState())
                        .updateBy(user.getUsername())
                        .createBy(user.getUsername())
                        .updateTime(nowDate)
                        .createTime(nowDate)
                        .build();
        return driverMapper.insertSelective(driver);
    }

    /**
     * Description 删除司机
     *
     * @param request 司机删除参数
     * @return Integer 成功条数
     * @author tcwong
     * @date 2021/3/20
     */
    @Override
    public Integer deleteDriver(DriverDeleteRequest request) {
        Driver driver = Driver.builder().isDelete(DeletedEnum.DONE_DELETED.getState()).build();
        DriverExample driverExample = new DriverExample();
        driverExample
                .createCriteria()
                .andIdIn(request.getIdList())
                .andIsDeleteEqualTo(DeletedEnum.UN_DELETED.getState());
        return driverMapper.updateByExampleSelective(driver, driverExample);
    }

    /**
     * Description 司机编辑
     *
     * @param request 司机编辑参数
     * @return Integer 成功条数
     * @author tcwong
     * @date 2021/3/20
     */
    @Override
    public Integer editDriver(DriverEditRequest request) {
        User user = PengContext.getUser();
        LocalDateTime nowDate = LocalDateTime.now();
        Driver driver =
                Driver.builder()
                        .id(request.getId())
                        .name(request.getName())
                        .sex(request.getSex())
                        .birthTime(request.getBirthTime())
                        .phoneNo(request.getPhoneNo())
                        .identityNo(request.getIdentityNo())
                        .state(request.getState())
                        .remark(request.getRemark())
                        .checkTime(nowDate)
                        .updateBy(user.getUsername())
                        .updateTime(nowDate)
                        .build();
        return driverMapper.updateByPrimaryKeySelective(driver);
    }

    /**
     * Description 司机分页查询
     *
     * @param request 司机分页查询请求
     * @return 司机分页结果
     * @author tcwong
     * @date 2021/3/20
     */
    @Override
    public WebPageResponse pageDriver(DriverPageRequest request) {
        PageHelper.startPage(request.getPageNum(), request.getPageSize());
        DriverExample driverExample = new DriverExample();
        driverExample.setOrderByClause("id DESC");
        DriverExample.Criteria criteria = driverExample.createCriteria();
        criteria.andIsDeleteEqualTo(DeletedEnum.UN_DELETED.getState());
        if (ObjectUtil.isNotEmpty(request.getName())) {
            criteria.andNameEqualTo(request.getName());
        }
        if (ObjectUtil.isNotEmpty(request.getState())) {
            criteria.andStateEqualTo(request.getState());
        }
        List<Driver> driverList = driverMapper.selectByExample(driverExample);
        PageInfo<Driver> driverPageInfo = new PageInfo<>(driverList);
        return new WebPageResponse(driverPageInfo.getTotal(), driverPageInfo.getList());
    }
}
