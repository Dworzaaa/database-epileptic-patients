package cz.cvut.fit.genepi.businessLayer.serviceImpl.card;

import cz.cvut.fit.genepi.businessLayer.VO.form.SeizureDetailVO;
import cz.cvut.fit.genepi.businessLayer.service.card.SeizureDetailService;
import cz.cvut.fit.genepi.dataLayer.entity.card.SeizureDetailEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SeizureDetailServiceImpl
        extends GenericCardServiceImpl<SeizureDetailVO, SeizureDetailEntity>
        implements SeizureDetailService {

    @Override
    @Transactional
    public void hide(int seizureDetailId) {
        SeizureDetailEntity entity = genericDAO.findByID(SeizureDetailEntity.class, seizureDetailId);
        entity.setStatus(1);
        genericDAO.save(entity);
    }

    @Override
    @Transactional
    public void unhide(int seizureDetailId) {
        SeizureDetailEntity entity = genericDAO.findByID(SeizureDetailEntity.class, seizureDetailId);
        entity.setStatus(0);
        genericDAO.save(entity);
    }
}
