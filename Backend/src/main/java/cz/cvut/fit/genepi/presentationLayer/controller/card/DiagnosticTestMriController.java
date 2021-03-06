package cz.cvut.fit.genepi.presentationLayer.controller.card;

import cz.cvut.fit.genepi.businessLayer.BO.display.PatientDisplayBO;
import cz.cvut.fit.genepi.businessLayer.BO.display.card.DiagnosticTestMriDisplayBO;
import cz.cvut.fit.genepi.businessLayer.BO.form.card.DiagnosticTestMriFormBO;
import cz.cvut.fit.genepi.businessLayer.service.AuthorizationChecker;
import cz.cvut.fit.genepi.businessLayer.service.PatientService;
import cz.cvut.fit.genepi.businessLayer.service.card.GenericCardService;
import cz.cvut.fit.genepi.dataLayer.entity.card.DiagnosticTestMriEntity;
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
@SessionAttributes({"diagnosticTestMri", "patient"})
public class DiagnosticTestMriController {

    private final AuthorizationChecker authorizationChecker;

    private final PatientService patientService;

    private final GenericCardService<DiagnosticTestMriDisplayBO, DiagnosticTestMriFormBO, DiagnosticTestMriEntity> genericCardService;

    @Autowired
    public DiagnosticTestMriController(AuthorizationChecker authorizationChecker,
                                       PatientService patientService,
                                       @Qualifier("genericCardServiceImpl")
                                       GenericCardService<DiagnosticTestMriDisplayBO, DiagnosticTestMriFormBO, DiagnosticTestMriEntity> genericCardService) {

        this.authorizationChecker = authorizationChecker;
        this.patientService = patientService;
        this.genericCardService = genericCardService;
    }

    @RequestMapping(value = "/patient/{patientId}/diagnostic-test-mri/create", method = RequestMethod.GET)
    public String diagnosticTestMriCreateGET(
            @PathVariable("patientId") int patientId, Model model, HttpServletRequest request) {

        if (!authorizationChecker.checkAuthoritaion(request)) {
            return "deniedView";
        }

        model.addAttribute("dateBeforeBirth", false);
        model.addAttribute("patient", patientService.getPatientDisplayByIdWithDoctor(patientId));
        model.addAttribute("diagnosticTestMri", new DiagnosticTestMriFormBO());
        return "patient/diagnosticTestMri/createView";
    }

    @RequestMapping(value = "/patient/{patientId}/diagnostic-test-mri/create", method = RequestMethod.POST)
    public String diagnosticTestMriCreatePOST(
            @ModelAttribute("diagnosticTestMri") @Valid DiagnosticTestMriFormBO diagnosticTestMri, BindingResult result,
            @ModelAttribute("patient") PatientDisplayBO patientDisplayBO,
            @PathVariable("patientId") int patientId,
            Model model, HttpServletRequest request) {

        if (!authorizationChecker.checkAuthoritaion(request)) {
            return "deniedView";
        } else {
            boolean dateNotOk = TimeConverter.compareDates(patientDisplayBO.getBirthday(), diagnosticTestMri.getDate());
            if (result.hasErrors() || dateNotOk) {
                model.addAttribute("dateBeforeBirth", dateNotOk);
                return "patient/diagnosticTestMri/createView";
            } else {
                if (!authorizationChecker.isSuperDoctor()) {
                    patientService.voidVerifyPatient(patientId);
                }
                genericCardService.save(diagnosticTestMri, DiagnosticTestMriEntity.class);
                return "redirect:/patient/" + patientId + "/diagnostic-test-mri/list";
            }
        }
    }

    @RequestMapping(value = "/patient/{patientId}/diagnostic-test-mri/{diagnosticTestMriId}/edit", method = RequestMethod.GET)
    public String diagnosticTestMriEditGET(
            @PathVariable("patientId") int patientId,
            @PathVariable("diagnosticTestMriId") int diagnosticTestMriId,
            Model model, HttpServletRequest request) {

        if (!authorizationChecker.checkAuthoritaion(request)) {
            return "deniedView";
        }

        model.addAttribute("dateBeforeBirth", false);
        model.addAttribute("patient", patientService.getPatientDisplayByIdWithDoctor(patientId));
        model.addAttribute("diagnosticTestMri", genericCardService.getById(diagnosticTestMriId, DiagnosticTestMriFormBO.class, DiagnosticTestMriEntity.class));
        return "patient/diagnosticTestMri/editView";
    }

