package cz.cvut.fit.genepi.businessLayer.BO.form.card;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.util.Date;

public class HistologyFormBO {

    private int id;

    private int patientId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past
    @NotNull
    private Date date;

    @Min(1)
    private int histopathology;

    private int fcdClassification;

    private String comment;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getHistopathology() {
        return histopathology;
    }

    public void setHistopathology(int histopathology) {
        this.histopathology = histopathology;
    }

    public int getFcdClassification() {
        return fcdClassification;
    }

    public void setFcdClassification(int fcdClassification) {
        this.fcdClassification = fcdClassification;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
