package com.booyaka.web.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

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

	@Autowired
	SysMenuService menuService;

	@GetMapping("/page")
	public ModelAndView indexPage() {
		return new ModelAndView("/system/menu-page");
	}

}