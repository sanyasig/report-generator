package com.sanyasi.trade_report.generator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.sanyasi.trade_report.models.Report;
import com.sanyasi.trade_report.models.ReportItem;
import com.sanyasi.trade_report.models.Trade;

public class ReportGenerator {

	public static void generateReport(ArrayList<Trade> trades) {
		
		List<? extends ReportItem> reportsToGenerate = Arrays.asList(new AmountUSDIncoming(), 
																	 new AmountUSDOutgoing(),
																	 new AmountRanking());
		
		ArrayList<Report> reports = new ArrayList<Report>();
		
		for (ReportItem reportIterm : reportsToGenerate){
			reports.add(reportIterm.genrateReport(trades));
		}
		
	}

}
