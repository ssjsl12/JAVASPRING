package org.maple.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.maple.domain.Completeditem;

public interface CompleteditemMapper {

	public List<Completeditem> getList();
	
	public List<Completeditem> getUserList(@Param("fkuser_no")int fkuser_no );
	
	public int insertcitem(@Param("fkexitem_no")int fkexitem_no ,
			@Param("fkinfo_no")int fkinfo_no ,@Param("count") int count ,@Param("price") int price);
	
	public int updatecitem(@Param("fkexitem_no")int fkexitem_no);
}
