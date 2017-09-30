package com.sanyasi.trade_report.generator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;

import com.sanyasi.trade_report.models.Report;
import com.sanyasi.trade_report.models.ReportItem;
import com.sanyasi.trade_report.models.Trade;

public class ReportGenerator {

	public ArrayList<Report>  generateReport(ArrayList<Trade> trades) {
		
		List<? extends ReportItem> reportsToGenerate = Arrays.asList(new AmountUSDIncoming(), 
																	 new AmountUSDOutgoing(),
																	 new AmountRanking());
		
		ArrayList<Report> reports = new ArrayList<Report>();
		
		for (ReportItem reportIterm : reportsToGenerate){
			reports.add(reportIterm.genrateReport(trades));
		}
		
		return reports;
	}

	public String getReportTOPrint(ArrayList<Report> reports) {
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("\n\n");
		for (Report report : reports){
			
			stringBuffer.append("****" + report.getLabel() + "****\n");
			stringBuffer.append(report.getKeyLabel() + "\t\t\t" + report.getValueLabel() + "\n");
			
			for(Entry<String, Object> entity: report.getReportValue().entrySet()){
				stringBuffer.append(entity.getKey() + "\t\t" + entity.getValue() + "\n");
			}
			stringBuffer.append("\n\n");
			
		}
		
		return stringBuffer.toString();
	}

}
