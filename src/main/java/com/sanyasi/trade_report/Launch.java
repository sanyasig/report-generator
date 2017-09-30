package com.sanyasi.trade_report;

import java.io.File;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sanyasi.trade_report.csv_loader.CsvLoader;
import com.sanyasi.trade_report.generator.ReportGenerator;
import com.sanyasi.trade_report.models.Report;
import com.sanyasi.trade_report.models.Trade;

public class Launch {
	private static final Logger log = LoggerFactory.getLogger(CsvLoader.class);

	public static void main(String[] args) throws TradeReportFailedException, RecordParseException {
		String filePath = args[0];
		
		if(!new File(filePath).exists())
			log.error("CSV file done not exixts at " + filePath);
			
		ArrayList<Trade> records = CsvLoader.loadCSVTrades(filePath);
		ReportGenerator rg = new ReportGenerator();
		ArrayList<Report> reports = rg.generateReport(records);
		System.out.println(rg.getReportTOPrint(reports));
		
	}
	
}
