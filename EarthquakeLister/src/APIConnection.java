import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.time.LocalDate;

//Class for connecting API and getting data
public class APIConnection {

	
	/*static method for taking JSON file 
		with one parameter (user input) for filtering last x days earthquakes*/
	public static String getJSON(int xDaysAgo) {
		
		//Calculating x days ago date format:YYYY-MM-DD and preparing URL string
		LocalDate today = LocalDate.now(); 
		String startTime = today.plusDays(xDaysAgo*-1).toString();
		
		//in API documentation it says that end time is present time so no need to give extra filter with end time for out program, event type is filtering only earthquakes
		String urlString =String.format("https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=%s&eventtype=earthquake", startTime);
		
		
		try {
			
			//Connect given URL with get request method
			URL url = new URL(urlString);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.connect();

			//controlling response code for any problem
			int responseCode = conn.getResponseCode();	
			
			//This response code for bad request, since API does not include data before 2021-12-18
			if(responseCode==400) {
				System.out.println("WARNING: Date to be scanned must be after 2021-12-18!");
				System.exit(0);
				return "";
				
			}
			
			//For other connection problems
			if(responseCode != 200) {
				System.out.println("WARNING: Can not connect API!");
				System.exit(0);
				return "";
				
			}
			
			else {
				
				//if there is no problem in connection get input stream of connection
				BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				StringBuffer responseContent = new StringBuffer();
				
				
				//Reading line by line and store StringBuffer
				String line;
				while((line = reader.readLine()) != null) {
					responseContent.append(line);
				}
				
				//Close BufferedReader and HTTP connection
				reader.close();
				conn.disconnect();
				return responseContent.toString();
			
			}
					
		
			
			
		}
		//Catch block for Internet connection lost
		catch (UnknownHostException e) {
			System.out.println("Please Check Your Internet Connection!");
			System.exit(0);
			return "";
		}
		catch (MalformedURLException e) {
			e.printStackTrace();
			return "";	
		} catch (IOException e) {
			e.printStackTrace();
			return "";	
		} 
		
		
	}
	
}
