package cz.cvut.fit.genepi.businessLayer.serviceImpl.card;

import cz.cvut.fit.genepi.businessLayer.BO.display.card.OutcomeDisplayBO;
import cz.cvut.fit.genepi.businessLayer.BO.form.card.OutcomeFormBO;
import cz.cvut.fit.genepi.businessLayer.service.card.OutcomeService;
import cz.cvut.fit.genepi.dataLayer.entity.card.OutcomeEntity;
import org.springframework.stereotype.Service;

@Service
public class OutcomeServiceImpl
        extends GenericCardServiceImpl<OutcomeDisplayBO, OutcomeFormBO, OutcomeEntity>
        implements OutcomeService {
}
