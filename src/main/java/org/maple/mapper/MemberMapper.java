package org.maple.mapper;

import org.maple.domain.MemberVO;

public interface MemberMapper {

	public MemberVO read(String userid);
	
	
	public MemberVO getMember(String userid);
}
