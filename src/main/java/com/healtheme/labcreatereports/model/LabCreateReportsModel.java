package com.healtheme.labcreatereports.model;

public class LabCreateReportsModel {

	private String patientEmail;
	private String reportDate;
	private String reportTime;
	private String prescribedBy;
	private String testsDone;
	private String result;

	public LabCreateReportsModel(String patientEmail, String reportDate, String reportTime, String prescribedBy,
			String testsDone, String result) {
		this.patientEmail = patientEmail;
		this.reportDate = reportDate;
		this.reportTime = reportTime;
		this.prescribedBy = prescribedBy;
		this.testsDone = testsDone;
		this.result = result;
	}

	public String getPatientEmail() {
		return patientEmail;
	}

	public void setPatientEmail(String patientEmail) {
		this.patientEmail = patientEmail;
	}

	public String getReportDate() {
		return reportDate;
	}

	public void setReportDate(String reportDate) {
		this.reportDate = reportDate;
	}

	public String getReportTime() {
		return reportTime;
	}

	public void setReportTime(String reportTime) {
		this.reportTime = reportTime;
	}

	public String getPrescribedBy() {
		return prescribedBy;
	}

	public void setPrescribedBy(String prescribedBy) {
		this.prescribedBy = prescribedBy;
	}

	public String getTestsDone() {
		return testsDone;
	}

	public void setTestsDone(String testsDone) {
		this.testsDone = testsDone;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
}
