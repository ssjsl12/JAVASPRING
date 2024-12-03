package org.maple.domain;

import lombok.Data;

/*CREATE TABLE Item (
	    user_no INT,
	    COUNT INT DEFAULT 0,
	    iteminfo INT NOT NULL,
	    
	    PRIMARY KEY (user_no),
	    FOREIGN KEY (iteminfo) REFERENCES ItemInfo(NO),
	    FOREIGN KEY (user_no) REFERENCES User(NO)
	);
*/

@Data
public class Item {

	private int fkuser_no;
	private int count;
	private int fkinfo_no;
	private String name;
	private String url;
}
