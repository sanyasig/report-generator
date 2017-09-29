package com.sanyasi.trade_report;

public class RecordParseException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public RecordParseException(String message, Throwable e){
		super(message, e);
	}

}
