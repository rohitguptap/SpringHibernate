package com.bank.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bank.service.AuthticateService;

/**
 * 
 */
@Controller
public class HomeController {
	@Autowired
	private AuthticateService authenticateService;

	public void setAuthenticateService(AuthticateService authenticateService) {
		this.authenticateService = authenticateService;
	}

	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);


	@RequestMapping("/registrationform.html")
		public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
				DateFormat.LONG, locale);

		String registration = dateFormat.format(date);

		model.addAttribute("serverTime", registration);
		return "registrationform";
	}

	@RequestMapping(value = "/registrationform1.html", method = RequestMethod.POST)
	public ModelAndView processCredentials(
			@RequestParam("userName") String userName,
			@RequestParam("password") String password) {
		String message = "Invalid credentials";
		if (authenticateService.verifyUserNameAndPassword(userName, password)) {
			message = "Welcome " + userName + "!!";
		}
		return new ModelAndView("results", "message", message);
	}

}
