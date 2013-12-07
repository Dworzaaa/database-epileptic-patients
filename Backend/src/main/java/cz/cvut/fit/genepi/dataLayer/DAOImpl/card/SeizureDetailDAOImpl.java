package cz.cvut.fit.genepi.dataLayer.DAOImpl.card;

import org.springframework.stereotype.Repository;

import cz.cvut.fit.genepi.dataLayer.DAO.card.SeizureDetailDAO;
import cz.cvut.fit.genepi.dataLayer.DAOImpl.GenericDAOImpl;
import cz.cvut.fit.genepi.dataLayer.entity.card.SeizureDetailEntity;

/**
 * Implementation of SeizureDetailDAO
 * Extending implementation of GenericDAO
 */

@Repository
public class SeizureDetailDAOImpl extends
		GenericDAOImpl<SeizureDetailEntity> implements
		SeizureDetailDAO {

}
