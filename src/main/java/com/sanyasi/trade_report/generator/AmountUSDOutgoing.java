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
 * Amount in USD settled outgoing everyday
 * @author sanyasig
 *
 */
public class AmountUSDOutgoing extends ReportItem {
	private static final Logger log = LoggerFactory.getLogger(AmountUSDOutgoing.class);

	@Override
	public Report genrateReport(ArrayList<Trade> trades) {
		log.info("Generatiing Amount in USD settled Outgoing everyday for " + trades.size() + " Trades");
				
		HashMap<String, Object> dayOutgoingmout = new HashMap<String, Object>();
		
		for (Trade trade : trades) {
			
			if (trade.isBuy()) {
				String srDate =  new SimpleDateFormat(DATE_FORMAT).format(trade.getSettlementDate());

				if (!dayOutgoingmout.containsKey(srDate)) {
					dayOutgoingmout.put(srDate, trade.getAmountUSD());
				} else {
					dayOutgoingmout.put(srDate, (Double)dayOutgoingmout.get(srDate) + trade.getAmountUSD());
				}
			}

		}
		
		return new Report("OutGoing Amount", 
							"Date", 
							"Amount(USD)", 
							dayOutgoingmout);
	}

}
