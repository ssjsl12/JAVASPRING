package org.maple.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.maple.domain.User;
import org.maple.domain.Wishlist;
import org.maple.mapper.UserMapper;
import org.maple.mapper.WishlistMapper;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
@RequiredArgsConstructor
public class WishlistServiceImpl implements WishlistService{
	
	private final WishlistMapper mapper;
	
	
	@Override
	public List<Wishlist> getUserWishList(User user) {
		// TODO Auto-generated method stub
		return mapper.getUserWishList(user);
	}

	
	@Override
	public int delete(@Param("userno")int userno,@Param("exchageItemNo") int exchageItemNo)
	{
		
		return mapper.delete(userno,exchageItemNo);
	}


	@Override
	public int insert(@Param("userno")int userno,@Param("exchageItemNo") int exchageItemNo) {
		// TODO Auto-generated method stub
		return mapper.insert(userno, exchageItemNo);
	}


	@Override
	public int autoDelete(int fkexitem_no) {
		// TODO Auto-generated method stub
		return mapper.autoDelete(fkexitem_no);
	}
	

}
