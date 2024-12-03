package org.maple.security.util;

import org.maple.domain.MemberVO;
import org.maple.domain.User;
import org.maple.security.domain.CustomUser;
import org.maple.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtil {

    public static MemberVO getCurrentMember() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Object principal = auth.getPrincipal();
        if (principal instanceof CustomUser) {
        
        	
        	return ((CustomUser) principal).getMember();
        }
        
       
        return null; // 로그인되지 않았거나 인증 정보 없음
        
        
        
        
    }

    public static User getCurrentUser(UserService userService) {
        MemberVO member = getCurrentMember();
        if (member != null) {
            return userService.getUserInt(member.getUser_no());
        }
        return null;
    }
}