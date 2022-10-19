package com.coder.form;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ResponseForm {
	private List<ReportDataForm> reportDataForm = new ArrayList<ReportDataForm>(0);
	private XSSFWorkbook wb;



	public XSSFWorkbook getWb() {
		return wb;
	}

	public void setWb(XSSFWorkbook wb) {
		this.wb = wb;
	}

	public List<ReportDataForm> getReportDataForm() {
		return reportDataForm;
	}

	public void setReportDataForm(List<ReportDataForm> reportDataForm) {
		this.reportDataForm = reportDataForm;
	}
}
