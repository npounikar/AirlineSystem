package data;

public class AvailabilityBean {
	
	public int flightno;
	public String date;
	public int aseats;
	
	public AvailabilityBean() {
		// TODO Auto-generated constructor stub
	}
	
	public AvailabilityBean(int flightno, String date, int aseats) {
		
		this.setAseats(aseats);
		this.setDate(date);
		this.setFlightno(flightno);
		
	}

	public int getFlightno() {
		return flightno;
	}

	public void setFlightno(int flightno) {
		this.flightno = flightno;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getAseats() {
		return aseats;
	}

	public void setAseats(int aseats) {
		this.aseats = aseats;
	}

	
}
