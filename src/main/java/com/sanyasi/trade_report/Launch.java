package com.sanyasi.trade_report;

import java.util.ArrayList;

import com.sanyasi.trade_report.csv_loader.CsvLoader;
import com.sanyasi.trade_report.models.Trade;

public class Launch {

	public static void main(String[] args) throws TradeReportFailedException, RecordParseException {
		
		System.out.println("Launch");
		ArrayList<Trade> records = CsvLoader.loadCSVTrades("/home/nani/records.csv");
		System.out.println("total number of records " + records.size());

	}
}
