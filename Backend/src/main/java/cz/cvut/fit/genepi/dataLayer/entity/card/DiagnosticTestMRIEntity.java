package cz.cvut.fit.genepi.dataLayer.entity.card;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import cz.cvut.fit.genepi.dataLayer.entity.PatientEntity;

@Entity
@Table(name = "diagnostic_test_mri")
public class DiagnosticTestMRIEntity implements
		Comparable<DiagnosticTestMRIEntity> {

	/* Autofilled fields */

	/** The id. */
	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue
	private int id;

	/** The add user id. */
	@NotNull
	@Column(name = "add_user_id", nullable = false)
	private int addUserId;

	/** The added. */
	@Column(name = "added", nullable = false, insertable = false)
	private Date added;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "patient_id")
	private PatientEntity patient;

	@Column(name = "status", nullable = false)
	private int status;

	/* Other fields */

	/** The date. */
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Past
	@NotNull
	@Column(name = "date", nullable = false)
	private Date date;

	@Column(name = "done")
	private boolean done;

	@Column(name = "mri_finding")
	private int mriFinding;

	@Size(max = 800)
	@Column(name = "mri_description", length = 800)
	private String mriDescription;

	@Column(name = "fdg_pet")
	private int fdgPet;

	@Size(max = 800)
	@Column(name = "description_pet_hypometabolism", length = 800)
	private String descriptionPetHypometabolism;

	@Column(name = "interictal_spect")
	private int interictalSpect;

	@Size(max = 800)
	@Column(name = "description_spect_hypoperfuse", length = 800)
	private String descriptionSpectHypoperfuse;

	@Column(name = "ictal_spect")
	private int ictalSpect;

	@Size(max = 800)
	@Column(name = "description_spect_hyperperfuse", length = 800)
	private String descriptionSpectHyperperfuse;

	@Column(name = "siscom")
	private boolean siscom;

	@Column(name = "mrs_protocol")
	private int mrsProtocol;

	@Column(name = "mrs_finding")
	private int mrsFinding;

	@Size(max = 800)
	@Column(name = "description_mrs_abnormality", length = 800)
	private String descriptionMrsAbnormality;

	@Column(name = "fmri")
	private boolean fmri;

	@Size(max = 800)
	@Column(name = "details_fmri", length = 800)
	private String detailsFmri;

	@Column(name = "dti")
	private boolean dti;

	@Size(max = 800)
	@Column(name = "details_dti_study", length = 800)
	private String detailsDtiStudy;

	@Column(name = "wada")
	private boolean wada;

	@Size(max = 800)
	@Column(name = "details_wada", length = 800)
	private String detailsWada;

	/** The comment. */
	@Size(max = 800)
	@Column(name = "comment", length = 800, nullable = true)
	private String comment;

	@Override
	public int compareTo(DiagnosticTestMRIEntity o) {
		int comparison = this.date.compareTo(o.getDate());
		if (comparison > 0) {
			return -1;
		} else if (comparison == 0) {
			return 0;
		} else {
			return 1;
		}
	}

	/* Getters and Setters */

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAddUserId() {
		return addUserId;
	}

	public void setAddUserId(int addUserId) {
		this.addUserId = addUserId;
	}

	public Date getAdded() {
		return added;
	}

	public void setAdded(Date added) {
		this.added = added;
	}

	public PatientEntity getPatient() {
		return patient;
	}

	public void setPatient(PatientEntity patient) {
		this.patient = patient;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

	public int getMriFinding() {
		return mriFinding;
	}

	public void setMriFinding(int mriFinding) {
		this.mriFinding = mriFinding;
	}

	public String getMriDescription() {
		return mriDescription;
	}

	public void setMriDescription(String mriDescription) {
		this.mriDescription = mriDescription;
	}

	public int getFdgPet() {
		return fdgPet;
	}

	public void setFdgPet(int fdgPet) {
		this.fdgPet = fdgPet;
	}

	public String getDescriptionPetHypometabolism() {
		return descriptionPetHypometabolism;
	}

	public void setDescriptionPetHypometabolism(
			String descriptionPetHypometabolism) {
		this.descriptionPetHypometabolism = descriptionPetHypometabolism;
	}

	public int getInterictalSpect() {
		return interictalSpect;
	}

	public void setInterictalSpect(int interictalSpect) {
		this.interictalSpect = interictalSpect;
	}

	public String getDescriptionSpectHypoperfuse() {
		return descriptionSpectHypoperfuse;
	}

	public void setDescriptionSpectHypoperfuse(
			String descriptionSpectHypoperfuse) {
		this.descriptionSpectHypoperfuse = descriptionSpectHypoperfuse;
	}

	public int getIctalSpect() {
		return ictalSpect;
	}

	public void setIctalSpect(int ictalSpect) {
		this.ictalSpect = ictalSpect;
	}

	public String getDescriptionSpectHyperperfuse() {
		return descriptionSpectHyperperfuse;
	}

	public void setDescriptionSpectHyperperfuse(
			String descriptionSpectHyperperfuse) {
		this.descriptionSpectHyperperfuse = descriptionSpectHyperperfuse;
	}

	public boolean isSiscom() {
		return siscom;
	}

	public void setSiscom(boolean siscom) {
		this.siscom = siscom;
	}

	public int getMrsProtocol() {
		return mrsProtocol;
	}

	public void setMrsProtocol(int mrsProtocol) {
		this.mrsProtocol = mrsProtocol;
	}

	public int getMrsFinding() {
		return mrsFinding;
	}

	public void setMrsFinding(int mrsFinding) {
		this.mrsFinding = mrsFinding;
	}

	public String getDescriptionMrsAbnormality() {
		return descriptionMrsAbnormality;
	}

	public void setDescriptionMrsAbnormality(String descriptionMrsAbnormality) {
		this.descriptionMrsAbnormality = descriptionMrsAbnormality;
	}

	public boolean isFmri() {
		return fmri;
	}

	public void setFmri(boolean fmri) {
		this.fmri = fmri;
	}

	public String getDetailsFmri() {
		return detailsFmri;
	}

	public void setDetailsFmri(String detailsFmri) {
		this.detailsFmri = detailsFmri;
	}

	public boolean isDti() {
		return dti;
	}

	public void setDti(boolean dti) {
		this.dti = dti;
	}

	public String getDetailsDtiStudy() {
		return detailsDtiStudy;
	}

	public void setDetailsDtiStudy(String detailsDtiStudy) {
		this.detailsDtiStudy = detailsDtiStudy;
	}

	public boolean isWada() {
		return wada;
	}

	public void setWada(boolean wada) {
		this.wada = wada;
	}

	public String getDetailsWada() {
		return detailsWada;
	}

	public void setDetailsWada(String detailsWada) {
		this.detailsWada = detailsWada;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
}
