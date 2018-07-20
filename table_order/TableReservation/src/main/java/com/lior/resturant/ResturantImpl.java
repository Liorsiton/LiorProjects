package com.lior.resturant;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.lior.order.Order;
import com.lior.order.TIMEOFDAY;
import com.lior.order.TimeOfOrder;
import com.lior.table.Table;

public class ResturantImpl implements Resturant {	
	
	private static Logger logger = LogManager.getLogger(ResturantImpl.class);
	
	private List<Table> tables;
	private List<Order> orders;
	private Map<Table ,TimeOfOrder> tableStatus = new HashMap<>();
	private int capacity;
	private String name;

	
	public ResturantImpl( int capacity ,String name){		
		this.capacity = capacity;
		this.name = name;
		tables = new ArrayList<>();
		orders = new ArrayList<>();
		init();
	}
	
	
	@Override
	public void init() {
		Table table1 = new Table(1,2);
		Table table2 = new Table(2,4);
		Table table3 = new Table(3,6);
		Table table4 = new Table(4,8);
		Table table5 = new Table(5,10);
		tables.add(table1);
		tables.add(table2);
		tables.add(table3);
		tables.add(table4);
		tables.add(table5);
		}
	
	
@Override
public boolean addOrder(Order order , TimeOfOrder too){
	int guestsNumber = order.getGuestsNumber();
	for(Table table : tables){
		if(table.getSize()==guestsNumber && table.isFree(too)){
			return addOrder(order,table,too);
		}
	}
	return false;
	
}

	
	private boolean addOrder(Order order, Table table , TimeOfOrder too) {	
		TimeOfOrder temp = tableStatus.get(table);
		if(temp != null){
			if(temp.getTimeOfDay().equals(too.getTimeOfDay()) && temp.getDate().equals(too.getDate())){
				logger.debug("That table {} in that date {}  and time frame {} is already occupied",table.getId() ,too.getDate(),too.getTimeOfDay());
				return false;
			}
			else{
				logger.debug("going to add order for table {} in date {} at time {}",table.getId() ,too.getDate(),too.getTimeOfDay());
				tableStatus.put(table, too);
				orders.add(order);
				return true;
			}
		}
		logger.debug("going to add order for table {} in date {} at time {}",table.getId() ,too.getDate(),too.getTimeOfDay());
		tableStatus.put(table, too);
		return true;
		
			
		}
		
		
  	@Override
	public boolean cancelOrder(Order order , TimeOfOrder too ) {
		for(Map.Entry<Table,TimeOfOrder> entry : tableStatus.entrySet()){
			if(entry.getValue().equals(too)){
				logger.debug("going to cancel table {} and order {}",entry.getKey().getId(),order.getId());
				tableStatus.remove(entry.getKey());
				order = null;
				return true;
			}
		}
		logger.debug("no table for orde {}",order.getId());
		return false;

	}
		
	
		

	








	

	

		


}
