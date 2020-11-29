package com.booyaka.resource.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/res")
public class ResourceController {

	@GetMapping(value = "/resource1")
	@PreAuthorize("hasAnyAuthority('resource_a')")
	public String resource1() {
		return "访问资源1";
	}

	@GetMapping(value = "/resource2")
	@PreAuthorize("hasAnyAuthority('b')")
	public String resource2() {
		return "访问资源2";
	}

}
