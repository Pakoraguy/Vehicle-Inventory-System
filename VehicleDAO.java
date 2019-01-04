import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class VehicleDAO {
	private static Connection getDBConnection() {
		Connection connection = null;
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		try {
			String dbURL = "jdbc:sqlite:vehicles.sqlite";
			connection = DriverManager.getConnection(dbURL);
			return connection;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return connection;
	}

	public int getLatestVehicle_id() throws SQLException {
		int latest_vehicle_id = -1;
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;
		String query = "SELECT * FROM vehicles ORDER BY vehicle_id DESC LIMIT 1;\r\n";
		try {
			connection = getDBConnection();
			statement = connection.createStatement();
			System.out.println("DBQuery: " + query);
			result = statement.executeQuery(query);
			while (result.next()) {
				latest_vehicle_id = result.getInt("vehicle_id");
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if (result != null) {
				result.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (connection != null) {
				connection.close();
			}
		}
		return latest_vehicle_id;
	}
	
	public ArrayList<Vehicle> getAllVehicles() throws SQLException {
		ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;
		String query = "SELECT * FROM Vehicles;";
		try {
			connection = getDBConnection();
			statement = connection.createStatement();
			System.out.println("DBQuery: " + query);
			result = statement.executeQuery(query);
			while (result.next()) {
				Vehicle vehicleToAdd = new Vehicle(result.getInt("vehicle_id"), result.getString("make"),
						result.getString("model"), result.getInt("year"), result.getInt("price"),
						result.getString("license_number"), result.getString("colour"), result.getInt("number_doors"),
						result.getString("transmission"), result.getInt("mileage"), result.getString("fuel_type"),
						result.getInt("engine_size"), result.getString("body_style"), result.getString("condition"),
						result.getString("notes"));
				vehicles.add(vehicleToAdd);

			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if (result != null) {
				result.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (connection != null) {
				connection.close();
			}
		}
		return vehicles;

	}

	public Vehicle getVehicle(int vehicle_id) throws SQLException {

		Vehicle vehicle = null;
		Connection dbConnection = null;
		Statement statement = null;
		ResultSet result = null;

		String query = "SELECT * FROM vehicles WHERE vehicle_id =" + vehicle_id + ";";

		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();
			System.out.println("DBQuery: " + query);
			result = statement.executeQuery(query);

			while (result.next()) {

				vehicle = new Vehicle(result.getInt("vehicle_id"), result.getString("make"), result.getString("model"),
						result.getInt("year"), result.getInt("price"), result.getString("license_number"),
						result.getString("colour"), result.getInt("number_doors"), result.getString("transmission"),
						result.getInt("mileage"), result.getString("fuel_type"), result.getInt("engine_size"),
						result.getString("body_style"), result.getString("condition"), result.getString("notes"));

			}
		} finally {
			if (result != null) {
				result.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		}
		return vehicle;
	}

	public Boolean deleteVehicle(int vehicle_id) throws SQLException {
		System.out.println("Deleting vehicle");
		Connection dbConnection = null;
		Statement statement = null;
		int result = 0;
		String query = "DELETE FROM vehicles WHERE vehicle_id = " + vehicle_id + ";";
		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();
			System.out.println(query);
			result = statement.executeUpdate(query);

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if (statement != null) {
				statement.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		}
		if (result == 1) {
			return true;
		} else {
			return false;
		}
	}

	public Boolean updateVehicle(Vehicle vehicle, int vehicle_id) throws SQLException {
		Connection dbConnection = null;
		Statement statement = null;

		String query = "UPDATE vehicles " + "SET Vehicle_ID = '" + vehicle_id + "', " + "Make = '" + vehicle.getMake()
				+ "', " + "Model = '" + vehicle.getModel() + "', " + "Year = '" + vehicle.getYear() + "', "
				+ "Price = '" + vehicle.getPrice() + "', " + "License_number ='" + vehicle.getLicense_number() + "', "
				+ "Colour ='" + vehicle.getColour() + "', " + "Number_doors ='" + vehicle.getNumber_doors() + "', "
				+ "Transmission = '" + vehicle.getTransmission() + "', " + "Mileage = '" + vehicle.getMileage() + "', "
				+ "Fuel_type = '" + vehicle.getFuel_type() + "', " + "Engine_size = '" + vehicle.getEngine_size()
				+ "', " + "Body_style = '" + vehicle.getBody_style() + "', " + "Condition = '" + vehicle.getCondition()
				+ "', " + "Notes = '" + vehicle.getNotes() + "' WHERE Vehicle_ID = " + vehicle_id;

		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();
			System.out.println(query);
			statement.executeUpdate(query);

		} catch (SQLException e) {

			System.out.println(e.getMessage());
			return false;

		} finally {

			if (statement != null) {
				statement.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		}
		return true;
	}

	public boolean insertVehicle(Vehicle vehicle) throws SQLException {
		Connection dbConnection = null;
		Statement statement = null;

		String query = "INSERT INTO vehicles (vehicle_id, make, model, year, price, license_number, colour, number_doors, transmission, mileage, fuel_type, engine_size, body_style, condition, notes) "
				+ "VALUES ('" + vehicle.getVehicle_id() + "', '" + vehicle.getMake() + "', '" + vehicle.getModel() + "', '"
				+ vehicle.getYear() + "', '" + vehicle.getPrice() + "', '" + vehicle.getLicense_number() + "', '"
				+ vehicle.getColour() + "', '" + vehicle.getNumber_doors() + "', '" + vehicle.getTransmission() + "', '"
				+ vehicle.getMileage() + "', '" + vehicle.getFuel_type() + "', '" + vehicle.getEngine_size() + "', '"
				+ vehicle.getBody_style() + "', '" + vehicle.getCondition() + "', '" + vehicle.getNotes()+"');";
		boolean ok = false;
		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();
			System.out.println(query);
			statement.executeUpdate(query);
			ok = true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if (statement != null) {
				statement.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}

		}
		return ok;
	}	
}