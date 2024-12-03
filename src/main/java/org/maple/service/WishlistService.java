package org.maple.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.maple.domain.User;
import org.maple.domain.Wishlist;
import org.springframework.stereotype.Service;

@Service
public interface WishlistService {

	public List<Wishlist> getUserWishList(User user);

	public int insert(@Param("userno") int userno , @Param("exchageItemNo") int exchageItemNo);
	
	
	public int delete(@Param("userno")int userno,@Param("exchageItemNo") int exchageItemNo);
	
	public int autoDelete(@Param("fkexitem_no")int fkexitem_no);
}
