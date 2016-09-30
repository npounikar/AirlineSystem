package controller;
import data.*;

import java.sql.*;
import java.util.ArrayList;

import org.apache.tomcat.dbcp.dbcp.DbcpException;

import data.FlightDetailsBean;


public class DatabaseController {

	private static Connection conn;

	/* -------------------------------------------------------------------------------------------------------------------------- */

     private static Connection getDBConnection() {
    	 
    	 java.sql.Connection connection = null;
    	 try {
 	    	
	    	 	String url = "jdbc:mysql://localhost/test";
	    	    String username = "root"; 
	    	    String password = "liquid";
	    	    
	    	    Class.forName("com.mysql.jdbc.Driver");
	    	    connection = DriverManager.getConnection(url,username,password);
	    	    
    	 	} catch (Exception e) {
    	 		e.printStackTrace();
    	 	}
    	 
    	return connection;
	}

     /* -------------------------------------------------------------------------------------------------------------------------- */

     ArrayList<FlightDetailsBean> getFlightDetails(String from, String to) throws SQLException {
    	
    	ArrayList<FlightDetailsBean> flights = new ArrayList<FlightDetailsBean>();
    	FlightDetailsBean fd;
    	try {
	    	
    			 conn = getDBConnection();
    
    			 String sql = "select flight_number,weekdays from airlines.flight where departure_airport_code=? and Arrival_airport_code=?";
    	         PreparedStatement statement = conn.prepareStatement(sql);
    	         statement.setString(1, from);
    	         statement.setString(2, to);
    	         
    	         // Executing the prepared Statement
    	         ResultSet results =  statement.executeQuery();
    		  
    			while(results.next()) {
    				fd = new FlightDetailsBean(results.getInt(1),results.getString(2));
    				flights.add(fd);
    			}
	         
    		
    			
    		} catch (Exception e) {
    				e.printStackTrace();
    		}
    	
    	return flights;
    }
     
     
     /* -------------------------------------------------------------------------------------------------------------------------- */

	public ArrayList<FareBean> getFareDetails(int fno) {
		
		ArrayList<FareBean> fares = new ArrayList<FareBean>();
		FareBean fb;
    	try {
	    	
    			 conn = getDBConnection();
    
    			 String sql = "select * from airlines.fare where flight_number=?";
    	         PreparedStatement statement = conn.prepareStatement(sql);
    	         statement.setInt(1, fno);
    	         
    	         
    	         // Executing the prepared Statement
    	         ResultSet results =  statement.executeQuery();
    		  
    			while(results.next()) {
    				fb = new FareBean(results.getInt(1),results.getString(2),results.getInt(3),results.getString(4));
    				fares.add(fb);
    			}
	         
    			
    		} catch (Exception e) {
    				e.printStackTrace();
    		}
    	
    	return fares;
	}

	/* -------------------------------------------------------------------------------------------------------------------------- */
	
	public ArrayList<AvailabilityBean> getAvailability(int fno, String date) {
		
		ArrayList<AvailabilityBean> av = new ArrayList<AvailabilityBean>();
		AvailabilityBean ab;
    	try {
	    	
    			 conn = getDBConnection();
    
    			 String sql = "select ((select total_number_of_seats from airlines.airplane a, airlines.flight_instance f where a.airplane_id = f.airplane_id and f.flight_number ="+fno+" and f.date='"+date+"') - (select count(*) from airlines.seat_reservation s where s.flight_number = "+fno+" and s.date='"+date+"'));";
    	         PreparedStatement statement = conn.prepareStatement(sql);
    	         
    	         // Executing the prepared Statement
    	         ResultSet results =  statement.executeQuery();
    	         
    	         while(results.next()) {
    	        	 
    				ab = new AvailabilityBean(fno, date,results.getInt(1));
    				av.add(ab);
    	         }
    	         
    			
    		} catch (Exception e) {
    				e.printStackTrace();
    		}
    	
    	return av;
		
		
	}

	
	/* -------------------------------------------------------------------------------------------------------------------------- */
	
