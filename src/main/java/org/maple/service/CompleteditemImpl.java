package org.maple.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.maple.domain.Completeditem;
import org.maple.mapper.CompleteditemMapper;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
@RequiredArgsConstructor
public class CompleteditemImpl implements CompleteditemService {

	
	private final CompleteditemMapper mapper;
	
	@Override
	public List<Completeditem> getList() {
		// TODO Auto-generated method stub
		return mapper.getList();
	}

	@Override
	public int insertcitem(int fkexitem_no, int fkinfo_no ,int count , int price) {
		// TODO Auto-generated method stub
		return mapper.insertcitem(fkexitem_no,fkinfo_no , count , price);
	}

	@Override
	public List<Completeditem> getUserList(int fkuser_no) {
		// TODO Auto-generated method stub
		return mapper.getUserList(fkuser_no);
	}

	@Override
	public int updatecitem(int fkexitem_no) {
		// TODO Auto-generated method stub
		return mapper.updatecitem(fkexitem_no);
	}

}
