import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		try {
			System.out.println("How many days of earthquakes do you want to view?");
			int numberOfDays = scanner.nextInt();
			scanner.nextLine();
			
			System.out.println("Enter the keyword you want to search in places.");
			String countryKeyword = scanner.nextLine();
			System.out.println("\n");
			
			DataProcess processData = new DataProcess(APIConnection.getJSON(numberOfDays),numberOfDays);
			processData.createEarthquakes(countryKeyword);
			processData.printEarthquakes();
		} catch (InputMismatchException e) {
			System.out.println("Entered wrong input");
		}finally {
			scanner.close();
		}
		
		
		
		
	}

}
