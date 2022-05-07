package com.healtheme.patientreports.model;

public class PatientReportsModel {

	private String testName;
	private String reportDate;
	private String reportTime;
	private String prescribedBy;
	private String result;

	public PatientReportsModel(String testName, String reportDate, String reportTime, String prescribedBy,
			String result) {
		this.testName = testName;
		this.reportDate = reportDate;
		this.reportTime = reportTime;
		this.prescribedBy = prescribedBy;
		this.result = result;
	}

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
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

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
}
