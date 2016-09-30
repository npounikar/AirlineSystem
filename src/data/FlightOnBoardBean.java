package data;

public class FlightOnBoardBean {
	
	public String from;
	public String to;
	public String dtime;
	public String atime;
	
	public FlightOnBoardBean() {
		// TODO Auto-generated constructor stub
	}
	
	public FlightOnBoardBean(String from, String to, String dtime, String atime) {
		this.setFrom(from);
		this.setTo(to);
		this.setDtime(dtime);
		this.setAtime(atime);
	}
	
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getDtime() {
		return dtime;
	}
	public void setDtime(String dtime) {
		this.dtime = dtime;
	}
	public String getAtime() {
		return atime;
	}
	public void setAtime(String atime) {
		this.atime = atime;
	}
	
	
	

}
