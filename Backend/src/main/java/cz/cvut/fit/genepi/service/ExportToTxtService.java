package cz.cvut.fit.genepi.service;

import java.util.List;

import cz.cvut.fit.genepi.entity.PatientEntity;
import cz.cvut.fit.genepi.entity.UserEntity;

// TODO: Auto-generated Javadoc
/**
 * The Interface ContactService.
 */
public interface ExportToTxtService {
	
	public void export(PatientEntity patient, UserEntity user,
			List<String> exports,List<String> listOfPossibleCards );
}