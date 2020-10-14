package com.tcwong.pengms.controller;

import com.tcwong.pengms.base.WebResponse;
import com.tcwong.pengms.dto.MenuVO;
import com.tcwong.pengms.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Description
 *
 * @author tcwong
 * @date 2020/10/14
 * Since 1.8
 */
@RestController
@RequestMapping("/pengms/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @GetMapping("/listMenus")
    public WebResponse listMenus() {
        List<MenuVO> menuVOS = menuService.listMenus();
        return WebResponse.success(menuVOS);
    }

}
