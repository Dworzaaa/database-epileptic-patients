package cz.cvut.fit.genepi.businessLayer.service.card;

import cz.cvut.fit.genepi.businessLayer.VO.form.card.InvasiveTestCorticalMappingVO;
import cz.cvut.fit.genepi.dataLayer.entity.card.InvasiveTestCorticalMappingEntity;

public interface InvasiveTestCorticalMappingService extends GenericCardService<InvasiveTestCorticalMappingVO, InvasiveTestCorticalMappingEntity> {

    public void hide(int invasiveTestCorticalMappingId);

    public void unhide(int invasiveTestCorticalMappingId);
}
