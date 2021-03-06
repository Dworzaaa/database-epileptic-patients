package cz.cvut.fit.genepi.presentationLayer.controller;

import cz.cvut.fit.genepi.businessLayer.BO.display.PatientDisplayBO;
import cz.cvut.fit.genepi.businessLayer.BO.display.card.*;
import cz.cvut.fit.genepi.businessLayer.BO.form.ExportParamsFormBO;
import cz.cvut.fit.genepi.businessLayer.BO.form.PatientFormBO;
import cz.cvut.fit.genepi.businessLayer.BO.form.card.*;
import cz.cvut.fit.genepi.businessLayer.service.*;
import cz.cvut.fit.genepi.businessLayer.service.card.AnamnesisService;
import cz.cvut.fit.genepi.businessLayer.service.card.GenericCardService;
import cz.cvut.fit.genepi.businessLayer.service.card.OperationService;
import cz.cvut.fit.genepi.businessLayer.service.card.SeizureService;
import cz.cvut.fit.genepi.dataLayer.DAO.GenericDAO;
import cz.cvut.fit.genepi.dataLayer.entity.ExportParamsEntity;
import cz.cvut.fit.genepi.dataLayer.entity.PatientEntity;
import cz.cvut.fit.genepi.dataLayer.entity.RoleEntity;
import cz.cvut.fit.genepi.dataLayer.entity.UserEntity;
import cz.cvut.fit.genepi.dataLayer.entity.card.*;
import cz.cvut.fit.genepi.util.JSONEncoder;
import cz.cvut.fit.genepi.util.LoggingService;
import cz.cvut.fit.genepi.util.TimeConverter;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * The Class PatientController.
 */
@Controller
@SessionAttributes({"patient", "patientFormBO", "exportParams", "doctors"})
public class PatientController {

    private final AnonymizeService anonymizeService;

    private final AuthorizationChecker authorizationChecker;

    private final ExportParamsService exportParamsService;

    /**
     * The patient service.
     */
    private final PatientService patientService;

    /**
     * The user service.
     */
    private final UserService userService;

    private final RoleService roleService;

    private final ExportToPdfService exportToPdfService;

    private final ExportToXlsxService exportToXlsxService;

    private final ExportToDocxService exportToDocxService;

    private final ExportToTxtService exportToTxtService;

    private final ExportToCsvService exportToCsvService;


    @Autowired
    @Qualifier("genericCardServiceImpl")
    private GenericCardService<PharmacotherapyDisplayBO, PharmacotherapyFormBO, PharmacotherapyEntity> pharmacotherapyCardService;

    @Autowired
    @Qualifier("genericDAOImpl")
    private GenericDAO<ExportParamsEntity> genericDAOExportParams;

    @Autowired
    @Qualifier("genericDAOImpl")
    private GenericDAO<PatientEntity> genericDAOPatient;

    private final AnamnesisService anamnesisService;

    private final SeizureService seizureService;

    private final GenericCardService<NeurologicalFindingDisplayBO, NeurologicalFindingFormBO, NeurologicalFindingEntity> nerologicalFindingCardService;

    private final GenericCardService<NeuropsychologyDisplayBO, NeuropsychologyFormBO, NeuropsychologyEntity> neuropsychologyCardService;

    private final GenericCardService<DiagnosticTestScalpEegDisplayBO, DiagnosticTestScalpEegFormBO, DiagnosticTestScalpEegEntity> diagnosticTestScalpEegCardService;

    private final GenericCardService<DiagnosticTestMriDisplayBO, DiagnosticTestMriFormBO, DiagnosticTestMriEntity> diagnosticTestMriCardService;

    private final GenericCardService<InvasiveTestEcogDisplayBO, InvasiveTestEcogFormBO, InvasiveTestEcogEntity> invasiveTestEcogCardService;

    private final GenericCardService<InvasiveTestEegDisplayBO, InvasiveTestEegFormBO, InvasiveTestEegEntity> invasiveTestEegCardService;

    private final GenericCardService<InvasiveTestCorticalMappingDisplayBO, InvasiveTestCorticalMappingFormBO, InvasiveTestCorticalMappingEntity> invasiveTestCorticalMappingCardService;

    private final OperationService operationService;

    private final GenericCardService<HistologyDisplayBO, HistologyFormBO, HistologyEntity> histologyCardService;

    private final GenericCardService<ComplicationDisplayBO, ComplicationFormBO, ComplicationEntity> complicationCardService;

