package cz.cvut.fit.genepi.presentationLayer.controller.card;

import cz.cvut.fit.genepi.businessLayer.service.PatientService;
import cz.cvut.fit.genepi.businessLayer.service.card.InvasiveTestEegService;
import cz.cvut.fit.genepi.dataLayer.entity.PatientEntity;
import cz.cvut.fit.genepi.dataLayer.entity.card.InvasiveTestEegEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.Locale;

@Controller
public class InvasiveTestEEGController {

    private PatientService patientService;

    private InvasiveTestEegService invasiveTestEegService;

    @Autowired
    public InvasiveTestEEGController(PatientService patientService,
                                     InvasiveTestEegService invasiveTestEegService) {
        this.patientService = patientService;
        this.invasiveTestEegService = invasiveTestEegService;
    }

    @RequestMapping(value = "/patient/{patientID}/invasiveTestEEG/create", method = RequestMethod.GET)
    public String invasiveTestEEGCreateGET(Locale locale, Model model,
                                           @PathVariable("patientID") Integer patientID) {
        PatientEntity patient = patientService.findByID(PatientEntity.class,
                patientID);

        model.addAttribute("patient", patient);
        model.addAttribute("invasiveTestEEG", new InvasiveTestEegEntity());
        return "patient/invasiveTestEEG/createView";
    }

    /**
     * Adds the invasiveTestEEG.
     *
     * @param invasiveTestEEG the invasiveTestEEG
     * @param result          the result
     * @param patientID       the patient id
     * @return the string
     */
    @RequestMapping(value = "/patient/{patientID}/invasiveTestEEG/create", method = RequestMethod.POST)
    public String invasiveTestEEGCreatePOST(
            @ModelAttribute("invasiveTestEEG") @Valid InvasiveTestEegEntity invasiveTestEEG,
            BindingResult result, @PathVariable("patientID") Integer patientID) {
        if (result.hasErrors()) {
            return "patient/invasiveTestEEG/createView";
        } else {
            invasiveTestEEG.setPatient(patientService.findByID(
                    PatientEntity.class, patientID));
            invasiveTestEegService.save(invasiveTestEEG);
            return "redirect:/patient/" + patientID + "/invasiveTestEEG/list";
        }
    }

    @RequestMapping(value = "/patient/{patientID}/invasiveTestEEG/{invasiveTestEEGID}/delete", method = RequestMethod.GET)
    public String invasiveTestEEGDeleteGET(Locale locale, Model model,
                                           @PathVariable("patientID") Integer patientID,
                                           @PathVariable("invasiveTestEEGID") Integer invasiveTestEEGID) {

        invasiveTestEegService.delete(invasiveTestEegService.findByID(
                InvasiveTestEegEntity.class, invasiveTestEEGID));
        return "redirect:/patient/" + patientID + "/invasiveTestEEG/list";
    }

    /**
     * Handles the GET request to hide invasiveTestEeg.
     *
     * @param patientId   the id of a patient whom we are creating an invasiveTestEeg.
     * @param anamnesisId
     * @param locale      the user's locale.
     * @param model       the model to be filled for view.
     * @return the address to which the user will be redirected.
     */
    @RequestMapping(value = "/patient/{patientId}/invasiveTestEeg/{invasiveTestEegId}/hide", method = RequestMethod.GET)
    public String invasiveTestEegHideGET(
            @PathVariable("patientId") Integer patientId,
            @PathVariable("invasiveTestEegId") Integer invasiveTestEegId,
            Locale locale, Model model) {

        invasiveTestEegService.hide(invasiveTestEegService.findByID(
                InvasiveTestEegEntity.class, invasiveTestEegId));
        return "redirect:/patient/" + patientId + "/invasiveTestEeg/list";
    }

    /**
     * Handles the GET request to unhide invasiveTestEeg.
     *
     * @param patientId   the id of a patient whom we are creating an invasiveTestEeg.
     * @param anamnesisId
     * @param locale      the user's locale.
     * @param model       the model to be filled for view.
     * @return the address to which the user will be redirected.
     */
    @RequestMapping(value = "/patient/{patientId}/invasiveTestEeg/{invasiveTestEegId}/unhide", method = RequestMethod.GET)
    public String invasiveTestEegUnhideGET(
            @PathVariable("patientId") Integer patientId,
            @PathVariable("invasiveTestEegId") Integer invasiveTestEegId,
            Locale locale, Model model) {

        invasiveTestEegService.unhide(invasiveTestEegService.findByID(
                InvasiveTestEegEntity.class, invasiveTestEegId));
        // TODO: address to get back to admin module where is list od hidden
        // records.
        return "redirect:/patient/" + patientId + "/invasiveTestEeg/list";
    }

    @RequestMapping(value = "/patient/{patientID}/invasiveTestEEG/{invasiveTestEEGID}/export", method = RequestMethod.GET)
    public String invasiveTestEEGExportGET(Locale locale, Model model,
                                           @PathVariable("patientID") Integer patientID,
                                           @PathVariable("invasiveTestEEGID") Integer invasiveTestEEGID) {
        return "redirect:/patient/" + patientID + "/invasiveTestEEG/list";
    }

    @RequestMapping(value = "/patient/{patientID}/invasiveTestEEG/list", method = RequestMethod.GET)
    public String invasiveTestEEGListGET(Locale locale, Model model,
                                         @PathVariable("patientID") Integer patientID) {
        PatientEntity patient = patientService
                .getPatientByIdWithInvasiveTestEEGList(patientID);
        model.addAttribute("patient", patient);
        return "patient/invasiveTestEEG/listView";
    }
}
