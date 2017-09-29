package com.sanyasi.trade_report.models;

import java.util.Date;

public class Trade {
	
	private String entity;
	private boolean buy = false;
	private boolean sell = false;
	private String currency;
	private Date instructionDate;
	private Date settlementDate;
	private int units;
	private double unitPrice;
	private double agreedFx;
	
	public String getEntity() {
		return entity;
	}
	public void setEntity(String entity) {
		this.entity = entity;
	}
	public boolean isBuy() {
		return buy;
	}
	public void setBuy() {
		this.buy = true;
	}
	public boolean isSell() {
		return sell;
	}
	public void setSell() {
		this.sell = true;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public Date getInstructionDate() {
		return instructionDate;
	}
	public void setInstructionDate(Date instructionDate) {
		this.instructionDate = instructionDate;
	}
	public Date getSettlementDate() {
		return settlementDate;
	}
	public void setSettlementDate(Date settlementDate) {
		this.settlementDate = settlementDate;
	}
	public int getUnits() {
		return units;
	}
	public void setUnits(int units) {
		this.units = units;
	}
	public double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}
	public double getAgreedFx() {
		return agreedFx;
	}
	public void setAgreedFx(double agreedFx) {
		this.agreedFx = agreedFx;
	}
	
}
