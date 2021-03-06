package cz.cvut.fit.genepi.presentationLayer.controller.card;

import cz.cvut.fit.genepi.businessLayer.BO.display.PatientDisplayBO;
import cz.cvut.fit.genepi.businessLayer.BO.display.card.InvasiveTestEcogDisplayBO;
import cz.cvut.fit.genepi.businessLayer.BO.form.card.InvasiveTestEcogFormBO;
import cz.cvut.fit.genepi.businessLayer.service.AuthorizationChecker;
import cz.cvut.fit.genepi.businessLayer.service.PatientService;
import cz.cvut.fit.genepi.businessLayer.service.card.GenericCardService;
import cz.cvut.fit.genepi.dataLayer.entity.card.InvasiveTestEcogEntity;
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
@SessionAttributes({"invasiveTestEcog", "patient"})
public class InvasiveTestEcogController {

    private final AuthorizationChecker authorizationChecker;

    private final PatientService patientService;

    private final GenericCardService<InvasiveTestEcogDisplayBO, InvasiveTestEcogFormBO, InvasiveTestEcogEntity> genericCardService;

    @Autowired
    public InvasiveTestEcogController(AuthorizationChecker authorizationChecker,
                                      PatientService patientService,
                                      @Qualifier("genericCardServiceImpl")
                                      GenericCardService<InvasiveTestEcogDisplayBO, InvasiveTestEcogFormBO, InvasiveTestEcogEntity> genericCardService) {

        this.authorizationChecker = authorizationChecker;
        this.patientService = patientService;
        this.genericCardService = genericCardService;
    }

    @RequestMapping(value = "/patient/{patientId}/invasive-test-ecog/create", method = RequestMethod.GET)
    public String invasiveTestEcogCreateGET(
            @PathVariable("patientId") int patientId, Model model, HttpServletRequest request) {

        if (!authorizationChecker.checkAuthoritaion(request)) {
            return "deniedView";
        }

        model.addAttribute("dateBeforeBirth", false);
        model.addAttribute("patient", patientService.getPatientDisplayByIdWithDoctor(patientId));
        model.addAttribute("invasiveTestEcog", new InvasiveTestEcogFormBO());
        return "patient/invasiveTestEcog/createView";
    }

    @RequestMapping(value = "/patient/{patientId}/invasive-test-ecog/create", method = RequestMethod.POST)
    public String invasiveTestEcogCreatePOST(
            @ModelAttribute("invasiveTestEcog") @Valid InvasiveTestEcogFormBO invasiveTestEcog, BindingResult result,
            @ModelAttribute("patient") PatientDisplayBO patientDisplayBO,
            @PathVariable("patientId") int patientId,
            Model model, HttpServletRequest request) {

        if (!authorizationChecker.checkAuthoritaion(request)) {
            return "deniedView";
        } else {
            boolean dateNotOk = TimeConverter.compareDates(patientDisplayBO.getBirthday(), invasiveTestEcog.getDate());
            if (result.hasErrors() || dateNotOk) {
                model.addAttribute("dateBeforeBirth", dateNotOk);
                return "patient/invasiveTestEcog/createView";
            } else {
                if (!authorizationChecker.isSuperDoctor()) {
                    patientService.voidVerifyPatient(patientId);
                }
                genericCardService.save(invasiveTestEcog, InvasiveTestEcogEntity.class);
                return "redirect:/patient/" + patientId + "/invasive-test-ecog/list";
            }
        }
    }

    @RequestMapping(value = "/patient/{patientId}/invasive-test-ecog/{invasiveTestEcogId}/edit", method = RequestMethod.GET)
    public String complicationEditGET(
            @PathVariable("patientId") int patientId,
            @PathVariable("invasiveTestEcogId") int invasiveTestEcogId,
            Model model, HttpServletRequest request) {

        if (!authorizationChecker.checkAuthoritaion(request)) {
            return "deniedView";
        }

        model.addAttribute("dateBeforeBirth", false);
        model.addAttribute("patient", patientService.getPatientDisplayByIdWithDoctor(patientId));
        model.addAttribute("invasiveTestEcog", genericCardService.getById(invasiveTestEcogId, InvasiveTestEcogFormBO.class, InvasiveTestEcogEntity.class));
        return "patient/invasiveTestEcog/editView";
    }

