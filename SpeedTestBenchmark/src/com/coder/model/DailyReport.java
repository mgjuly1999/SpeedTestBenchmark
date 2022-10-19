package com.coder.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "daily_report", catalog = "speedtestbenchmarkdb")
public class DailyReport {
	
private Integer reportId;
private double avgDownloadSpeed;
private double avgUploadSpeed;
private double avgPing;
private Date createDate;
private Set<ConnectionSpeed> connectionSpeeds = new HashSet<ConnectionSpeed>(0);

@Id
@GeneratedValue(strategy = IDENTITY)

@Column(name = "report_id", unique = true, nullable = false)
public Integer getReportId() {
	return reportId;
}
public void setReportId(Integer reportId) {
	this.reportId = reportId;
}

@Column(name = "avg_download_speed", nullable = false)
public double getAvgDownloadSpeed() {
	return avgDownloadSpeed;
}
public void setAvgDownloadSpeed(double avgDownloadSpeed) {
	this.avgDownloadSpeed = avgDownloadSpeed;
}

@Column(name = "avg_upload_speed", nullable = false)
public double getAvgUploadSpeed() {
	return avgUploadSpeed;
}
public void setAvgUploadSpeed(double avgUploadSpeed) {
	this.avgUploadSpeed = avgUploadSpeed;
}

@Column(name = "avg_ping", nullable = false)
public double getAvgPing() {
	return avgPing;
}
public void setAvgPing(double avgPing) {
	this.avgPing = avgPing;
}

@Temporal(TemporalType.DATE)
@Column(name = "create_date", nullable = false, length = 10)
public Date getCreateDate() {
	return createDate;
}
public void setCreateDate(Date createDate) {
	this.createDate = createDate;
}

@OneToMany(fetch = FetchType.LAZY, mappedBy = "dailyReport")
public Set<ConnectionSpeed> getConnectionSpeeds() {
	return connectionSpeeds;
}
public void setConnectionSpeeds(Set<ConnectionSpeed> connectionSpeeds) {
	this.connectionSpeeds = connectionSpeeds;
}



}
