package cz.cvut.fit.genepi.dataLayer.DAOImpl;

import cz.cvut.fit.genepi.dataLayer.DAO.ExportParamsDAO;
import cz.cvut.fit.genepi.dataLayer.entity.ExportParamsEntity;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of ExportParamsDAO
 * Extending implementation of GenericDAO
 */
@Repository
public class ExportParamsDAOImpl extends GenericDAOImpl<ExportParamsEntity>
        implements ExportParamsDAO {
    @Override

	/*
     * (non-Javadoc)
	 * @see cz.cvut.fit.genepi.dataLayer.DAO.ExportParamsDAO#findExportParamsByUserID(int)
	 */
    @SuppressWarnings("unchecked")
    public List<ExportParamsEntity> findExportParamsByUserID(int userID) {
        List<ExportParamsEntity> exportParamsEntities = new ArrayList<ExportParamsEntity>();
        Query query = sessionFactory.getCurrentSession().createQuery(
                "from ExportParamsEntity where userID = :user_id");
        query.setParameter("user_id", userID);
        exportParamsEntities =  (List<ExportParamsEntity>) query.list();

        return exportParamsEntities;
    }
}
