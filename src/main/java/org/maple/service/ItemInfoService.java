package org.maple.service;

import java.util.List;

import org.maple.domain.ItemInfo;
import org.springframework.stereotype.Service;

@Service
public interface ItemInfoService {

	public List<ItemInfo> getList();
	
}
