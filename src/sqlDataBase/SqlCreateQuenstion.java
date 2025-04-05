package sqlDataBase;

import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import animals.Cervus;
import animals.Lemur;
import animals.Lion;
import animals.Penguin;
import animals.Tiger;
import characteristicsOfAnimals.Animal;
import characteristicsOfAnimals.CharacteristicOfAFish;
import characteristicsOfAnimals.Gender;
import characteristicsOfAnimals.FishColor.GoldFishColor;

public class SqlCreateQuenstion {

	private static  String url ;
	private static  String user;
	private static  String password ;
	
	
	public void setTheFileLocation( String url , String user  , String password ) {
		this.url = url;
		this.user = user;
		this.password = password;
	}
	

	private int insertAnimal(Connection connection, int age) throws SQLException {
		PreparedStatement stmt1 = null;
		PreparedStatement stmt2 = null;
		ResultSet rs = null;

		try {
			String insertAnimalSQL = "INSERT INTO animals (age,happiness) VALUES (?,100)";
			stmt1 = connection.prepareStatement(insertAnimalSQL);
			stmt1.setInt(1, age);
			stmt1.executeUpdate();

			String getAnimalIdSQL = "SELECT currval('animals_id_seq') as animalid";
			stmt2 = connection.prepareStatement(getAnimalIdSQL);
			rs = stmt2.executeQuery();

			if (rs.next()) {
				return rs.getInt("animalid"); // מחזיר את ה-ID של החיה שנוצרה
			} else {
				throw new SQLException("Failed to retrieve animal ID.");
			}
		} finally {
			if (rs != null)
				rs.close();
			if (stmt1 != null)
				stmt1.close();
			if (stmt2 != null)
				stmt2.close();
		}
	}

	private void createAnimalName(Connection connection, int id, String nameofanimal) throws SQLException {
		PreparedStatement stmt1 = null;
		String insertAnimalNameSQL = "INSERT INTO animalsnames (animalid , nameofanimal) VALUES (?, ?)";
		stmt1 = connection.prepareStatement(insertAnimalNameSQL);
		stmt1.setInt(1, id);
		stmt1.setString(2, nameofanimal);
		int affectedRows = stmt1.executeUpdate();
		if (affectedRows == 0)
			throw new SQLException("Creating animal name failed, no rows affected.");

		if (stmt1 != null)
			stmt1.close();
	}

	private void creategender(Connection connection, int id, Gender gender) throws SQLException {
		PreparedStatement stmt1 = null;
		String insertAnimalNameSQL = "INSERT INTO gender (animalid , gender) VALUES (?, ?)";
		stmt1 = connection.prepareStatement(insertAnimalNameSQL);
		stmt1.setInt(1, id);
		stmt1.setString(2, gender.toString());
		int affectedRows = stmt1.executeUpdate();
		if (affectedRows == 0)
			throw new SQLException("Creating animal gender failed, no rows affected.");

		if (stmt1 != null)
			stmt1.close();
	}

	// ............................ FISH.........................//

	public int createAquariumFish(int age, double fishLength, ArrayList<Object> hisColor,
			CharacteristicOfAFish hisTrait) throws SQLException {
		Connection connection = null;
		PreparedStatement stmt = null;

		try {
			connection = DriverManager.getConnection(url, user, password);

			int animalId = insertAnimal(connection, age);

			String[] fishColorStrArray = hisColor.stream().map(Object::toString).toArray(String[]::new);
			Array sqlFishColorArray = connection.createArrayOf("text", fishColorStrArray);

			String insertClownfishSQL = "INSERT INTO aquariumfish (animalid, fishlength ,hiscolor , histrait) VALUES (?, ?, ? , ?)";
			stmt = connection.prepareStatement(insertClownfishSQL);
			stmt.setInt(1, animalId);
			stmt.setDouble(2, fishLength);
			stmt.setArray(3, sqlFishColorArray);
			stmt.setString(4, hisTrait.toString());

			stmt.executeUpdate();

			return animalId;
		} finally {
			if (stmt != null)
				stmt.close();
			if (connection != null)
				connection.close();
		}

	}

