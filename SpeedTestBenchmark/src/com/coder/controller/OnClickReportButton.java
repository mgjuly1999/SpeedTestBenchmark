package com.coder.controller;

import java.io.FileNotFoundException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xddf.usermodel.chart.AxisPosition;
import org.apache.poi.xddf.usermodel.chart.ChartTypes;
import org.apache.poi.xddf.usermodel.chart.LegendPosition;
import org.apache.poi.xddf.usermodel.chart.MarkerStyle;
import org.apache.poi.xddf.usermodel.chart.XDDFCategoryAxis;
import org.apache.poi.xddf.usermodel.chart.XDDFChartLegend;
import org.apache.poi.xddf.usermodel.chart.XDDFDataSource;
import org.apache.poi.xddf.usermodel.chart.XDDFDataSourcesFactory;
import org.apache.poi.xddf.usermodel.chart.XDDFLineChartData;
import org.apache.poi.xddf.usermodel.chart.XDDFNumericalDataSource;
import org.apache.poi.xddf.usermodel.chart.XDDFValueAxis;
import org.apache.poi.xssf.usermodel.XSSFChart;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import com.coder.form.ReportDataForm;
import com.coder.form.ResponseForm;
import com.coder.service.SpeedTestService;
import com.coder.util.FileUpload;
import com.coder.util.ServerPath;

@Controller
@Transactional
public class OnClickReportButton {
	@Autowired
	private SpeedTestService speedTestService;
	private static final String CONTENT_TYPE_XLSX = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

	@RequestMapping("/submitDownload")
	public void downloadReportDispatcher(ModelMap modelMap, HttpServletRequest req, HttpServletResponse resp)
			throws FileNotFoundException, IOException {

		ResponseForm respForm = new ResponseForm();
		this.speedTestService.prepareReport(respForm);
		this.lineChart(respForm);

		String filename = "Avg Upload Download speed & Ping result.xlsx";

		String serverPath = ServerPath.getPath();

		Path file = Paths.get(serverPath, filename);

		if (Files.exists(file)) {
			resp.setContentType(CONTENT_TYPE_XLSX);
			resp.addHeader("Content-Disposition", "attachment; filename=" + filename);
			try {
				Files.copy(file, resp.getOutputStream());
				resp.getOutputStream().flush();
			} catch (IOException e) {
				System.out.println("Error :- " + e.getMessage());
			}
		} else {

		}

	}

	public void lineChart(ResponseForm respForm) throws FileNotFoundException, IOException {
		try (XSSFWorkbook wb = new XSSFWorkbook()) {
			List<ReportDataForm> reportDataForms = respForm.getReportDataForm();
			String sheetName = "Avg Upload Download speed & Ping result";

			XSSFSheet sheet = wb.createSheet(sheetName);

			Row row0 = sheet.createRow((short) 0);

			int index = 0;
			for (ReportDataForm obj : reportDataForms) {
				Cell cell = row0.createCell((short) index);
				cell.setCellValue(obj.getCreateDate().toString());

				index += 1;

			}

			Row row1 = sheet.createRow((short) 1);

			index = 0;
			for (ReportDataForm obj : reportDataForms) {
				Cell cell = row1.createCell((short) index);
				cell.setCellValue(obj.getAvgUploadSpeed());

				index += 1;

			}

			Row row2 = sheet.createRow((short) 2);

			index = 0;
			for (ReportDataForm obj : reportDataForms) {
				Cell cell = row2.createCell((short) index);
				cell.setCellValue(obj.getAvgDownloadSpeed());

				index += 1;

			}

			Row row3 = sheet.createRow((short) 3);

			index = 0;
			for (ReportDataForm obj : reportDataForms) {
				Cell cell = row3.createCell((short) index);
				cell.setCellValue(obj.getAvgPing());

				index += 1;

			}

			XSSFDrawing drawing = sheet.createDrawingPatriarch();
			XSSFClientAnchor anchor = drawing.createAnchor(0, 0, 0, 0, 0,5, 10, 30);

			XSSFChart chart = drawing.createChart(anchor);
			chart.setTitleText("Avg Upload Download speed & Ping result");
			chart.setTitleOverlay(false);

			XDDFChartLegend legend = chart.getOrAddLegend();
			legend.setPosition(LegendPosition.TOP_RIGHT);

			XDDFCategoryAxis bottomAxis = chart.createCategoryAxis(AxisPosition.BOTTOM);
			bottomAxis.setTitle("Daily");
			XDDFValueAxis leftAxis = chart.createValueAxis(AxisPosition.LEFT);
			leftAxis.setTitle("Avg Upload Download speed & Ping");

			XDDFDataSource<String> xs = XDDFDataSourcesFactory.fromStringCellRange(sheet,
					new CellRangeAddress(0, 0, 0, reportDataForms.size() - 1));

			XDDFNumericalDataSource<Double> ys1 = XDDFDataSourcesFactory.fromNumericCellRange(sheet,
					new CellRangeAddress(1, 1, 0, reportDataForms.size() - 1));

			XDDFNumericalDataSource<Double> ys2 = XDDFDataSourcesFactory.fromNumericCellRange(sheet,
					new CellRangeAddress(2, 2, 0, reportDataForms.size() - 1));

			XDDFNumericalDataSource<Double> ys3 = XDDFDataSourcesFactory.fromNumericCellRange(sheet,
					new CellRangeAddress(3, 3, 0, reportDataForms.size() - 1));

			XDDFLineChartData data = (XDDFLineChartData) chart.createData(ChartTypes.LINE, bottomAxis, leftAxis);

			XDDFLineChartData.Series series1 = (XDDFLineChartData.Series) data.addSeries(xs, ys1);
			series1.setTitle("Avg Upload Speed", null);
			series1.setSmooth(false);
			series1.setMarkerStyle(MarkerStyle.STAR);

			XDDFLineChartData.Series series2 = (XDDFLineChartData.Series) data.addSeries(xs, ys2);
			series2.setTitle("Avg Download Speed", null);
			series2.setSmooth(false);
			series2.setMarkerStyle(MarkerStyle.DASH);

			XDDFLineChartData.Series series3 = (XDDFLineChartData.Series) data.addSeries(xs, ys3);
			series3.setTitle("Avg Ping", null);
			series3.setSmooth(false);
			series3.setMarkerStyle(MarkerStyle.DOT);

			chart.plot(data);

			respForm.setWb(wb);
			String filename = "Avg Upload Download speed & Ping result";
			FileUpload.uploadFile(wb, filename);
		}
	}

}
