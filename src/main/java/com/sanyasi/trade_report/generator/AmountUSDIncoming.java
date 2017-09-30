package com.sanyasi.trade_report.generator;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sanyasi.trade_report.models.Report;
import com.sanyasi.trade_report.models.ReportItem;
import com.sanyasi.trade_report.models.Trade;

/**
 * @author nani
 * Amount in USD settled incoming everyday
 */

public class AmountUSDIncoming extends ReportItem {
	private static final Logger log = LoggerFactory.getLogger(AmountUSDIncoming.class);

	@Override
	public Report genrateReport(ArrayList<Trade> trades) {
		log.info("Generatiing Amount in USD settled incoming everyday for " + trades.size() + " Trades");
		return null;
	}

}
