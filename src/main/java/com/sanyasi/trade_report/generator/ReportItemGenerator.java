package com.sanyasi.trade_report.generator;

import java.util.ArrayList;

import com.sanyasi.trade_report.models.Report;
import com.sanyasi.trade_report.models.Trade;

public interface ReportItemGenerator {
	Report genrateReport(ArrayList<Trade> trades);
}
