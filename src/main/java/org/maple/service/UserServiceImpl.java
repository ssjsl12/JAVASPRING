package org.maple.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.maple.domain.User;
import org.maple.mapper.UserMapper;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

	private final UserMapper mapper;
	
	public List<User> getList() {
		
		
		return mapper.getList();
	}

	@Override
	public int insertUser(User vo) {
		// TODO Auto-generated method stub
		
		return mapper.insertUser(vo);
		
	}

	@Override
	public int comparisonId(User vo) {

		return mapper.comparisonId(vo);
	}

	@Override
	public int updateUserCash(@Param("cash")int cash , @Param("userno")int userno) {
		// TODO Auto-generated method stub
		return mapper.updateUserCash(cash , userno);
	}

	@Override
	public User getUser(String userid) {
		// TODO Auto-generated method stub
		return mapper.getUser(userid);
	}

	@Override
	public User getUserInt(int userno) {
		// TODO Auto-generated method stub
		return mapper.getUserInt(userno);
	}

	
	
	
	
}
