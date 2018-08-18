package com.lior.dao;


import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import com.lior.order.Order;
import com.lior.order.TIMEOFDAY;
import com.lior.order.TimeOfOrder;
import com.lior.resturant.Resturant;
import com.lior.resturant.ResturantImpl;
import com.lior.table.Table;

public class MainTest {
	

	public static void main(String[] args) throws SQLException {
		
		LocalDate date = LocalDate.of(2018, 8, 18);
		TimeOfOrder timeOfOrder = new TimeOfOrder(TIMEOFDAY.TWELF,date);
		Order order = new Order(123 ,"Lior" , "0542434484" ,timeOfOrder,4,new ResturantImpl("mamaia"));
		Dao dao = new Dao();		
		int restID = dao.getResturantByName("mamaia");
		System.out.println(restID);
		int todId = dao.getTod(timeOfOrder);
		System.out.println(todId);
		List<Table> tables = dao.getTableByOrderParams(order);
		for(Table table : tables){
			System.out.println(table.getId() + " " + table.getTableNumber() + " " + table.getSize()); 
		}
		
		
		
		
	

	}

}
