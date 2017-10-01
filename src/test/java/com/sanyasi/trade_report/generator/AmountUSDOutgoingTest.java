package com.sanyasi.trade_report.generator;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import com.sanyasi.trade_report.csv_loader.CsvLoader;
import com.sanyasi.trade_report.models.Report;
import com.sanyasi.trade_report.models.Trade;

public class AmountUSDOutgoingTest {

	private AmountUSDOutgoing unit= new AmountUSDOutgoing();
	
	@Test
	public void testAmountOutGoing() throws Exception {
		ArrayList<Trade> trades = CsvLoader.loadCSVTrades(this.getClass().getResource("/trades.csv").getFile());
		Report reports = unit.genrateReport(trades);
		assertEquals(reports.getLabel(), "OutGoing Amount");
		assertEquals(reports.getKeyLabel(), "Date");
		assertEquals(reports.getValueLabel(), "Amount(USD)");
		assertEquals(reports.getReportValue().size(),1, 0);
	}
	
	@Test
	public void testNOAmountOutGoing() throws Exception {
		ArrayList<Trade> trades = CsvLoader.loadCSVTrades(this.getClass().getResource("/trades.csv").getFile());
		ArrayList<Trade> noOutgoing = new ArrayList<>(Arrays.asList(trades.get(1), trades.get(2), trades.get(3), trades.get(4),trades.get(5)));
		
		Report reports = unit.genrateReport(noOutgoing);
		assertEquals(reports.getLabel(), "OutGoing Amount");
		assertEquals(reports.getKeyLabel(), "Date");
		assertEquals(reports.getValueLabel(), "Amount(USD)");
		assertEquals(reports.getReportValue().size(),0, 0);
	}
	
	@Test
	public void testNoTrades() throws Exception {
		ArrayList<Trade> notrades = new ArrayList<>();
		
		Report reports = unit.genrateReport(notrades);
		assertEquals(reports.getLabel(), "OutGoing Amount");
		assertEquals(reports.getKeyLabel(), "Date");
		assertEquals(reports.getValueLabel(), "Amount(USD)");
		assertEquals(reports.getReportValue().size(),0, 0);
	}
	
	@Test
	public void testAmountOutGoingAmounts() throws Exception {
		ArrayList<Trade> trades = CsvLoader.loadCSVTrades(this.getClass().getResource("/trades.csv").getFile());
		Report reports = unit.genrateReport(trades);
		assertTrue(reports.getReportValue().keySet().contains("04 Jan 2016"));
		assertEquals((Double)reports.getReportValue().get("04 Jan 2016"), 10025.0, 0);
	}

}
