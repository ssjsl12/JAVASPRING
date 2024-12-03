package org.maple.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.maple.domain.Item;
import org.maple.domain.User;
import org.springframework.stereotype.Service;

@Service
public interface ItemService {

	//유저가 가지고있는 아이템 찾아서 넣기
	public List<Item> getUserItemList(User user);
	public Item getUserItem(@Param("fkuser_no")int fkuser , @Param("fkinfo_no")int fkinfo);
	int updateItem(@Param("count") int count, 
            @Param("fkuser_no") int fkuserNo, 
            @Param("fkinfo_no") int fkinfoNo);
	
	public int insertItem(
            @Param("fkuser_no") int fkuserNo, 
            @Param("count") int count,
            @Param("fkinfo_no") int fkinfoNo);
}
