package com.coder.service;

import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.coder.dao.ConnectionSpeedDao;
import com.coder.dao.DailyReportDao;
import com.coder.form.ReportDataForm;
import com.coder.form.ResponseForm;
import com.coder.model.ConnectionSpeed;
import com.coder.model.DailyReport;
import com.coder.util.GeneratedRandom;
import com.coder.util.MyDate;
import com.coder.util.ParseDouble;

@Service
@Repository("speedTestService")
public class SpeedTestService {

	@Autowired
	private ConnectionSpeedDao connectionSpeedDao;
	@Autowired
	private DailyReportDao dailyReportDao;

	@Scheduled(fixedRate = 3600000)
	public void saveConnectionSpeed() {

		this.saveNewDailyReport();
		
		List<DailyReport> dailyReports = dailyReportDao.getDailyReports();
		
		for (DailyReport obj : dailyReports) {
		
		this.saveConnectionSpeedifNot(obj);
		this.updateOldDailyReport(obj);
		}
		

	}

	private DailyReport updateOldDailyReport(DailyReport dailyReport) {
		Long count = this.connectionSpeedDao.getCountByDailyReport(dailyReport);
		if (count == 24) {

			double avgDownload = ParseDouble.formatDouble(this.connectionSpeedDao.getAverageDownloadSpeed(dailyReport));
			double avgUpload = ParseDouble.formatDouble(this.connectionSpeedDao.getAverageUploadSpeed(dailyReport));
			double avgPing = ParseDouble.formatDouble(this.connectionSpeedDao.getAveragePing(dailyReport));

			dailyReport.setAvgDownloadSpeed(avgDownload);
			dailyReport.setAvgUploadSpeed(avgUpload);
			dailyReport.setAvgPing(avgPing);
			this.dailyReportDao.updateDailyReport(dailyReport);
			
			return this.dailyReportDao.getDailyReportById(dailyReport.getReportId());
		}
		
		return dailyReport;

	}

	private void saveConnectionSpeedifNot(DailyReport dailyReport) {

		Long count = this.connectionSpeedDao.getCountByDailyReport(dailyReport);

		if (count < 24) {
			ConnectionSpeed connectionSpeed = new ConnectionSpeed();
			float uploadSpeed = GeneratedRandom.getRandomValue();
			float downloadSpeed = GeneratedRandom.getRandomValue();
			float ping = GeneratedRandom.getRandomValue();
			connectionSpeed.setUploadSpeed(uploadSpeed);
			connectionSpeed.setDownloadSpeed(downloadSpeed);
			connectionSpeed.setPing(ping);
			connectionSpeed.setDailyReport(dailyReport);

			this.connectionSpeedDao.saveConnectionSpeed(connectionSpeed);
		}

	}

	private DailyReport saveNewDailyReport() {
		
		DailyReport dailyReport=this.dailyReportDao.getDailyReportByDate(MyDate.getDate());
		
		if (dailyReport == null) {

			DailyReport newDailyReport = new DailyReport();
			newDailyReport.setCreateDate(MyDate.getDate());
			this.dailyReportDao.saveDailyReport(newDailyReport);
			dailyReport = this.dailyReportDao.getDailyReportByDate(MyDate.getDate());

		}

		return dailyReport;
	}

	public void prepareReport(ResponseForm respForm) {
		List<DailyReport> dailyReports = dailyReportDao.getDailyReports();
		for (DailyReport obj : dailyReports) {
			ReportDataForm reportDataForm = new ReportDataForm();
			obj=this.updateOldDailyReport(obj);
			
			reportDataForm.setReportId(obj.getReportId());
			reportDataForm.setAvgUploadSpeed(obj.getAvgUploadSpeed());
			reportDataForm.setAvgDownloadSpeed(obj.getAvgDownloadSpeed());
			reportDataForm.setAvgPing(obj.getAvgPing());
			reportDataForm.setCreateDate(obj.getCreateDate());

			respForm.getReportDataForm().add(reportDataForm);
		}

	}

}