    @Autowired
    public PatientController(AnonymizeService anonymizeService,
                             AuthorizationChecker authorizationChecker,
                             ExportParamsService exportParamsService,
                             PatientService patientService,
                             UserService userService,
                             RoleService roleService,
                             ExportToPdfService exportToPdfService,
                             ExportToXlsxService exportToXlsxService,
                             ExportToDocxService exportToDocxService,
                             ExportToTxtService exportToTxtService,
                             ExportToCsvService exportToCsvService,
                             AnamnesisService anamnesisService,
                             SeizureService seizureService,
                             @Qualifier("genericCardServiceImpl")
                             GenericCardService<NeurologicalFindingDisplayBO, NeurologicalFindingFormBO, NeurologicalFindingEntity> nerologicalFindingCardService,
                             @Qualifier("genericCardServiceImpl")
                             GenericCardService<NeuropsychologyDisplayBO, NeuropsychologyFormBO, NeuropsychologyEntity> neuropsychologyCardService,
                             @Qualifier("genericCardServiceImpl")
                             GenericCardService<DiagnosticTestScalpEegDisplayBO, DiagnosticTestScalpEegFormBO, DiagnosticTestScalpEegEntity> diagnosticTestScalpEegCardService,
                             @Qualifier("genericCardServiceImpl")
                             GenericCardService<DiagnosticTestMriDisplayBO, DiagnosticTestMriFormBO, DiagnosticTestMriEntity> diagnosticTestMriCardService,
                             @Qualifier("genericCardServiceImpl")
                             GenericCardService<InvasiveTestEcogDisplayBO, InvasiveTestEcogFormBO, InvasiveTestEcogEntity> invasiveTestEcogCardService,
                             @Qualifier("genericCardServiceImpl")
                             GenericCardService<InvasiveTestEegDisplayBO, InvasiveTestEegFormBO, InvasiveTestEegEntity> invasiveTestEegCardService,
                             @Qualifier("genericCardServiceImpl")
                             GenericCardService<InvasiveTestCorticalMappingDisplayBO, InvasiveTestCorticalMappingFormBO, InvasiveTestCorticalMappingEntity> invasiveTestCorticalMappingCardService,
                             OperationService operationService,
                             @Qualifier("genericCardServiceImpl")
                             GenericCardService<HistologyDisplayBO, HistologyFormBO, HistologyEntity> histologyCardService,
                             @Qualifier("genericCardServiceImpl")
                             GenericCardService<ComplicationDisplayBO, ComplicationFormBO, ComplicationEntity> complicationCardService) {

        this.anonymizeService = anonymizeService;
        this.authorizationChecker = authorizationChecker;
        this.exportParamsService = exportParamsService;
        this.patientService = patientService;
        this.userService = userService;
        this.roleService = roleService;
        this.exportToPdfService = exportToPdfService;
        this.exportToXlsxService = exportToXlsxService;
        this.exportToDocxService = exportToDocxService;
        this.exportToTxtService = exportToTxtService;
        this.exportToCsvService = exportToCsvService;
        this.anamnesisService = anamnesisService;
        this.seizureService = seizureService;
        this.nerologicalFindingCardService = nerologicalFindingCardService;
        this.neuropsychologyCardService = neuropsychologyCardService;
        this.diagnosticTestScalpEegCardService = diagnosticTestScalpEegCardService;
        this.diagnosticTestMriCardService = diagnosticTestMriCardService;
        this.invasiveTestEcogCardService = invasiveTestEcogCardService;
        this.invasiveTestEegCardService = invasiveTestEegCardService;
        this.invasiveTestCorticalMappingCardService = invasiveTestCorticalMappingCardService;
        this.operationService = operationService;
        this.histologyCardService = histologyCardService;
        this.complicationCardService = complicationCardService;
    }

    /**
     * The Constant logger.
     */
    private final LoggingService logger = new LoggingService();

    @RequestMapping(value = "/patient/create", method = RequestMethod.GET)
    public String patientCreateGET(HttpServletRequest request, Model model) {

        if (!authorizationChecker.checkAuthoritaion(request)) {
            return "deniedView";
        }

        model.addAttribute("begginningEpiOk", true);
        model.addAttribute("doctors", roleService.getAllDoctors());
        model.addAttribute("patientFormBO", new PatientFormBO());
        return "patient/createView";
    }

    @RequestMapping(value = "/patient/create", method = RequestMethod.POST)
    public String patientCreatePOST(
            @ModelAttribute("patientFormBO") @Valid PatientFormBO patientFormBO, BindingResult result,
            HttpServletRequest request, Model model) {

        if (!authorizationChecker.checkAuthoritaion(request)) {
            return "deniedView";
        }

        if (result.hasErrors() || TimeConverter.compareDates(patientFormBO.getBirthday(), DateTime.now())) {
            model.addAttribute("begginningEpiOk", true);
            return "patient/createView";
        } else {
            int patientId = patientService.save(patientFormBO, PatientEntity.class);
            return "redirect:/patient/" + Integer.toString(patientId) + "/overview";
        }
    }