	public ArrayList<PassengersBean> getPassengersList(int fno, String date) {
		
		ArrayList<PassengersBean> pblist = new ArrayList<PassengersBean>();
		PassengersBean pobj;
    	try {
	    	
    			 conn = getDBConnection();
    
    			 String sql = "select seat_number,customer_name,customer_phone from airlines.seat_reservation where flight_number="+fno+" and date='"+date+"';";
    	         PreparedStatement statement = conn.prepareStatement(sql);
    	         
    	         // Executing the prepared Statement
    	         ResultSet results =  statement.executeQuery();
    	         
    	         while(results.next()) {
    	        	 
    				pobj = new PassengersBean(results.getString(2),results.getString(3),results.getString(1));
    				pblist.add(pobj);
    	         }
    	         
    			
    		} catch (Exception e) {
    				e.printStackTrace();
    		}
    	
    	return pblist;
		
	}

	/* -------------------------------------------------------------------------------------------------------------------------- */
	
	public ArrayList<PassengerInstanceBean> getPassengersInstance(String name) {
		
		ArrayList<PassengerInstanceBean> pblist = new ArrayList<PassengerInstanceBean>();
		PassengerInstanceBean pobj;
    	try {
	    	
    			 conn = getDBConnection();
    
    			 String sql = "select s.flight_number,s.date, s.seat_number, f.departure_airport_code, f.arrival_airport_code,f.scheduled_departure_time, f.scheduled_arrival_time from airlines.flight f, airlines.seat_reservation s where f.flight_number = s.flight_number and s.customer_name =?";
    	         PreparedStatement statement = conn.prepareStatement(sql);
    	         statement.setString(1, name);
    	         
    	         // Executing the prepared Statement
    	         ResultSet results =  statement.executeQuery();
    	         
    	         while(results.next()) {
    	        	 
    				pobj = new PassengerInstanceBean(results.getInt(1),results.getString(2),results.getString(3),results.getString(4),results.getString(5),results.getString(6),results.getString(7));
    				pblist.add(pobj);
    	         }
    	         
    			
    		} catch (Exception e) {
    				e.printStackTrace();
    		}
    	
    	return pblist;
		
	}

	/* -------------------------------------------------------------------------------------------------------------------------- */
	
	public ArrayList<FlightOnBoardBean> getFlightOnBoardList(int fno) {
		
		ArrayList<FlightOnBoardBean> f = new ArrayList<FlightOnBoardBean>();
		FlightOnBoardBean fb;
    	try {
	    	
    			 conn = getDBConnection();
    
    			 String sql = "select departure_airport_code, arrival_airport_code,scheduled_departure_time, scheduled_arrival_time from airlines.flight where flight_number="+fno+";";
    	         PreparedStatement statement = conn.prepareStatement(sql);
    	      
    	         
    	         // Executing the prepared Statement
    	         ResultSet results =  statement.executeQuery();
    		  
    			while(results.next()) {
    				fb = new FlightOnBoardBean(results.getString(1),results.getString(2),results.getString(3),results.getString(4));
    				f.add(fb);
    			}
	         
    			
    		} catch (Exception e) {
    				e.printStackTrace();
    		}
    	
    	return f;
		
	}
	
	
	/* -------------------------------------------------------------------------------------------------------------------------- */

	public ArrayList<ConnectingBean> getDirectFlight(String from, String to) {
		
		ArrayList<ConnectingBean> direct = new ArrayList<ConnectingBean>(); 
		ConnectingBean cb;
		
		try {
	    	
			 conn = getDBConnection();

			 String sql = "select f1.flight_number, f1.departure_airport_code,f1.arrival_airport_code, f1.scheduled_departure_time, f1.scheduled_arrival_time, f1.weekdays from "
			 		+ "airlines.flight f1 where f1.departure_airport_code = '"+from+"' AND f1.arrival_airport_code='"+to+"';";
	         PreparedStatement statement = conn.prepareStatement(sql);
	      
	         
	         // Executing the prepared Statement
	         ResultSet results =  statement.executeQuery();
		  
			while(results.next()) {
				cb = new ConnectingBean(results.getInt(1),results.getString(2),results.getString(3),results.getString(4),results.getString(5),results.getString(6));
				direct.add(cb);
			}
        
			
		} catch (Exception e) {
				e.printStackTrace();
		}
		
		
		return direct;
	}

