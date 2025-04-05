package sqlDataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import project_ZOO.ZooManger;

public class DelateAnimalsFromTable {

	private static String url;
	private static String user;
	private static String password;

	public void setTheFileLocation(String url, String user, String password) {
		this.url = url;
		this.user = user;
		this.password = password;
	}

	public String deleteAnimalById(int animalid) throws SQLException {
		StringBuffer str = new StringBuffer();
		Connection connection = null;
		PreparedStatement stmt = null;

		try {
			connection = DriverManager.getConnection(url, user, password);
			String deleteAnimalSQL = "DELETE FROM animals WHERE animalid = ?";
			stmt = connection.prepareStatement(deleteAnimalSQL);

			stmt.setInt(1, animalid);
			stmt.executeUpdate();


			str.append("Animal with ID " + animalid + " was deleted successfully.");

		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
		return str.toString();
	}

	public void deleteAllAnimal() throws SQLException {
		Connection connection = null;
		PreparedStatement stmt = null;

		try {
			connection = DriverManager.getConnection(url, user, password);
			String deleteAnimalSQL = "DELETE FROM animals";
			stmt = connection.prepareStatement(deleteAnimalSQL);

			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
		}
	}

}
