package sqlDataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import java.sql.ResultSet;


public class MoneyDataBase {
	
	private static String url;
	private static String user;
	private static String password;
	

	public void setTheFileLocation(String url, String user, String password) {
		this.url = url;
		this.user = user;
		this.password = password;
	}
	
	public int GetMoney() {
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			connection = DriverManager.getConnection(url, user, password);
			String query = "SELECT amount FROM money WHERE creditcardid = 1";
			stmt = connection.prepareStatement(query);
			rs = stmt.executeQuery();
			
			if(rs.next()) {
			int money = rs.getInt("amount");
			return money;
			}
			else {	
				FirstMakeMoney();
				return 6000;
			}
		}
		catch (SQLException e) {
			 e.printStackTrace();
			 return 0;
		}	
		finally {
			try {
	            if (rs != null) rs.close();
	            if (stmt != null) stmt.close();
	            if (connection != null) connection.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
		}
	}
	
	public void FirstMakeMoney() {
		Connection connection = null;
		PreparedStatement stmt = null;

		try {
			connection = DriverManager.getConnection(url, user, password);
			String insertIntoMoney= "INSERT INTO money (creditcardid,amount) VALUES (1,6000)";
			stmt = connection.prepareStatement(insertIntoMoney);
			stmt.executeUpdate();
			System.out.println("money was insert");
		}
		catch (SQLException e) {
			
		}
	}
	
	public void UpdateMoney(int priceChange) {
		JOptionPane IO = new JOptionPane();
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			connection = DriverManager.getConnection(url, user, password);
			String getMoney= " SELECT amount FROM money WHERE creditcardid = 1";
			stmt = connection.prepareStatement(getMoney);
			rs = stmt.executeQuery();
			if(rs.next()) {
				int currentAmount = rs.getInt("amount");
				int updatedAmount = currentAmount + priceChange;
			String SetMoney = "UPDATE money SET amount = ? WHERE creditcardid = 1";
			stmt = connection.prepareStatement(SetMoney);
			stmt.setInt(1, updatedAmount);
			//IO.showMessageDialog(null , "money has change from - " + currentAmount + " to - "+ updatedAmount);
			}
			stmt.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	
	public void ResetMoney() {
		Connection connection = null;
		PreparedStatement stmt = null;
		try {
			connection = DriverManager.getConnection(url,user,password);
			String Reset = "UPDATE money SET amount = 6000 WHERE creditcardid = 1";
			stmt = connection.prepareStatement(Reset);
			stmt.executeUpdate();
			System.out.println("Money was Reset");
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
