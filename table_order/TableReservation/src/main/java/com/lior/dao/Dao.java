package com.lior.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
	private final static String getTimeOfOrder = "select id  from tod where order_time = ? and order_date =? ";
	private final static String deleteTimeOfOrder = "delete from tod where id =?";
	private final static String createTable = "insert into table_in_resturant(table_number,size,resturant)  values(?,?,?)";
	private final static String updateTable = "update table_in_resturant set order_time = ? where id = ? ";
	private final static String getResturantId = "select id from resturant where name = ?";
	private final static String getTableIdbyOrderParams = "select id, table_number,size from table_in_resturant where size = ? and resturant = ? and (order_time <> ? or order_time is null)";
	private final static String deleteTable = "delete from table_in_resturant where id =? ";
	private final static String createOrder = "insert into orders (order_time,people_number,order_name ,telephone_number) values (?,?,?,?) ";
	private final static String getOrder = "select id,order_time from orders where order_name = ? and telephone_number = ?";
	private final static String deleteOrder = "delete from orders where id =? ";
	private final static String getallFreeTablesInResturantInTF = "select * from table_in_resturant where resturant = ? and order_time<> ? ";
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
			updateTable(order) ;
			
		}
		return status;
	}
	
	private void updateTable(Order order) throws SQLException {
		Table choosen;	
		List<Table> tables = getTableByOrderParams(order);
		if(tables.isEmpty()){
			logger.debug("There is no free table in resturan {} in time {} and date {}",order.getRest().getName(),order.getTimeOfOrder().getTimeOfDay(),order.getTimeOfOrder().getDate());	
			return;
		}
		choosen = tables.get(0);
			
		int status = 0;
		Connection con = getCon();
		try (PreparedStatement ps = con.prepareStatement(updateTable)) {
			int todToDB =getTod(order.getTimeOfOrder());
			if(todToDB ==0){
				createTod(order.getTimeOfOrder());
				todToDB =getTod(order.getTimeOfOrder());
			}
			ps.setInt(1, todToDB);
			ps.setInt(2, choosen.getId());
			
		}
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
			ps.setInt(1, table.getTableNumber());			
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
	
	public List<Table> getTableByOrderParams(Order order) throws SQLException {
		List<Table> tables = new ArrayList<>();
		Connection con = getCon();
		try (PreparedStatement ps = con.prepareStatement(getTableIdbyOrderParams)) {
			
			ps.setInt(1, order.getGuestsNumber());
			ps.setInt(2, getResturantByName(order.getRest().getName()));
			ps.setInt(3, getTod(order.getTimeOfOrder()));
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
//				TimeOfOrder timeOfOrder = new TimeOfOrder(rs.getInt(3), rs.getDate(4).toLocalDate());
			tables.add(new Table(rs.getInt(1),rs.getInt(2),rs.getInt(3)));
			}
		}

		return tables;
		
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
	
//	public int deleteTable(Table table) throws SQLException {
//		int status = 0;
//		Connection con = getCon();
//		try (PreparedStatement ps = con.prepareStatement(deleteTable)) {
//			int tableID = getTable(table);
//			ps.setInt(1, tableID);
//			status = ps.executeUpdate();
//			
//		}
//		return status;
//	}
	
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
