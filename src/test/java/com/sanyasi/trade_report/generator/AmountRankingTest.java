package com.sanyasi.trade_report.generator;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import com.sanyasi.trade_report.csv_loader.CsvLoader;
import com.sanyasi.trade_report.models.Report;
import com.sanyasi.trade_report.models.Trade;

public class AmountRankingTest {
	private AmountRanking unit = new AmountRanking();
	
	@Test
	public void testAmountRankingNOTraders() {
		ArrayList<Trade> notrades = new ArrayList<>();
		Report reports = unit.genrateReport(notrades);
		assertEquals(reports.getLabel(), "Buy/Sell Entity Rank");
		assertEquals(reports.getKeyLabel(), "Type");
		assertEquals(reports.getValueLabel(), "Entities High-to-Low ");
		assertEquals(reports.getReportValue().size(), 2, 0);
		assertTrue(reports.getReportValue().keySet().contains("Buy Orders"));
		assertTrue(reports.getReportValue().keySet().contains("Sell Orders"));}
	
	@Test
	public void testAmountSellRanking() throws Exception {
		ArrayList<Trade> trades = CsvLoader.loadCSVTrades(this.getClass().getResource("/trades.csv").getFile());
		Report reports = unit.genrateReport(trades);
		assertEquals(reports.getLabel(), "Buy/Sell Entity Rank");
		String sellRank = (String)reports.getReportValue().get("Sell Orders");
		assertEquals(sellRank, "[foobar, updateAED, updateSAR, updateSD, bar]");
	}
	
	@Test
	public void testAmountBuyRanking() throws Exception {
		ArrayList<Trade> trades = CsvLoader.loadCSVTrades(this.getClass().getResource("/trades.csv").getFile());
		Report reports = unit.genrateReport(trades);
		assertEquals(reports.getLabel(), "Buy/Sell Entity Rank");
		String sellRank = (String)reports.getReportValue().get("Buy Orders");
		assertEquals(sellRank, "[foo]");
	}
	
	@Test
	public void testAmountBuyRankingNoBuyOrders() throws Exception {
		ArrayList<Trade> trades = CsvLoader.loadCSVTrades(this.getClass().getResource("/trades.csv").getFile());
		ArrayList<Trade> noOutgoing = new ArrayList<>(Arrays.asList(trades.get(1), trades.get(2), trades.get(3), trades.get(4),trades.get(5)));
		Report reports = unit.genrateReport(noOutgoing);
		assertEquals(reports.getLabel(), "Buy/Sell Entity Rank");
		String sellRank = (String)reports.getReportValue().get("Buy Orders");
		assertEquals(sellRank, "[]");
	}
	
	@Test
	public void testAmountSellRankingNoSellOrders() throws Exception {
		ArrayList<Trade> trades = CsvLoader.loadCSVTrades(this.getClass().getResource("/trades.csv").getFile());
		ArrayList<Trade> noOutgoing = new ArrayList<>(Arrays.asList(trades.get(0)));
		Report reports = unit.genrateReport(noOutgoing);
		assertEquals(reports.getLabel(), "Buy/Sell Entity Rank");
		String sellRank = (String)reports.getReportValue().get("Sell Orders");
		assertEquals(sellRank, "[]");
	}

}
