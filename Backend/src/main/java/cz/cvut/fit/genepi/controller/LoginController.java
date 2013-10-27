package cz.cvut.fit.genepi.controller;

import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * This class is a controller class which handles requests connected with a
 * login page.
 */
@Controller
public class LoginController {

	// TODO: is this even used?
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginPOST(Model model, Locale locale) {
		return "loginView";
	}

	/**
	 * Handles the request to access login page.
	 * 
	 * @param locale
	 *            the user's locale.
	 * @param model
	 *            the model to be filled for view.
	 * 
	 * @return the string of a view to be rendered.
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginGET(Locale locale, Model model) {
		return "loginView";
	}
}