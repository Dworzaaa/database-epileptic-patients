package cz.cvut.fit.genepi.dataLayer.DAOImpl;

import cz.cvut.fit.genepi.dataLayer.DAO.ExportParamsDAO;
import cz.cvut.fit.genepi.dataLayer.entity.ExportParamsEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Implementation of ExportParamsDAO
 * Extending implementation of GenericDAO
 */
@Repository
public class ExportParamsDAOImpl
        extends GenericDAOImpl<ExportParamsEntity>
        implements ExportParamsDAO {

    @SuppressWarnings("unchecked")
    @Override
    public List<ExportParamsEntity> findExportParamsByUserID(int userID) {
        List<ExportParamsEntity> exportParamsEntities;
        Query query = sessionFactory.getCurrentSession().createQuery(
                "from ExportParamsEntity where userID = :user_id");
        query.setParameter("user_id", userID);
        exportParamsEntities = (List<ExportParamsEntity>) query.list();

        return exportParamsEntities;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<ExportParamsEntity> getGenericConfigurations() {
        Criteria criteria = sessionFactory.getCurrentSession()
                .createCriteria(ExportParamsEntity.class)
                .add(Restrictions.eq("generic", true));

        return (List<ExportParamsEntity>) criteria.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<ExportParamsEntity> getConfigurationsByUsername(int userId) {
        Criteria criteria = sessionFactory.getCurrentSession()
                .createCriteria(ExportParamsEntity.class)
                .add(Restrictions.eq("generic", false))
                .add(Restrictions.eq("userID", userId));

        return (List<ExportParamsEntity>) criteria.list();
    }
}
