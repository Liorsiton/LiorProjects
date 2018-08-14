package com.lior.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.lior.order.Order;
import com.lior.order.TIMEOFDAY;
import com.lior.order.TimeOfOrder;
import com.lior.resturant.Resturant;
import com.lior.resturant.ResturantImpl;
import com.lior.table.Table;

public class Dao {
	
	private static Logger logger = LogManager.getLogger(Dao.class);
	private final static String mySQLdriver = "com.mysql.jdbc.Driver";
	private final static String pathToDb = "jdbc:mysql://localhost:3306/tableResrvation";
	private final static String user = "root";
	private final static String password = "Hili2604";
	
	private final static String createResturant = "insert into resturant (name) values(?)";
	private final static String deleteResturnt = "delete from resturant where id =? ";
	private final static String createTimeOfOrder = "insert into tod(order_time,order_date) values(?,?)";
	private final static String getTimeOfOrder = "select id from tod where order_time =? and order_date =?";
	private final static String deleteTimeOfOrder = "delete from tod where id =?";
	private final static String createTable = "insert into table_in_resturant (id,size,resturant)  values(?,?,?)";
	private final static String getResturantId = "select id from resturant where name = ?";
	private final static String getTableId = "select id from table_in_resturant where id = ?";
	private final static String deleteTable = "delete from table_in_resturant where id =? ";
	private final static String createOrder = "insert into orders (order_time,people_number,order_name ,telephone_number) values (?,?,?,?) ";
	private final static String getOrder = "select id from orders where order_name = ? and telephone_number = ?";
	private final static String deleteOrder = "delete from orders where id =? ";

	public Connection getCon() {
		Connection con = null;
		try {
			Class.forName(mySQLdriver);
			con = DriverManager.getConnection(pathToDb, user, password);
		} catch (Exception e) {
			System.out.println(e);
		}
		return con;
	}
	
	
	public int createResturant(ResturantImpl rest) throws SQLException {
		int status = 0;
		Connection con = getCon();
		try (PreparedStatement ps = con.prepareStatement(createResturant)) {
			ps.setString(1, rest.getName());
			status = ps.executeUpdate();
			
		}
		return status;
	}
	
	public int createTod(TimeOfOrder tod) throws SQLException {
		int status = 0;
		Connection con = getCon();
		try (PreparedStatement ps = con.prepareStatement(createTimeOfOrder)) {
			int todToDB = getTod(tod.getTimeOfDay());
			ps.setInt(1, todToDB);
			Date date =Date.valueOf(tod.getDate());
			ps.setDate(2, date);
			status = ps.executeUpdate();
			
		}
		return status;
	}
	
	
	
	
	public int createOrder(Order order) throws SQLException {
		int status = 0;
		Connection con = getCon();
		try (PreparedStatement ps = con.prepareStatement(createOrder)) {
			int todToDB =getTod(order.getTimeOfOrder());
			if(todToDB ==0){
				createTod(order.getTimeOfOrder());
				todToDB =getTod(order.getTimeOfOrder());
			}
			ps.setInt(1, todToDB);
			ps.setInt(2, order.getGuestsNumber());
			ps.setString(3, order.getName());
			ps.setString(4, order.getTelNumber());
			status = ps.executeUpdate();
			
		}
		return status;
	}
	
	public int getOrder(Order order) throws SQLException {
		int id = 0;
		Connection con = getCon();
		try (PreparedStatement ps = con.prepareStatement(getOrder)) {
			ps.setString(1, order.getName());
			ps.setString(2, order.getTelNumber());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				id = rs.getInt(1);
			}
		}

		return id;
		
	}
	
	
	public int deleteOrder(Order order) throws SQLException {
		int status = 0;
		Connection con = getCon();
		try (PreparedStatement ps = con.prepareStatement(deleteOrder)) {
			int orderId = getOrder(order);
			logger.debug("order id : {}" , orderId);
			ps.setInt(1, orderId);
			status = ps.executeUpdate();
			
		}
		return status;
	}
	
	
	public int createTableInResturant(Table table) throws SQLException {
		int status = 0;
		Connection con = getCon();
		try (PreparedStatement ps = con.prepareStatement(createTable)) {			
			ps.setInt(1, table.getId());			
			ps.setInt(2, table.getSize());
			int restID = getResturantByName(table.getRest().getName());
			if(restID != 0){
				ps.setInt(3, restID);
				status = ps.executeUpdate();
			}
			else 
			{
				logger.debug("There is no resturant with id {}" , restID);
				
			}			
			
		}
		return status;
	}
	
	
	public int getResturantByName(String Name) throws SQLException {
		int id = 0;
		Connection con = getCon();
		try (PreparedStatement ps = con.prepareStatement(getResturantId)) {
			ps.setString(1, Name);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				id = rs.getInt(1);
			}
		}

		return id;
		
	}
	
	public int getTable(Table table) throws SQLException {
		int id = 0;
		Connection con = getCon();
		try (PreparedStatement ps = con.prepareStatement(getTableId)) {
			ps.setInt(1, table.getId());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				id = rs.getInt(1);
			}
		}

		return id;
		
	}
	
	
	public int getTod(TimeOfOrder timeOfOrder) throws SQLException {
		int id = 0;
		Connection con = getCon();
		try (PreparedStatement ps = con.prepareStatement(getTimeOfOrder)) {
			int todToDB = getTod(timeOfOrder.getTimeOfDay());
			Date date =Date.valueOf(timeOfOrder.getDate());
			ps.setInt(1,  todToDB);
			ps.setDate(2, date);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				id = rs.getInt(1);
			}
		}

		return id;
		
	}
	
	public int deleteResturant(ResturantImpl rest) throws SQLException {
		int status = 0;  
		Connection con = getCon();
		try (PreparedStatement ps = con.prepareStatement(deleteResturnt)) {
			int restId = getResturantByName(rest.getName());
			ps.setInt(1, restId);
			status = ps.executeUpdate();
			
		}
		return status;
	}
	
	public int deleteTable(Table table) throws SQLException {
		int status = 0;
		Connection con = getCon();
		try (PreparedStatement ps = con.prepareStatement(deleteTable)) {
			int tableID = getTable(table);
			ps.setInt(1, tableID);
			status = ps.executeUpdate();
			
		}
		return status;
	}
	
	public int deleteTod(TimeOfOrder timeOfOrder) throws SQLException {
		int status = 0;
		Connection con = getCon();
		try (PreparedStatement ps = con.prepareStatement(deleteTimeOfOrder)) {
			int todid = getTod(timeOfOrder);
			ps.setInt(1, todid);
			status = ps.executeUpdate();
			
		}
		return status;
	}




	private int getTod(TIMEOFDAY timeOfDay) {
	    return timeOfDay.getTime();
	}
}
