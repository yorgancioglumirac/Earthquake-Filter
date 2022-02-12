import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.json.JSONArray;
import org.json.JSONObject;

//This class is for creates earthquakes according to input and prints them
public class DataProcess {

	/*response is response content coming from API
	    earthquakes array list for storing earthquakes matching the input
	    	xDaysAgo filters the earthquake with date */
	    
	private String response;
	private ArrayList<Earthquake> earthquakes = new ArrayList<Earthquake>();
	private int xDaysAgo;

	//Constructor and getter setters for instance variables
	public DataProcess(String response,int xDaysAgo) {
		this.response = response;
		this.xDaysAgo = xDaysAgo;
	}

	public int getxDaysAgo() {
		return xDaysAgo;
	}

	public void setxDaysAgo(int xDaysAgo) {
		this.xDaysAgo = xDaysAgo;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}
	

	public ArrayList<Earthquake> getEarthquakes() {
		return earthquakes;
	}

	public void setEarthquakes(ArrayList<Earthquake> earthquakes) {
		this.earthquakes = earthquakes;
	}

	
	//Creates JSON objects and controlling their country if it is match create Earthquake object and stores them in array list
	public void createEarthquakes(String country) {
		
		//Creates JSON Object and reaches its features array for earthquakes
		JSONObject myResponse = new JSONObject(this.response.toString());
		JSONArray jsonArr = (JSONArray) myResponse.get("features");
		
		
		//iterates over array controls for match and store
		for(int i = 0; i < jsonArr.length(); i++) {
			
			//Reach properties attribute of event
			JSONObject JSONearthquake = jsonArr.getJSONObject(i);
			JSONObject propertiesOfEarthquake = (JSONObject) JSONearthquake.get("properties");
			
			
			//Takes place of earthquake try catch block is avoiding from error, some event's place can be null
			String placeAndCountry = "";
			try {
				 placeAndCountry = propertiesOfEarthquake.getString("place");
			} catch (Exception e) {
				continue;
				
			}
			
			
			/*Controls earthquake's place if it contains input
			   for avoid lower case upper case mismatch it convert place and input both lower case*/
			if(placeAndCountry.toLowerCase(Locale.ENGLISH).contains(country.toLowerCase(Locale.ENGLISH))) {
				
				
				//this try catch block for avoiding null content
				try {
					//Magnitude of earthquake
					float magnitudeOfEarthquake = propertiesOfEarthquake.getFloat("mag");
					
					
					//time is millisecond and converting to actual date
					Calendar timeOfEarthquake = Calendar.getInstance();
					timeOfEarthquake.setTimeInMillis(propertiesOfEarthquake.getLong("time"));	
					Date timeOfEarthquakeDate = timeOfEarthquake.getTime();
	
					//Create Earthquake object and store in array list
					Earthquake earthquake = new Earthquake(placeAndCountry, magnitudeOfEarthquake, timeOfEarthquakeDate );
					earthquakes.add(earthquake);
				} catch (Exception e) {
					continue;
					
				}
				
				

			}
			
		}
	}
	
	
	public void printEarthquakes() {
		if(this.earthquakes.size()==0) {
			System.out.println(String.format("No Earthquakes were recorded past %s days.", this.xDaysAgo));
			return;
		}
		
		
		System.out.printf("%-65s%-25s%-40s%-25s\n","Place","Magnitude","Date","Time(GMT+3)");
		System.out.printf("%-65s%-25s%-40s%-25s\n","*****","*********","****","***********");
		for(int i = 0; i < this.earthquakes.size();i++) {
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/YY");
			SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
			
			
			System.out.printf("%-65s%-25.2f%-40s%-25s\n",
														this.earthquakes.get(i).getPlace(),
															this.earthquakes.get(i).getMagnitude(),
																dateFormat.format(this.earthquakes.get(i).getDate()),
																timeFormat.format(this.earthquakes.get(i).getDate()));
			
		}
		
	}
	
}
