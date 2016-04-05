package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import userinfo.UserInfo;

public class Data {
	private String url = "jdbc:mysql://localhost:3306/errugbyelkartea";
	private String user = "root"; 
	private String passwd = "Sopela95";
	private String driver = "com.mysql.jdbc.Driver";

	private Connection conn;

	public Data(){

		try {
			Class.forName(this.driver).newInstance();
			this.conn = DriverManager.getConnection(this.url,this.user,this.passwd);
		} catch (Exception e) {
			System.out.println("--Exception:  "+e.getMessage());
		}
	}

	public void setUserInfo(String ErabiltzaileIzena, String Pasahitza, String Izena, String Abizena, String Email, 
			String Kalea, String Herria, String PostaKodea, String JaiotzeData, String Telefonoa, String Lizentzia)
					throws SQLException{

		String query = "INSERT INTO users VALUES ('" + ErabiltzaileIzena + "', '" + Pasahitza + "', '" + Izena +
				"', '" + Abizena + "', '" + Email + "', '" + Kalea + "', '" + Herria + "', '" + PostaKodea +
				"', '" + JaiotzeData + "', '" + Telefonoa + "', '" + Lizentzia + "');";

		System.out.println("    --> DB query: " + query);

		Statement st = this.conn.createStatement();
		st.executeUpdate(query);  	
	}

	public String getUsername(String Email, String Pasahitza) {

		String query = "SELECT ErabiltzaileIzena FROM users WHERE Email ='" + Email + "' AND Pasahitza='" + Pasahitza + "';";

		System.out.println("     DB query: " + query);
		String ErabilzaileIzena = null;

		try {
			Statement st = this.conn.createStatement();
			ResultSet res = st.executeQuery(query); 
			while(res.next()) {
				ErabilzaileIzena = res.getString("ErabiltzaileIzena");
			}
		} catch(Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}
		return ErabilzaileIzena;
	}

	public void updateUserPassword(String Email, String Pasahitza){

		String query = "UPDATE users SET Pasahitza = '" + Pasahitza + "' WHERE Email = '" + Email + "'";

		System.out.println("    DB query: " + query);

		try {
			Statement st = this.conn.createStatement();
			st.executeUpdate(query);
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}
	}
	
	public String getUserPass(String Email){
		String query = "SELECT * FROM users WHERE Email='"+ Email +"';";
		
		System.out.println("    DB query "+ query);
		
		String pasahitza = null;
		try {
			Statement st = this.conn.createStatement();
			ResultSet res = st.executeQuery(query);
			while (res.next()) {
				pasahitza = res.getString("Pasahitza");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pasahitza;
	}

	public void updateUserInfo(String ErabiltzaileIzena, String Izena, String Abizena, String Email, String Kalea, String Herria,
			String PostaKodea, String JaiotzeData, String Telefonoa){

		String query = "UPDATE users SET ErabiltzaileIzena = '" + ErabiltzaileIzena + "', Izena = '" + Izena + "', Abizena = '" + Abizena +
				"', Kalea = '" + Kalea + "', Herria = '" + Herria + "', PostaKodea = '" + PostaKodea +
				"', JaiotzeData = '" + JaiotzeData + "', Telefonoa = '" + Telefonoa + "' WHERE Email = '" + Email + "';";

		System.out.println("    DB query: " + query);

		try {
			Statement st = this.conn.createStatement();
			st.executeUpdate(query);
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}
	}

	public UserInfo getUserInfo(String Email){
		String query = "SELECT * FROM users WHERE Email ='"+ Email +"';";

		System.out.println("    DB query: "+ query);
		
		UserInfo datuak = null;

		try {
			Statement st = this.conn.createStatement();
			ResultSet res = st.executeQuery(query);
			while (res.next()) {
				datuak = new UserInfo(res.getString("ErabiltzaileIzena"), res.getString("Izena"), res.getString("Abizena"),
						res.getString("Email"), res.getString("Kalea"), res.getString("Herria"), res.getString("PostaKodea"),
						res.getString("JaiotzeData"), res.getString("Telefonoa"), res.getString("Lizentzia"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return datuak;
	}

	public void updateUserLicence(String Email, String Lizentzia){

		String query = "UPDATE users SET Lizentzia = '" + Lizentzia + "' WHERE Email = '"+ Email +"';";
		System.out.println("    DB query: " + query);

		try {
			Statement st = this.conn.createStatement();
			st.executeUpdate(query);
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}
	}

//	public ArrayList<UserInfo> getLicencedUserInfo() {
//
//		String query = "SELECT * FROM users;";
//		System.out.println("     DB query: " + query);
//		ArrayList<UserInfo> infoList = new ArrayList<UserInfo>(); 
//
//		try {
//			Statement st = this.conn.createStatement();
//			ResultSet res = st.executeQuery(query); 
//
//			while(res.next()) {
//				infoList.add(new UserInfo(res.getString("ErabiltzaileIzena"), res.getString("Izena"), res.getString("Abizena"),
//						res.getString("Email"), res.getString("Kalea"), res.getString("Herria"), res.getString("PostaKodea"),
//						res.getString("JaiotzeData"), res.getString("Telefonoa"), res.getString("LizentziaMota")));
//
//			}			
//		} catch(Exception e) {
//			System.out.println("Exception: " + e.getMessage());
//		}
//		return infoList;
//	}

}
