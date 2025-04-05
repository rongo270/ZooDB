package sqlDataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateHappinessOfAnimals {

	private static String url;
	private static String user;
	private static String password;

	public void setTheFileLocation(String url, String user, String password) {
		this.url = url;
		this.user = user;
		this.password = password;
	}

	public void updateAnimalHappiness(int animalid, int newHappiness) throws SQLException {
		Connection connection = null;
		PreparedStatement stmt = null;

		try {
			connection = DriverManager.getConnection(url, user, password);
			String updateAnimalSQL = "UPDATE animals SET happiness = ? WHERE animalid = ?";
			stmt = connection.prepareStatement(updateAnimalSQL);

			stmt.setInt(1, newHappiness);
			stmt.setInt(2, animalid);

			stmt.executeUpdate();
		} finally {
			if (stmt != null) {
				stmt.close();
			}
			if (connection != null) {
				connection.close();
			}
		}
	}

}
