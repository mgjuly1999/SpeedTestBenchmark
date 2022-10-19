package com.coder.form;

import java.util.Date;

public class ReportDataForm {
	
	private Integer reportId;
	private double avgDownloadSpeed;
	private double avgUploadSpeed;
	private double avgPing;
	private Date createDate;
	
	public Integer getReportId() {
		return reportId;
	}
	public void setReportId(Integer reportId) {
		this.reportId = reportId;
	}
	public double getAvgDownloadSpeed() {
		return avgDownloadSpeed;
	}
	public void setAvgDownloadSpeed(double avgDownloadSpeed) {
		this.avgDownloadSpeed = avgDownloadSpeed;
	}
	public double getAvgUploadSpeed() {
		return avgUploadSpeed;
	}
	public void setAvgUploadSpeed(double avgUploadSpeed) {
		this.avgUploadSpeed = avgUploadSpeed;
	}
	public double getAvgPing() {
		return avgPing;
	}
	public void setAvgPing(double avgPing) {
		this.avgPing = avgPing;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	@Override
	public String toString() {
		return "ReportDataForm [reportId=" + reportId + ", avgDownloadSpeed=" + avgDownloadSpeed + ", avgUploadSpeed="
				+ avgUploadSpeed + ", avgPing=" + avgPing + ", createDate=" + createDate + "]";
	}
}
