package com.tcwong.pengms.utils;

import com.tcwong.pengms.dto.MenuVO;
import com.tcwong.pengms.model.Menu;
import java.util.*;
import org.springframework.beans.BeanUtils;

/**
 * Description 菜单树工具
 *
 * @author tcwong
 * @date 2020/10/14 Since 1.8
 */
public class MenuTreeUtil {

    public static List<MenuVO> convert(List<Menu> menus) {
        Map<Integer, MenuVO> menuVOMap = new LinkedHashMap<>();
        for (Menu menu : menus) {
            Integer parentId = menu.getParentId();
            Integer id = menu.getId();
            if (parentId == 1) {
                MenuVO menuEntry = menuVOMap.get(id);
                if (menuEntry == null) {
                    MenuVO menuVO = new MenuVO();
                    BeanUtils.copyProperties(menu, menuVO);
                    menuVOMap.put(id, menuVO);
                } else {
                    BeanUtils.copyProperties(menu, menuEntry);
                }
            } else {
                MenuVO menuEntry = menuVOMap.get(parentId);
                MenuVO.MenuChild menuChild = new MenuVO.MenuChild();
                BeanUtils.copyProperties(menu, menuChild);
                if (menuEntry == null) {
                    menuEntry = new MenuVO();
                    List<MenuVO.MenuChild> children = new ArrayList<>();
                    children.add(menuChild);
                    menuEntry.setChildren(children);
                    menuVOMap.put(parentId, menuEntry);
                } else {
                    List<MenuVO.MenuChild> children = menuEntry.getChildren();
                    if (children == null) {
                        children = new ArrayList<>();
                        children.add(menuChild);
                        menuEntry.setChildren(children);
                    } else {
                        children.add(menuChild);
                    }
                }
            }
        }
        Collection<MenuVO> menuVOs = menuVOMap.values();
        return new ArrayList<>(menuVOs);
    }
}
