package org.maple.mapper;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.maple.domain.Completeditem;
import org.maple.domain.User;
import org.maple.service.ExchangeItemService;
import org.maple.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml",
	"file:src/main/webapp/WEB-INF/spring/security-context.xml"})
@Log4j
public class ItemInfoMapperTest {
	@Autowired
	private ItemInfoMapper mapper;
	
	@Autowired 
	private ItemMapper mapper3;
	
	@Autowired
	private UserMapper usermapper;
	
	@Autowired
	private ExchangeItemService exmapper;
	
	@Autowired
	private WishlistService wishmapper;
	
	@Autowired
	private CompleteditemMapper cimapper;
	

	@Autowired
	private PasswordEncoder pwencoder;
	
	@Autowired
	private DataSource ds;
	
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private SqlSessionFactory sessionFactory;
	
	@Test
	public void testConnection() {
		try(Connection con = dataSource.getConnection()) {
			log.info("con : " + con);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void testMyBatis() {
		try(
				SqlSession session = sessionFactory.openSession();
				Connection con = session.getConnection();
		) {
			log.info("con : " + con);
			log.info("session : " + session);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void CompletedItem() {
		
	  List<Completeditem> list =cimapper.getUserList(1);
		
	  list.forEach(i -> log.info(i));
	  

	  
	  
	}
	
	@Test
	public void ItemInfo()
	{
		
	    
		System.out.println(mapper.getList().size());
	}
	
	@Test
	public void Item() {
		
		User u = new User();
		u.setUser_no(1);
		
		//mapper3.getUserItemList(u).forEach(i -> log.info(i));
		
	 org.maple.domain.Item item	= mapper3.getUserItem(2,3);
	 
	 log.info(item);
		
	 int a = mapper3.updateItem(5, 2, 3);
	 
	 log.info(a);
	 
	}
	
	@Test
	public void Wish() {
		
		int wishno = 2;
		
		User u = new User();
		u.setUser_no(wishno);
		
		System.out.println("user : " + u);
		
		/* wishmapper.delete(wishno, 5); */
		
		wishmapper.getUserWishList(u).forEach(i -> log.info(i));
	
	}
	
	
	@Test
	public void ExchangeItem() {
		
		int purchaseAmount = 1;  // 구매 수량
		int exchangeItemNo = 3; // 고른 물품 번호

		//exmapper.insertItem(500, 1, 3);
		
		/*
		 * List<org.maple.domain.ExchangeItem> item = exmapper.getUserItem(2);
		 * 
		 * System.out.println(item);
		 * 
		 * System.out.println(item.size());
		 */
	
		/*
		 * wishmapper.autoDelete(2); exmapper.deleteItem(2);
		 */
		
		
		org.maple.domain.ExchangeItem item = exmapper.getitem(15);
	
		System.out.println(item);
		
	}
	
	
	
	
	@Test
	public void User() {
		/*
		 * User vo = new User(); vo.setUser_id("123"); vo.setUser_pwd("123");
		 * vo.setName("123"); mapper2.insertUser(vo);
		 */
		
		System.out.println(usermapper.getUserInt(3));
		
		
		//usermapper.updateUserCash(-1000, 1);
		
		//usermapper.getList().forEach(i -> log.info(i));
	}
	

	@Test
	public void member()
	{
		
		
	}
	
			
			
			
	
	
	@Test
	public void insertmember() {
		
		String sql = "insert into tbl_member(userid ,userpw,username) values (?,?,?)";		
		
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		System.out.println("pwencoder : " + pwencoder);
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			
			System.out.println("con : " + con  + ", pstmt : " + pstmt);
			
			pstmt.setString(1, "user" + 2);
			pstmt.setString(2,pwencoder.encode("pw" + 2));
			pstmt.setString(3, "general user" + 2);
			
			pstmt.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally {
		
				try {
					pstmt.close();
					con.close();
				} catch (Exception e2) {
					// TODO: handle exception
				}
		}
		
		
	}
	
	
	
}
