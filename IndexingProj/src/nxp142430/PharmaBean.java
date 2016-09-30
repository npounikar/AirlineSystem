package nxp142430;

public class PharmaBean {
	
	int id;
	String company;
	String drug_id;
	short trials;
	short patients;
	short dosage;
	float reading;
	boolean double_blind,controlled_study,govt_funded,fda_approved;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getDrug_id() {
		return drug_id;
	}
	public void setDrug_id(String drug_id) {
		this.drug_id = drug_id;
	}
	public short getTrials() {
		return trials;
	}
	public void setTrials(short trails) {
		this.trials = trails;
	}
	public short getPatients() {
		return patients;
	}
	public void setPatients(short patients) {
		this.patients = patients;
	}
	public short getDosage() {
		return dosage;
	}
	public void setDosage(short dosage) {
		this.dosage = dosage;
	}
	public float getReading() {
		return reading;
	}
	public void setReading(float reading) {
		this.reading = reading;
	}
	public boolean isDouble_blind() {
		return double_blind;
	}
	public void setDouble_blind(boolean double_blind) {
		this.double_blind = double_blind;
	}
	public boolean isControlled_study() {
		return controlled_study;
	}
	public void setControlled_study(boolean controlled_study) {
		this.controlled_study = controlled_study;
	}
	public boolean isGovt_funded() {
		return govt_funded;
	}
	public void setGovt_funded(boolean govt_funded) {
		this.govt_funded = govt_funded;
	}
	public boolean isFda_approved() {
		return fda_approved;
	}
	public void setFda_approved(boolean fda_approved) {
		this.fda_approved = fda_approved;
	}
	
	

}
