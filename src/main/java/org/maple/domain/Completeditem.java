package org.maple.domain;



import java.util.Date;

import lombok.Data;

@Data
public class Completeditem {

	private int fkexitem_no;
	private int fkinfo_no;
	private Date cidate;
	private int count;
	private int price;
	private String url;
	private String name;
	private int getprice;
}
