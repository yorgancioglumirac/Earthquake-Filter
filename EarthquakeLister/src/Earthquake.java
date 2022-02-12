import java.util.Date;


//Earthquake class for store earthquakes properly
public class Earthquake {

	//instance variables of earthquakes according to wanted output
	private String place;
	private double magnitude;
	private Date date;
	
	
	//Simple Constructor with all instance variables
	public Earthquake(String place, double magnitude, Date date) {
		this.place = place;
		this.magnitude = magnitude;
		this.date = date;
	}
	
	
	//Encapsulate field / getters and setters
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}

	public double getMagnitude() {
		return magnitude;
	}
	public void setMagnitude(double magnitude) {
		this.magnitude = magnitude;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	
	
	
}
