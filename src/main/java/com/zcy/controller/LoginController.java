package com.zcy.controller;

import com.zcy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

/**
 * @Title 登录
 * @Description 
 * @author zhanghui
 * @date 2020年07月20日 10:51
 * @version V1.0
 * @see 
 * @since V1.0
 */
@Controller
public class LoginController {

	@Autowired
	private UserService userService;

	/**
	 * <p> 描述 : 登录页面
	 * @author : blackcat
	 * @date  : 2020/7/20 10:54
	*/
	@RequestMapping(value="/",method= RequestMethod.GET)
	public String login(){
		return "/login/login";
	}

	/**
	 * <p> 描述 : 登录表单提交
	 * @author : blackcat
	 * @date  : 2020/7/20 10:54
	*/
	@RequestMapping(value="/login",method= RequestMethod.POST)
	public String login(String loginName, HttpSession session){
		return "redirect:/leave/toLeave?loginName="+loginName;
	}
}
