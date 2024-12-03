package org.maple.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.maple.domain.ExchangeItem;
import org.maple.domain.Item;
import org.maple.domain.MemberVO;
import org.maple.domain.User;
import org.maple.security.domain.CustomUser;
import org.maple.security.util.SecurityUtil;
import org.maple.service.ExchangeItemService;
import org.maple.service.ItemService;
import org.maple.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/auction/*")
@RequiredArgsConstructor
@Log4j
@EnableMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SellController implements BaseController {

	public final ItemService service;
	public final ExchangeItemService exservice;
	public final UserService userservice;
	@PostMapping("/sell")
	public void postsell() {
		
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/sell")
	public void getList(Model model) {
	
		 User user = SecurityUtil.getCurrentUser(userservice);
		
		logRequest("sell");

		List<Item> list = service.getUserItemList(user);
		
		List<ExchangeItem> exlist = exservice.getUserItem(user.getUser_no());
		
		model.addAttribute("list", list); 
		model.addAttribute("exlist" , exlist);
	}
	
}
