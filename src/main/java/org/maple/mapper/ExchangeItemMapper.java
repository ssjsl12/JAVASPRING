package org.maple.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.maple.domain.ExchangeItem;

public interface ExchangeItemMapper {

	//아이템 리스트
	public List<ExchangeItem> getList();
	
	//아이템 상태 
	public int updateState(int itemNo);
	
	//아이템
	public ExchangeItem getitem(int itemNo);
	
	//아이템 개수 
	int updateExchangeItemCount(@Param("purchaseAmount") int purchaseAmount, 
            @Param("exchangeItemNo") int exchangeItemNo);
	
	int insertItem(@Param("price")int price, @Param("count")int count , @Param("fkuser_no") int fkuser_no , 
			@Param("fkinfo_no") int fkinfo_no);
	
	public List<ExchangeItem> getUserItem(@Param("fkuser_no") int fkuser_no);
	
	int deleteItem(@Param("exitem_no") int exitem_no);
	
}
