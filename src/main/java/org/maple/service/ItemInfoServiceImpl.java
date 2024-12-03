package org.maple.service;

import java.util.List;

import org.maple.domain.ItemInfo;
import org.maple.mapper.ItemInfoMapper;
import org.maple.mapper.UserMapper;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
@RequiredArgsConstructor
public class ItemInfoServiceImpl implements ItemInfoService{

	private final ItemInfoMapper mapper;
	
	public List<ItemInfo> getList()
	{
		return null;
	}
	
}
