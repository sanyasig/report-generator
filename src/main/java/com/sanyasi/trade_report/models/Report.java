package com.sanyasi.trade_report.models;

import java.util.HashMap;

public class Report {
	
	private String label;
	private String KeyLabel;
	private String ValueLabel;
	private HashMap<String, Object> reportValue;
	
	public Report(String label, String keyLabel, String valueLabel, HashMap<String, Object> dayIncomingAmout) {
		this.label = label;
		KeyLabel = keyLabel;
		ValueLabel = valueLabel;
		this.reportValue = dayIncomingAmout;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getKeyLabel() {
		return KeyLabel;
	}

	public void setKeyLabel(String keyLabel) {
		KeyLabel = keyLabel;
	}

	public String getValueLabel() {
		return ValueLabel;
	}

	public void setValueLabel(String valueLabel) {
		ValueLabel = valueLabel;
	}

	public HashMap<String, Object> getReportValue() {
		return reportValue;
	}

	public void setReportValue(HashMap<String, Object> reportValue) {
		this.reportValue = reportValue;
	}
	
}