    @RequestMapping(value = "/patient/{patientId}/edit", method = RequestMethod.GET)
    public String patientEditGET(@PathVariable("patientId") int patientId,
                                 Model model, HttpServletRequest request) {

        if (!authorizationChecker.checkAuthoritaion(request)) {
            return "deniedView";
        }

        PatientDisplayBO patient = patientService.getPatientDisplayByIdWithDoctor(patientId);

        model.addAttribute("begginningEpiOk", true);
        model.addAttribute("patient", patient);
        model.addAttribute("doctors", roleService.getAllDoctors());
        model.addAttribute("patientFormBO", patientService.getById(patientId, PatientFormBO.class, PatientEntity.class));

        return "patient/editView";
    }

    @RequestMapping(value = "/patient/{patientId}/edit", method = RequestMethod.POST)
    public String patientEditPOST(
            @ModelAttribute("patientFormBO") @Valid PatientFormBO patientFormBO, BindingResult result,
            @PathVariable("patientId") int patientId, Model model, HttpServletRequest request) {

        if (!authorizationChecker.checkAuthoritaion(request)) {
            return "deniedView";
        }

        boolean isBeginningOfEpilepsyOk = patientService.verifyBeginningEpilepsy(patientFormBO.getId(), patientFormBO.getBeginningEpilepsy());
        if (result.hasErrors() || !isBeginningOfEpilepsyOk) {

            model.addAttribute("begginningEpiOk", isBeginningOfEpilepsyOk);

            return "patient/editView";
        } else {
            patientService.update(patientFormBO, PatientEntity.class);
            return "redirect:/patient/" + Integer.toString(patientId) + "/overview";
        }
    }

    @RequestMapping(value = "/patient/{patientId}/verify", method = RequestMethod.GET)
    public String patientVerifyGET(
            @PathVariable("patientId") Integer patientId, Model model, HttpServletRequest request) {

        if (!authorizationChecker.checkAuthoritaion(request)) {
            return "deniedView";
        }

        PatientDisplayBO patientDisplay = patientService.getPatientDisplayByIdWithDoctor(patientId);
        PatientFormBO patient = patientService.getById(patientId, PatientFormBO.class, PatientEntity.class);

        model.addAttribute("patient", patientDisplay);
        model.addAttribute("patientFormBO", patient);

        return "patient/verifyView";
    }

    @RequestMapping(value = "/patient/{patientId}/verify", method = RequestMethod.POST)
    public String patientVerifyPOST(
            @ModelAttribute("patientFormBO") @Valid PatientFormBO patientFormBO, BindingResult result,
            @PathVariable("patientId") Integer patientId, Model model, HttpServletRequest request) {

        if (!authorizationChecker.checkAuthoritaion(request)) {
            return "deniedView";
        }
        if (result.hasErrors()) {
            return "patient/verifyView";
        } else {
            patientService.update(patientFormBO, PatientEntity.class);
            return "redirect:/patient/" + patientId + "/overview";
        }
    }

