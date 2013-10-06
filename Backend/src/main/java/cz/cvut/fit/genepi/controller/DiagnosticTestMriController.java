package cz.cvut.fit.genepi.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cz.cvut.fit.genepi.entity.PatientEntity;
import cz.cvut.fit.genepi.service.PatientService;

@Controller
public class DiagnosticTestMriController {
	@Autowired
	PatientService patientService;
	
	@RequestMapping(value = "/patient/{patientID}/diagnosticTestMri/list", method = RequestMethod.GET)
	public String diagnosticTestMriListGET(Locale locale, Model model,
			@PathVariable("patientID") Integer patientID) {
		PatientEntity patient = patientService.findByID(PatientEntity.class,
				patientID);
		model.addAttribute("patient", patient);
		return "patient/diagnosticTestMri/listView";
	}
}
