package com.devopsbuddy.utils;

import com.devopsbuddy.backend.persistence.domain.backend.User;

public class UserUtils {
	
	private UserUtils(){
		throw new AssertionError("Non Instantiable");
	} 
	
	 public static User createBasicUser(String userName,String email) {

	        User user = new User();
	        user.setUsername(userName);
	        user.setPassword("secret");
	        user.setEmail(email);
	        user.setFirstName("firstName");
	        user.setLastName("lastName");
	        user.setPhoneNumber("123456789123");
	        user.setCountry("GB");
	        user.setEnabled(true);
	        user.setDescription("A basic user");
	        user.setProfileImageUrl("https://blabla.images.com/basicuser");

	        return user;
	    }

}
