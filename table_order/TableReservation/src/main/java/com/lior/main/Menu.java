package com.lior.main;

import java.time.LocalDate;
import java.util.Random;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.lior.order.Order;
import com.lior.order.TIMEOFDAY;
import com.lior.order.TimeOfOrder;
import com.lior.resturant.Resturant;
import com.lior.resturant.ResturantImpl;

public class Menu {
	private static Logger logger = LogManager.getLogger(Menu.class);
	private Scanner scan = new Scanner(System.in);
	private Order order;	
	private TimeOfOrder timeOfOrder;
	
	
	
	
	public void addOrder(){
		Random rand = new Random();
		String name;
		String tel;
		String guestsNumberString;
		String date;
		int id;
		id = rand.nextInt(99);			
		System.out.println("Please enter order name");
		name = scan.nextLine();
		System.out.println("Please enter order tel");
		tel = scan.nextLine();
		System.out.println("Please enter guests number");
		guestsNumberString =scan.nextLine();
		int guestsNumber = parseInt(guestsNumberString);
		System.out.println("Please enter order date in format:yyyy.mm.dd");
		date = scan.nextLine();
		int year = getdate(date.substring(0,4));
		int month = getdate(date.substring(5,7));
		int day = getdate(date.substring(8, 10));
		LocalDate dateOfOrder = LocalDate.of(year, month, day);
		TIMEOFDAY timeOfDay = chooseHour();
		timeOfOrder = new TimeOfOrder(timeOfDay , dateOfOrder);
		order = new Order(id , name , tel , timeOfOrder ,guestsNumber );		
	    logger.debug("order is {}",order);
		Resturant rest = new ResturantImpl(50,"mamaia");
		rest.init();
		rest.addOrder(order, timeOfOrder);

	}



	private int parseInt(String guestsNumberString) {
		int number=0;
		try{
			number = Integer.parseInt(guestsNumberString);
			}
		catch(NumberFormatException e){
			System.out.println(e.getMessage());
		}
		return number;
	}



	private TIMEOFDAY chooseHour() {
		System.out.println("for 12 enter 1");
		System.out.println("for 14 enter 2");
		System.out.println("for 16 enter 3");
		System.out.println("for 18 enter 4");
		System.out.println("for 20 enter 5");
		System.out.println("for 22 enter 6");
		int choose = scan.nextInt();
		switch(choose){
		case 1:
			return TIMEOFDAY.TWELF;
		case 2:
			return TIMEOFDAY.FOURTEEN;
		case 3:
			return TIMEOFDAY.SIXTEEN;
		case 4:
			return TIMEOFDAY.EIHGTEEN;
		case 5:
			return TIMEOFDAY.TWENTY;
		case 6:
			return TIMEOFDAY.TWENTYTOW;
			
			
		}
		return TIMEOFDAY.NONE;
	}



	private int getdate(String datePart) {
		int date=0;
		try{
			date = Integer.parseInt(datePart);
		}
		catch(NumberFormatException e){
			
			
		}
		return date;
	}
	

}
