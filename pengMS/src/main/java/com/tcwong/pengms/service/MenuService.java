package com.tcwong.pengms.service;

import com.tcwong.pengms.dto.MenuVO;
import java.util.List;

/**
 * Description
 *
 * @author tcwong
 * @date 2020/10/14 Since 1.8
 */
public interface MenuService {
    List<MenuVO> listMenus();
}