    @RequestMapping(value = "/patient/{patientId}/overview", method = RequestMethod.GET)
    public String patientOverviewGET(
            @PathVariable("patientId") Integer patientId, Model model, HttpServletRequest request) {
        request.getSession().setAttribute("neuropsychology", null);
        if (!authorizationChecker.checkAuthoritaion(request)) {
            return "deniedView";
        }

        PatientDisplayBO patient = patientService.getPatientDisplayByIdWithDoctor(patientId);

        /* get all patients records */
        AnamnesisDisplayBO anamnesisDisplayBO = anamnesisService.getRecordsByPatientId(patientId);
        SeizureDisplayBO seizureDisplayBO = seizureService.getLatestRecordByPatientId(patientId);
        List<PharmacotherapyDisplayBO> pharmacotherapyDisplayBOList = pharmacotherapyCardService.getRecordsByPatientId(patientId, PharmacotherapyDisplayBO.class, PharmacotherapyEntity.class);
        NeurologicalFindingDisplayBO neurologicalFindingDisplayBO = nerologicalFindingCardService.getLatestRecordByPatientId(patientId, NeurologicalFindingDisplayBO.class, NeurologicalFindingEntity.class);
        NeuropsychologyDisplayBO neuropsychologyDisplayBO = neuropsychologyCardService.getLatestRecordByPatientId(patientId, NeuropsychologyDisplayBO.class, NeuropsychologyEntity.class);
        DiagnosticTestScalpEegDisplayBO diagnosticTestScalpEegDisplayBO = diagnosticTestScalpEegCardService.getLatestRecordByPatientId(patientId, DiagnosticTestScalpEegDisplayBO.class, DiagnosticTestScalpEegEntity.class);
        DiagnosticTestMriDisplayBO diagnosticTestMriDisplayBO = diagnosticTestMriCardService.getLatestRecordByPatientId(patientId, DiagnosticTestMriDisplayBO.class, DiagnosticTestMriEntity.class);
        InvasiveTestEcogDisplayBO invasiveTestEcogDisplayBO = invasiveTestEcogCardService.getLatestRecordByPatientId(patientId, InvasiveTestEcogDisplayBO.class, InvasiveTestEcogEntity.class);
        InvasiveTestEegDisplayBO invasiveTestEegDisplayBO = invasiveTestEegCardService.getLatestRecordByPatientId(patientId, InvasiveTestEegDisplayBO.class, InvasiveTestEegEntity.class);
        InvasiveTestCorticalMappingDisplayBO invasiveTestCorticalMappingDisplayBO = invasiveTestCorticalMappingCardService.getLatestRecordByPatientId(patientId, InvasiveTestCorticalMappingDisplayBO.class, InvasiveTestCorticalMappingEntity.class);
        OperationDisplayBO operationDisplayBO = operationService.getLatestOperationByPatientId(patientId);
        List<HistologyDisplayBO> histologyDisplayBOList = histologyCardService.getRecordsByPatientId(patientId, HistologyDisplayBO.class, HistologyEntity.class);
        List<ComplicationDisplayBO> complicationDisplayBOList = complicationCardService.getRecordsByPatientId(patientId, ComplicationDisplayBO.class, ComplicationEntity.class);
        OperationWithOutcomesDisplayBO operationWithOutcomesDisplayBO = operationService.getLatestOperationWithOutcomesByPatientId(patientId);

        /* pass patient and all records to model */
        model.addAttribute("patient", patient)
                .addAttribute("anamnesisDisplayBO", anamnesisDisplayBO)
                .addAttribute("seizureDisplayBO", seizureDisplayBO)
                .addAttribute("pharmacotherapyDisplayBOList", pharmacotherapyDisplayBOList)
                .addAttribute("neurologicalFindingDisplayBO", neurologicalFindingDisplayBO)
                .addAttribute("neuropsychologyDisplayBO", neuropsychologyDisplayBO)
                .addAttribute("diagnosticTestScalpEegDisplayBO", diagnosticTestScalpEegDisplayBO)
                .addAttribute("diagnosticTestMriDisplayBO", diagnosticTestMriDisplayBO)
                .addAttribute("invasiveTestEcogDisplayBO", invasiveTestEcogDisplayBO)
                .addAttribute("invasiveTestEegDisplayBO", invasiveTestEegDisplayBO)
                .addAttribute("invasiveTestCorticalMappingDisplayBO", invasiveTestCorticalMappingDisplayBO)
                .addAttribute("operationDisplayBO", operationDisplayBO)
                .addAttribute("histologyDisplayBOList", histologyDisplayBOList)
                .addAttribute("complicationDisplayBOList", complicationDisplayBOList)
                .addAttribute("operationWithOutcomesDisplayBO", operationWithOutcomesDisplayBO);

        return "patient/overviewView";
    }

    @RequestMapping(value = "/patient/list", method = RequestMethod.GET)
    public String patientsListGET(
            @RequestParam(value = "maxResults", defaultValue = "20", required = false) int maxResults,
            Model model, HttpServletRequest request) {

        if (!authorizationChecker.checkAuthoritaion(request)) {
            return "deniedView";
        }

        model.addAttribute("maxResult", maxResults);
        return "patient/listView";
    }

    /**
     * Patients list search get. This method is used to provide results for AJAX
     * calls
     *
     * @return the string
     */
    @RequestMapping(value = "/patient/list-search", method = RequestMethod.GET, produces = "text/plain; charset=utf-8")
    @ResponseBody
    public String patientsListSearchGET(
            @RequestParam("maxResults") int maxResults,
            @RequestParam("pageNumber") int pageNumber,
            @RequestParam("search") String searchString, HttpServletRequest request) {

        if (!authorizationChecker.checkAuthoritaion(request)) {
            return "deniedView";
        }

        boolean onlyResearcher = authorizationChecker.onlyResearcher();

        int patientsCount = patientService.getCountOfUnhidden(onlyResearcher, searchString);

        List<PatientDisplayBO> patients = patientService.getBySearchStringWithPagination(maxResults, pageNumber, onlyResearcher, searchString);

        if (onlyResearcher) {
            anonymizeService.anonymizePatients(patients);
        }

        JSONEncoder e = new JSONEncoder();
        return e.encode(patients, patientsCount);
    }

    /**
     * Patient delete get.
     *
     * @return the string
     */
    @RequestMapping(value = "/patient/{patientId}/delete", method = RequestMethod.GET)
    public String patientDeleteGET(
            @PathVariable("patientId") int patientId, HttpServletRequest request) {

        if (!authorizationChecker.checkAuthoritaion(request)) {
            return "deniedView";
        }

        patientService.delete(patientId, PatientEntity.class);
        return "redirect:/patient/list";
    }

    @RequestMapping(value = "/patient/{patientId}/hide", method = RequestMethod.GET)
    public String patientHideGET(@PathVariable("patientId") Integer patientId, HttpServletRequest request) {

        if (!authorizationChecker.checkAuthoritaion(request)) {
            return "deniedView";
        }

        patientService.hide(patientId);
        return "redirect:/patient/list?maxResults=20";
    }

