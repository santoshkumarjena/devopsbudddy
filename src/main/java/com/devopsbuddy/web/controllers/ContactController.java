package com.devopsbuddy.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.devopsbuddy.backend.service.EmailService;
import com.devopsbuddy.web.domain.frontend.FeedbackPojo;
import com.devopsbuddy.backend.service.I18NService;

@Controller
public class ContactController {
	
	/**The application logger*/
	private static final org.slf4j.Logger LOG = org.slf4j.LoggerFactory.getLogger(I18NService.class);
	
	/**The key which identifies the feedback payload in the Model*/
	public static final String FEEDBCK_MODEL_KEY="feedback";
	
	/**The Contact us view name*/
	public static final String CONTACT_US_VIEW_NAME="contact/contact";
	
	@Autowired
	private EmailService emailService;
	
	@RequestMapping(value = "/contact" , method = RequestMethod.GET)
	public String contectGet(ModelMap model){		
		FeedbackPojo feedbackPojo = new FeedbackPojo();
		model.addAttribute(ContactController.FEEDBCK_MODEL_KEY, feedbackPojo);
		return ContactController.CONTACT_US_VIEW_NAME;		
	}
	
	@RequestMapping(value = "/contact", method = RequestMethod.POST)
    public String contactPost(@ModelAttribute(FEEDBCK_MODEL_KEY) FeedbackPojo feedback) {
        LOG.debug("Feedback POJO content: {}", feedback);
        emailService.sendFeedbackEmail(feedback);
        return ContactController.CONTACT_US_VIEW_NAME;
    }

}
