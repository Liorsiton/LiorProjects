package tests;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

import com.lior.order.Order;
import com.lior.order.TIMEOFDAY;
import com.lior.order.TimeOfOrder;
import com.lior.resturant.Resturant;
import com.lior.resturant.ResturantImpl;

public class Tests {

	@Test
	public void testAddOrderPositive() {
		LocalDate first =  LocalDate.of(2018,6,17);
		TimeOfOrder too = new TimeOfOrder();
		assertTrue(too.setOrder(TIMEOFDAY.TWELF, first));
		Order order = new Order(1,"Lior","05421811610",too,4 , new ResturantImpl("mamaia"));
		assertEquals("Lior",order.getName());
		assertEquals("05421811610",order.getTelNumber());
		assertEquals(too,order.getTimeOfOrder());
		assertEquals(4,order.getGuestsNumber());
		
		
	}
	
	
	@Test
	public void testAddOrderAndIsOcupiedNegativeSameDateDiffHour() {
		LocalDate first =  LocalDate.of(2018,6,17);		
		TimeOfOrder too = new TimeOfOrder();
		assertTrue(too.setOrder(TIMEOFDAY.FOURTEEN, first));
		assertFalse(too.isFree(TIMEOFDAY.TWELF, first));
		assertTrue(too.setOrder(TIMEOFDAY.TWELF, first));
	}
	
	
	@Test
	public void testAddOrderAndIsOcupiedNegativeSameHourDiffDate() {
		LocalDate first =  LocalDate.of(2018,6,17);	
		LocalDate second =  LocalDate.of(2018,6,18);
		TimeOfOrder too = new TimeOfOrder();
		too.setOrder(TIMEOFDAY.FOURTEEN, first);
		assertFalse(too.isFree(TIMEOFDAY.FOURTEEN, second));
	}
	
	@Test
	public void testRemoveOrderThatExists() {
		LocalDate first =  LocalDate.of(2018,6,17);
		TimeOfOrder too = new TimeOfOrder();
		too.setOrder(TIMEOFDAY.TWELF, first);
		assertTrue(too.isFree(TIMEOFDAY.TWELF, first));
		too.removeOrder(TIMEOFDAY.TWELF, first);
		assertFalse(too.isFree(TIMEOFDAY.TWELF, first));
	}
	
	@Test
	public void testOrderATableInAResturantCaseTableIsFree(){
		Resturant mamaia = new ResturantImpl(50 ,"mamaia");
		LocalDate date = LocalDate.of(2018, 7, 15);
		TimeOfOrder timeOfOrder = new TimeOfOrder(TIMEOFDAY.TWELF ,date);
		Order order = new Order(101,"Hana Rozen" , "0542134567", timeOfOrder , 4 , new ResturantImpl("mamaia"));
		assertTrue(mamaia.addOrder(order, timeOfOrder));		
	}
	
	@Test
	public void testOrderATableInAResturantCaseTableIsNotFree(){
		Resturant mamaia = new ResturantImpl(50 ,"mamaia");
		LocalDate date = LocalDate.of(2018, 7, 15);
		TimeOfOrder timeOfOrder = new TimeOfOrder(TIMEOFDAY.TWELF ,date);
		Order order1 = new Order(101,"Hana Rozen" , "0542134567", timeOfOrder , 4 , new ResturantImpl("mamaia"));
		Order order2 = new Order(102,"Yorm Gaon" , "0542134547", timeOfOrder , 4 , new ResturantImpl("mamaia"));
		assertTrue(mamaia.addOrder(order1, timeOfOrder));
		assertFalse(mamaia.addOrder(order2, timeOfOrder));	
	}
	
	@Test
	public void testOrderATableInAResturantCaseSameTableDifferentHours(){
		Resturant mamaia = new ResturantImpl(50 , "mamaia");
		LocalDate date = LocalDate.of(2018, 7, 15);
		TimeOfOrder timeOfOrder1 = new TimeOfOrder(TIMEOFDAY.TWELF ,date);
		TimeOfOrder timeOfOrder2 = new TimeOfOrder(TIMEOFDAY.FOURTEEN ,date);
		Order order1 = new Order(101,"Hana Rozen" , "0542134567", timeOfOrder1 , 4 , new ResturantImpl("mamaia"));
		Order order2 = new Order(102,"Yorm Gaon" , "0542134547", timeOfOrder2 , 4 , new ResturantImpl("mamaia"));
		assertTrue(mamaia.addOrder(order1, timeOfOrder1));
		assertTrue(mamaia.addOrder(order2, timeOfOrder2));	
	}
	
	@Test
	public void testOrderATableInAResturantCaseSameTableDifferentDays(){
		Resturant mamaia = new ResturantImpl(50 , "mamaia");
		LocalDate date1 = LocalDate.of(2018, 7, 15);
		LocalDate date2 = LocalDate.of(2018, 7, 16);
		TimeOfOrder timeOfOrder1 = new TimeOfOrder(TIMEOFDAY.TWELF ,date1);
		TimeOfOrder timeOfOrder2 = new TimeOfOrder(TIMEOFDAY.TWELF ,date2);
		Order order1 = new Order(101,"Hana Rozen" , "0542134567", timeOfOrder1 , 4 ,new ResturantImpl("mamaia"));
		Order order2 = new Order(102,"Yorm Gaon" , "0542134547", timeOfOrder2 , 4 ,new ResturantImpl("mamaia"));
		assertTrue(mamaia.addOrder(order1, timeOfOrder1));
		assertTrue(mamaia.addOrder(order2, timeOfOrder2));	
	}
	
	
	
	

}
