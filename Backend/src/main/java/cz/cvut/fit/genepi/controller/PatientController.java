package cz.cvut.fit.genepi.controller;

import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cz.cvut.fit.genepi.entity.PatientEntity;
import cz.cvut.fit.genepi.service.AnamnesisService;
import cz.cvut.fit.genepi.service.PatientService;

// TODO: Auto-generated Javadoc
/**
 * The Class CreatedPatientController.
 */
@Controller
public class PatientController {

	/** The patient service. */
	@Autowired
	private PatientService patientService;

	/** The anamnesis service. */
	@Autowired
	private AnamnesisService anamnesisService;
	
	/**
	 * Creates the patient get.
	 *
	 * @param locale the locale
	 * @param model the model
	 * @return the string
	 */
	@RequestMapping(value = "/createPatient", method = RequestMethod.GET)
	public String createPatientGET(Locale locale, Model model) {
		model.addAttribute("patient", new PatientEntity());
		return "createPatientView";
	}

	/**
	 * Adds the patient.
	 *
	 * @param patient the patient
	 * @param result the result
	 * @return the string
	 */
	@RequestMapping(value = "/addPatient", method = RequestMethod.POST)
	public String addPatient(
			@ModelAttribute("patient") @Valid PatientEntity patient,
			BindingResult result) {
		if (result.hasErrors()) {
			return "createPatientView";
		} else {
			patientService.save(patient);
			return "redirect:/patientOverview/"
					+ Integer.toString(patient.getId());
		}
	}

	/**
	 * Patient overview post.
	 *
	 * @param id the id
	 * @param locale the locale
	 * @param model the model
	 * @return the string
	 */
	@RequestMapping(value = "/patientOverview", method = RequestMethod.POST)
	public String patientOverviewPOST(@RequestParam("id") int id,
			Locale locale, Model model) {
		/* this.id = id; */
		model.addAttribute("id", id);
		return "patientOverviewView";
	}

	/**
	 * Patient overview get.
	 *
	 * @param locale the locale
	 * @param model the model
	 * @param patientID the patient id
	 * @return the string
	 */
	@RequestMapping(value = "/patientOverview/{patientID}", method = RequestMethod.GET)
	public String patientOverviewGET(Locale locale, Model model,
			@PathVariable("patientID") Integer patientID) {
		PatientEntity patient = patientService.findByID(patientID);
		model.addAttribute("patient", patient);
		model.addAttribute("anamnesis",
				anamnesisService.findLatestAnamnesisByPatientID(patientID));
		return "patientOverviewView";
	}

	/**
	 * Patients list get.
	 *
	 * @param locale the locale
	 * @param model the model
	 * @return the string
	 */
	@RequestMapping(value = "/patientsList", method = RequestMethod.GET)
	public String patientsListGET(Locale locale, Model model) {
		model.addAttribute("patientList", patientService.findAll());
		return "patientsListView";
	}

}