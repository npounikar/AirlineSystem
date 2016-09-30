package data;

public class ConnectingBean {
	
	
	public int flightNo1;
	public String from1;
	public String to1;
	public String dtime1;
	public String atime1;
	
	public String day;
	
	public ConnectingBean() {
		
	}
	
	public ConnectingBean( int flightNo1,  String from1,  String to1,  String dtime1,  String atime1, String day) {
		
		this.setFlightNo1(flightNo1);
		this.setFrom1(from1);
		this.setTo1(to1);
		this.setDtime1(dtime1);
		this.setAtime1(atime1);
		
		
	
		this.setDay(day);
		
	}

	public int getFlightNo1() {
		return flightNo1;
	}

	public void setFlightNo1(int flightNo1) {
		this.flightNo1 = flightNo1;
	}

	public String getFrom1() {
		return from1;
	}

	public void setFrom1(String from1) {
		this.from1 = from1;
	}

	public String getTo1() {
		return to1;
	}

	public void setTo1(String to1) {
		this.to1 = to1;
	}

	public String getDtime1() {
		return dtime1;
	}

	public void setDtime1(String dtime1) {
		this.dtime1 = dtime1;
	}

	public String getAtime1() {
		return atime1;
	}

	public void setAtime1(String atime1) {
		this.atime1 = atime1;
	}


	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	
	

}
