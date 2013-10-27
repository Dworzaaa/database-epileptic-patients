package cz.cvut.fit.genepi.serviceImpl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import com.itextpdf.text.Paragraph;

import cz.cvut.fit.genepi.entity.PatientEntity;
import cz.cvut.fit.genepi.entity.UserEntity;
import cz.cvut.fit.genepi.entity.card.AnamnesisEntity;
import cz.cvut.fit.genepi.service.LoggingService;

public class ExportToTxtServiceImpl {

	@Autowired
	private static MessageSource messageSource;

	private static PatientEntity patient;

	//private static UserEntity user;
	/** The Constant logger. */
	private LoggingService logger = new LoggingService();

	private static String getDate() {
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		Date today = Calendar.getInstance().getTime();
		String reportDate = df.format(today);
		return reportDate;
	}

	public void export(PatientEntity patient, UserEntity user,
			java.util.List<String> exports,
			java.util.List<String> listOfPossibleCards) {
		logger.setLogger(ExportToTxtServiceImpl.class);

		ExportToTxtServiceImpl.patient = patient;
		//ExportToTxtServiceImpl.user = user;
		String downloadFolder = System.getProperty("user.home")
				+ System.getProperty("file.separator") + "Download_Links"
				+ System.getProperty("file.separator");
		File f = null;
		String os = System.getProperty("os.name").toLowerCase();
		if (os.indexOf("win") >= 0) {
			downloadFolder.replace("\\", "/");
		} else {
			downloadFolder = "/usr/local/tomcat/webapps/GENEPI/resources/downloads/";
			f = new File(downloadFolder + "patient" + patient.getId() + ".txt");
			if (!f.getParentFile().exists())
				f.getParentFile().mkdirs();
			if (f.exists())
				f.delete();
			try {
				f.createNewFile();
			} catch (IOException e) {
				logger.logError(
						"Couldn't create new file when trying to save txt file.",
						e);
			}
		}

		FileWriter fw = null;
		try {
			fw = new FileWriter(f.getAbsoluteFile());
		} catch (IOException e) {
			logger.logError("Exception when trying to get FileWriter for txt file.",
					e);
			e.printStackTrace();
		}
		BufferedWriter bw = new BufferedWriter(fw);

		String content = "";
		addTitlePage(f, bw, content);
		addContent(f, content, exports, listOfPossibleCards);
		
		try {
			bw.write(content);
		} catch (IOException e) {
			logger.logError("Exception when trying to write to txt file.",
					e);
			e.printStackTrace();
		}
		try {
			bw.close();
		} catch (IOException e) {
			logger.logError("Exception when trying to close txt file.",
					e);
			e.printStackTrace();
		}
	}

	private String addEmptyLine(String content) {
		String emptyLine = "";
		for (int i = 0; i != 30; i++) {
			emptyLine += " ";
		}
		return (content + "\n" + emptyLine + "\n");
	}

	private String addDashLine(String content) {
		String emptyLine = "";
		for (int i = 0; i != 30; i++) {
			emptyLine += "-";
		}
		return (content + "\n" + emptyLine + "\n");
	}

	private String addStarLine(String content) {
		String emptyLine = "";
		for (int i = 0; i != 30; i++) {
			emptyLine += "*";
		}
		return (content + "\n" + emptyLine + "\n");
	}

	private void addTitlePage(File f, BufferedWriter bw, String content) {
		content += (new Paragraph("Export of the patient "
				+ patient.getContact().getFirstName() + " "
				+ patient.getContact().getLastName() + " ,ID:"
				+ patient.getId() + " " + getDate()));

		content = addEmptyLine(content);
		content += ("Report generated by: " + System.getProperty("user.name")
				+ ", " + new Date() + "\n");
		content = addEmptyLine(content);
	}

	private void addContent(File f, String content,
			java.util.List<String> exports,
			java.util.List<String> listOfPossibleCards) {

		content = addDashLine(content);

		for (String s : exports) {
			if (s.equals(listOfPossibleCards.get(0))) {
				content += ("Anamnesis\n\n");
				for (AnamnesisEntity a : patient.getAnamnesisList()) {
					content += (a.getAdded());
					content = addStarLine(content);
				}

			}
			content = addDashLine(content);

			if (s.equals(listOfPossibleCards.get(1))) {
				content += ("Anamnesis\n\n");
				for (AnamnesisEntity a : patient.getAnamnesisList()) {
					content += (a.getAdded());
				}
			}
			if (s.equals(listOfPossibleCards.get(2))) {
				content += ("Anamnesis\n\n");
				for (AnamnesisEntity a : patient.getAnamnesisList()) {
					content += (a.getAdded());
				}
			}
			if (s.equals(listOfPossibleCards.get(3))) {
				content += ("Anamnesis\n\n");
				for (AnamnesisEntity a : patient.getAnamnesisList()) {
					content += (a.getAdded());
				}
			}
			if (s.equals(listOfPossibleCards.get(4))) {
				content += ("Anamnesis\n\n");
				for (AnamnesisEntity a : patient.getAnamnesisList()) {
					content += (a.getAdded());
				}
			}
			if (s.equals(listOfPossibleCards.get(5))) {
				content += ("Anamnesis\n\n");
				for (AnamnesisEntity a : patient.getAnamnesisList()) {
					content += (a.getAdded());
				}
			}
			if (s.equals(listOfPossibleCards.get(6))) {
				content += ("Anamnesis\n\n");
				for (AnamnesisEntity a : patient.getAnamnesisList()) {
					content += (a.getAdded());
				}
			}
			if (s.equals(listOfPossibleCards.get(7))) {
				content += ("Anamnesis\n\n");
				for (AnamnesisEntity a : patient.getAnamnesisList()) {
					content += (a.getAdded());
				}

			}
		}
	}
}