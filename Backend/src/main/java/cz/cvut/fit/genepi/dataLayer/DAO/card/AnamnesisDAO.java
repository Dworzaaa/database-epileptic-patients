package cz.cvut.fit.genepi.dataLayer.DAO.card;

import cz.cvut.fit.genepi.dataLayer.DAO.GenericDAO;
import cz.cvut.fit.genepi.dataLayer.entity.card.AnamnesisEntity;

import java.util.List;

/**
 * AnamnesisDAO interface
 */
public interface AnamnesisDAO extends GenericDAO<AnamnesisEntity> {


    public List<AnamnesisEntity> findAllHidden();
}