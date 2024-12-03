package org.maple.domain;

import java.util.Date;

import lombok.Data;

/*CREATE TABLE Wishlist(
		user_no INT NOT NULL,
		exitem_id INT NOT NULL,
		
		 PRIMARY KEY (user_no),
	    FOREIGN KEY (exitem_id) REFERENCES ExchangeItem(NO),
	    FOREIGN KEY (user_no) REFERENCES User(NO)
		
	);*/

@Data
public class Wishlist {

	private int fkuser_no;
	private int fkexitem_no;
	
	//join
	private String url;
	private String name;
	private int price;
	private Date exDate;
	
}
