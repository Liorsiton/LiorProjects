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
	private int size;		
	private ResturantImpl rest;
	private Map<Table,TimeOfOrder> tableStatus;
	
	
	
		
	public Table(int id,  int size) {		
		this.id = id;
		this.size = size;
		tableStatus = new HashMap<>();
				
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	


	public int getSize() {
		return size;
	}



	public void setSize(int size) {
		this.size = size;
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
		return "Table [id=" + id + ", size=" + size + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		if (id != other.id)
			return false;
		return true;
	}
	

	
	

}
