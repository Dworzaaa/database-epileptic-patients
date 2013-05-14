package cz.cvut.fit.genepi.controllers;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

// TODO: Auto-generated Javadoc
/**
 * The Class OtherPageController.
 */
@Controller
public class OtherPageController {
	
	/** The name. */
	String name;

	/** The Constant logger. */
	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);

	

	/* notice, that you get the locale, model and now even the @RequestParam
	 * in the constructor
	 */
	
	/**
	 * Admin.
	 *
	 * @param locale the locale
	 * @param model the model
	 * @param fieldName the field name
	 * @return the string
	 */
	@RequestMapping(value = "foo", method = RequestMethod.POST)
	public String admin(Locale locale, Model model,
			@RequestParam String fieldName) {
		logger.info("Welcome to the other page! The client locale is {}.", locale);
	
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);		
		String formattedDate = dateFormat.format(date);		
		model.addAttribute("anotherServerTime", formattedDate );
		
		name = fieldName;
		model.addAttribute("name", name);
		return "otherPage";
	}
}