    @RequestMapping(value = "/patient/{patientId}/unhide", method = RequestMethod.GET)
    public String patientUnhideGET(@PathVariable("patientId") Integer patientId, HttpServletRequest request) {

        if (!authorizationChecker.checkAuthoritaion(request)) {
            return "deniedView";
        }

        patientService.unhide(patientId);
        return "redirect:/hidden";
    }

    /**
     * Patient export get.
     *
     * @param model     the model
     * @param patientId the patient id
     * @return the string
     */
    @Deprecated
    @RequestMapping(value = "/patient/{patientId}/export", method = RequestMethod.GET)
    public String patientExportGET(
            @PathVariable("patientId") int patientId,
            Model model, HttpServletRequest request) {

        if (!authorizationChecker.checkAuthoritaion(request)) {
            return "deniedView";
        }
        Authentication auth = SecurityContextHolder.getContext()
                .getAuthentication();
        UserEntity user = userService.getUserByUsername(auth.getName());

        List<ExportParamsEntity> listOfSavedConfigurations = new ArrayList<>();
        List<ExportParamsEntity> listOfSavedConfigurationsTmp = genericDAOExportParams.findAll(ExportParamsEntity.class);

        for (ExportParamsEntity exportEntityParams : listOfSavedConfigurationsTmp) {
            if (exportEntityParams.isGeneric())
                listOfSavedConfigurations.add(exportEntityParams);
        }

        List<ExportParamsEntity> listOfUsersSavedConfigurations = new ArrayList<>();
        listOfUsersSavedConfigurations = exportParamsService
                .findExportParamsEntityByUserID(user.getId());

        if (listOfSavedConfigurations.size() > 0)
            listOfSavedConfigurations.remove(0);
        if ((listOfUsersSavedConfigurations.size() > 0) && (user.getId() == 1))
            listOfUsersSavedConfigurations.remove(0);


        model.addAttribute("listOfSavedConfigurations",
                listOfSavedConfigurations);
        model.addAttribute("listOfUsersSavedConfigurations",
                listOfUsersSavedConfigurations);
        model.addAttribute("user", user);
        model.addAttribute("exportParams",
                exportParamsService.getById(1, ExportParamsFormBO.class, ExportParamsEntity.class));


        List<PatientEntity> listOfPatients = new ArrayList<PatientEntity>();
        listOfPatients.add(genericDAOPatient.getById(patientId, PatientEntity.class));


        model.addAttribute("patientList", listOfPatients);
        model.addAttribute("patient", listOfPatients.get(0));
        return "patient/exportViewTBD";
    }

