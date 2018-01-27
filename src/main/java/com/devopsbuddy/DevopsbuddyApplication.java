package com.devopsbuddy;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.devopsbuddy.backend.persistence.domain.backend.Role;
import com.devopsbuddy.backend.persistence.domain.backend.User;
import com.devopsbuddy.backend.persistence.domain.backend.UserRole;
import com.devopsbuddy.backend.service.UserService;
import com.devopsbuddy.enums.PlansEnum;
import com.devopsbuddy.enums.RolesEnum;
import com.devopsbuddy.utils.UserUtils;

import groovy.util.logging.Slf4j;

@SpringBootApplication
public class DevopsbuddyApplication implements CommandLineRunner {
	
	/**The application logger*/
	private static final org.slf4j.Logger LOG = org.slf4j.LoggerFactory.getLogger(DevopsbuddyApplication.class);
	
	@Autowired
	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(DevopsbuddyApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		String userName="santosh";
		String email="santosh@test.com";
		User user = UserUtils.createBasicUser(userName,email);
		Set<UserRole> userRoles = new HashSet<>();
		userRoles.add(new UserRole(user, new Role(RolesEnum.BASIC)));
		LOG.debug("Creating user with username {}", user.getUsername());
		userService.createUser(user, PlansEnum.PRO, userRoles);
		LOG.info("User {} created", user.getUsername());
	}
}
