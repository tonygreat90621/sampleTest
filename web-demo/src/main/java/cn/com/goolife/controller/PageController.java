package cn.com.goolife.controller;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.com.goolife.dao.UserDao;
import cn.com.goolife.db.controller.DBControllerWithOutPool;

@Controller
public class PageController{

	@Autowired
	private DBControllerWithOutPool dbController;
	
	public String testAnnotation() {
		System.out.println("applicationConfig OK1");
		return "modelViewBack";
	}

	@RequestMapping(value="/back")
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("applicationConfig OK");
		List<UserDao> list = dbController.getUsersByJdbcTemplate();
		list = dbController.getUsers("hello");
//		List<String> list = dbController.getUserNames();
		ModelAndView modelAndView = new ModelAndView("modelViewBack");
		modelAndView.addObject("list", list);
		return modelAndView;
	}
}
