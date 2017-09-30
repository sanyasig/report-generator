package com.sanyasi.trade_report.csv_loader;

import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
	private static final long DAY_MILLI = 24 * 60 * 60 * 1000;
	
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
			String currency = record[headerMap.get("currency")].toLowerCase();
			trade.setCurrency(currency);
			trade.setUnits(Integer.parseInt(record[headerMap.get("units")]));
			trade.setUnitPrice(Double.parseDouble(record[headerMap.get("price_per_unit")]));
			trade.setEntity(record[headerMap.get("entity")]);
			trade.setInstructionDate(fmt.parse(record[headerMap.get("instructiondate")]));
			Date origianlSD = fmt.parse(record[headerMap.get("settlementdate")]);
			
			Calendar c = Calendar.getInstance();
			c.setTime(origianlSD);
			int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
			Date newSD = origianlSD;
			
			//A work week starts Monday and ends Friday, unless the currency of the trade is AED or SAR, where the work week starts Sunday and ends Thursday.
			if(currency.equalsIgnoreCase("AED") || currency.equalsIgnoreCase("SAR")) {
				// update to next working day in a week
				if(dayOfWeek == 6 || dayOfWeek == 7){
					long milliToAdd = ((7-dayOfWeek)+1) * DAY_MILLI;
					newSD = new Date(milliToAdd + origianlSD.getTime());
				}
			} else if(dayOfWeek == 1 || dayOfWeek == 7) {
				int dayaToadd = dayOfWeek == 1 ? 1: 2;
				newSD = new Date((dayaToadd * DAY_MILLI) + origianlSD.getTime());
			}
			
			trade.setSettlementDate(newSD);
		
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
