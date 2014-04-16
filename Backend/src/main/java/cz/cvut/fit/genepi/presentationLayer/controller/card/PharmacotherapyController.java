package cz.cvut.fit.genepi.presentationLayer.controller.card;

import cz.cvut.fit.genepi.businessLayer.VO.display.PatientDisplayVO;
import cz.cvut.fit.genepi.businessLayer.VO.display.card.PharmacotherapyDisplayVO;
import cz.cvut.fit.genepi.businessLayer.VO.form.card.PharmacotherapyVO;
import cz.cvut.fit.genepi.businessLayer.service.AuthorizationChecker;
import cz.cvut.fit.genepi.businessLayer.service.PatientService;
import cz.cvut.fit.genepi.businessLayer.service.card.GenericCardService;
import cz.cvut.fit.genepi.dataLayer.entity.card.PharmacotherapyEntity;
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
@SessionAttributes({"pharmacotherapy", "patient"})
public class PharmacotherapyController {

    private final AuthorizationChecker authorizationChecker;

    private final PatientService patientService;

    private final GenericCardService<PharmacotherapyDisplayVO, PharmacotherapyVO, PharmacotherapyEntity> genericCardService;

    @Autowired
    public PharmacotherapyController(AuthorizationChecker authorizationChecker,
                                     PatientService patientService,
                                     @Qualifier("genericCardServiceImpl")
                                     GenericCardService<PharmacotherapyDisplayVO, PharmacotherapyVO, PharmacotherapyEntity> genericCardService) {

        this.authorizationChecker = authorizationChecker;
        this.patientService = patientService;
        this.genericCardService = genericCardService;
    }

    @RequestMapping(value = "/patient/{patientId}/pharmacotherapy/create", method = RequestMethod.GET)
    public String pharmacotherapyCreateGET(
            @PathVariable("patientId") int patientId, Model model, HttpServletRequest request) {

        if (!authorizationChecker.checkAuthoritaion(request)) {
            return "deniedView";
        }

        model.addAttribute("dateBeforeBirth", false);
        model.addAttribute("patient", patientService.getPatientDisplayByIdWithDoctor(patientId));
        model.addAttribute("pharmacotherapy", new PharmacotherapyVO());
        return "patient/pharmacotherapy/createView";
    }


    @RequestMapping(value = "/patient/{patientId}/pharmacotherapy/create", method = RequestMethod.POST)
    public String pharmacotherapyCreatePOST(
            @ModelAttribute("pharmacotherapy") @Valid PharmacotherapyVO pharmacotherapy, BindingResult result,
            @ModelAttribute("patient") PatientDisplayVO patientDisplayVo,
            @PathVariable("patientId") int patientId, Model model, HttpServletRequest request) {

        if (!authorizationChecker.checkAuthoritaion(request)) {
            return "deniedView";
        } else {
            boolean dateNotOk = TimeConverter.compareDates(patientDisplayVo.getBirthday(), pharmacotherapy.getDate());
            if (result.hasErrors() || dateNotOk) {
                model.addAttribute("dateBeforeBirth", dateNotOk);
                return "patient/pharmacotherapy/createView";
            } else {
                if (!authorizationChecker.isSuperDoctor()) {
                    patientService.voidVerifyPatient(patientId);
                }
                genericCardService.save(pharmacotherapy, PharmacotherapyEntity.class);
                return "redirect:/patient/" + patientId + "/pharmacotherapy/list";
            }
        }
    }

    @RequestMapping(value = "/patient/{patientId}/pharmacotherapy/{pharmacotherapyId}/edit", method = RequestMethod.GET)
    public String pharmacotherapyEditGET(@PathVariable("patientId") int patientId,
                                         @PathVariable("pharmacotherapyId") int pharmacotherapyId,
                                         Model model, HttpServletRequest request) {

        if (!authorizationChecker.checkAuthoritaion(request)) {
            return "deniedView";
        }

        model.addAttribute("dateBeforeBirth", false);
        model.addAttribute("patient", patientService.getPatientDisplayByIdWithDoctor(patientId));
        model.addAttribute("pharmacotherapy", genericCardService.getById(pharmacotherapyId, PharmacotherapyVO.class, PharmacotherapyEntity.class));
        return "patient/pharmacotherapy/editView";
    }

