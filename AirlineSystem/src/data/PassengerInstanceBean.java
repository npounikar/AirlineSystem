package data;

public class PassengerInstanceBean {

	public int fno;
	public String from;
	public String to;
	public String date;
	public String seat_no;
	public String dtime;
	public String atime;

	
	public PassengerInstanceBean() {
		
	}
	
	public PassengerInstanceBean(int fno,  String date,  String seat, String from, String to,  String dtime, String atime) {
		
		this.setFno(fno);
		this.setDate(date);
		this.setSeat_no(seat);
		this.setAtime(atime);
		this.setDtime(dtime);
		this.setFrom(from);
		this.setTo(to);
		
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

	public int getFno() {
		return fno;
	}

	public void setFno(int fno2) {
		this.fno = fno2;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getSeat_no() {
		return seat_no;
	}

	public void setSeat_no(String seat_no) {
		this.seat_no = seat_no;
	}

	
	
	
}
