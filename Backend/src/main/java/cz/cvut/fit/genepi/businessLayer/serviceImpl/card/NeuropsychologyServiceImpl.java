package cz.cvut.fit.genepi.businessLayer.serviceImpl.card;

import cz.cvut.fit.genepi.businessLayer.VO.form.NeuropsychologyVO;
import cz.cvut.fit.genepi.businessLayer.service.card.NeuropsychologyService;
import cz.cvut.fit.genepi.dataLayer.entity.card.NeuropsychologyEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NeuropsychologyServiceImpl
        extends GenericCardServiceImpl<NeuropsychologyVO, NeuropsychologyEntity>
        implements NeuropsychologyService {

    @Override
    @Transactional
    public void hide(int neuropsychologyId) {
        NeuropsychologyEntity entity = genericDAO.findByID(NeuropsychologyEntity.class, neuropsychologyId);
        entity.setStatus(1);
        genericDAO.save(entity);
    }

    @Transactional
    public void unhide(int neuropsychologyId) {
        NeuropsychologyEntity entity = genericDAO.findByID(NeuropsychologyEntity.class, neuropsychologyId);
        entity.setStatus(0);
        genericDAO.save(entity);
    }
}
