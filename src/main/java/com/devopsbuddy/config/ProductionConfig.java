package com.devopsbuddy.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

import com.devopsbuddy.backend.service.EmailService;
import com.devopsbuddy.backend.service.SmtpEmailService;
import com.devopsbuddy.web.i18n.I18NService;

import groovy.util.logging.Log;

@Configuration
@Profile("prod")
@PropertySource("file:///E:\\devopsbuddy\\application-prod.properties")
public class ProductionConfig {
	
	
	
	 @Bean
	    public EmailService emailService() {
	        return new SmtpEmailService();
	    }

}