    @RequestMapping(value = "/patient/{patientId}/invasive-test-ecog/{invasiveTestEcogId}/edit", method = RequestMethod.POST)
    public String invasiveTestEcogEditPOST(
            @ModelAttribute("invasiveTestEcog") @Valid InvasiveTestEcogFormBO invasiveTestEcog, BindingResult result,
            @ModelAttribute("patient") PatientDisplayBO patientDisplayBO,
            @PathVariable("patientId") int patientId,
            @PathVariable("invasiveTestEcogId") int invasiveTestEcogId,
            Model model, HttpServletRequest request) {

        if (!authorizationChecker.checkAuthoritaion(request)) {
            return "deniedView";
        } else {
            boolean dateNotOk = TimeConverter.compareDates(patientDisplayBO.getBirthday(), invasiveTestEcog.getDate());
            if (result.hasErrors() || dateNotOk) {
                model.addAttribute("dateBeforeBirth", dateNotOk);
                return "patient/invasiveTestEcog/editView";
            } else {
                if (!authorizationChecker.isSuperDoctor()) {
                    patientService.voidVerifyPatient(patientId);
                }
                genericCardService.makeHistory(invasiveTestEcogId, InvasiveTestEcogEntity.class);
                invasiveTestEcog.setId(0);
                genericCardService.save(invasiveTestEcog, InvasiveTestEcogEntity.class);
                return "redirect:/patient/" + patientId + "/invasive-test-ecog/list";
            }
        }
    }

    @RequestMapping(value = "/patient/{patientId}/invasive-test-ecog/{invasiveTestEcogId}/delete", method = RequestMethod.GET)
    public String invasiveTestEcogDeleteGET(
            @PathVariable("patientId") int patientId,
            @PathVariable("invasiveTestEcogId") int invasiveTestEcogId,
            HttpServletRequest request) {

        if (!authorizationChecker.checkAuthoritaion(request)) {
            return "deniedView";
        }

        genericCardService.delete(invasiveTestEcogId, InvasiveTestEcogEntity.class);
        return "redirect:/patient/" + patientId + "/invasive-test-ecog/list";
    }

    /**
     * Handles the GET request to hide invasiveTestEcog.
     *
     * @param patientId the id of a patient whom we are creating an invasiveTestEcog.
     * @return the address to which the user will be redirected.
     */
    @RequestMapping(value = "/patient/{patientId}/invasive-test-ecog/{invasiveTestEcogId}/hide", method = RequestMethod.GET)
    public String invasiveTestEcogHideGET(
            @PathVariable("patientId") int patientId,
            @PathVariable("invasiveTestEcogId") int invasiveTestEcogId,
            HttpServletRequest request) {

        if (!authorizationChecker.checkAuthoritaion(request)) {
            return "deniedView";
        }
        genericCardService.hide(invasiveTestEcogId, InvasiveTestEcogEntity.class);
        return "redirect:/patient/" + patientId + "/invasive-test-ecog/list";
    }

    /**
     * Handles the GET request to unhide invasiveTestEcog.
     *
     * @param patientId the id of a patient whom we are creating an invasiveTestEcog.
     * @return the address to which the user will be redirected.
     */
    @RequestMapping(value = "/patient/{patientId}/invasive-test-ecog/{invasiveTestEcogId}/unhide", method = RequestMethod.GET)
    public String invasiveTestEcogUnhideGET(
            @PathVariable("patientId") int patientId,
            @PathVariable("invasiveTestEcogId") int invasiveTestEcogId,
            HttpServletRequest request) {

        if (!authorizationChecker.checkAuthoritaion(request)) {
            return "deniedView";
        }
        genericCardService.unhide(invasiveTestEcogId, InvasiveTestEcogEntity.class);
        return "redirect:/patient/" + patientId + "/invasive-test-ecog/list";
    }

    @RequestMapping(value = "/patient/{patientId}/invasive-test-ecog/list", method = RequestMethod.GET)
    public String invasiveTestEcogListGET(
            @PathVariable("patientId") Integer patientId, Model model, HttpServletRequest request) {

        if (!authorizationChecker.checkAuthoritaion(request)) {
            return "deniedView";
        }
        PatientDisplayBO patient = patientService.getPatientDisplayByIdWithDoctor(patientId);
        List<InvasiveTestEcogDisplayBO> invasiveTestEcogDisplayBOList = genericCardService.getRecordsByPatientId(patientId, InvasiveTestEcogDisplayBO.class, InvasiveTestEcogEntity.class);
        model.addAttribute("invasiveTestEcogDisplayBOList", invasiveTestEcogDisplayBOList);
        model.addAttribute("patient", patient);
        return "patient/invasiveTestEcog/listView";
    }
}
