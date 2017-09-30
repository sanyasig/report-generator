package com.sanyasi.trade_report.generator;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sanyasi.trade_report.models.Report;
import com.sanyasi.trade_report.models.ReportItem;
import com.sanyasi.trade_report.models.Trade;

/**
 * Amount in USD settled outgoing everyday
 * @author sanyasig
 *
 */
public class AmountUSDOutgoing extends ReportItem {
	private static final Logger log = LoggerFactory.getLogger(AmountUSDOutgoing.class);

	@Override
	public Report genrateReport(ArrayList<Trade> trades) {
		log.info("Generatiing Amount in USD settled Outgoing everyday for " + trades.size() + " Trades");
		return null;
	}

}
