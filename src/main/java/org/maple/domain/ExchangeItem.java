package org.maple.domain;

import java.util.Date;

import lombok.Data;

/*CREATE TABLE ExchangeItem (
	    NO INT PRIMARY KEY AUTO_INCREMENT,
	    price INT NOT NULL,
	    count INT NOT NULL,
	    user_no INT NOT NULL,
	    exDate DATE,
	    state INT NOT NULL,
	    FOREIGN KEY (user_no) REFERENCES User (NO)
	);*/

@Data
public class ExchangeItem {

	private int exitem_no;
	private int price;
	private int count;
	private int fkuser_no;
	private int fkinfo_no;
	private Date exDate;
	private int state;
	private String name;
	private String url;
}
