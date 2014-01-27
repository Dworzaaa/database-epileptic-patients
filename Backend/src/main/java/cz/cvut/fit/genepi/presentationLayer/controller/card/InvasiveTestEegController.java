package cz.cvut.fit.genepi.presentationLayer.controller.card;

import cz.cvut.fit.genepi.businessLayer.VO.form.InvasiveTestEegVO;
import cz.cvut.fit.genepi.businessLayer.service.PatientService;
import cz.cvut.fit.genepi.businessLayer.service.card.InvasiveTestEegService;
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
public class InvasiveTestEegController {

    private PatientService patientService;

    private InvasiveTestEegService invasiveTestEegService;

    @Autowired
    public InvasiveTestEegController(PatientService patientService,
                                     InvasiveTestEegService invasiveTestEegService) {

        this.patientService = patientService;
        this.invasiveTestEegService = invasiveTestEegService;
    }

    @RequestMapping(value = "/patient/{patientId}/invasive-test-eeg/create", method = RequestMethod.GET)
    public String invasiveTestEegCreateGET(
            @PathVariable("patientId") Integer patientId, Locale locale, Model model) {

        model.addAttribute("patient", patientService.getPatientDisplayByIdWithDoctor(patientId));
        model.addAttribute("invasiveTestEeg", new InvasiveTestEegVO());
        return "patient/invasiveTestEeg/formView";
    }

    @RequestMapping(value = "/patient/{patientId}/invasive-test-eeg/{invasiveTestEegId}/edit", method = RequestMethod.GET)
    public String complicationEditGET(
            @PathVariable("patientId") Integer patientId,
            @PathVariable("invasiveTestEegId") Integer invasiveTestEegId,
            Locale locale, Model model) {

        model.addAttribute("patient", patientService.getPatientDisplayByIdWithDoctor(patientId));
        model.addAttribute("invasiveTestEeg", invasiveTestEegService.getById(InvasiveTestEegVO.class, invasiveTestEegId));
        return "patient/invasiveTestEeg/formView";
    }

    /**
     * Adds the invasiveTestEEG.
     *
     * @param invasiveTestEEG the invasiveTestEEG
     * @param result          the result
     * @param patientID       the patient id
     * @return the string
     */
    @RequestMapping(value = "/patient/{patientId}/invasive-test-eeg/save", method = RequestMethod.POST)
    public String invasiveTestEegSavePOST(
            @ModelAttribute("invasiveTestEeg") @Valid InvasiveTestEegVO invasiveTestEeg,
            @PathVariable("patientId") Integer patientId,
            BindingResult result, Locale locale, Model model) {

        if (result.hasErrors()) {
            model.addAttribute("patient", patientService.getPatientDisplayByIdWithDoctor(patientId));
            return "patient/invasiveTestEeg/createView";
        } else {
            invasiveTestEeg.setPatientId(patientId);
            invasiveTestEegService.save(invasiveTestEeg);
            return "redirect:/patient/" + patientId + "/invasive-test-eeg/list";
        }
    }

    @RequestMapping(value = "/patient/{patientId}/invasive-test-eeg/{invasiveTestEegId}/delete", method = RequestMethod.GET)
    public String invasiveTestEegDeleteGET(
            @PathVariable("patientId") Integer patientId,
            @PathVariable("invasiveTestEegId") Integer invasiveTestEegId,
            Locale locale, Model model) {

        invasiveTestEegService.delete(invasiveTestEegId);
        return "redirect:/patient/" + patientId + "/invasive-test-eeg/list";
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
    @RequestMapping(value = "/patient/{patientId}/invasive-test-eeg/{invasiveTestEegId}/hide", method = RequestMethod.GET)
    public String invasiveTestEegHideGET(
            @PathVariable("patientId") Integer patientId,
            @PathVariable("invasiveTestEegId") Integer invasiveTestEegId,
            Locale locale, Model model) {

        invasiveTestEegService.hide(invasiveTestEegId);
        return "redirect:/patient/" + patientId + "/invasive-test-eeg/list";
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
    @RequestMapping(value = "/patient/{patientId}/invasive-test-eeg/{invasiveTestEegId}/unhide", method = RequestMethod.GET)
    public String invasiveTestEegUnhideGET(
            @PathVariable("patientId") Integer patientId,
            @PathVariable("invasiveTestEegId") Integer invasiveTestEegId,
            Locale locale, Model model) {

        invasiveTestEegService.unhide(invasiveTestEegId);
        // TODO: address to get back to admin module where is list od hidden
        // records.
        return "redirect:/patient/" + patientId + "/invasive-test-eeg/list";
    }

   /* @RequestMapping(value = "/patient/{patientID}/invasiveTestEEG/{invasiveTestEEGID}/export", method = RequestMethod.GET)
    public String invasiveTestEEGExportGET(Locale locale, Model model,
                                           @PathVariable("patientID") Integer patientID,
                                           @PathVariable("invasiveTestEEGID") Integer invasiveTestEEGID) {
        return "redirect:/patient/" + patientID + "/invasiveTestEEG/list";
    }*/

    @RequestMapping(value = "/patient/{patientId}/invasive-test-eeg/list", method = RequestMethod.GET)
    public String invasiveTestEegListGET(
            @PathVariable("patientId") Integer patientId, Locale locale, Model model) {

        model.addAttribute("patient", patientService.getPatientDisplayByIdWithInvasiveTestEegList(patientId));
        return "patient/invasiveTestEeg/listView";
    }
}
