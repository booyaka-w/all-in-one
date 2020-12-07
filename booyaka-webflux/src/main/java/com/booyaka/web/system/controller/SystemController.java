package com.booyaka.web.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.booyaka.web.system.service.SysMenuService;
import com.booyaka.web.system.service.SysUserInfoService;

import reactor.core.publisher.Mono;

/**
 * TODO Controller
 *
 * @author booyaka
 * @date 2020-11-27 14:05:36
 */
@RestController
@RequestMapping("/system")
public class SystemController {

	@Autowired
	SysUserInfoService userInfoService;

	@Autowired
	SysMenuService menuService;

	@GetMapping("/login")
	public Mono<String> loginPage() {
		return Mono.just("index");
	}

}