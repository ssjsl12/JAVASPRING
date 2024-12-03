package org.maple.aop;

import java.util.Arrays;

import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.maple.domain.MemberVO;
import org.maple.domain.User;
import org.maple.security.util.SecurityUtil;
import org.maple.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import lombok.extern.log4j.Log4j;

@Component
@Log4j
@Aspect
public class LogAdivce {
	
	@Autowired
	public UserService userservice;
	
	@Pointcut("execution(* org.maple.controller..*.*(..))")
	public void cut() {
		log.info("--------------Log Before-----------------");

	
	}
	
	@Before("cut()")
	public void before(JoinPoint joinPoint) {
	
		   User user = SecurityUtil.getCurrentUser(userservice);
		   
		   HttpSession session = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();

	       session.setAttribute("user", user);
	      
	}
	
	

	
	
	
}
