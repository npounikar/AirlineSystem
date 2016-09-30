package data;

public class PassengersBean {

	
	public String cust_name;
	public String cust_phone;
	public String seat_no;
	
	public PassengersBean() {
		
	}
	
	public PassengersBean(String cust_name, String cust_phone, String seat_no) {
		this.setCust_name(cust_name);
		this.setCust_phone(cust_phone);
		this.setSeat_no(seat_no);
	}

	public String getCust_name() {
		return cust_name;
	}

	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}

	public String getCust_phone() {
		return cust_phone;
	}

	public void setCust_phone(String cust_phone) {
		this.cust_phone = cust_phone;
	}

	public String getSeat_no() {
		return seat_no;
	}

	public void setSeat_no(String seat_no) {
		this.seat_no = seat_no;
	}
	
}
