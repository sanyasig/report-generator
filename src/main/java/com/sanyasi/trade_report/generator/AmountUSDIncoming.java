package com.sanyasi.trade_report.generator;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sanyasi.trade_report.models.Report;
import com.sanyasi.trade_report.models.ReportItem;
import com.sanyasi.trade_report.models.Trade;

/**
 * @author sanyasig
 * Amount in USD settled incoming everyday
 */

public class AmountUSDIncoming extends ReportItem {
	private static final Logger log = LoggerFactory.getLogger(AmountUSDIncoming.class);

	@Override
	public Report genrateReport(ArrayList<Trade> trades) {
		HashMap<String, Object> dayIncomingAmout = new HashMap<String, Object>();
		
		for (Trade trade : trades) {
			if (trade.isSell()) {

				String srDate = new SimpleDateFormat(DATE_FORMAT).format(trade.getSettlementDate());

				if (!dayIncomingAmout.containsKey(srDate)) {
					dayIncomingAmout.put(srDate, trade.getAmountUSD());
				} else {
					dayIncomingAmout.put(srDate, (Double)(dayIncomingAmout.get(srDate)) + trade.getAmountUSD());
				}
			}

		}
		
		log.info("Generatiing Amount in USD settled incoming everyday for " + trades.size() + " Trades");
		return new Report("Incoming Amount", 
				"Date", 
				"Amount(USD)", 
				dayIncomingAmout);
	}

}
