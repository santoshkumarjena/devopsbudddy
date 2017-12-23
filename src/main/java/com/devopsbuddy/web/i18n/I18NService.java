package com.devopsbuddy.web.i18n;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;


@Service
public class I18NService {
	
	private MessageSource messageSource;
	
	/**The application logger*/
	private static final org.slf4j.Logger LOG = org.slf4j.LoggerFactory.getLogger(I18NService.class);
	
	/*
	 * Return a message for the given message id and the default locale in the session context
	 * @param messageId The key to the message resource file 
	 * */
	public String getMessage(String messageId){		
		LOG.info("Returl i18n message for the message ID {}",messageId);
		Locale locale = LocaleContextHolder.getLocale();		
		return getMessage(messageId,locale);
	}
	
	/*
	 * Return a message for the given message id and  locale 
	 * @param messageId The key to the message resource file
	 * @param locale The locale 
	 * */
	
	public String getMessage(String messageId,Locale locale){
		return messageSource.getMessage(messageId, null,locale);
	}

}
