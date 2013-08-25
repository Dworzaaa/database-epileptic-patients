package cz.cvut.fit.genepi.DAOImpl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import cz.cvut.fit.genepi.DAO.AnamnesisDAO;
import cz.cvut.fit.genepi.entity.AnamnesisEntity;

// TODO: Auto-generated Javadoc
/**
 * The Class AnamnesisDAOImpl.
 */
@Repository
public class AnamnesisDAOImpl extends
		GenericDAOImpl<AnamnesisEntity, Serializable> implements AnamnesisDAO {

	/* (non-Javadoc)
	 * @see cz.cvut.fit.genepi.DAO.AnamnesisDAO#findAnamnesisByPatientID(int)
	 */
	@Override
	public List<AnamnesisEntity> findAnamnesisByPatientID(int patientId) {
		List<AnamnesisEntity> anamnesisEntities = new ArrayList<AnamnesisEntity>();
		Query query = sessionFactory.getCurrentSession()
				.createQuery("from AnamnesisEntity where patientId = :patient_id");
		query.setParameter("patient_id", patientId);		
		anamnesisEntities = findMany(query);
		
		return anamnesisEntities;
	}

	/* (non-Javadoc)
	 * @see cz.cvut.fit.genepi.DAO.AnamnesisDAO#findLatestAnamnesisByPatientID(int)
	 */
	@Override
	public AnamnesisEntity findLatestAnamnesisByPatientID(int patientID) {
		AnamnesisEntity anamnesisEntity;
		Query query = sessionFactory.getCurrentSession()
				.createQuery("from AnamnesisEntity where id=(select max(id) from AnamnesisEntity where patientId = :patient_id)"); 
		query.setParameter("patient_id", patientID);		
		anamnesisEntity = findOne(query);
		return anamnesisEntity;
	}
}