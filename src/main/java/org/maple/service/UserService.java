package org.maple.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.maple.domain.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {


	public List<User> getList();
	
	public int insertUser(User vo);

	int comparisonId(User vo);
	
	int updateUserCash(@Param("cash") int cash , @Param("userno")int userno);
	
	public User getUser(String userid);
	
	public User getUserInt(int userno);
	
}