    @Deprecated
    @RequestMapping(value = "/patient/export", method = RequestMethod.POST)
    public String patientExportPOST(
            @ModelAttribute("exportParams") ExportParamsEntity exportParams, BindingResult result,
            @RequestParam("patient") Integer[] patientID,
            //@RequestParam("toTable") boolean toTable,
            @RequestParam("exportType") String exportType, Locale locale, boolean anonymize,
            Model model, HttpServletRequest request) {

        if (!authorizationChecker.checkAuthoritaion(request)) {
            return "deniedView";
        }
        logger.setLogger(PatientController.class);

        Authentication auth = SecurityContextHolder.getContext()
                .getAuthentication();

 /*
         * TODO
         * FOR TESTING PURPOSES ONLY ! DELETE AFTER TESTING
         */
        boolean toTable = false;
/*
        exportParams.setPatient(true);
        exportParams.setPatientBirthday(true);
        exportParams.setContactFirstName(true);
        exportParams.setContactLastName(true);
        exportParams.setPatientNin(true);
        exportParams.setPatientGender(true);
        exportParams.setContactCountry(true);
        exportParams.setContactAddressStreet(true);
        exportParams.setContactAddressHn(true);
        exportParams.setContactAddressCity(true);
        exportParams.setContactPhoneNumber(true);
        exportParams.setContactEmail(true);
        exportParams.setPatientAgeAtTheBeginningOfEpilepsy(true);
        exportParams.setPatientDoctorId(true);


        exportParams.setSeizure(true);
        exportParams.setSeizureAdded(true);
        exportParams.setSeizureAddUserId(true);
        exportParams.setSeizureComment(true);
        exportParams.setSeizureDate(true);
        exportParams.setSeizureDetailComment(true);
        exportParams.setSeizureDoctorId(true);
        exportParams.setSeizureFrequency(true);
        exportParams.setSeizureDetailComment(true);
        exportParams.setSeizureDetailILAEClassification(true);
        exportParams.setSeizureDetailSSCClassification(true);
        exportParams.setSeizureSecondarilyGeneralizedSeizure(true);
        exportParams.setSeizureSeizuresWhileAwakeEpi(true);
        exportParams.setSeizureSeizuresWhileAwakeLatent(true);
        exportParams.setSeizureSeizuresWhileAwakeNonEpi(true);
        exportParams.setSeizureSeizuresWhileSleepEpi(true);
        exportParams.setSeizureSeizuresWhileSleepLatent(true);
        exportParams.setSeizureSeizuresWhileSleepNonEpi(true);

        exportParams.setPharmacotherapy(true);
        exportParams.setPharmacotherapyDuringSurgery(true);
        exportParams.setPharmacotherapyEffective(true);
        exportParams.setPharmacotherapyComment(true);
        exportParams.setPharmacotherapyAED(true);

        exportParams.setNeurologicalFinding(true);
        exportParams.setNeurologicalFindingAbnormalNeurologicalFinding(true);
        exportParams.setNeurologicalFindingAdded(true);
        exportParams.setNeurologicalFindingAddUserId(true);
        exportParams.setNeurologicalFindingComment(true);
        exportParams.setNeurologicalFindingDate(true);
        exportParams.setNeurologicalFindingDoctorId(true);
        exportParams.setNeurologicalFindingHemiparesis(true);
        exportParams.setNeurologicalFindingHemisphereDominance(true);
        exportParams.setNeurologicalFindingId(true);
        exportParams.setNeurologicalFindingVisualFieldDefects(true);

        exportParams.setNeuropsychology(true);
        exportParams.setNeuropsychologyAdded(true);
        exportParams.setNeuropsychologyAddUserId(true);
        exportParams.setNeuropsychologyComment(true);
        exportParams.setNeuropsychologyDate(true);
        exportParams.setNeuropsychologyDoctorId(true);
        exportParams.setNeuropsychologyEmotionalStatus(true);
        exportParams.setNeuropsychologyFindingDetail(true);
        exportParams.setNeuropsychologyId(true);
        exportParams.setNeuropsychologyIntellect(true);
        exportParams.setNeuropsychologyIntellectualPerformance(true);
        exportParams.setNeuropsychologyIntellectualPerformanceNonverbalDesignCap(true);
        exportParams.setNeuropsychologyIntellectualPerformanceNonverbalAbstraction(true);
        exportParams.setNeuropsychologyIntellectualPerformanceVerbally(true);
        exportParams.setNeuropsychologyEmotionalStatus(true);
        exportParams.setNeuropsychologyNeurodevelopmentalExamination(true);
        exportParams.setNeuropsychologyNeurodevelopmentalExaminationAdaptability(true);
        exportParams.setNeuropsychologyNeurodevelopmentalExaminationFineMotorSkills(true);
        exportParams.setNeuropsychologyNeurodevelopmentalExaminationGrossMotorSkills(true);
        exportParams.setNeuropsychologyNeuropsychologicalProfileAttention(true);
        exportParams.setNeuropsychologyNeurodevelopmentalExaminationSocialBehavior(true);
        exportParams.setNeuropsychologyNeurodevelopmentalExaminationSpeechExpressively(true);
        exportParams.setNeuropsychologyNeuropsychologicalProfileCognitiveSpeed(true);
        exportParams.setNeuropsychologyNeurodevelopmentalExaminationSpeechReceptively(true);
        exportParams.setNeuropsychologyNeurodevelopmentalExaminationAdaptability(true);
        exportParams.setNeuropsychologyNeuropsychologicalProfileMotorSkillsDexterity(true);
        exportParams.setNeuropsychologyPresenceOfChangesDetail(true);
        exportParams.setNeuropsychologyPatientId(true);
        exportParams.setNeuropsychologyNeuropsychologicalProfile(true);
        exportParams.setNeuropsychologyNeuropsychologicalProfileSpeechUnderstanding(true);
        exportParams.setNeuropsychologyNeuropsychologicalProfilePerceptionVisual(true);
        exportParams.setNeuropsychologyNeuropsychologicalProfileMotorCoordination(true);
        exportParams.setNeuropsychologyNeuropsychologicalProfileMemoryOperating(true);
        exportParams.setNeuropsychologyNeuropsychologicalProfileMemoryVerbal(true);
        exportParams.setNeuropsychologyNeuropsychologicalProfileMemoryNonverbal(true);
        exportParams.setNeuropsychologyNeuropsychologicalProfileMemoryLearning(true);

        exportParams.setNeuropsychologyOld(true);
        exportParams.setNeuropsychologyOldAdded(true);
        exportParams.setNeuropsychologyOldAddUserId(true);
        exportParams.setNeuropsychologyOldAdhdSyndrome(true);
        exportParams.setNeuropsychologyOldComment(true);
        exportParams.setNeuropsychologyOldDate(true);
        exportParams.setNeuropsychologyOldDevelopmentalLanguageDisorders(true);
        exportParams.setNeuropsychologyOldDoctorId(true);
        exportParams.setNeuropsychologyOldIntelligenceLevel(true);
        exportParams.setNeuropsychologyOldNeuropsychologicalExamination(true);
        exportParams.setNeuropsychologyOldSpecificLearning(true);
        exportParams.setNeuropsychologyOldIntelligenceLevel(true);

        exportParams.setDiagnosticTestEEG(true);
        exportParams.setDiagnosticTestEEGBasicActivity(true);
        exportParams.setDiagnosticTestEEGComment(true);
        exportParams.setDiagnosticTestEEGDate(true);
        exportParams.setDiagnosticTestEEGDoctorId(true);
        exportParams.setDiagnosticTestEEGDone(true);
        exportParams.setDiagnosticTestEEGIctalEEGPatterns(true);
        exportParams.setDiagnosticTestEEGInterictalEEGSpikes(true);
        exportParams.setDiagnosticTestEEGDescriptionVideoEEG(true);
        exportParams.setDiagnosticTestEEGLocalizationIctalEEGPattern(true);
        exportParams.setDiagnosticTestEEGLocalizationInerictalEEGSpikes(true);
        exportParams.setDiagnosticTestEEGBasicActivity(true);
        exportParams.setDiagnosticTestEEGSecondarySidedSynchrony(true);
        exportParams.setDiagnosticTestEEGSlow(true);
        exportParams.setDiagnosticTestEEGStatusEpilepticus(true);

        exportParams.setDiagnosticTestMRI(true);
        exportParams.setDiagnosticTestMRIDate(true);
        exportParams.setDiagnosticTestMRIDescribe(true);
        exportParams.setDiagnosticTestMRIComment(true);
        exportParams.setDiagnosticTestMRIDescription(true);
        exportParams.setDiagnosticTestMRIDescriptionMrsAbnormality(true);
        exportParams.setDiagnosticTestMRIDescriptionPetHypometabolism(true);
        exportParams.setDiagnosticTestMRIDescriptionSpectHyperperfuse(true);
        exportParams.setDiagnosticTestMRIDescriptionSpectHypoperfuse(true);
        exportParams.setdiagnosticTestMRILocalizationPetHypometabolism(true);
        exportParams.setdiagnosticTestMRIInterictalSpect(true);
        exportParams.setDiagnosticTestMRIWada(true);
        exportParams.setDiagnosticTestMRISiscom(true);
        exportParams.setDiagnosticTestMRIProtocol(true);
        exportParams.setDiagnosticTestMRIPatientId(true);
        exportParams.setDiagnosticTestMRIMrsProtocol(true);
        exportParams.setDiagnosticTestMRIMrsFinding(true);
        exportParams.setdiagnosticTestMRILocalizationPetHypometabolism(true);
        exportParams.setdiagnosticTestMRIInterictalSpect(true);
        exportParams.setDiagnosticTestMRIWada(true);
        exportParams.setDiagnosticTestMRISiscom(true);
        exportParams.setDiagnosticTestMRIProtocol(true);
        exportParams.setDiagnosticTestMRILocalizationSpecHypoperfuse(true);
        exportParams.setdiagnosticTestMRILocalizationPetHypometabolism(true);
        exportParams.setDiagnosticTestMRIMrsProtocol(true);
        exportParams.setDiagnosticTestMRIPatientId(true);
        exportParams.setdiagnosticTestMRIInterictalSpect(true);























*/


        // Find out, if data should be anonymized or not
        boolean shallAnonymize = true;

        UserEntity user = userService.getUserByUsername(auth.getName());
        List<RoleEntity> listOfAssignedRoles = user.getRoles();

        boolean approved = false;
        for (RoleEntity assignedRole : listOfAssignedRoles) {
            if (assignedRole.getAuthority().equals("ROLE_SUPER_DOCTOR") || assignedRole.getAuthority().equals("ROLE_DOCTOR")) {
                approved = true;
                break;
            }
        }

        if (!anonymize & approved) {
            shallAnonymize = false;
        }

        List<PatientEntity> patientList = new ArrayList<PatientEntity>();
        for (Integer patient : patientID) {
            patientList.add(patientService.getPatientByIdWithAllLists(patient));
        }
        if (exportType.equals("pdf")) {
            try {
                String url = exportToPdfService.export(patientList,
                        userService.getUserByUsername(auth.getName()), locale,
                        exportParams, shallAnonymize, toTable);
                return "redirect:/resources/downloads/" + url;
            } catch (FileNotFoundException e) {
                logger.logError(
                        "File wasn't found when trying to export to pdf.", e);
            }
        } else if (exportType.equals("xlsx")) {
            String url = exportToXlsxService.export(patientList,
                    userService.getUserByUsername(auth.getName()), locale,
                    exportParams, shallAnonymize);
            return "redirect:/resources/downloads/" + url;
        } else if (exportType.equals("docx")) {
            String url = exportToDocxService.export(patientList,
                    userService.getUserByUsername(auth.getName()), locale,
                    exportParams, shallAnonymize, toTable);
            return "redirect:/resources/downloads/" + url;
        } else if (exportType.equals("txt")) {
            String url = exportToTxtService.export(patientList,
                    userService.getUserByUsername(auth.getName()), locale,
                    exportParams, shallAnonymize);
            return "redirect:/resources/downloads/" + url;
        } else if (exportType.equals("csv")) {
            String url = exportToCsvService.export(patientList,
                    userService.getUserByUsername(auth.getName()), locale,
                    exportParams, shallAnonymize);
            return "redirect:/resources/downloads/" + url;
        }

        List<PatientEntity> listOfPatients = new ArrayList<PatientEntity>();
        listOfPatients.add(genericDAOPatient.getById(patientID[0], PatientEntity.class));
        model.addAttribute("patientList", listOfPatients);
        model.addAttribute("listOfPossibleExportParams",
                genericDAOExportParams.findAll(ExportParamsEntity.class));
        model.addAttribute("exportType", exportType);
        model.addAttribute("patient", listOfPatients.get(0));
        return "patient/exportViewTBD";
    }

