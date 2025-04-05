package sqlDataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class StartIdOfTheAnimals {

	private static String url;
	private static String user;
	private static String password;

	public void setTheFileLocation(String url, String user, String password) {
		this.url = url;
		this.user = user;
		this.password = password;
	}

	public void startIdOfAnimals(int startId) {
		JOptionPane IO = new JOptionPane();

		try {
			Connection connection = DriverManager.getConnection(url, user, password);

			String updateAnimalSQL = "ALTER SEQUENCE animals_id_seq RESTART WITH " + startId;
			PreparedStatement stmt = connection.prepareStatement(updateAnimalSQL);
			stmt.executeUpdate();

			IO.showMessageDialog(null, "Animal ID sequence reset to: " + startId);

			stmt.close();
			connection.close();

		} catch (SQLException e) {
			IO.showMessageDialog(null, e.getMessage());
		}
	}

}

//public int continueID(){
//JOptionPane IO = new JOptionPane();
//Connection connection = null;
//PreparedStatement stmt = null;
//ResultSet rs = null;
//int count;
//try {
//	Connection connection = DriverManager.getConnection(url, user, password);
//	String GetSequence ="SELECT nextval('animals_id_seq')";
//	stmt = connection.prepareStatement(GetSequence);
//	rs = stmt.executeQuery();
//	if(rs.next())
//	 count = rs.getInt("nextval");
//	else
//		count = 1000;
//	stmt.close();
//	connection.close();
//	IO.showMessageDialog(null, "Animal ID sequence continue on: " + count);
//	return count;
//
//} catch (SQLException e) {
//	IO.showMessageDialog(null, e.getMessage());
//}
//}
//
//}