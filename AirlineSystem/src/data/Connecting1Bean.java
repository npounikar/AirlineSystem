package data;

public class Connecting1Bean {
	
	
	public int flightNo1;
	public String from1;
	public String to1;
	public String dtime1;
	public String atime1;
	
	public int flightNo2;
	public String from2;
	public String to2;
	public String dtime2;
	public String atime2;
	
	public String day;
	
	public Connecting1Bean() {
		
	}
	
	public Connecting1Bean( int flightNo1,  String from1,  String to1,  String dtime1,  String atime1, int flightNo2,  String from2,  String to2,  String dtime2,  String atime2, String day) {
		
		this.setFlightNo1(flightNo1);
		this.setFrom1(from1);
		this.setTo1(to1);
		this.setDtime1(dtime1);
		this.setAtime1(atime1);
		
		this.setFlightNo2(flightNo2);
		this.setFrom2(from2);
		this.setTo2(to2);
		this.setDtime2(dtime2);
		this.setAtime2(atime2);
	
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

	public int getFlightNo2() {
		return flightNo2;
	}

	public void setFlightNo2(int flightNo2) {
		this.flightNo2 = flightNo2;
	}

	public String getFrom2() {
		return from2;
	}

	public void setFrom2(String from2) {
		this.from2 = from2;
	}

	public String getTo2() {
		return to2;
	}

	public void setTo2(String to2) {
		this.to2 = to2;
	}

	public String getDtime2() {
		return dtime2;
	}

	public void setDtime2(String dtime2) {
		this.dtime2 = dtime2;
	}

	public String getAtime2() {
		return atime2;
	}

	public void setAtime2(String atime2) {
		this.atime2 = atime2;
	}


	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	
	

}
