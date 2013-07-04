package cz.cvut.fit.genepi.controllers;

import java.util.List;
import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cz.cvut.fit.genepi.entity.PatientEntity;
import cz.cvut.fit.genepi.serviceImpl.PatientServiceImpl;

// TODO: Auto-generated Javadoc
/**
 * The Class PatientsListController.
 */
@Controller
public class PatientsListController {

	/**
	 * Find all.
	 *
	 * @return the list
	 */
	public List<PatientEntity> findAll(){
		PatientServiceImpl patientImpl = new PatientServiceImpl();
		return patientImpl.findAll();	
	}
	
	/**
	 * selects the profile view to render by returning its name.
	 *
	 * @param locale the locale
	 * @param model the model
	 * @return the string
	 */	

	@RequestMapping(value = "/patientsList", method = RequestMethod.POST)
	public String patientsListPOST(Locale locale, Model model) {	
		return "patientsListView";
	}

	/**
	 * Patients list post.
	 *
	 * @param locale the locale
	 * @param model the model
	 * @return the string
	 */
	@RequestMapping(value = "/patientsList", method = RequestMethod.GET)
	public String patientsListGET(Locale locale, Model model) {
		return "patientsListView";
	}
}