	/* -------------------------------------------------------------------------------------------------------------------------- */
	
	public ArrayList<Connecting1Bean> getOneConnectingFlight(String from,String to) {
		
		ArrayList<Connecting1Bean> connect1 = new ArrayList<Connecting1Bean>(); 
		Connecting1Bean cb1;
		
		try {
	    	
			 conn = getDBConnection();
			 
			 
			 // Monday
			 String sql1 = "select f1.flight_number, f1.departure_airport_code,f1.arrival_airport_code, f1.scheduled_departure_time, f1.scheduled_arrival_time," 
					 + "f2.flight_number, f2.departure_airport_code, f2.arrival_airport_code, f2.scheduled_departure_time, f2.scheduled_arrival_time from "
					 + "airlines.flight f1, airlines.flight f2 where f1.departure_airport_code = '"+from+"' AND f1.arrival_airport_code = f2.departure_airport_code AND "
					 + "f2.arrival_airport_code='"+to+"' AND f2.scheduled_departure_time > f1.scheduled_arrival_time AND  "
					 		+ "((timediff(f2.Scheduled_departure_time,f1.Scheduled_arrival_time) >= '01:00:00')) AND ((f1.weekdays like '%Mon%' AND f2.weekdays like '%Mon%'));";
	         PreparedStatement statement1 = conn.prepareStatement(sql1);
	      
	         
	         // Executing the prepared Statement
	         ResultSet results1 =  statement1.executeQuery();
		  
			while(results1.next()) {
				cb1 = new Connecting1Bean(results1.getInt(1),results1.getString(2),results1.getString(3),results1.getString(4),results1.getString(5),
						results1.getInt(6),results1.getString(7),results1.getString(8),results1.getString(9),results1.getString(10),"Monday");
				connect1.add(cb1);
			}
			
			
			
			// Tuesday
			String sql2 = "select f1.flight_number, f1.departure_airport_code,f1.arrival_airport_code, f1.scheduled_departure_time, f1.scheduled_arrival_time," 
					 + "f2.flight_number, f2.departure_airport_code, f2.arrival_airport_code, f2.scheduled_departure_time, f2.scheduled_arrival_time from "
					 + "airlines.flight f1, airlines.flight f2 where f1.departure_airport_code = '"+from+"' AND f1.arrival_airport_code = f2.departure_airport_code AND "
					 + "f2.arrival_airport_code='"+to+"' AND f2.scheduled_departure_time > f1.scheduled_arrival_time AND "
					 		+ "((timediff(f2.Scheduled_departure_time,f1.Scheduled_arrival_time) >= '01:00:00')) AND ((f1.weekdays like '%Tue%' AND f2.weekdays like '%Tue%'));";
	         PreparedStatement statement2 = conn.prepareStatement(sql2);
	      
	         
	         // Executing the prepared Statement
	         ResultSet results2 =  statement2.executeQuery();
		  
			while(results2.next()) {
				cb1 = new Connecting1Bean(results2.getInt(1),results2.getString(2),results2.getString(3),results2.getString(4),results2.getString(5),
						results2.getInt(6),results2.getString(7),results2.getString(8),results2.getString(9),results2.getString(10),"Tuesday");
				connect1.add(cb1);
			}
			

			// Wednesday
			String sql3 = "select f1.flight_number, f1.departure_airport_code,f1.arrival_airport_code, f1.scheduled_departure_time, f1.scheduled_arrival_time," 
					+ "f2.flight_number, f2.departure_airport_code, f2.arrival_airport_code, f2.scheduled_departure_time, f2.scheduled_arrival_time from "
					+ "airlines.flight f1, airlines.flight f2 where f1.departure_airport_code = '"+from+"' AND f1.arrival_airport_code = f2.departure_airport_code AND "
					+ "f2.arrival_airport_code='"+to+"' AND f2.scheduled_departure_time > f1.scheduled_arrival_time AND "
							+ "((timediff(f2.Scheduled_departure_time,f1.Scheduled_arrival_time) >= '01:00:00')) AND ((f1.weekdays like '%Wed%' AND f2.weekdays like '%Wed%'));";
			PreparedStatement statement3 = conn.prepareStatement(sql3);


			// Executing the prepared Statement
			ResultSet results3 =  statement3.executeQuery();

			while(results3.next()) {
				cb1 = new Connecting1Bean(results3.getInt(1),results3.getString(2),results3.getString(3),results3.getString(4),results3.getString(5),
						results3.getInt(6),results3.getString(7),results3.getString(8),results3.getString(9),results3.getString(10),"Wednesday");
				connect1.add(cb1);
			}			
			
			
			// Thursday
			String sql4 = "select f1.flight_number, f1.departure_airport_code,f1.arrival_airport_code, f1.scheduled_departure_time, f1.scheduled_arrival_time," 
					+ "f2.flight_number, f2.departure_airport_code, f2.arrival_airport_code, f2.scheduled_departure_time, f2.scheduled_arrival_time from "
					+ "airlines.flight f1, airlines.flight f2 where f1.departure_airport_code = '"+from+"' AND f1.arrival_airport_code = f2.departure_airport_code AND "
					+ "f2.arrival_airport_code='"+to+"' AND f2.scheduled_departure_time > f1.scheduled_arrival_time AND "
							+ "((timediff(f2.Scheduled_departure_time,f1.Scheduled_arrival_time) >= '01:00:00')) AND ((f1.weekdays like '%Thu%' AND f2.weekdays like '%Thu%'));";
			PreparedStatement statement4 = conn.prepareStatement(sql4);


			// Executing the prepared Statement
			ResultSet results4 =  statement4.executeQuery();

			while(results4.next()) {
				cb1 = new Connecting1Bean(results4.getInt(1),results4.getString(2),results4.getString(3),results4.getString(4),results4.getString(5),
						results4.getInt(6),results4.getString(7),results4.getString(8),results4.getString(9),results4.getString(10),"Thursday");
				connect1.add(cb1);
			}	


			// Friday
			String sql5 = "select f1.flight_number, f1.departure_airport_code,f1.arrival_airport_code, f1.scheduled_departure_time, f1.scheduled_arrival_time," 
					+ "f2.flight_number, f2.departure_airport_code, f2.arrival_airport_code, f2.scheduled_departure_time, f2.scheduled_arrival_time from "
					+ "airlines.flight f1, airlines.flight f2 where f1.departure_airport_code = '"+from+"' AND f1.arrival_airport_code = f2.departure_airport_code AND "
					+ "f2.arrival_airport_code='"+to+"' AND f2.scheduled_departure_time > f1.scheduled_arrival_time AND "
							+ "((timediff(f2.Scheduled_departure_time,f1.Scheduled_arrival_time) >= '01:00:00')) AND ((f1.weekdays like '%Fri%' AND f2.weekdays like '%Fri%'));";
			PreparedStatement statement5 = conn.prepareStatement(sql5);


			// Executing the prepared Statement
			ResultSet results5 =  statement5.executeQuery();

			while(results5.next()) {
				cb1 = new Connecting1Bean(results5.getInt(1),results5.getString(2),results5.getString(3),results5.getString(4),results5.getString(5),
						results5.getInt(6),results5.getString(7),results5.getString(8),results5.getString(9),results5.getString(10),"Friday");
				connect1.add(cb1);
			}	


			// Saturday
			String sql6 = "select f1.flight_number, f1.departure_airport_code,f1.arrival_airport_code, f1.scheduled_departure_time, f1.scheduled_arrival_time," 
					+ "f2.flight_number, f2.departure_airport_code, f2.arrival_airport_code, f2.scheduled_departure_time, f2.scheduled_arrival_time from "
					+ "airlines.flight f1, airlines.flight f2 where f1.departure_airport_code = '"+from+"' AND f1.arrival_airport_code = f2.departure_airport_code AND "
					+ "f2.arrival_airport_code='"+to+"' AND f2.scheduled_departure_time > f1.scheduled_arrival_time AND "
							+ "((timediff(f2.Scheduled_departure_time,f1.Scheduled_arrival_time) >= '01:00:00')) AND ((f1.weekdays like '%Sat%' AND f2.weekdays like '%Sat%'));";
			PreparedStatement statement6 = conn.prepareStatement(sql6);


			// Executing the prepared Statement
			ResultSet results6 =  statement6.executeQuery();

			while(results6.next()) {
				cb1 = new Connecting1Bean(results6.getInt(1),results6.getString(2),results6.getString(3),results6.getString(4),results6.getString(5),
						results6.getInt(6),results6.getString(7),results6.getString(8),results6.getString(9),results6.getString(10),"Saturday");
				connect1.add(cb1);
			}


			// Sunday
			String sql7 = "select f1.flight_number, f1.departure_airport_code,f1.arrival_airport_code, f1.scheduled_departure_time, f1.scheduled_arrival_time," 
					+ "f2.flight_number, f2.departure_airport_code, f2.arrival_airport_code, f2.scheduled_departure_time, f2.scheduled_arrival_time from "
					+ "airlines.flight f1, airlines.flight f2 where f1.departure_airport_code = '"+from+"' AND f1.arrival_airport_code = f2.departure_airport_code AND "
					+ "f2.arrival_airport_code='"+to+"' AND f2.scheduled_departure_time > f1.scheduled_arrival_time AND "
							+ "((timediff(f2.Scheduled_departure_time,f1.Scheduled_arrival_time) >= '01:00:00')) AND ((f1.weekdays like '%Sun%' AND f2.weekdays like '%Sun%'));";
			PreparedStatement statement7 = conn.prepareStatement(sql7);


			// Executing the prepared Statement
			ResultSet results7 =  statement7.executeQuery();

			while(results7.next()) {
				cb1 = new Connecting1Bean(results7.getInt(1),results7.getString(2),results7.getString(3),results7.getString(4),results7.getString(5),
						results7.getInt(6),results7.getString(7),results7.getString(8),results7.getString(9),results7.getString(10),"Sunday");
				connect1.add(cb1);
			}	
       
			
		} catch (Exception e) {
				e.printStackTrace();
		}
		
		return connect1;
	}

