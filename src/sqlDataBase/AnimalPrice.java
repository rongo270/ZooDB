package sqlDataBase;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class AnimalPrice {
	
	
	private static String url;
	private static String user;
	private static String password;
	

	public void setTheFileLocation(String url, String user, String password) {
		this.url = url;
		this.user = user;
		this.password = password;
	}
	
	
	public void firstSetAnimalPrice(){
		Connection connection = null;
	    PreparedStatement stmt = null;
	    ResultSet rs = null;
		try {
			connection = DriverManager.getConnection(url, user, password);
			String countQuery = "SELECT COUNT(*) AS row_count FROM animals_price";
	        stmt = connection.prepareStatement(countQuery);
	        rs = stmt.executeQuery();
			if(rs.next() && rs.getInt("row_count") == 0) {
			    String insertQuery = "INSERT INTO animals_price(name_of_animal, price) VALUES(?, ?)";
	            stmt = connection.prepareStatement(insertQuery);

		                stmt.setString(1, "FISH");
		                stmt.setInt(2, 20);
		                stmt.addBatch();

		                stmt.setString(1, "PENGUIN");
		                stmt.setInt(2, 50);
		                stmt.addBatch();

		                stmt.setString(1, "TIGER");
		                stmt.setInt(2, 100);
		                stmt.addBatch();

		                stmt.setString(1, "LION");
		                stmt.setInt(2, 150);
		                stmt.addBatch();

		                stmt.setString(1, "LEMUR");
		                stmt.setInt(2, 250);
		                stmt.addBatch();

		                stmt.setString(1, "CERVUS");
		                stmt.setInt(2, 150);
		                stmt.addBatch();

		                stmt.executeBatch();	
		                System.out.println("Animal prices were inserted successfully.");
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
		finally {
			try {
	            if (rs != null) rs.close();
	            if (stmt != null) stmt.close();
	            if (connection != null) connection.close();
			}catch (SQLException e) {
	            e.printStackTrace();
	        }
		}
	}
	
	
	public int getPrice(String name) {
		Connection connection = null;
		PreparedStatement stmt = null;
		 int price = 50;
		 ResultSet rs = null;
		 try {
			 connection = DriverManager.getConnection(url,user,password);
			 String nameToSelcet = "SELECT price FROM animals_price WHERE name_of_animal = ?";
			 stmt = connection.prepareStatement(nameToSelcet);
			 stmt.setString(1, name);
			 rs = stmt.executeQuery();
			 if(rs.next()) {
				 price = rs.getInt("price");
			 }
			}catch (SQLException e) {
	            e.printStackTrace();
	        }
			finally {
				try {
		            if (rs != null) rs.close();
		            if (stmt != null) stmt.close();
		            if (connection != null) connection.close();
				}catch (SQLException e) {
		            e.printStackTrace();
		        }
			}
		 return price;
	}
	
	public void SetAnimalPrice(String name, int price) {
	    Connection connection = null;
	    PreparedStatement stmtSelect = null;
	    PreparedStatement stmtUpdate = null;
	    ResultSet rs = null;
	    try {
	        // Establish connection
	        connection = DriverManager.getConnection(url, user, password);

	        // Prepare the SELECT query to check if the animal exists
	        String nameToSelect = "SELECT price FROM animals_price WHERE name_of_animal = ?";
	        stmtSelect = connection.prepareStatement(nameToSelect);
	        stmtSelect.setString(1, name);
	        rs = stmtSelect.executeQuery();

	        // Check if the animal exists
	        if (rs.next()) {
	            // Prepare the UPDATE query
	            String updatePriceQuery = "UPDATE animals_price SET price = ? WHERE name_of_animal = ?";
	            stmtUpdate = connection.prepareStatement(updatePriceQuery);

	            // Set the parameters for the update query
	            stmtUpdate.setInt(1, price);
	            stmtUpdate.setString(2, name);

	            // Execute the update
	            stmtUpdate.executeUpdate();
	        } else {
	            System.out.println("Error: Animal not found.");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        // Close all resources
	        try {
	            if (rs != null) rs.close();
	            if (stmtSelect != null) stmtSelect.close();
	            if (stmtUpdate != null) stmtUpdate.close();
	            if (connection != null) connection.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}
}
