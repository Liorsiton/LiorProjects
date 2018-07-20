package com.lior.order;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.lior.resturant.ResturantImpl;

public class TimeOfOrder {
	private static Logger logger = LogManager.getLogger(TimeOfOrder.class);
	private TIMEOFDAY timeOfDay;
	private LocalDate date;	
	private Map<TIMEOFDAY ,LocalDate> orders  = new HashMap<>();	
	
	
		
	
public TimeOfOrder(TIMEOFDAY timeOfDay, LocalDate date) {
		
		this.timeOfDay = timeOfDay;
		this.date = date;
	}

public TimeOfOrder(){}


public TIMEOFDAY getTimeOfDay() {
		return timeOfDay;
	}


	public void setTimeOfDay(TIMEOFDAY timeOfDay) {
		this.timeOfDay = timeOfDay;
	}


	public LocalDate getDate() {
		return date;
	}


	public void setDate(LocalDate date) {
		this.date = date;
	}


	/*
 * this method set order as occupied on time and date */
	public boolean setOrder(TIMEOFDAY tod ,LocalDate date) {		
		//set the date and time as occupied
		LocalDate dateTemp = orders.get(tod);
		if(dateTemp!=null){			
			if(!dateTemp.equals(date)){
				orders.put(tod , date);
				logger.info("add order in time {} and in date {}",tod,date);
				return true;
			}		
			else{
				logger.info(" from orders :this time {} and date {} is already ordered",tod,date);
				return false;
			}
		}
		
		orders.put(tod , date);	
		return true;		
		
	}
	
	
	public void removeOrder(TIMEOFDAY tod ,LocalDate date) {
		
		//set the date and time as occupied
		LocalDate dateTemp = orders.get(tod);
		if(dateTemp!=null){			
			if(dateTemp.equals(date)){
				orders.remove(tod);		
			}		
			else{
				logger.info("There is no order on the date {} on that timeofDay {}" ,date,tod);
				}
		}
		else{
			logger.info("There is no order on the date {} on that timeofDay {}" ,date,tod);
		}
		
	}
	
	
	
	
	public boolean isFree(TIMEOFDAY tod ,LocalDate date){
		LocalDate temp = orders.get(tod);
		if(temp==null){
			return true;
		}
		if(orders.get(tod).equals(date)){
			return false;		
		}
		return true;
	}
	
	 
	
	

}
