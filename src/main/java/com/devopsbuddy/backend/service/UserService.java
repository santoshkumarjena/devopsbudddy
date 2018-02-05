package com.devopsbuddy.backend.service;



import java.util.Set;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devopsbuddy.backend.persistence.domain.backend.PasswordResetToken;
import com.devopsbuddy.backend.persistence.domain.backend.Plan;
import com.devopsbuddy.backend.persistence.domain.backend.User;
import com.devopsbuddy.backend.persistence.domain.backend.UserRole;
import com.devopsbuddy.backend.persistence.repositories.PasswordResetTokenRepository;
import com.devopsbuddy.backend.persistence.repositories.PlanRepository;
import com.devopsbuddy.backend.persistence.repositories.RoleRepository;
import com.devopsbuddy.backend.persistence.repositories.UserRepository;
import com.devopsbuddy.enums.PlansEnum;


@Service
@Transactional(readOnly = true)
public class UserService {
	
	@Autowired
    private PlanRepository planRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    
    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;

   /**The application logger*/
private static final org.slf4j.Logger LOG = org.slf4j.LoggerFactory.getLogger(UserService.class);
    
    @Transactional
    public User createUser(User user, PlansEnum plansEnum,Set<UserRole> userRoles){
    	
    	  String encryptedPassword = passwordEncoder.encode(user.getPassword());
          user.setPassword(encryptedPassword);
    	
    	Plan plan = new Plan(plansEnum);
    	//It makes Sure that the plans exists in the database
    	if(!planRepository.exists(plansEnum.getId())){    		
    		planRepository.save(plan);    		
    	}    	
    	user.setPlan(plan);    	
    	for (UserRole userRole : userRoles) {
			roleRepository.save(userRole.getRole());
		}    	
    	user.getUserRoles().addAll(userRoles);    	
    	user = userRepository.save(user);
    	    	
    	return user;
    }
    
    @Transactional
    public void updateUserPassword(long userId, String password) {
        password = passwordEncoder.encode(password);
        userRepository.updateUserPassword(userId, password);
        LOG.debug("Password updated successfully for user id {} ", userId);

        Set<PasswordResetToken> resetTokens = passwordResetTokenRepository.findAllByUserId(userId);
        if (!resetTokens.isEmpty()) {
            passwordResetTokenRepository.delete(resetTokens);
        }
    }

}
