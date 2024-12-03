package org.maple.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.maple.domain.Completeditem;
import org.maple.domain.ExchangeItem;
import org.maple.domain.Item;
import org.maple.domain.MemberVO;
import org.maple.domain.User;
import org.maple.domain.Wishlist;
import org.maple.security.util.SecurityUtil;
import org.maple.service.CompleteditemService;
import org.maple.service.ExchangeItemService;
import org.maple.service.ItemService;
import org.maple.service.UserService;
import org.maple.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController // @Controller와 @ResponseBody의 조합
@RequestMapping("/auction") // 기본 경로 설정
@EnableMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class ExchangeItemController implements BaseController {

	@Autowired
	private ExchangeItemService exservice;

	@Autowired
	private UserService userservice;

	@Autowired
	private ItemService itemservice;
	
	@Autowired
	private WishlistService wishservice;
	
	@Autowired
	private CompleteditemService ciservice;
	
	// 여기서 아이템 업데이트, 유저 캐쉬 업데이트

	@PreAuthorize("isAuthenticated()")
	@PutMapping("/buy")
	public ResponseEntity<String> purchaseItem(@RequestParam("exitem_no") int itemNo, @RequestParam("count") int count,
			@RequestParam("fkuser_no") int fkuser_no) {

		   User user = SecurityUtil.getCurrentUser(userservice);
		
		ExchangeItem item = exservice.getitem(itemNo);
		
		if(user.getCash() < item.getPrice() * count)
		{
			return new ResponseEntity<>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		wishservice.autoDelete(itemNo);
		
		// 현재 아이템 가져옴
	
		
		// 현재 아이템이 유저가 산 개수보다 작으면 에러
		if (item.getCount() < count) {
			return new ResponseEntity<>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
		}

		// 아이템 구매
		int success = exservice.updateExchangeItemCount(count, itemNo);

		// 아이템 다시 들고옴
		item = exservice.getitem(itemNo);

		//아이템이 사지면 상태 변환
		if (item.getCount() < 1) {

			exservice.updateState(itemNo);
		}

		user = userState(userservice.getUserInt(fkuser_no) , 
				user , item , count);
			

		
		 //유저가 아이템을 가지고있을경우 update
		 //유저가 아이템을 가지고 있지 않을경우 insert

		if (success == 1) {
			return new ResponseEntity<>("success", HttpStatus.OK);
		}
		return new ResponseEntity<>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	//거래소에 아이템 올렸을때
	@PostMapping("/new")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<String> PostMarket(@RequestParam("price") int price, @RequestParam("count") int count,
			@RequestParam("fkuser_no") int fkuser_no , @RequestParam("fkinfo_no") int fkinfo_no) {


		int success = exservice.insertItem(price, count, fkuser_no , fkinfo_no);
			
		int itemCount = itemservice.getUserItem(fkuser_no, fkinfo_no).getCount();
		
	    int update =  itemservice.updateItem(itemCount - count, fkuser_no, fkinfo_no);

		if (success == 1) 
		{
			return new ResponseEntity<>("success", HttpStatus.OK);
		}
		else 
		{
			return new ResponseEntity<>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	
	}
	
	@PostMapping("/cancle")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<String> CancleMarket(@RequestParam("fkuser_no") int fkuser_no
			, @RequestParam("fkinfo_no") int fkinfo_no, @RequestParam ("exitem_no") int exitem_no)
	{
		
		//찜한 유저 삭제
		wishservice.autoDelete(exitem_no);
		
		//교환소에올라온 아이템
		ExchangeItem item = exservice.getitem(exitem_no);
		Item userItem = itemservice.getUserItem(fkuser_no, fkinfo_no);
		
		if(userItem != null)
		{
			int itemCount = itemservice.getUserItem(fkuser_no, fkinfo_no).getCount();
			itemservice.updateItem(item.getCount() + itemCount, fkuser_no, fkinfo_no);
		}
		else 
		{
			itemservice.insertItem(item.getFkuser_no(),item.getCount(),item.getFkinfo_no());
		}
	
		int success = exservice.deleteItem(exitem_no);
	
		if (success == 1) 
		{
			return new ResponseEntity<>("success", HttpStatus.OK);
		}
		else 
		{
			return new ResponseEntity<>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/wish")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<String> WishItem(@RequestParam ("exitem_no") int exitem_no
			)
	{	
		   User user = SecurityUtil.getCurrentUser(userservice);
		
		List<Wishlist> wishlist = wishservice.getUserWishList(user);
		
		for(int i = 0; i < wishlist.size(); ++i)
		{
			if(wishlist.get(i).getFkexitem_no() == exitem_no)
			{
				return new ResponseEntity<>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		
		int success = wishservice.insert(user.getUser_no(), exitem_no);
		
		if (success == 1) 
		{
			return new ResponseEntity<>("success", HttpStatus.OK);
		}
		else 
		{
			return new ResponseEntity<>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/canclewish")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<String> cancleWishItem(@RequestParam ("fkexitem_no") int exitem_no)
	{	
		

		   User user = SecurityUtil.getCurrentUser(userservice);
		
		int success = wishservice.delete(user.getUser_no(), exitem_no);
		
		if (success == 1) 
		{
			return new ResponseEntity<>("success", HttpStatus.OK);
		}
		else 
		{
			return new ResponseEntity<>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/collectmeso")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<String> collectMeso(@RequestParam ("count") int count,
			@RequestParam ("price") int price ,@Param("fkexitem_no")int fkexitem_no)
	{	
		

		   User user = SecurityUtil.getCurrentUser(userservice);
		
		int cash = count * price;
		
		int success = userservice.updateUserCash(cash, user.getUser_no());
		
		user.setCash(user.getCash() + cash);
		
	
		
		ciservice.updatecitem(fkexitem_no);
		
		if (success == 1) 
		{
			return new ResponseEntity<>("success", HttpStatus.OK);
		}
		else 
		{
			return new ResponseEntity<>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	
	}
	
	
	//RequestBody를 쓸려면 잭슨을 추가해야함
	@PostMapping("/collectAllMeso")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<Map<String, String>> collectAllMeso(@RequestBody  List<Completeditem> requests, HttpSession session ) 
	{
		
		User user = (User) session.getAttribute("user");
		
		int cash = 0;
		
		for(int i = 0 ; i < requests.size(); ++i)
		{
			cash += requests.get(i).getCount() * requests.get(i).getPrice();
			ciservice.updatecitem(requests.get(i).getFkexitem_no());
		}
		
		System.out.println(cash);
		
		user.setCash(user.getCash() + cash);
		
		int success = userservice.updateUserCash(cash, user.getUser_no());

		System.out.println(success);
		
		 Map<String, String> response = new HashMap<>();
		    if (success == 1) {
		        response.put("status", "success");
		    } else {
		        response.put("status", "error");
		    }

		 return new ResponseEntity<>(response, HttpStatus.OK); // JSON 형식으로 반환
	}
	
	
	public User userState(User seller , User buyer , ExchangeItem item , int count) {

		int cash = item.getPrice() * count;
		
		//userservice.updateUserCash(cash, seller.getUser_no());
		userservice.updateUserCash(-cash, buyer.getUser_no());
		
		User updateUser = userservice.getUserInt(buyer.getUser_no());
		
		
		Item userItem = itemservice.getUserItem(buyer.getUser_no(),item.getFkinfo_no());
		 
			if(userItem != null)
			{
				int itemCount = itemservice.getUserItem(buyer.getUser_no(), item.getFkinfo_no()).getCount();
				itemservice.updateItem(itemCount + count, buyer.getUser_no(), item.getFkinfo_no());
			}
			else 
			{
				
				itemservice.insertItem(buyer.getUser_no(),count,item.getFkinfo_no());
			}
		
		 ciservice.insertcitem(item.getExitem_no(),item.getFkinfo_no(),count, item.getPrice());
			
		
		return updateUser;
	}
	
	
	

}
