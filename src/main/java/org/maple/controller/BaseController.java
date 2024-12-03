package org.maple.controller;

import java.time.LocalDateTime;

public interface BaseController {

	
	  default void logRequest(String endpoint) {
	        System.out.println("Request to [" + endpoint + "] at: " + LocalDateTime.now());
	    }
	 
}
