package cz.cvut.fit.genepi.presentationLayer.controller.card;

import cz.cvut.fit.genepi.businessLayer.BO.display.PatientDisplayBO;
import cz.cvut.fit.genepi.businessLayer.BO.display.card.ComplicationDisplayBO;
import cz.cvut.fit.genepi.businessLayer.BO.form.card.ComplicationFormBO;
import cz.cvut.fit.genepi.businessLayer.service.AuthorizationChecker;
import cz.cvut.fit.genepi.businessLayer.service.PatientService;
import cz.cvut.fit.genepi.businessLayer.service.card.GenericCardService;
import cz.cvut.fit.genepi.dataLayer.entity.card.ComplicationEntity;
import cz.cvut.fit.genepi.util.TimeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
@SessionAttributes({"complication", "patient"})
public class ComplicationController {

    private final AuthorizationChecker authorizationChecker;

    private final PatientService patientService;

    private final GenericCardService<ComplicationDisplayBO, ComplicationFormBO, ComplicationEntity> genericCardService;

    @Autowired
    public ComplicationController(AuthorizationChecker authorizationChecker,
                                  PatientService patientService,
                                  @Qualifier("genericCardServiceImpl")
                                  GenericCardService<ComplicationDisplayBO, ComplicationFormBO, ComplicationEntity> genericCardService) {

        this.authorizationChecker = authorizationChecker;
        this.patientService = patientService;
        this.genericCardService = genericCardService;
    }

    @RequestMapping(value = "/patient/{patientId}/complication/create", method = RequestMethod.GET)
    public String complicationCreateGET(
            @PathVariable("patientId") int patientId, Model model, HttpServletRequest request) {

        if (!authorizationChecker.checkAuthoritaion(request)) {
            return "deniedView";
        }

        model.addAttribute("dateBeforeBirth", false);
        model.addAttribute("chooseBoth", false);
        model.addAttribute("patient", patientService.getPatientDisplayByIdWithDoctor(patientId));
        model.addAttribute("complication", new ComplicationFormBO());
        return "patient/complication/createView";
    }

    @RequestMapping(value = "/patient/{patientId}/complication/create", method = RequestMethod.POST)
    public String complicationCreatePOST(
            @ModelAttribute("complication") @Valid ComplicationFormBO complication, BindingResult result,
            @ModelAttribute("patient") PatientDisplayBO patientDisplayBO,
            @PathVariable("patientId") int patientId,
            Model model, HttpServletRequest request) {

        if (!authorizationChecker.checkAuthoritaion(request)) {
            return "deniedView";
        } else {
            boolean dateNotOk = TimeConverter.compareDates(patientDisplayBO.getBirthday(), complication.getDate());
            boolean complicationAndComplicationTypeNotOk = complication.getWithComplication() == 2 &&
                    (complication.getComplicationType() == 0 || complication.getComplication() == 0);

            if (result.hasErrors() || dateNotOk || complicationAndComplicationTypeNotOk) {

                model.addAttribute("dateBeforeBirth", dateNotOk);
                model.addAttribute("chooseBoth", complicationAndComplicationTypeNotOk);
                return "patient/complication/createView";
            } else {

                if (!authorizationChecker.isSuperDoctor()) {
                    patientService.voidVerifyPatient(patientId);
                }
                genericCardService.save(complication, ComplicationEntity.class);
                return "redirect:/patient/" + patientId + "/complication/list";
            }
        }
    }

    @RequestMapping(value = "/patient/{patientId}/complication/{complicationId}/edit", method = RequestMethod.GET)
    public String complicationEditGET(
            @PathVariable("patientId") int patientId,
            @PathVariable("complicationId") int complicationId,
            Model model, HttpServletRequest request) {

        if (!authorizationChecker.checkAuthoritaion(request)) {
            return "deniedView";
        }

        model.addAttribute("chooseBoth", false);
        model.addAttribute("dateBeforeBirth", false);
        model.addAttribute("patient", patientService.getPatientDisplayByIdWithDoctor(patientId));
        model.addAttribute("complication", genericCardService.getById(complicationId, ComplicationFormBO.class, ComplicationEntity.class));
        return "patient/complication/editView";
    }

