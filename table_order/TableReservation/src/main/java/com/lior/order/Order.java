package com.lior.order;

import java.time.LocalDate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.lior.resturant.ResturantImpl;
import com.lior.table.Table;

public class Order {
	
	private static Logger logger = LogManager.getLogger(Order.class);
	
	private int id;
	private String guestName;
	private String TelNumber;
	private TimeOfOrder timeOfOrder;
	private int guestsNumber;
	private Table table;
	private ResturantImpl rest;
	
	
	
	
	public Order(int id ,String name, String telNumber, TimeOfOrder timeOfOrder,int guestsNumber ,ResturantImpl rest) {	
		this.id = id;
		this.guestName = name;
		TelNumber = telNumber;
		this.timeOfOrder = timeOfOrder;
		this.guestsNumber = guestsNumber;
		this.rest = rest;
	}
	
	public boolean setOrder(TIMEOFDAY tod , LocalDate date){
		if(this.timeOfOrder.setOrder(tod , date)){
			return true;			
		}
		logger.debug("order already done in time of day {} and date {} ",tod ,date);
		return false;
	}


	public String getName() {
		return guestName;
	}


	public void setName(String name) {
		this.guestName = name;
	}



	public String getTelNumber() {
		return TelNumber;
	}



	public void setTelNumber(String telNumber) {
		TelNumber = telNumber;
	}



	public TimeOfOrder getTimeOfOrder() {
		return timeOfOrder;
	}



	public void setTimeOfOrder(TimeOfOrder timeOfOrder) {
		this.timeOfOrder = timeOfOrder;
	}



	public int getGuestsNumber() {
		return guestsNumber;
	}




	public void setGuestsNumber(int guestsNumber) {
		this.guestsNumber = guestsNumber;
	}
	
	
	
	public ResturantImpl getRest() {
		return rest;
	}

	public void setRest(ResturantImpl rest) {
		this.rest = rest;
	}

	@Override
	public String toString() {
		return "Order [id= " + id + ",name= " + guestName + ",TelNumber= " + TelNumber + ", timeOfOrder= " + timeOfOrder +",guestsNumber= " + guestsNumber + "Resturan= " + rest +"]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
	
	
	
	

}
