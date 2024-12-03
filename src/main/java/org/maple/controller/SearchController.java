package org.maple.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.aspectj.lang.annotation.Aspect;
import org.maple.domain.Completeditem;
import org.maple.domain.ExchangeItem;
import org.maple.domain.MemberVO;
import org.maple.domain.User;
import org.maple.domain.Wishlist;
import org.maple.mapper.MemberMapper;
import org.maple.security.util.SecurityUtil;
import org.maple.service.CompleteditemService;
import org.maple.service.ExchangeItemService;
import org.maple.service.UserService;
import org.maple.service.WishlistService;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/auction/")
@RequiredArgsConstructor
@Log4j
@EnableMethodSecurity(securedEnabled = true, prePostEnabled = true)
@EnableAspectJAutoProxy(proxyTargetClass = false) 
public class SearchController implements BaseController {

	public final ExchangeItemService service;
	public final CompleteditemService ciservice;
	public final WishlistService whservice;
	public final MemberMapper memberMapper;
	public final UserService userservice;

	@GetMapping("/search")
	public void getController(Model model) {

		   User user = SecurityUtil.getCurrentUser(userservice);
		   
		logRequest("search");

		List<ExchangeItem> list = service.getList();

		if (user != null) {

			List<ExchangeItem> templist = new ArrayList<ExchangeItem>();

			for (int i = 0; i < list.size(); ++i) {
				if (user.getUser_no() != list.get(i).getFkuser_no()) {
					templist.add(list.get(i));
				}

			}
	
			model.addAttribute("list", templist);
		}
		else {
			
			model.addAttribute("list", list);
		}
		 
	}

	@GetMapping("/market")
	public void getMarket(HttpSession session, Model model) {

		logRequest("market");

		List<Completeditem> list = ciservice.getList();

		model.addAttribute("list", list); /// WEB-INF/views/board/list.jsp
	}

	@PreAuthorize("isAuthenticated()")
	@GetMapping("/wishlist")
	public void getWish(HttpSession session, Model model) {

		   User user = SecurityUtil.getCurrentUser(userservice);

		logRequest("wishlist");

		List<Wishlist> list = whservice.getUserWishList(user);

		model.addAttribute("list", list); /// WEB-INF/views/board/list.jsp
	}

	@PreAuthorize("isAuthenticated()")
	@GetMapping("/completed")
	public void getCompleted(HttpSession session, Model model) {

		   User user = SecurityUtil.getCurrentUser(userservice);

		List<Completeditem> list = ciservice.getUserList(user.getUser_no());

		logRequest("completed");

		model.addAttribute("list", list); /// WEB-INF/views/board/list.jsp
	}

}
