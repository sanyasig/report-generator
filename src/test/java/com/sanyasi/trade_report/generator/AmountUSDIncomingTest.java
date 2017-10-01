package com.sanyasi.trade_report.generator;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import com.sanyasi.trade_report.csv_loader.CsvLoader;
import com.sanyasi.trade_report.models.Report;
import com.sanyasi.trade_report.models.Trade;

public class AmountUSDIncomingTest {

	private AmountUSDIncoming unit= new AmountUSDIncoming();
	
	@Test
	public void testAmountIncoming() throws Exception {
		ArrayList<Trade> trades = CsvLoader.loadCSVTrades(this.getClass().getResource("/trades.csv").getFile());
		Report reports = unit.genrateReport(trades);
		assertEquals(reports.getLabel(), "Incoming Amount");
		assertEquals(reports.getKeyLabel(), "Date");
		assertEquals(reports.getValueLabel(), "Amount(USD)");
		assertEquals(reports.getReportValue().size(),5, 0);
	}
	
	@Test
	public void testNOAmountInGoing() throws Exception {
		ArrayList<Trade> trades = CsvLoader.loadCSVTrades(this.getClass().getResource("/trades.csv").getFile());
		ArrayList<Trade> noOutgoing = new ArrayList<>(Arrays.asList(trades.get(0), trades.get(0)));
		
		Report reports = unit.genrateReport(noOutgoing);
		assertEquals(reports.getLabel(), "Incoming Amount");
		assertEquals(reports.getKeyLabel(), "Date");
		assertEquals(reports.getValueLabel(), "Amount(USD)");
		assertEquals(reports.getReportValue().size(),0, 0);
	}
	
	@Test
	public void testNoTrades() throws Exception {
		ArrayList<Trade> notrades = new ArrayList<>();
		
		Report reports = unit.genrateReport(notrades);
		assertEquals(reports.getLabel(), "Incoming Amount");
		assertEquals(reports.getKeyLabel(), "Date");
		assertEquals(reports.getValueLabel(), "Amount(USD)");
		assertEquals(reports.getReportValue().size(),0, 0);
	}
	
	@Test
	public void testAmountIncommingAmounts() throws Exception {
		ArrayList<Trade> trades = CsvLoader.loadCSVTrades(this.getClass().getResource("/trades.csv").getFile());
		Report reports = unit.genrateReport(trades);
		assertEquals(reports.getReportValue().keySet().size(), 5, 0);
		assertEquals((Double)reports.getReportValue().get("27 Apr 2015"), 994999.5, 0);
		assertEquals((Double)reports.getReportValue().get("10 Jan 2016"), 30789.0, 0);
		assertEquals((Double)reports.getReportValue().get("15 Mar 2015"), 994999.5, 0);
		assertEquals((Double)reports.getReportValue().get("19 Jul 2015"), 994999.5, 0);
	}

}