    @RequestMapping(value = "/patient/{patientId}/pharmacotherapy/{pharmacotherapyId}/edit", method = RequestMethod.POST)
    public String pharmacotherapyEditPOST(
            @ModelAttribute("pharmacotherapy") @Valid PharmacotherapyVO pharmacotherapy, BindingResult result,
            @ModelAttribute("patient") PatientDisplayVO patientDisplayVo,
            @PathVariable("patientId") int patientId,
            @PathVariable("pharmacotherapyId") int pharmacotherapyId,
            Model model, HttpServletRequest request) {

        if (!authorizationChecker.checkAuthoritaion(request)) {
            return "deniedView";
        } else {
            boolean dateNotOk = TimeConverter.compareDates(patientDisplayVo.getBirthday(), pharmacotherapy.getDate());
            if (result.hasErrors() || dateNotOk) {
                model.addAttribute("dateBeforeBirth", dateNotOk);
                return "patient/pharmacotherapy/editView";
            } else {
                if (!authorizationChecker.isSuperDoctor()) {
                    patientService.voidVerifyPatient(patientId);
                }
                genericCardService.makeHistory(pharmacotherapyId, PharmacotherapyEntity.class);
                pharmacotherapy.setId(0);
                genericCardService.save(pharmacotherapy, PharmacotherapyEntity.class);
                return "redirect:/patient/" + patientId + "/pharmacotherapy/list";
            }
        }
    }

    @RequestMapping(value = "/patient/{patientId}/pharmacotherapy/{pharmacotherapyId}/delete", method = RequestMethod.GET)
    public String pharmacotherapyDeleteGET(
            @PathVariable("patientId") int patientId,
            @PathVariable("pharmacotherapyId") int pharmacotherapyId, HttpServletRequest request) {

        if (!authorizationChecker.checkAuthoritaion(request)) {
            return "deniedView";
        }
        genericCardService.delete(pharmacotherapyId, PharmacotherapyEntity.class);
        return "redirect:/patient/" + patientId + "/pharmacotherapy/list";
    }

    /**
     * Handles the GET request to hide pharmacotherapy.
     *
     * @param patientId the id of a patient whom we are creating an pharmacotherapy.
     * @return the address to which the user will be redirected.
     */
    @RequestMapping(value = "/patient/{patientId}/pharmacotherapy/{pharmacotherapyId}/hide", method = RequestMethod.GET)
    public String pharmacotherapyHideGET(
            @PathVariable("patientId") int patientId,
            @PathVariable("pharmacotherapyId") int pharmacotherapyId,
            HttpServletRequest request) {

        if (!authorizationChecker.checkAuthoritaion(request)) {
            return "deniedView";
        }
        genericCardService.hide(pharmacotherapyId, PharmacotherapyEntity.class);
        return "redirect:/patient/" + patientId + "/pharmacotherapy/list";
    }

    /**
     * Handles the GET request to unhide pharmacotherapy.
     *
     * @param patientId the id of a patient whom we are creating an pharmacotherapy.
     * @return the address to which the user will be redirected.
     */
    @RequestMapping(value = "/patient/{patientId}/pharmacotherapy/{pharmacotherapyId}/unhide", method = RequestMethod.GET)
    public String pharmacotherapyUnhideGET(
            @PathVariable("patientId") int patientId,
            @PathVariable("pharmacotherapyId") int pharmacotherapyId,
            HttpServletRequest request) {

        if (!authorizationChecker.checkAuthoritaion(request)) {
            return "deniedView";
        }
        genericCardService.unhide(pharmacotherapyId, PharmacotherapyEntity.class);
        return "redirect:/patient/" + patientId + "/pharmacotherapy/list";
    }

    @RequestMapping(value = "/patient/{patientId}/pharmacotherapy/list", method = RequestMethod.GET)
    public String pharmacotherapyListGET(
            @PathVariable("patientId") int patientId, Model model, HttpServletRequest request) {

        if (!authorizationChecker.checkAuthoritaion(request)) {
            return "deniedView";
        }
        PatientDisplayVO patient = patientService.getPatientDisplayByIdWithDoctor(patientId);
        List<PharmacotherapyDisplayVO> pharmacotherapyDisplayVoList = genericCardService.getRecordsByPatientId(patientId, PharmacotherapyDisplayVO.class, PharmacotherapyEntity.class);
        model.addAttribute("pharmacotherapyDisplayVoList", pharmacotherapyDisplayVoList);
        model.addAttribute("patient", patient);
        return "patient/pharmacotherapy/listView";
    }
}