    @RequestMapping(value = "/patient/{patientId}/diagnostic-test-mri/{diagnosticTestMriId}/edit", method = RequestMethod.POST)
    public String diagnosticTestMriEditPOST(
            @ModelAttribute("diagnosticTestMri") @Valid DiagnosticTestMriFormBO diagnosticTestMri, BindingResult result,
            @ModelAttribute("patient") PatientDisplayBO patientDisplayBO,
            @PathVariable("patientId") int patientId,
            @PathVariable("diagnosticTestMriId") int diagnosticTestMriId,
            Model model, HttpServletRequest request) {

        if (!authorizationChecker.checkAuthoritaion(request)) {
            return "deniedView";
        } else {
            boolean dateNotOk = TimeConverter.compareDates(patientDisplayBO.getBirthday(), diagnosticTestMri.getDate());
            if (result.hasErrors() || dateNotOk) {
                model.addAttribute("dateBeforeBirth", dateNotOk);
                return "patient/diagnosticTestMri/createView";
            } else {
                if (!authorizationChecker.isSuperDoctor()) {
                    patientService.voidVerifyPatient(patientId);
                }
                genericCardService.makeHistory(diagnosticTestMriId, DiagnosticTestMriEntity.class);
                diagnosticTestMri.setId(0);
                genericCardService.save(diagnosticTestMri, DiagnosticTestMriEntity.class);
                return "redirect:/patient/" + patientId + "/diagnostic-test-mri/list";
            }
        }
    }

    @RequestMapping(value = "/patient/{patientId}/diagnostic-test-mri/{diagnosticTestMriId}/delete", method = RequestMethod.GET)
    public String diagnosticTestMriDeleteGET(
            @PathVariable("patientId") int patientId,
            @PathVariable("diagnosticTestMriId") int diagnosticTestMriId,
            HttpServletRequest request) {

        if (!authorizationChecker.checkAuthoritaion(request)) {
            return "deniedView";
        }
        genericCardService.delete(diagnosticTestMriId, DiagnosticTestMriEntity.class);
        return "redirect:/patient/" + patientId + "/diagnosticTestMri/list";
    }

    /**
     * Handles the GET request to hide diagnosticTestMri.
     *
     * @param patientId the id of a patient whom we are creating an diagnosticTestMri.
     * @return the address to which the user will be redirected.
     */
    @RequestMapping(value = "/patient/{patientId}/diagnostic-test-mri/{diagnosticTestMriId}/hide", method = RequestMethod.GET)
    public String diagnosticTestMriHideGET(
            @PathVariable("patientId") Integer patientId,
            @PathVariable("diagnosticTestMriId") Integer diagnosticTestMriId,
            HttpServletRequest request) {

        if (!authorizationChecker.checkAuthoritaion(request)) {
            return "deniedView";
        }
        genericCardService.hide(diagnosticTestMriId, DiagnosticTestMriEntity.class);
        return "redirect:/patient/" + patientId + "/diagnostic-test-mri/list";
    }

    /**
     * Handles the GET request to unhide diagnosticTestMri.
     *
     * @param patientId the id of a patient whom we are creating an diagnosticTestMri.
     * @return the address to which the user will be redirected.
     */
    @RequestMapping(value = "/patient/{patientId}/diagnostic-test-mri/{diagnosticTestMriId}/unhide", method = RequestMethod.GET)
    public String diagnosticTestMriUnhideGET(
            @PathVariable("patientId") Integer patientId,
            @PathVariable("diagnosticTestMriId") Integer diagnosticTestMriId,
            HttpServletRequest request) {

        if (!authorizationChecker.checkAuthoritaion(request)) {
            return "deniedView";
        }
        genericCardService.unhide(diagnosticTestMriId, DiagnosticTestMriEntity.class);
        return "redirect:/patient/" + patientId + "/diagnosticTestMri/list";
    }

    @RequestMapping(value = "/patient/{patientId}/diagnostic-test-mri/list", method = RequestMethod.GET)
    public String diagnosticTestMriListGET(
            @PathVariable("patientId") Integer patientId, Model model, HttpServletRequest request) {

        if (!authorizationChecker.checkAuthoritaion(request)) {
            return "deniedView";
        }

        PatientDisplayBO patient = patientService.getPatientDisplayByIdWithDoctor(patientId);
        List<DiagnosticTestMriDisplayBO> diagnosticTestMriDisplayBOList = genericCardService.getRecordsByPatientId(patientId, DiagnosticTestMriDisplayBO.class, DiagnosticTestMriEntity.class);
        model.addAttribute("diagnosticTestMriDisplayBOList", diagnosticTestMriDisplayBOList);
        model.addAttribute("patient", patient);
        return "patient/diagnosticTestMri/listView";
    }
}
