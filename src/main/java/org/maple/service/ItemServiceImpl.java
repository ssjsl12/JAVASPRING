package org.maple.service;

import java.util.List;

import org.maple.domain.Item;
import org.maple.domain.User;
import org.maple.mapper.ItemMapper;
import org.maple.mapper.UserMapper;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService{
	
	private final ItemMapper mapper;
	
	@Override
	public List<Item> getUserItemList(User user) {
		// TODO Auto-generated method stub
		return mapper.getUserItemList(user);
	}

	@Override
	public Item getUserItem(int fkuser , int fkinfo){
		// TODO Auto-generated method stub
		return mapper.getUserItem(fkuser , fkinfo);
	}

	@Override
	public int updateItem(int count, int fkuserNo, int fkinfoNo) {
		// TODO Auto-generated method stub
		return mapper.updateItem(count, fkuserNo, fkinfoNo);
	}

	@Override
	public int insertItem(int fkuserNo, int count, int fkinfoNo) {
		// TODO Auto-generated method stub
		return mapper.insertItem(fkuserNo, count, fkinfoNo);
	}

	
}
