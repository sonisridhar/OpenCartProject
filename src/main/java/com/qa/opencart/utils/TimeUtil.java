package com.qa.opencart.utils;

public class TimeUtil {
	
	public static final int DEFAULT_TIME = 500;
	public static final int DEFAULT_SHORT_TIME = 2;
	public static final int DEFAULT_AVG_TIME = 5;
	public static final int DEFAULT_LONG_TIME = 10;
	public static final int MAX_TIME = 15;
	
	public static void defaultTime() {
		try {
			Thread.sleep(DEFAULT_TIME);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void defaultShortTime() {
		try {
			Thread.sleep(DEFAULT_SHORT_TIME*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void defaultAvgTime() {
		try {
			Thread.sleep(DEFAULT_AVG_TIME*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void defaultLongTime() {
		try {
			Thread.sleep(DEFAULT_LONG_TIME*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void maxTime() {
		try {
			Thread.sleep(MAX_TIME*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void applyTime(int time) {
		try {
			Thread.sleep(time*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
