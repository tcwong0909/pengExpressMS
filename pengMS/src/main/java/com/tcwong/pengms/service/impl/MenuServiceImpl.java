package com.tcwong.pengms.service.impl;

import com.tcwong.pengms.dao.MenuMapper;
import com.tcwong.pengms.dto.MenuVO;
import com.tcwong.pengms.model.Menu;
import com.tcwong.pengms.model.example.MenuExample;
import com.tcwong.pengms.service.MenuService;
import com.tcwong.pengms.utils.MenuTreeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * Description
 *
 * @author tcwong
 * @date 2020/10/14
 * Since 1.8
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<MenuVO> listMenus() {
        List<Menu> menus = menuMapper.selectByExample(new MenuExample());
        List<MenuVO> menuVOList = MenuTreeUtil.convert(menus);
        return menuVOList;
    }
}
