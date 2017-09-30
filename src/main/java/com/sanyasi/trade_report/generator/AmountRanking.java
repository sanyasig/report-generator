
package com.sanyasi.trade_report.generator;


import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sanyasi.trade_report.models.Report;
import com.sanyasi.trade_report.models.ReportItem;
import com.sanyasi.trade_report.models.Trade;

/**
 * @author sanyasig
 * Ranking of entities based on incoming and outgoing amount.
 */

public class AmountRanking extends ReportItem {
	private static final Logger log = LoggerFactory.getLogger(AmountUSDOutgoing.class);
	
	public Report genrateReport(ArrayList<Trade> trades) {
		log.info("genrating Report for Ranking of entities based on incoming and outgoing amount. " + trades.size() + " Trades");
		return null;
	}
	
}
