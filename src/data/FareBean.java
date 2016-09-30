package data;

public class FareBean {
	
	public int flightNo;
	public String fare_code;
	public int amount;
	public String restrictions;
	
	public FareBean() {
		
	}
	
	public FareBean(int flightNo, String fare_code, int amount, String restrictions) {
		this.setFlightNo(flightNo);
		this.setFare_code(fare_code);
		this.setAmount(amount);
		this.setRestrictions(restrictions);
	}
	
	public int getFlightNo() {
		return flightNo;
	}
	public void setFlightNo(int flightNo) {
		this.flightNo = flightNo;
	}
	public String getFare_code() {
		return fare_code;
	}
	public void setFare_code(String fare_code) {
		this.fare_code = fare_code;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getRestrictions() {
		return restrictions;
	}
	public void setRestrictions(String restrictions) {
		this.restrictions = restrictions;
	}
	
	

}
