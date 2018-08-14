package tests;

import static org.junit.Assert.*;


import java.sql.SQLException;
import java.time.LocalDate;

import org.junit.BeforeClass;
import org.junit.Test;

import com.lior.dao.Dao;
import com.lior.order.Order;
import com.lior.order.TIMEOFDAY;
import com.lior.order.TimeOfOrder;
import com.lior.resturant.ResturantImpl;
import com.lior.table.Table;

public class DaoTests {
	
	Dao dao = new Dao();


	@Test
	public void testCreateGetAndDeleteResturrant() throws SQLException {
		ResturantImpl testRest = new ResturantImpl("testRest");
		assertEquals(1 ,dao.createResturant(testRest));
       // System.out.println(dao.createResturant(testRest));
        //test with select
        int result = dao.getResturantByName("testRest");
       // System.out.println(result);
        assertNotEquals(0 , result);
        //delete from db
        dao.deleteResturant(testRest);	
        result = dao.getResturantByName("testRest");
        assertEquals(0 , result);   
	
	}
	
	@Test
	public void testCreateGetAndDeleteTable() throws SQLException {
		ResturantImpl testRest = new ResturantImpl("testRest");
		assertEquals(1 ,dao.createResturant(testRest));
		Table table = new Table(2 , 4 );
		table.setRest(testRest);
		assertEquals(1 ,dao.createTableInResturant(table));
       // System.out.println(dao.createResturant(testRest));
        //test with select
        int result = dao.getTable(table);
       // System.out.println(result);
        assertNotEquals(0 , result);
        //delete from db
        dao.deleteTable(table);	
        result = dao.getTable(table);
        assertEquals(0 , result);   
	
	}
	
	@Test
	public void testCreateGetAndDeleteTimeOfOrder() throws SQLException {
		TimeOfOrder timeOfOrder = new TimeOfOrder(TIMEOFDAY.FOURTEEN ,  LocalDate.of(2018,8,15));
		assertEquals(1 ,dao.createTod(timeOfOrder));	
       // System.out.println(dao.createResturant(testRest));
        //test with select
        int result = dao.getTod(timeOfOrder);
       // System.out.println(result);
        assertNotEquals(0 , result);
        //delete from db
        dao.deleteTod(timeOfOrder);
        result = dao.getTod(timeOfOrder);
        assertEquals(0 , result);   
	
	}
	
	@Test
	public void testCreateGetAndDeleteOrder() throws SQLException {
		TimeOfOrder timeOfOrder = new TimeOfOrder(TIMEOFDAY.SIXTEEN ,LocalDate.of(2018,8,16));
		Order order = new Order(123,"Lior","0503457657",timeOfOrder,4);
		assertEquals(1 ,dao.createOrder(order));
       // System.out.println(dao.createResturant(testRest));
        //test with select
        int result = dao.getOrder(order);
       // System.out.println(result);
        assertNotEquals(0 , result);
        //delete from db
        dao.deleteOrder(order);
        result = dao.getOrder(order);
        assertEquals(0 , result);   
	
	}

}
