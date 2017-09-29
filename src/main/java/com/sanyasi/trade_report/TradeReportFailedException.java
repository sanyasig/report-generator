package com.sanyasi.trade_report;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TradeReportFailedException extends Exception {
		private static final long serialVersionUID = 1L;
		private static final Logger LOG = LoggerFactory.getLogger(TradeReportFailedException.class);

		public TradeReportFailedException(String message, Exception e) {
			LOG.error(message, e);
			System.exit(0);
		}
}
