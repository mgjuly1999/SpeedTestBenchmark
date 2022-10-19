package com.coder.model;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "connection_speed", catalog = "speedtestbenchmarkdb")
public class ConnectionSpeed {
	
private Integer speedId;
private float downloadSpeed;
private float uploadSpeed;
private float ping;
private DailyReport dailyReport;

@Id
@GeneratedValue(strategy = IDENTITY)

@Column(name = "speed_id", unique = true, nullable = false)
public Integer getSpeedId() {
	return speedId;
}
public void setSpeedId(Integer speedId) {
	this.speedId = speedId;
}

@Column(name = "download_speed", nullable = false)
public float getDownloadSpeed() {
	return downloadSpeed;
}
public void setDownloadSpeed(float downloadSpeed) {
	this.downloadSpeed = downloadSpeed;
}
@Column(name = "upload_speed", nullable = false)
public float getUploadSpeed() {
	return uploadSpeed;
}
public void setUploadSpeed(float uploadSpeed) {
	this.uploadSpeed = uploadSpeed;
}
@Column(name = "ping", nullable = false)
public float getPing() {
	return ping;
}
public void setPing(float ping) {
	this.ping = ping;
}

@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "report_id", nullable = false)
public DailyReport getDailyReport() {
	return dailyReport;
}
public void setDailyReport(DailyReport dailyReport) {
	this.dailyReport = dailyReport;
}


}