	public int createGoldFish(int age, double fishLength, GoldFishColor fishColor) throws SQLException {
		Connection connection = null;
		PreparedStatement stmt = null;

		try {
			connection = DriverManager.getConnection(url, user, password);

			int animalId = insertAnimal(connection, age);

			String insertClownfishSQL = "INSERT INTO goldfish (animalid, fishlength ,hiscolor) VALUES (?, ?, ?)";
			stmt = connection.prepareStatement(insertClownfishSQL);
			stmt.setInt(1, animalId);
			stmt.setDouble(2, fishLength);
			stmt.setString(3, fishColor.toString());
			stmt.executeUpdate();

			return animalId;
		} finally {
			if (stmt != null)
				stmt.close();
			if (connection != null)
				connection.close();
		}

	}

	public int createClownFish(int age, double fishLength) throws SQLException {
		Connection connection = null;
		PreparedStatement stmt = null;

		try {
			connection = DriverManager.getConnection(url, user, password);

			int animalId = insertAnimal(connection, age);

			String insertClownfishSQL = "INSERT INTO clownfish (animalid, fishlength) VALUES (?, ?)";
			stmt = connection.prepareStatement(insertClownfishSQL);
			stmt.setInt(1, animalId);
			stmt.setDouble(2, fishLength);
			stmt.executeUpdate();

			return animalId;
		} finally {
			if (stmt != null)
				stmt.close();
			if (connection != null)
				connection.close();
		}

	}

	// ............................ FISH.........................//

	
	
	// ............................ CERVUS.......................//

	private <T extends Animal> int createCervusOrCarnivore(String name, Gender genderCervus, int age, double value,
			Class<T> classWanted) throws SQLException {
		Connection connection = null;
		PreparedStatement stmt = null;

		try {
			connection = DriverManager.getConnection(url, user, password);

			int animalId = insertAnimal(connection, age);

			String insertClownfishSQL = "";
			
			if (classWanted.equals(Cervus.class))
				insertClownfishSQL = "INSERT INTO cervus (animalid, lengthoftherays) VALUES (?, ?)";
			
			else if (classWanted.equals(Lion.class))
				insertClownfishSQL = "INSERT INTO lion (animalid, weight) VALUES (?, ?)";
			
			else if (classWanted.equals(Tiger.class))
				insertClownfishSQL = "INSERT INTO tiger (animalid, weight) VALUES (?, ?)";
			
			stmt = connection.prepareStatement(insertClownfishSQL);
			stmt.setInt(1, animalId);
			stmt.setDouble(2, value);
			stmt.executeUpdate();

			createAnimalName(connection, animalId, name);
			creategender(connection, animalId, genderCervus);

			return animalId;
		} finally {
			if (stmt != null)
				stmt.close();
			if (connection != null)
				connection.close();
		}

	}

	public int createCervus(String name, Gender genderCervus, int age, double lengthOfTheRays) throws SQLException {
		return createCervusOrCarnivore(name, genderCervus, age, lengthOfTheRays, Cervus.class);

	}
	
	public int createLion(String name, Gender gender, int age, float weight) throws SQLException {
		return createCervusOrCarnivore(name, gender, age, weight, Lion.class);

	}
	

	public int createTiger(String name, Gender gender, int age, float weight) throws SQLException {
		return createCervusOrCarnivore(name, gender, age, weight, Tiger.class);

	}

	// ............................ CERVUS + TIGER + LION.......................//

	// ............................ LEMUR + PENGUIN.......................//

	private <T> int createLemurOrPenguin(String name, int age, float height, Class<T> classWanted) throws SQLException {

		Connection connection = null;
		PreparedStatement stmt = null;

		try {
			connection = DriverManager.getConnection(url, user, password);

			int animalId = insertAnimal(connection, age);

			String insertClownfishSQL;
			if (classWanted.equals(Lemur.class))
				insertClownfishSQL = "INSERT INTO lemur (animalid, height) VALUES (?, ?)";
			else
				insertClownfishSQL = "INSERT INTO penguin (animalid, height) VALUES (?, ?)";

			stmt = connection.prepareStatement(insertClownfishSQL);
			stmt.setInt(1, animalId);
			stmt.setFloat(2, height);
			stmt.executeUpdate();

			createAnimalName(connection, animalId, name);

			return animalId;
		} finally {
			if (stmt != null)
				stmt.close();
			if (connection != null)
				connection.close();
		}

	}

	public int createLemur(String name, int age, float height) throws SQLException {
		return createLemurOrPenguin(name, age, height, Lemur.class);
	}

	public int createPenguin(String name, int age, float height) throws SQLException {
		return createLemurOrPenguin(name, age, height, Penguin.class);
	}

	// ............................ LEMUR + PENGUIN.......................//

}
