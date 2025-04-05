package sqlDataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateAgeOfAnimals {

	private static String url;
	private static String user;
	private static String password;

	public void setTheFileLocation(String url, String user, String password) {
		this.url = url;
		this.user = user;
		this.password = password;
	}

	public void updateAnimalAge(int animalid, int newAge) throws SQLException {
		Connection connection = null;
		PreparedStatement stmt = null;

		try {
			connection = DriverManager.getConnection(url, user, password);
			String updateAnimalSQL = "UPDATE animals SET age = ? WHERE animalid = ?";
			stmt = connection.prepareStatement(updateAnimalSQL);
			stmt.setInt(1, newAge);
			stmt.setInt(2, animalid);
			
			stmt.executeUpdate();

		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
	}

}