    @RequestMapping(value = "/patient/{patientId}/complication/{complicationId}/edit", method = RequestMethod.POST)
    public String complicationEditPOST(
            @ModelAttribute("complication") @Valid ComplicationFormBO complication, BindingResult result,
            @ModelAttribute("patient") PatientDisplayBO patientDisplayBO,
            @PathVariable("patientId") int patientId,
            @PathVariable("complicationId") int complicationId,
            Model model, HttpServletRequest request) {

        if (!authorizationChecker.checkAuthoritaion(request)) {
            return "deniedView";
        } else {
            boolean dateNotOk = TimeConverter.compareDates(patientDisplayBO.getBirthday(), complication.getDate());
            boolean complicationAndComplicationTypeNotOk = complication.getWithComplication() == 2 &&
                    (complication.getComplicationType() == 0 || complication.getComplication() == 0);
            if (result.hasErrors() || dateNotOk || complicationAndComplicationTypeNotOk) {

                model.addAttribute("dateBeforeBirth", dateNotOk);
                model.addAttribute("chooseBoth", complicationAndComplicationTypeNotOk);
                return "patient/complication/editView";
            } else {

                if (!authorizationChecker.isSuperDoctor()) {
                    patientService.voidVerifyPatient(patientId);
                }
                genericCardService.makeHistory(complicationId, ComplicationEntity.class);
                complication.setId(0);
                genericCardService.save(complication, ComplicationEntity.class);
                return "redirect:/patient/" + patientId + "/complication/list";
            }
        }
    }

    @RequestMapping(value = "/patient/{patientId}/complication/{complicationId}/delete", method = RequestMethod.GET)
    public String complicationDeleteGET(
            @PathVariable("patientId") int patientId,
            @PathVariable("complicationId") int complicationId,
            HttpServletRequest request) {

        if (!authorizationChecker.checkAuthoritaion(request)) {
            return "deniedView";
        }
        genericCardService.delete(complicationId, ComplicationEntity.class);
        return "redirect:/patient/" + patientId + "/complication/list";
    }

    /**
     * Handles the GET request to hide complication.
     *
     * @param patientId the id of a patient whom we are creating an complication.
     * @return the address to which the user will be redirected.
     */
    @RequestMapping(value = "/patient/{patientId}/complication/{complicationId}/hide", method = RequestMethod.GET)
    public String complicationHideGET(
            @PathVariable("patientId") int patientId,
            @PathVariable("complicationId") int complicationId,
            HttpServletRequest request) {

        if (!authorizationChecker.checkAuthoritaion(request)) {
            return "deniedView";
        }
        genericCardService.hide(complicationId, ComplicationEntity.class);
        return "redirect:/patient/" + patientId + "/complication/list";
    }

    /**
     * Handles the GET request to unhide complication.
     *
     * @param patientId the id of a patient whom we are creating an complication.
     * @return the address to which the user will be redirected.
     */
    @RequestMapping(value = "/patient/{patientId}/complication/{complicationId}/unhide", method = RequestMethod.GET)
    public String complicationUnhideGET(
            @PathVariable("patientId") Integer patientId,
            @PathVariable("complicationId") Integer complicationId,
            HttpServletRequest request) {

        if (!authorizationChecker.checkAuthoritaion(request)) {
            return "deniedView";
        }
        genericCardService.unhide(complicationId, ComplicationEntity.class);
        return "redirect:/patient/" + patientId + "/complication/list";
    }

    @RequestMapping(value = "/patient/{patientId}/complication/list", method = RequestMethod.GET)
    public String complicationListGET(
            @PathVariable("patientId") Integer patientId, Model model, HttpServletRequest request) {

        if (!authorizationChecker.checkAuthoritaion(request)) {
            return "deniedView";
        }
        PatientDisplayBO patient = patientService.getPatientDisplayByIdWithDoctor(patientId);
        List<ComplicationDisplayBO> complicationDisplayBOList = genericCardService.getRecordsByPatientId(patientId, ComplicationDisplayBO.class, ComplicationEntity.class);
        model.addAttribute("complicationDisplayBOList", complicationDisplayBOList);
        model.addAttribute("patient", patient);
        return "patient/complication/listView";
    }
}
