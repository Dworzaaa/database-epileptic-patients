package cz.cvut.fit.genepi.presentationLayer.controller;

import cz.cvut.fit.genepi.businessLayer.service.ContactService;
import cz.cvut.fit.genepi.businessLayer.service.PatientService;
import cz.cvut.fit.genepi.businessLayer.service.card.AnamnesisService;
import cz.cvut.fit.genepi.util.AuthorizationChecker;
import cz.cvut.fit.genepi.util.LoggingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

/**
 * This class is a controller class which handles requests connected with a
 * user.
 */
@Controller
public class HiddenController {

    /**
     * The user service.
     */
    private PatientService patientService;

    /**
     * The contact service.
     */
    private ContactService contactService;

    /**
     * The anamnesis service.
     */
    private AnamnesisService anamnesisService;

    /**
     * Constructor which serves to autowire services.
     *
     * @param patientService   the patientService to be autowired.
     * @param anamnesisService the anamnesisService to be autowired.
     */
    @Autowired
    public HiddenController(PatientService patientService,
                            AnamnesisService anamnesisService, ContactService contactService) {
        this.patientService = patientService;
        this.anamnesisService = anamnesisService;
        this.contactService = contactService;
    }

    /**
     * The Constant logger.
     */
    private LoggingService s = new LoggingService();

    /**
     * Handles the request to access list of hidden records.
     *
     * @param locale the user's locale.
     * @param model  the model to be filled for view.
     * @return the string of a view to be rendered.
     */
    @RequestMapping(value = "/hidden", method = RequestMethod.GET)
    public String userListGET(Locale locale, Model model, HttpServletRequest request) {
        if (!AuthorizationChecker.checkAuthoritaion(request)) {
            return "deniedView";
        }

        model.addAttribute("patientList", patientService.findAllHidden());
        //model.addAttribute("anamnesisList", anamnesisService.findAllHidden());
        return "hiddenView";
    }

}