	/* -------------------------------------------------------------------------------------------------------------------------- */
	
	public ArrayList<Connecting2Bean> getTwoConnectingFlight(String from,String to) {
		
		ArrayList<Connecting2Bean> connect2 = new ArrayList<Connecting2Bean>(); 
		Connecting2Bean cb2;
		try {
	    	
			 conn = getDBConnection();
			 
			 
			 // Monday
			 String sql1 = "select f1.flight_number, f1.departure_airport_code,f1.arrival_airport_code, f1.scheduled_departure_time, f1.scheduled_arrival_time," 
					 		+"f2.flight_number, f2.departure_airport_code, f2.arrival_airport_code, f2.scheduled_departure_time, f2.scheduled_arrival_time,"
					 		+"f3.flight_number, f3.departure_airport_code, f3.arrival_airport_code, f3.scheduled_departure_time, f3.scheduled_arrival_time from "
					 		+"airlines.flight f1, airlines.flight f2, airlines.flight f3 where f1.departure_airport_code = '"+from+"' AND f1.arrival_airport_code = f2.departure_airport_code "
					 		+"AND f2.arrival_airport_code = f3.departure_airport_code  AND f3.arrival_airport_code='"+to+"' AND "
					 		+"f2.scheduled_departure_time > f1.scheduled_arrival_time AND f3.scheduled_departure_time > f2.scheduled_arrival_time AND "
					 		+ "((timediff(f2.Scheduled_departure_time,f1.Scheduled_arrival_time) >= '01:00:00') AND (timediff(f3.Scheduled_departure_time,f2.Scheduled_arrival_time) >= '01:00:00')) AND"
					 		+"((f1.weekdays like '%Mon%' AND f2.weekdays like '%Mon%' AND f3.weekdays like '%Mon%'));";
	         PreparedStatement statement1 = conn.prepareStatement(sql1);
	      
	         
	         // Executing the prepared Statement
	         ResultSet results1 =  statement1.executeQuery();
		  
			while(results1.next()) {
				cb2 = new Connecting2Bean(results1.getInt(1),results1.getString(2),results1.getString(3),results1.getString(4),results1.getString(5),
						results1.getInt(6),results1.getString(7),results1.getString(8),results1.getString(9),results1.getString(10),
						results1.getInt(11),results1.getString(12),results1.getString(13),results1.getString(14),results1.getString(15),"Monday");
				connect2.add(cb2);
			}
			
			
			
			// Tuesday
			String sql2 = "select f1.flight_number, f1.departure_airport_code,f1.arrival_airport_code, f1.scheduled_departure_time, f1.scheduled_arrival_time," 
			 		+"f2.flight_number, f2.departure_airport_code, f2.arrival_airport_code, f2.scheduled_departure_time, f2.scheduled_arrival_time,"
			 		+"f3.flight_number, f3.departure_airport_code, f3.arrival_airport_code, f3.scheduled_departure_time, f3.scheduled_arrival_time from "
			 		+"airlines.flight f1, airlines.flight f2, airlines.flight f3 where f1.departure_airport_code = '"+from+"' AND f1.arrival_airport_code = f2.departure_airport_code "
			 		+"AND f2.arrival_airport_code = f3.departure_airport_code  AND f3.arrival_airport_code='"+to+"' AND "
			 		+"f2.scheduled_departure_time > f1.scheduled_arrival_time AND f3.scheduled_departure_time > f2.scheduled_arrival_time AND "
			 		+ "((timediff(f2.Scheduled_departure_time,f1.Scheduled_arrival_time) >= '01:00:00') AND (timediff(f3.Scheduled_departure_time,f2.Scheduled_arrival_time) >= '01:00:00')) AND"
			 		+"((f1.weekdays like '%Tue%' AND f2.weekdays like '%Tue%' AND f3.weekdays like '%Tue%'));";
	         PreparedStatement statement2 = conn.prepareStatement(sql2);
	      
	         
	         // Executing the prepared Statement
	         ResultSet results2 =  statement2.executeQuery();
		  
			while(results2.next()) {
				cb2 = new Connecting2Bean(results2.getInt(1),results2.getString(2),results2.getString(3),results2.getString(4),results2.getString(5),
						results2.getInt(6),results2.getString(7),results2.getString(8),results2.getString(9),results2.getString(10),
						results2.getInt(11),results2.getString(12),results2.getString(13),results2.getString(14),results2.getString(15),"Tuesday");
				connect2.add(cb2);
			}
			

			// Wednesday
			String sql3 = "select f1.flight_number, f1.departure_airport_code,f1.arrival_airport_code, f1.scheduled_departure_time, f1.scheduled_arrival_time," 
			 		+"f2.flight_number, f2.departure_airport_code, f2.arrival_airport_code, f2.scheduled_departure_time, f2.scheduled_arrival_time,"
			 		+"f3.flight_number, f3.departure_airport_code, f3.arrival_airport_code, f3.scheduled_departure_time, f3.scheduled_arrival_time from "
			 		+"airlines.flight f1, airlines.flight f2, airlines.flight f3 where f1.departure_airport_code = '"+from+"' AND f1.arrival_airport_code = f2.departure_airport_code "
			 		+"AND f2.arrival_airport_code = f3.departure_airport_code  AND f3.arrival_airport_code='"+to+"' AND "
			 		+"f2.scheduled_departure_time > f1.scheduled_arrival_time AND f3.scheduled_departure_time > f2.scheduled_arrival_time AND "
			 		+ "((timediff(f2.Scheduled_departure_time,f1.Scheduled_arrival_time) >= '01:00:00') AND (timediff(f3.Scheduled_departure_time,f2.Scheduled_arrival_time) >= '01:00:00')) AND"
			 		+"((f1.weekdays like '%Wed%' AND f2.weekdays like '%Wed%' AND f3.weekdays like '%Wed%'));";
			PreparedStatement statement3 = conn.prepareStatement(sql3);


			// Executing the prepared Statement
			ResultSet results3 =  statement3.executeQuery();

			while(results3.next()) {
				cb2 = new Connecting2Bean(results3.getInt(1),results3.getString(2),results3.getString(3),results3.getString(4),results3.getString(5),
						results3.getInt(6),results3.getString(7),results3.getString(8),results3.getString(9),results3.getString(10),
						results3.getInt(11),results3.getString(12),results3.getString(13),results3.getString(14),results3.getString(15),"Wednesday");
				connect2.add(cb2);
			}			
			
			
			// Thursday
			String sql4 = "select f1.flight_number, f1.departure_airport_code,f1.arrival_airport_code, f1.scheduled_departure_time, f1.scheduled_arrival_time," 
			 		+"f2.flight_number, f2.departure_airport_code, f2.arrival_airport_code, f2.scheduled_departure_time, f2.scheduled_arrival_time,"
			 		+"f3.flight_number, f3.departure_airport_code, f3.arrival_airport_code, f3.scheduled_departure_time, f3.scheduled_arrival_time from "
			 		+"airlines.flight f1, airlines.flight f2, airlines.flight f3 where f1.departure_airport_code = '"+from+"' AND f1.arrival_airport_code = f2.departure_airport_code "
			 		+"AND f2.arrival_airport_code = f3.departure_airport_code  AND f3.arrival_airport_code='"+to+"' AND "
			 		+"f2.scheduled_departure_time > f1.scheduled_arrival_time AND f3.scheduled_departure_time > f2.scheduled_arrival_time AND "
			 		+ "((timediff(f2.Scheduled_departure_time,f1.Scheduled_arrival_time) >= '01:00:00') AND (timediff(f3.Scheduled_departure_time,f2.Scheduled_arrival_time) >= '01:00:00')) AND"
			 		+"((f1.weekdays like '%Thu%' AND f2.weekdays like '%Thu%' AND f3.weekdays like '%Thu%'));";
			PreparedStatement statement4 = conn.prepareStatement(sql4);


			// Executing the prepared Statement
			ResultSet results4 =  statement4.executeQuery();

			while(results4.next()) {
				cb2 = new Connecting2Bean(results4.getInt(1),results4.getString(2),results4.getString(3),results4.getString(4),results4.getString(5),
						results4.getInt(6),results4.getString(7),results4.getString(8),results4.getString(9),results4.getString(10),
						results4.getInt(11),results4.getString(12),results4.getString(13),results4.getString(14),results4.getString(15),"Thursday");
				connect2.add(cb2);
			}	


			// Friday
			String sql5 = "select f1.flight_number, f1.departure_airport_code,f1.arrival_airport_code, f1.scheduled_departure_time, f1.scheduled_arrival_time," 
			 		+"f2.flight_number, f2.departure_airport_code, f2.arrival_airport_code, f2.scheduled_departure_time, f2.scheduled_arrival_time,"
			 		+"f3.flight_number, f3.departure_airport_code, f3.arrival_airport_code, f3.scheduled_departure_time, f3.scheduled_arrival_time from "
			 		+"airlines.flight f1, airlines.flight f2, airlines.flight f3 where f1.departure_airport_code = '"+from+"' AND f1.arrival_airport_code = f2.departure_airport_code "
			 		+"AND f2.arrival_airport_code = f3.departure_airport_code  AND f3.arrival_airport_code='"+to+"' AND "
			 		+"f2.scheduled_departure_time > f1.scheduled_arrival_time AND f3.scheduled_departure_time > f2.scheduled_arrival_time AND "
			 		+ "((timediff(f2.Scheduled_departure_time,f1.Scheduled_arrival_time) >= '01:00:00') AND (timediff(f3.Scheduled_departure_time,f2.Scheduled_arrival_time) >= '01:00:00')) AND"
			 		+"((f1.weekdays like '%Fri%' AND f2.weekdays like '%Fri%' AND f3.weekdays like '%Fri%'));";
			PreparedStatement statement5 = conn.prepareStatement(sql5);


			// Executing the prepared Statement
			ResultSet results5 =  statement5.executeQuery();

			while(results5.next()) {
				cb2 = new Connecting2Bean(results5.getInt(1),results5.getString(2),results5.getString(3),results5.getString(4),results5.getString(5),
						results5.getInt(6),results5.getString(7),results5.getString(8),results5.getString(9),results5.getString(10),
						results5.getInt(11),results5.getString(12),results5.getString(13),results5.getString(14),results5.getString(15),"Friday");
				connect2.add(cb2);
			}	


			// Saturday
			String sql6 = "select f1.flight_number, f1.departure_airport_code,f1.arrival_airport_code, f1.scheduled_departure_time, f1.scheduled_arrival_time," 
			 		+"f2.flight_number, f2.departure_airport_code, f2.arrival_airport_code, f2.scheduled_departure_time, f2.scheduled_arrival_time,"
			 		+"f3.flight_number, f3.departure_airport_code, f3.arrival_airport_code, f3.scheduled_departure_time, f3.scheduled_arrival_time from "
			 		+"airlines.flight f1, airlines.flight f2, airlines.flight f3 where f1.departure_airport_code = '"+from+"' AND f1.arrival_airport_code = f2.departure_airport_code "
			 		+"AND f2.arrival_airport_code = f3.departure_airport_code  AND f3.arrival_airport_code='"+to+"' AND "
			 		+"f2.scheduled_departure_time > f1.scheduled_arrival_time AND f3.scheduled_departure_time > f2.scheduled_arrival_time AND "
			 		+ "((timediff(f2.Scheduled_departure_time,f1.Scheduled_arrival_time) >= '01:00:00') AND (timediff(f3.Scheduled_departure_time,f2.Scheduled_arrival_time) >= '01:00:00')) AND"
			 		+"((f1.weekdays like '%Sat%' AND f2.weekdays like '%Sat%' AND f3.weekdays like '%Sat%'));";
			PreparedStatement statement6 = conn.prepareStatement(sql6);


			// Executing the prepared Statement
			ResultSet results6 =  statement6.executeQuery();

			while(results6.next()) {
				cb2 = new Connecting2Bean(results6.getInt(1),results6.getString(2),results6.getString(3),results6.getString(4),results6.getString(5),
						results6.getInt(6),results6.getString(7),results6.getString(8),results6.getString(9),results6.getString(10),
						results6.getInt(11),results6.getString(12),results6.getString(13),results6.getString(14),results6.getString(15),"Saturday");
				connect2.add(cb2);
			}


			// Sunday
			String sql7 = "select f1.flight_number, f1.departure_airport_code,f1.arrival_airport_code, f1.scheduled_departure_time, f1.scheduled_arrival_time," 
			 		+"f2.flight_number, f2.departure_airport_code, f2.arrival_airport_code, f2.scheduled_departure_time, f2.scheduled_arrival_time,"
			 		+"f3.flight_number, f3.departure_airport_code, f3.arrival_airport_code, f3.scheduled_departure_time, f3.scheduled_arrival_time from "
			 		+"airlines.flight f1, airlines.flight f2, airlines.flight f3 where f1.departure_airport_code = '"+from+"' AND f1.arrival_airport_code = f2.departure_airport_code "
			 		+"AND f2.arrival_airport_code = f3.departure_airport_code  AND f3.arrival_airport_code='"+to+"' AND "
			 		+"f2.scheduled_departure_time > f1.scheduled_arrival_time AND f3.scheduled_departure_time > f2.scheduled_arrival_time AND "
			 		+ "((timediff(f2.Scheduled_departure_time,f1.Scheduled_arrival_time) >= '01:00:00') AND (timediff(f3.Scheduled_departure_time,f2.Scheduled_arrival_time) >= '01:00:00')) AND"
			 		+"((f1.weekdays like '%Sun%' AND f2.weekdays like '%Sun%' AND f3.weekdays like '%Sun%'));";
			PreparedStatement statement7 = conn.prepareStatement(sql7);


			// Executing the prepared Statement
			ResultSet results7 =  statement7.executeQuery();

			while(results7.next()) {
				cb2 = new Connecting2Bean(results7.getInt(1),results7.getString(2),results7.getString(3),results7.getString(4),results7.getString(5),
						results7.getInt(6),results7.getString(7),results7.getString(8),results7.getString(9),results7.getString(10),
						results7.getInt(11),results7.getString(12),results7.getString(13),results7.getString(14),results7.getString(15),"Sunday");
				connect2.add(cb2);
			}	
      
			
		} catch (Exception e) {
				e.printStackTrace();
		}
		
		
		return connect2;
	}

   
    /* -------------------------------------------------------------------------------------------------------------------------- */

    
}

