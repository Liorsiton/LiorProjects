package com.lior.order;

public enum TIMEOFDAY {
	TWELF(12),
	FOURTEEN(14),
	SIXTEEN(16),
	EIHGTEEN(18),
	TWENTY(20),
	TWENTYTOW(22),
	NONE(0);
	
	private int time;
	
	TIMEOFDAY(int time){
		this.time=time;
	}
	
	
   public int getTime(){
	   return time;
   }
	


}
