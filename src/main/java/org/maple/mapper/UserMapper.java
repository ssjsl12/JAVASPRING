package org.maple.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.maple.domain.User;

public interface UserMapper {

	public List<User> getList();	
	
	int insertUser(User vo);
	
	int comparisonId(User vo);
	
	int updateUserCash(@Param("cash")int cash , @Param("userno")int userno);
	
	public User getUser(String userid);
	
	public User getUserInt(int userno);
}
