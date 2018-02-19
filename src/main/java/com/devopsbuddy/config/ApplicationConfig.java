package com.devopsbuddy.config;



import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3Client;
import com.devopsbuddy.backend.service.UserService;



@Configuration
@EnableJpaRepositories(basePackages = "com.devopsbuddy.backend.persistence.repositories")
@EntityScan(basePackages = "com.devopsbuddy.backend.persistence.domain.backend")
@EnableTransactionManagement
@PropertySource("file:///E:\\devopsbuddy\\application-common.properties")
public class ApplicationConfig {
	
	/**The application logger*/
	private static final org.slf4j.Logger LOG = org.slf4j.LoggerFactory.getLogger(ApplicationConfig.class);
	

	 @Value("${aws.s3.profile}")
	 private String awsProfileName;
	 
	
	 
	 @Bean
	 public AmazonS3Client s3Client(){
		 
		 LOG.debug("@@@@@@@@@@@==>"+awsProfileName);
		 
		 AWSCredentials awsCredentials = new ProfileCredentialsProvider(awsProfileName).getCredentials(); 
		 AmazonS3Client s3Client = new AmazonS3Client(awsCredentials);
		 Region region = Region.getRegion(Regions.US_EAST_1);
		 s3Client.setRegion(region);
		 return s3Client;		 
		 
	 }

}
