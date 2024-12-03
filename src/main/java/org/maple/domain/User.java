package org.maple.domain;

import java.util.List;
import lombok.Data;

/*CREATE TABLE User (
	    NO INT PRIMARY KEY AUTO_INCREMENT,
	    name VARCHAR(50) NOT NULL,
	    cash int DEFAULT 0,
	    user_id VARCHAR(50) NOT NULL,
	    user_pwd VARCHAR(50) NOT NULL
	);*/

@Data
public class User {

		private int user_no;
		private String name;
		private int cash;
		private String user_id;
		private String user_pwd;

}
