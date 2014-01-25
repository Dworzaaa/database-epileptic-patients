package cz.cvut.fit.genepi.businessLayer.VO.form;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * Created by Jan on 25.1.14.
 */
public class AnamnesisVO {

    private int id;

    private int patientId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past
    @NotNull
    private Date date;

    private boolean epilepsyInFamily;

    private boolean prenatalRisk;

    private boolean fibrilConvulsions;

    private boolean inflammationCns;

    private boolean injuryCns;

    private boolean operationCns;

    private boolean earlyPmdRetardation;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past
    @NotNull
    private Date beginningEpilepsy;

    private boolean firstFever;

    private boolean infantileSpasm;

    private int specificSyndrome;

    @Size(max = 800)
    private String nonCnsComorbidity;

    @Size(max = 800)
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

    public boolean isEpilepsyInFamily() {
        return epilepsyInFamily;
    }

    public void setEpilepsyInFamily(boolean epilepsyInFamily) {
        this.epilepsyInFamily = epilepsyInFamily;
    }

    public boolean isPrenatalRisk() {
        return prenatalRisk;
    }

    public void setPrenatalRisk(boolean prenatalRisk) {
        this.prenatalRisk = prenatalRisk;
    }

    public boolean isFibrilConvulsions() {
        return fibrilConvulsions;
    }

    public void setFibrilConvulsions(boolean fibrilConvulsions) {
        this.fibrilConvulsions = fibrilConvulsions;
    }

    public boolean isInflammationCns() {
        return inflammationCns;
    }

    public void setInflammationCns(boolean inflammationCns) {
        this.inflammationCns = inflammationCns;
    }

    public boolean isInjuryCns() {
        return injuryCns;
    }

    public void setInjuryCns(boolean injuryCns) {
        this.injuryCns = injuryCns;
    }

    public boolean isOperationCns() {
        return operationCns;
    }

    public void setOperationCns(boolean operationCns) {
        this.operationCns = operationCns;
    }

    public boolean isEarlyPmdRetardation() {
        return earlyPmdRetardation;
    }

    public void setEarlyPmdRetardation(boolean earlyPmdRetardation) {
        this.earlyPmdRetardation = earlyPmdRetardation;
    }

    public Date getBeginningEpilepsy() {
        return beginningEpilepsy;
    }

    public void setBeginningEpilepsy(Date beginningEpilepsy) {
        this.beginningEpilepsy = beginningEpilepsy;
    }

    public boolean isFirstFever() {
        return firstFever;
    }

    public void setFirstFever(boolean firstFever) {
        this.firstFever = firstFever;
    }

    public boolean isInfantileSpasm() {
        return infantileSpasm;
    }

    public void setInfantileSpasm(boolean infantileSpasm) {
        this.infantileSpasm = infantileSpasm;
    }

    public int getSpecificSyndrome() {
        return specificSyndrome;
    }

    public void setSpecificSyndrome(int specificSyndrome) {
        this.specificSyndrome = specificSyndrome;
    }

    public String getNonCnsComorbidity() {
        return nonCnsComorbidity;
    }

    public void setNonCnsComorbidity(String nonCnsComorbidity) {
        this.nonCnsComorbidity = nonCnsComorbidity;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
