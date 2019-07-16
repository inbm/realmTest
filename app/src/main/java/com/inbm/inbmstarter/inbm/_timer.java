package com.inbm.inbmstarter.inbm;

public class _timer {
	static long start, end;
	public static void start(){
		start =  System.currentTimeMillis();
	}
	
	public static long elapse(){
		return System.currentTimeMillis() - start;
	}
}
