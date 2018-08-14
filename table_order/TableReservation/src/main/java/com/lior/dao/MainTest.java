package com.lior.dao;

import java.sql.SQLException;
import java.time.LocalDate;

import com.lior.order.TIMEOFDAY;
import com.lior.order.TimeOfOrder;
import com.lior.resturant.Resturant;
import com.lior.resturant.ResturantImpl;
import com.lior.table.Table;

public class MainTest {
	

	public static void main(String[] args) throws SQLException {
		ResturantImpl test = new ResturantImpl("testRest1");
		Dao dao = new Dao();
		System.out.println(dao.getResturantByName("mamaia")); 
		
		
	

	}

}
