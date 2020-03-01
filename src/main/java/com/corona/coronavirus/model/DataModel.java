package com.corona.coronavirus.model;

public class DataModel {
	private String state;
	private String countryRegion;
	private int lastCasesNum;
private int diffFromPreviosDay;
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountryRegion() {
		return countryRegion;
	}

	public void setCountryRegion(String countryRegion) {
		this.countryRegion = countryRegion;
	}

	public int getLastCasesNum() {
		return lastCasesNum;
	}

	public void setLastCasesNum(int lastCasesNum) {
		this.lastCasesNum = lastCasesNum;
	}

	public int getDiffFromPreviosDay() {
		return diffFromPreviosDay;
	}

	public void setDiffFromPreviosDay(int diffFromPreviosDay) {
		this.diffFromPreviosDay = diffFromPreviosDay;
	}

	
}
