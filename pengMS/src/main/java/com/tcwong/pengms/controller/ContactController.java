package com.tcwong.pengms.controller;

import com.tcwong.pengms.base.LogFilter;
import com.tcwong.pengms.base.WebPageResponse;
import com.tcwong.pengms.base.WebResponse;
import com.tcwong.pengms.constant.LogOperationType;
import com.tcwong.pengms.model.Contact;
import com.tcwong.pengms.service.ContactService;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/** 车辆绑定 */
@RestController
@RequestMapping("/pengms/contact/")
public class ContactController {

    @Resource private ContactService contactService;

    @LogFilter(description = "车辆绑定", logOperationType = LogOperationType.UPDATE)
    @PutMapping("/put")
    public WebResponse bindTruck(@RequestBody Contact contact) {
        int num = contactService.bindTruck(contact);
        if (num > 0) {
            return WebResponse.success("绑定成功");
        }
        return WebResponse.failed("绑定失败");
    }

    @LogFilter(description = "解除车辆绑定", logOperationType = LogOperationType.UPDATE)
    @PutMapping("/delete/{ids}")
    public WebResponse unbindTrucks(@PathVariable String ids) {
        int num = contactService.unbindTrucks(ids);
        if (num > 0) {
            return WebResponse.success("解除成功");
        }
        return WebResponse.failed("解除失败");
    }

    @GetMapping("/getAll")
    public WebResponse getContact(
            Integer page,
            Integer size,
            Integer fkTruckid,
            Integer fkDriverid,
            String drivername,
            String teamname,
            Integer bindId) {
        WebPageResponse pageResponse =
                contactService.getAll(
                        page, size, fkTruckid, fkDriverid, drivername, teamname, bindId);
        if (pageResponse != null) {
            return WebResponse.success(pageResponse, "查询成功");
        }
        return WebResponse.failed("查询失败");
    }
}
