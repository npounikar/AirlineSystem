package data;

public class FlightDetailsBean {
	
	public int flightNo;
	public String days;
	
	public FlightDetailsBean() {
		
	}
	public FlightDetailsBean(int fno, String wdays) {
		this.setFlightNo(fno);
		this.setDays(wdays);
	}
	
	public String getDays() {
		return days;
	}
	public void setDays(String days) {
		this.days = days;
	}
	
	public int getFlightNo() {
		return flightNo;
	}
	public void setFlightNo(int flightNo) {
		this.flightNo = flightNo;
	}
	

}
