import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class VehicleController {
	static VehicleDAO dao = new VehicleDAO();				
	static Scanner input = new Scanner(System.in);


	public static void retrieveVehicles() {
		System.out.println("Retrieving all vehicles");
		ArrayList<Vehicle> vehicles;
		try {
			vehicles = dao.getAllVehicles();
			for (int i = 0; i < vehicles.size(); i++) {
				System.out.println("---------------------------");
				System.out.println(vehicles.get(i));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("---------------------------");


	}
	
	public static void retrieveVehicle() {
		System.out.println("VIEW VEHICLE");
		System.out.println("---------------------------");
		System.out.println("Enter a vehicle id: ");
		int vehicle_id = input.nextInt();
		Vehicle vehicle = null;
		try {
			vehicle = dao.getVehicle(vehicle_id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
		System.out.println("---------------------------");
		if(vehicle == null) {
			System.out.printf("No vehicle of id: %d was found\n" , vehicle_id);
		} else {
			System.out.println(vehicle);	
		}
		System.out.println("---------------------------");
		
	}
	
	public static void deleteVehicle() {
		System.out.println("DELETE VEHICLE");
		System.out.println("---------------------------");
		System.out.println("Enter the vehicle id:");
		int vehicle_id = input.nextInt();
		Boolean deleted = false;
		try {
			deleted = dao.deleteVehicle(vehicle_id);		
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
		System.out.println("---------------------------");
		if(deleted == false) {
			System.out.printf("No vehicle of id: %d was deleted\n" , vehicle_id);
		} else {
			System.out.printf("Vehicle of id: %d was deleted \n", vehicle_id);	
		}
		System.out.println("---------------------------");
	}

	public static void main(String[] args) {
		Boolean exit = false;
		while (!exit) {
			try {
				int n = 0;
				String menu = "Vehicle Inventory System\r\n" + "Choose from these options\r\n"
						+ "-------------------------\r\n" + "1 - Retrieve all vehicles\r\n"
						+ "2 - Search for vehicle\r\n" + "3 - Insert new vehicle into database\r\n"
						+ "4 - Update existing vehicle details\r\n" + "5 - Delete vehicle from database\r\n"
						+ "6 - Exit\r\n" + "Enter choice > ";
				System.out.println(menu);
				n = input.nextInt();

				if (n < 1 || n > 6) {
					System.out.println("Not a valid option");
				}
				
				if (n == 1) {
					retrieveVehicles();
				} else if (n == 2) {
					retrieveVehicle();
					
				} else if (n== 3) {
				
				} else if (n ==4 ) {
					
				} else if (n == 5) {
					deleteVehicle();
				} else if (n ==6) {
					exit = true;
				}
			} catch (java.util.InputMismatchException e) {
				System.out.println("Not a valid input or option");
			}
		}

	}
}
