package com.lior.resturant;

import java.time.LocalDate;

import com.lior.order.Order;
import com.lior.order.TIMEOFDAY;
import com.lior.order.TimeOfOrder;
import com.lior.table.Table;

public interface Resturant {
	
	 boolean addOrder(Order order , TimeOfOrder too);
	 boolean cancelOrder(Order order , TimeOfOrder too);
	 void init();
	

}
