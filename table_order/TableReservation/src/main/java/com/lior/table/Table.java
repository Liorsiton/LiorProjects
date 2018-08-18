package com.lior.table;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import com.lior.order.TIMEOFDAY;
import com.lior.order.TimeOfOrder;
import com.lior.resturant.ResturantImpl;

public class Table {
	
	private int id;
	private int tableNumber;
	private int size;		
	private ResturantImpl rest;
	private TimeOfOrder timeOfOrder;
	private Map<Table,TimeOfOrder> tableStatus;
	
	
	
		
	public Table(int id ,int table_number,  int size) {		
		this.id = id;
		this.tableNumber = table_number;
		this.size = size;
		tableStatus = new HashMap<>();
				
	}
	
	public Table(int id,  int size,TimeOfOrder timeOfOrder) {		
		this.tableNumber = id;
		this.size = size;
		this.timeOfOrder = timeOfOrder;
				
	}

	public int getTableNumber() {
		return tableNumber;
	}

	public void setTableNumber(int tableNumber) {
		this.tableNumber = tableNumber;
	}
	


	public int getSize() {
		return size;
	}



	public void setSize(int size) {
		this.size = size;
	}
	
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public TimeOfOrder getTimeOfOrder() {
		return timeOfOrder;
	}

	public void setTimeOfOrder(TimeOfOrder timeOfOrder) {
		this.timeOfOrder = timeOfOrder;
	}
	
	

	public boolean isFree(TimeOfOrder too) {
		return too.isFree(too.getTimeOfDay(), too.getDate());
		
	}



	public ResturantImpl getRest() {
		return rest;
	}

	public void setRest(ResturantImpl rest) {
		this.rest = rest;
	}

	@Override
	public String toString() {
		return "Table [id=" + tableNumber + ", size=" + size + " time of order= " +timeOfOrder.toString() + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + tableNumber;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Table other = (Table) obj;
		if (tableNumber != other.tableNumber)
			return false;
		return true;
	}
	

	
	

}
