package com.inbm.inbmstarter.inbm;

public class _memory {
	public static void print(){
		String total, max, free, alloc;
		
		total = Runtime.getRuntime().totalMemory() / (1024 * 1024) + "MB ";
		max = Runtime.getRuntime().maxMemory() / (1024 * 1024) + "MB ";
		 free = Runtime.getRuntime().freeMemory() / (1024 * 1024) + "MB ";
		 alloc = (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / (1024 * 1024) + "MB";
		 _log.e(total + max + free + alloc);
	}
}
