package com.sanyasi.trade_report.csv_loader;

import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

import com.sanyasi.trade_report.RecordParseException;
import com.sanyasi.trade_report.TradeReportFailedException;
import com.sanyasi.trade_report.models.Trade;

import au.com.bytecode.opencsv.CSVParser;
import au.com.bytecode.opencsv.CSVReader;

public class CsvLoader {
	
	/**
	 * @param csvFilePath : absolute CSV File of trades to generate reports
	 * @return ArrayList of processed Trades 
	 */
	
	private static HashMap<Integer, String> indexColunmMap = new HashMap<Integer, String>();
	private static HashMap<String, Integer> headerMap = new HashMap<String, Integer>();
	
	public static ArrayList<Trade> loadCSVTrades(String csvFilePath) throws TradeReportFailedException, RecordParseException {
		CSVReader reader;
		ArrayList<Trade> trades = new ArrayList<Trade>();
		try {
			reader = new CSVReader(new FileReader(csvFilePath), CSVParser.DEFAULT_SEPARATOR,
					CSVParser.DEFAULT_QUOTE_CHARACTER, '\\');
			
			headerMap = getHeaderMap(reader.readNext());
			String[] record = reader.readNext();
			
			while (record != null){
				trades.add(processTrade(record));
				record = reader.readNext();
			}
		
			reader.close();
		
		} catch (IOException e) {
			throw new TradeReportFailedException("ERROR Loading Trade CSV File, Exiting Report Generation ", e);
		} 
		return trades;
	}
	
	
	
	private static Trade processTrade(String[] record) throws RecordParseException {
		SimpleDateFormat fmt = new SimpleDateFormat("dd MMM yyyy");
		Trade trade = new Trade();
		try {
			trade.setAgreedFx(Double.parseDouble(record[headerMap.get("agreedfx")]));
			trade.setCurrency(record[headerMap.get("currency")]);
			trade.setUnits(Integer.parseInt(record[headerMap.get("units")]));
			trade.setUnitPrice(Double.parseDouble(record[headerMap.get("price_per_unit")]));
			trade.setEntity(record[headerMap.get("entity")]);
			trade.setInstructionDate(fmt.parse(record[headerMap.get("instructiondate")]));
			trade.setSettlementDate(fmt.parse(record[headerMap.get("settlementdate")]));
		
		if(record[headerMap.get("buy/sell")].equalsIgnoreCase("buy"))
			trade.setBuy();
		else
			trade.setSell();
		} catch (ParseException e) {
			 throw new RecordParseException("Error Proccesing a Trade Record Ignoring it for Report Prodesing", e);
		}
	
		
		
		return trade;
	}

	
	private static HashMap<String, Integer> getHeaderMap(String[] headers) {
		
		HashMap<String, Integer> headerMap = new HashMap<String, Integer>();
		for(int i=0; i<headers.length;i++){
			headerMap.put(headers[i].toLowerCase(), i);
			indexColunmMap.put(i, headers[i]);
		}
					
		return headerMap;
	}
	

}
