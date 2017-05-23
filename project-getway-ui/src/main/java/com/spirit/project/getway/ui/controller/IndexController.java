package com.spirit.project.getway.ui.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.common.collect.Lists;
import com.spirit.project.common.ui.exception.SpiritUIServiceException;
import com.spirit.project.common.ui.security.SpiritLoginUser;
import com.spirit.project.common.ui.util.LoginUserUtils;
import com.spirit.project.getway.ui.service.UserService;
import com.spirit.project.getway.ui.vo.LoginUserMenuVO;

@Controller
public class IndexController {

	private static final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);
	
	@Autowired
	private UserService userService;

	@RequestMapping("/")
	public String index(Model model) {
		SpiritLoginUser loginUser = LoginUserUtils.loginUser();
		List<LoginUserMenuVO> menus = Lists.newLinkedList();
		try {
			menus = userService.findUserResourceByUserId(loginUser.getId());
		} catch (SpiritUIServiceException e) {
			LOGGER.error("用户 {} 访问系统错误", loginUser, e);
		}
		model.addAttribute("menus", menus);
		return "index/navtop";
	}
}
