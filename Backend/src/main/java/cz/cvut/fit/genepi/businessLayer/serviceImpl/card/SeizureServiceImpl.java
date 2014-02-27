package cz.cvut.fit.genepi.businessLayer.serviceImpl.card;

import cz.cvut.fit.genepi.businessLayer.VO.form.card.SeizureVO;
import cz.cvut.fit.genepi.businessLayer.service.card.SeizureService;
import cz.cvut.fit.genepi.dataLayer.entity.card.SeizureEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SeizureServiceImpl
        extends GenericCardServiceImpl<SeizureVO, SeizureEntity>
        implements SeizureService {

    @Override
    @Transactional
    public void hide(int seizureId) {
        SeizureEntity entity = genericDAO.findByID(SeizureEntity.class, seizureId);
        entity.setHidden(true);
        genericDAO.save(entity);
    }

    @Override
    @Transactional
    public void unhide(int seizureId) {
        SeizureEntity entity = genericDAO.findByID(SeizureEntity.class, seizureId);
        entity.setHidden(false);
        genericDAO.save(entity);
    }
}