    @Deprecated
    @RequestMapping(value = "/patient/export/save", method = RequestMethod.POST)
    public String patientExportSavePOST(
            @ModelAttribute("exportParams") ExportParamsEntity exportParams,
            @RequestParam("patient") Integer[] patient,
            @RequestParam("isGeneric") boolean isGeneric,
            @RequestParam("exportName") String exportName, Locale locale,
            Model model, HttpServletRequest request) {
        if (!authorizationChecker.checkAuthoritaion(request)) {
            return "deniedView";
        }
        exportParams.setName(exportName);
        Authentication auth = SecurityContextHolder.getContext()
                .getAuthentication();
        exportParams.setUserID(userService.getUserByUsername(auth.getName())
                .getId());
        exportParams.setGeneric(isGeneric);
        // exportParamsService.save(exportParams);

        return "redirect:/patient/" + patient[0] + "/export";
    }

    @Deprecated
    @RequestMapping(value = "/patient/export/load", method = RequestMethod.POST)
    public String patientExportLoadPOST(Model model, Locale locale,
                                        @RequestParam("patient") Integer[] patientID,
                                        @RequestParam("exportId") Integer exportID, HttpServletRequest request) {
        if (!authorizationChecker.checkAuthoritaion(request)) {
            return "deniedView";
        }
        if (logger.getLogger() == null)
            logger.setLogger(PatientController.class);

        ExportParamsEntity exportParams = genericDAOExportParams.getById(exportID, ExportParamsEntity.class);

        Authentication auth = SecurityContextHolder.getContext()
                .getAuthentication();
        UserEntity user = userService.getUserByUsername(auth.getName());

        model.addAttribute("exportParams", exportParams);

        List<ExportParamsEntity> listOfSavedConfigurations = new ArrayList<ExportParamsEntity>();
        List<ExportParamsEntity> listOfSavedConfigurationsTmp = new ArrayList<ExportParamsEntity>();
        listOfSavedConfigurationsTmp = genericDAOExportParams
                .findAll(ExportParamsEntity.class);

        for (ExportParamsEntity exportEntityParams : listOfSavedConfigurationsTmp) {
            if (exportEntityParams.isGeneric())
                listOfSavedConfigurations.add(exportEntityParams);
        }

        List<ExportParamsEntity> listOfUsersSavedConfigurations = new ArrayList<ExportParamsEntity>();
        listOfUsersSavedConfigurations = exportParamsService
                .findExportParamsEntityByUserID(user.getId());

        if (listOfSavedConfigurations.size() > 0)
            listOfSavedConfigurations.remove(0);
        if ((listOfUsersSavedConfigurations.size() > 0) && (user.getId() == 1))
            listOfUsersSavedConfigurations.remove(0);

        model.addAttribute("listOfSavedConfigurations",
                listOfSavedConfigurations);
        model.addAttribute("listOfUsersSavedConfigurations",
                listOfUsersSavedConfigurations);
        model.addAttribute("user", user);
        model.addAttribute("patient",
                genericDAOPatient.getById(patientID[0], PatientEntity.class));
        List<PatientEntity> patientList = new ArrayList<PatientEntity>();
        for (Integer patient : patientID) {
            patientList.add(patientService.getPatientByIdWithAllLists(patient));
        }

        model.addAttribute("patientList", patientList);
        return "patient/exportViewTBD";
    }

    @Deprecated
    @RequestMapping(value = "/patient/export/delete", method = RequestMethod.POST)
    public String patientExportDeletePOST(
            @RequestParam("patient") Integer[] patientId,
            @RequestParam("exportId") Integer exportId, HttpServletRequest request) {
        if (!authorizationChecker.checkAuthoritaion(request)) {
            return "deniedView";
        }
     /*  exportParamsService.delete(exportParamsService.findByID(
                ExportParamsEntity.class, exportId));*/
        return "redirect:/patient/" + patientId[0] + "/export";
    }
}
