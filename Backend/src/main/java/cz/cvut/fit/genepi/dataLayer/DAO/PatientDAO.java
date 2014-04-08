package cz.cvut.fit.genepi.dataLayer.DAO;

import cz.cvut.fit.genepi.dataLayer.entity.AdvancedSearchEntity;
import cz.cvut.fit.genepi.dataLayer.entity.PatientEntity;

import java.util.List;

/**
 * PatientDAO interface
 */
public interface PatientDAO extends GenericDAO<PatientEntity> {

    /**
     * Finds Pateint and all his lists for chosen patient
     *
     * @param patientId the ID of the pateint
     * @return Pateint as the PateintEntity
     */
    public PatientEntity getPatientByIdWithAllLists(int patientId);

    /**
     * Finds Pateint and his anamnesis list for chosen patient
     *
     * @param patientId the ID of the pateint
     * @return Pateint as the PateintEntity
     */
    public PatientEntity getPatientByIdWithAnamnesisList(int patientId);

    /**
     * Finds Pateint and his complication list for chosen patient
     *
     * @param patientID the ID of the pateint
     * @return Pateint as the PateintEntity
     */
    public PatientEntity getPatientByIdWithComplicationList(int patientId);

    /**
     * Finds Pateint and his diagnosticTestScalpEEG list for chosen patient
     *
     * @param patientID the ID of the pateint
     * @return Pateint as the PateintEntity
     */
    public PatientEntity getPatientByIdWithDiagnosticTestScalpEegList(int patientId);

    /**
     * Finds Pateint and his diagnosticTestMRI list for chosen patient
     *
     * @param patientID the ID of the pateint
     * @return Pateint as the PateintEntity
     */
    public PatientEntity getPatientByIdWithDiagnosticTestMriList(int patientId);

    /**
     * Finds Pateint and his histology list for chosen patient
     *
     * @param patientID the ID of the pateint
     * @return Pateint as the PateintEntity
     */
    public PatientEntity getPatientByIdWithHistologyList(int patientId);

    /**
     * Finds Pateint and his InvasiveTestCorticalMapping list for chosen patient
     *
     * @param patientID the ID of the pateint
     * @return Pateint as the PateintEntity
     */
    public PatientEntity getPatientByIdWithInvasiveTestCorticalMappingList(
            int patientId);

    /**
     * Finds Pateint and his InvasiveTestECOG list for chosen patient
     *
     * @param patientID the ID of the pateint
     * @return Pateint as the PateintEntity
     */
    public PatientEntity getPatientByIdWithInvasiveTestEcogList(int patientId);

    /**
     * Finds Pateint and his invasiveTestEEG list for chosen patient
     *
     * @param patientID the ID of the pateint
     * @return Pateint as the PateintEntity
     */
    public PatientEntity getPatientByIdWithInvasiveTestEegList(int patientId);

    /**
     * Finds Pateint and his neurologicalFinding list for chosen patient
     *
     * @param patientID the ID of the pateint
     * @return Pateint as the PateintEntity
     */
    public PatientEntity getPatientByIdWithNeurologicalFindingList(int patientId);

    /**
     * Finds Pateint and his neuropsychology list for chosen patient
     *
     * @param patientID the ID of the pateint
     * @return Pateint as the PateintEntity
     */
    public PatientEntity getPatientByIdWithNeuropsychologyList(int patientId);

    /**
     * Finds Pateint and his neuropsychologyOld list for chosen patient
     *
     * @param patientID the ID of the pateint
     * @return Pateint as the PateintEntity
     */
    public PatientEntity getPatientByIdWithNeuropsychologyOldList(int patientId);

    /**
     * Finds Pateint and his operation list for chosen patient
     *
     * @param patientID the ID of the pateint
     * @return Pateint as the PateintEntity
     */
    public PatientEntity getPatientByIdWithOperationList(int patientId);

    public PatientEntity getPatientByIdWithOperationWithOutcomeList(int patientId);

    /**
     * Finds Pateint and his outcome list for chosen patient
     *
     * @param patientID the ID of the pateint
     * @return Pateint as the PateintEntity
     */
    public PatientEntity getPatientByIdWithOutcomeList(int patientId);

    /**
     * Finds Pateint and his pharmacotherapy list for chosen patient
     *
     * @param patientID the ID of the pateint
     * @return Pateint as the PateintEntity
     */
    public PatientEntity getPatientByIdWithPharmacotherapyList(int patientId);

    /**
     * Finds Pateint and his seizure list for chosen patient
     *
     * @param patientID the ID of the pateint
     * @return Pateint as the PateintEntity
     */
    public PatientEntity getPatientByIdWithSeizureList(int patientId);


    /**
     * Does the searching
     *
     * @param advancedSearch as AdvancedSearchEntity
     * @return List of PatientEntity
     */
    public List<PatientEntity> performSearch(AdvancedSearchEntity advancedSearch);

    /**
     * Finds Patient with his doctor
     *
     * @param patientId the ID of the pateint
     * @return Pateint as the PateintEntity
     */
    public PatientEntity getPatientByIdWithDoctor(int patientId);

    public List<PatientEntity> findAllHidden();

    public List<PatientEntity> findAllWithHiddenRecords();

    public int getCountOfUnhidden(String searchString);

    public List<PatientEntity> findByNameWithPagination(int maxResults, int pageNumber, List<String> parameters, String name);
}