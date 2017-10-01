package com.sanyasi.trade_report.csv_loader;


import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;

import com.sanyasi.trade_report.models.Trade;

public class CsvLoaderTest {
	private String tradeCSV = this.getClass().getResource("/trades.csv").getFile();

	@Test
	public void testCountCSVRecords() throws Exception {
		ArrayList<Trade> trades = CsvLoader.loadCSVTrades(tradeCSV);
		assertEquals(trades.size(), 7);
		assertEquals(trades.get(0).getEntity(), "foo");
		assertEquals(trades.get(1).getCurrency(), "aed");
		assertEquals(trades.get(2).isBuy(), false);
		assertEquals(trades.get(2).isSell(), true);
	}
	
	@Test
	public void testInvalidTradesIgnored() throws Exception {
		ArrayList<Trade> trades = CsvLoader.loadCSVTrades(this.getClass().getResource("/Invalidtrades.csv").getFile());
		assertEquals(trades.size(), 4);
	}
	
	@Test
	public void testAmount() throws Exception {
		ArrayList<Trade> trades = CsvLoader.loadCSVTrades(tradeCSV);
		assertEquals(trades.get(0).getAmountUSD(), 10025, 0.5);
		assertEquals(trades.get(1).getAmountUSD(), 14899.5, 0.5);
		assertEquals(trades.get(2).getAmountUSD(), 15889.5, 0.5);
		assertEquals(trades.get(3).getAmountUSD(), 994999.5, 0.5);
	}
	
	@Test
	public void testSetelemnetDateAED() throws Exception {
		ArrayList<Trade> trades = CsvLoader.loadCSVTrades(tradeCSV);
		assertEquals(trades.get(4).getCurrency(), "aed");
		Date sd = trades.get(4).getSettlementDate();
		String srDate =  new SimpleDateFormat("dd MMM yyyy").format(sd);
		assertEquals(srDate, "19 Jul 2015");
	}
	
	@Test
	public void testSetelemnetDateSAR() throws Exception {
		ArrayList<Trade> trades = CsvLoader.loadCSVTrades(tradeCSV);
		assertEquals(trades.get(5).getCurrency(), "sar");
		Date sd = trades.get(5).getSettlementDate();
		String srDate =  new SimpleDateFormat("dd MMM yyyy").format(sd);
		assertEquals(srDate, "15 Mar 2015");
	}
	
	@Test
	public void testUpdateToMondaySetelemnetDate() throws Exception {
 		ArrayList<Trade> trades = CsvLoader.loadCSVTrades(tradeCSV);
		assertEquals(trades.get(6).getCurrency(), "asd");
		Date sd = trades.get(6).getSettlementDate();
		String srDate =  new SimpleDateFormat("dd MMM yyyy").format(sd);
		assertEquals(srDate, "27 Apr 2015");
	}
	
}
