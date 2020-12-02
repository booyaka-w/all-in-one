package com.booyaka.web.system.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.booyaka.web.system.model.SysMenu;
import com.booyaka.web.system.service.SysMenuService;

/**
 * TODO Controller
 *
 * @author booyaka
 * @date 2020-11-27 14:05:36
 */
@RestController
@RequestMapping("/system/menu")
public class MenuController {

	private final String PARENT_ID = "0";

	@Autowired
	SysMenuService menuService;

	@GetMapping("/page")
	public ModelAndView indexPage() {
		return new ModelAndView("/system/menu-page");
	}

	@GetMapping("/page/data")
	public String menuListData(SysMenu sysMenu) {
		List<SysMenu> list = menuService.querySelective(sysMenu);
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		int size = list.size();
		for (int i = 0; i < size; i++) {
			SysMenu temp = list.get(i);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", temp.getMenuId());
			map.put("pid", PARENT_ID.equals(temp.getParentId()) ? 0 : temp.getParentId());
			map.put("status", temp.getMenuStatus());
			map.put("name", temp.getMenuName());
			map.put("icon", temp.getMenuIcon());
			map.put("path", temp.getMenuPath());
			map.put("subordinate", temp.getMenuSubordinate());
			map.put("type", temp.getMenuType());
			map.put("version", temp.getVersion());
			listMap.add(map);
		}

		return JSON.toJSONString(listMap);
	}
}