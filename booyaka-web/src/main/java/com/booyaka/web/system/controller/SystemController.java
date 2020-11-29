package com.booyaka.web.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.booyaka.ResponseResult;
import com.booyaka.emuns.ResponseEnum;
import com.booyaka.web.system.service.SystemService;

import cn.amorou.uid.UidGenerator;

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
	SystemService systemService;

	@Autowired
	private UidGenerator uidGenerator;

	@GetMapping("/login")
	public ModelAndView loginPage() {

		System.err.println(uidGenerator.getUID());
		System.err.println(uidGenerator.parseUID(uidGenerator.getUID()));

		return new ModelAndView("login");
	}

	@GetMapping("/index")
	public ModelAndView indexPage() {
		return new ModelAndView("index");
	}

	@PostMapping("/dologin")
	public ResponseResult doLogin(String userName, String password) {
		ResponseResult result = new ResponseResult(ResponseEnum.SUCCESS);
		return result;
	}
}