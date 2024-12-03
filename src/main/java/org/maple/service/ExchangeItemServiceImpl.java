package org.maple.service;

import java.util.List;

import org.maple.domain.ExchangeItem;
import org.maple.mapper.ExchangeItemMapper;
import org.maple.mapper.UserMapper;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
@RequiredArgsConstructor
public class ExchangeItemServiceImpl implements ExchangeItemService {
	
	private final ExchangeItemMapper mapper;
	
	@Override
	public List<ExchangeItem> getList() {
		log.info("getList......");
		return mapper.getList();
	}

	@Override
	public int updateState(int itemNo) {
		// TODO Auto-generated method stub
		return mapper.updateState(itemNo);
	}

	@Override
	public int updateExchangeItemCount(int purchaseAmount, int exchangeItemNo) {
		// TODO Auto-generated method stub
		return mapper.updateExchangeItemCount(purchaseAmount, exchangeItemNo);
	}

	@Override
	public ExchangeItem getitem(int itemNo) {
		// TODO Auto-generated method stub
		return mapper.getitem(itemNo);
	}

	@Override
	public int insertItem(int price, int count, int fkuser_no , int fkinfo_no) {
		// TODO Auto-generated method stub
		return mapper.insertItem(price, count, fkuser_no ,fkinfo_no);
	}

	@Override
	public List<ExchangeItem> getUserItem(int fkuser_no) {
		// TODO Auto-generated method stub
		return mapper.getUserItem(fkuser_no);
	}

	@Override
	public int deleteItem(int exitem_no) {
		// TODO Auto-generated method stub
		return mapper.deleteItem(exitem_no);
	}

	

}
