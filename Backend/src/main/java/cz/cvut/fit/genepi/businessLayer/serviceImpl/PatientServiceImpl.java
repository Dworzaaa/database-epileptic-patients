package cz.cvut.fit.genepi.businessLayer.serviceImpl;

import cz.cvut.fit.genepi.businessLayer.VO.display.PatientDisplayVO;
import cz.cvut.fit.genepi.businessLayer.service.PatientService;
import cz.cvut.fit.genepi.dataLayer.DAO.PatientDAO;
import cz.cvut.fit.genepi.dataLayer.entity.PatientEntity;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

// TODO: Auto-generated Javadoc

/**
 * The Class PatientServiceImpl.
 */
@Service
public class PatientServiceImpl extends GenericServiceImpl<PatientEntity>
        implements PatientService {

    /**
     * The patient dao.
     */
    @Autowired
    private PatientDAO patientDAO;

    @Autowired
    private Mapper dozer;

    @Override
    @Transactional
    public PatientEntity getPatientByIdWithAllLists(int patientId) {
        return patientDAO.getPatientByIdWithAllLists(patientId);
    }

    @Override
    @Transactional
    public PatientEntity getPatientByIdWithAnamnesisList(int patientId) {
        return patientDAO.getPatientByIdWithAnamnesisList(patientId);
    }

    @Override
    @Transactional
    public PatientEntity getPatientByIdWithComplicationList(int patientId) {
        return patientDAO.getPatientByIdWithComplicationList(patientId);

    }

    @Override
    @Transactional
    public PatientEntity getPatientByIdWithDiagnosticTestScalpEEGList(int patientId) {
        return patientDAO.getPatientByIdWithDiagnosticTestScalpEEGList(patientId);

    }

    @Override
    @Transactional
    public PatientEntity getPatientByIdWithDiagnosticTestMRIList(int patientId) {
        return patientDAO.getPatientByIdWithDiagnosticTestMRIList(patientId);

    }

    @Override
    @Transactional
    public PatientEntity getPatientByIdWithHistologyList(int patientId) {
        return patientDAO.getPatientByIdWithHistologyList(patientId);

    }

    @Override
    @Transactional
    public PatientEntity getPatientByIdWithInvasiveTestCorticalMappingList(
            int patientId) {
        return patientDAO
                .getPatientByIdWithInvasiveTestCorticalMappingList(patientId);

    }

    @Override
    @Transactional
    public PatientEntity getPatientByIdWithInvasiveTestECOGList(int patientId) {
        return patientDAO.getPatientByIdWithInvasiveTestECOGList(patientId);

    }

    @Override
    @Transactional
    public PatientEntity getPatientByIdWithInvasiveTestEEGList(int patientId) {
        return patientDAO.getPatientByIdWithInvasiveTestEEGList(patientId);

    }

    @Override
    @Transactional
    public PatientEntity getPatientByIdWithNeurologicalFindingList(int patientId) {
        return patientDAO.getPatientByIdWithNeurologicalFindingList(patientId);
    }

    @Override
    @Transactional
    public PatientEntity getPatientByIdWithNeuropsychologyList(int patientId) {
        return patientDAO.getPatientByIdWithNeuropsychologyList(patientId);

    }

    @Override
    @Transactional
    public PatientEntity getPatientByIdWithNeuropsychologyOldList(int patientId) {
        return patientDAO.getPatientByIdWithNeuropsychologyOldList(patientId);

    }

    @Override
    @Transactional
    public PatientEntity getPatientByIdWithOperationList(int patientId) {
        return patientDAO.getPatientByIdWithOperationList(patientId);

    }

    @Override
    @Transactional
    public PatientEntity getPatientByIdWithOutcomeList(int patientId) {
        return patientDAO.getPatientByIdWithOutcomeList(patientId);

    }

    @Override
    @Transactional
    public PatientEntity getPatientByIdWithPharmacotherapyList(int patientId) {
        return patientDAO.getPatientByIdWithPharmacotherapyList(patientId);

    }

    @Override
    @Transactional
    public PatientEntity getPatientByIdWithSeizureList(int patientId) {
        return patientDAO.getPatientByIdWithSeizureList(patientId);

    }

    @Override
    @Transactional
    public PatientEntity getPatientByIdWithDoctor(int patientId) {
        return patientDAO.getPatientByIdWithDoctor(patientId);

    }

    @Override
    @Transactional
    public List<PatientEntity> findAllHidden() {
        return patientDAO.findAllHidden();
    }

    @Override
    @Transactional
    public PatientDisplayVO getPatientDisplayByIdWithNeurologicalFindingList(int patientId) {
        PatientEntity patient = patientDAO.getPatientByIdWithNeurologicalFindingList(patientId);
        PatientDisplayVO patientVO = dozer.map(patient, PatientDisplayVO.class);
        return patientVO;
    }

    @Override
    @Transactional
    public PatientDisplayVO getPatientDisplayByIdWithSeizureList(int patientId) {
        PatientEntity patient = patientDAO.getPatientByIdWithSeizureList(patientId);
        PatientDisplayVO patientVO = dozer.map(patient, PatientDisplayVO.class);
        return patientVO;
    }

    @Override
    @Transactional
    public PatientDisplayVO getPatientDisplayByIdWithPharmacotherapyList(int patientId) {
        PatientEntity patient = patientDAO.getPatientByIdWithSeizureList(patientId);
        PatientDisplayVO patientVO = dozer.map(patient, PatientDisplayVO.class);
        return patientVO;
    }

    @Override
    @Transactional
    public PatientDisplayVO getPatientDisplayByIdWithDoctor(int patientId) {
        PatientEntity patient = patientDAO.getPatientByIdWithDoctor(patientId);
        PatientDisplayVO patientVO = dozer.map(patient, PatientDisplayVO.class);
        return patientVO;
    }
}
