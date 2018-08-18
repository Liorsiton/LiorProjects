package com.lior.resturant;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.lior.dao.Dao;
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
    private int id;
	
	public ResturantImpl( int capacity ,String name){		
		this.capacity = capacity;
		this.name = name;
		tables = new ArrayList<>();
		orders = new ArrayList<>();
		init();
	}
	
	
	public ResturantImpl (String name){
		this.name = name;
	}
	
	
	
	
	public void setName(String name) {
		this.name = name;
	}

	
	

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	@Override
	public void init() {
	//TODO
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
				logger.debug("That table {} in that date {}  and time frame {} is already occupied",table.getTableNumber() ,too.getDate(),too.getTimeOfDay());
				return false;
			}
			else{
				logger.debug("going to add order for table {} in date {} at time {}",table.getTableNumber() ,too.getDate(),too.getTimeOfDay());
				tableStatus.put(table, too);
				orders.add(order);
				return true;
			}
		}
		logger.debug("going to add order for table {} in date {} at time {}",table.getTableNumber() ,too.getDate(),too.getTimeOfDay());
		tableStatus.put(table, too);
		return true;
		
			
		}
		
		
  	@Override
	public boolean cancelOrder(Order order , TimeOfOrder too ) {
		for(Map.Entry<Table,TimeOfOrder> entry : tableStatus.entrySet()){
			if(entry.getValue().equals(too)){
				logger.debug("going to cancel table {} and order {}",entry.getKey().getTableNumber(),order.getId());
				tableStatus.remove(entry.getKey());
				order = null;
				return true;
			}
		}
		logger.debug("no table for orde {}",order.getId());
		return false;

	}
  	
  	
  	public String getName(){
  		return this.name;
  	}
  	
//  	public List<Table> getTables() throws SQLException{
//  		Dao dao = new Dao();
//  		tables = dao.getAllTablesInResturant(this);
//  		return tables;
//  	}
//		
	
		

	








	

	

		


}
