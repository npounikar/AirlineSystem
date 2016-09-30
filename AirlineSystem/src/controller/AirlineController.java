package controller;


import java.sql.SQLException;
import java.util.ArrayList;

import data.FareBean;
import data.*;



public class AirlineController {
	
	DatabaseController dbController;
	
	/* -------------------------------------------------------------------------------------------------------------------------- */
	
	
	public ArrayList<FlightDetailsBean> getFlightDetails(String from,String to) throws SQLException {
	    	
	    	dbController = new DatabaseController();
		 	return dbController.getFlightDetails(from,to);
	    	
	    }
	
	 /* -------------------------------------------------------------------------------------------------------------------------- */
	
	/* -------------------------------------------------------------------------------------------------------------------------- */
	
	
	public ArrayList<FareBean> getFareDetails(int fno) throws SQLException {

	    	dbController = new DatabaseController();
		 	return dbController.getFareDetails(fno);
	    	
	    }
	
	 /* -------------------------------------------------------------------------------------------------------------------------- */
	
	/* -------------------------------------------------------------------------------------------------------------------------- */
	
	
	public ArrayList<AvailabilityBean> getAvailability(int fno, String date) throws SQLException {

	    	dbController = new DatabaseController();
		 	return dbController.getAvailability(fno,date); 
	    	
	    }
	
	 /* -------------------------------------------------------------------------------------------------------------------------- */
	
	/* -------------------------------------------------------------------------------------------------------------------------- */
	
	
	public ArrayList<PassengersBean> getPassengersList(int fno, String date) throws SQLException {

	    	dbController = new DatabaseController();
		 	return dbController.getPassengersList(fno,date); 
	    	
	    }
	
	 /* -------------------------------------------------------------------------------------------------------------------------- */
	
	
	/* -------------------------------------------------------------------------------------------------------------------------- */
	
	
	public ArrayList<PassengerInstanceBean> getPassengersInstance(String name) throws SQLException {

	    	dbController = new DatabaseController();
		 	return dbController.getPassengersInstance(name); 
	    	
	    }
	
	 /* -------------------------------------------------------------------------------------------------------------------------- */
	
    /* -------------------------------------------------------------------------------------------------------------------------- */
	
	
	public ArrayList<FlightOnBoardBean> getFlightOnBoardList(int fno) throws SQLException {

	    	dbController = new DatabaseController();
		 	return dbController.getFlightOnBoardList(fno); 
	    	
	    }
	
	 /* -------------------------------------------------------------------------------------------------------------------------- */

	/* -------------------------------------------------------------------------------------------------------------------------- */
	
	
	public ArrayList<ConnectingBean> getDirectFlight(String from,String to) throws SQLException {
	    	
	    	dbController = new DatabaseController();
		 	return dbController.getDirectFlight(from,to);
	    	
	    }
	
	 /* -------------------------------------------------------------------------------------------------------------------------- */
	
		/* -------------------------------------------------------------------------------------------------------------------------- */
	
	
	public ArrayList<Connecting1Bean> getOneConnectingFlight(String from,String to) throws SQLException {
	    	
	    	dbController = new DatabaseController();
		 	return dbController.getOneConnectingFlight(from,to);
	    	
	    }
	
	 /* -------------------------------------------------------------------------------------------------------------------------- */
	
	/* -------------------------------------------------------------------------------------------------------------------------- */
	
	
	public ArrayList<Connecting2Bean> getTwoConnectingFlight(String from,String to) throws SQLException {
	    	
	    	dbController = new DatabaseController();
		 	return dbController.getTwoConnectingFlight(from,to);
	    	
	    }
	
	 /* -------------------------------------------------------------------------------------------------------------------------- */
	 
}
