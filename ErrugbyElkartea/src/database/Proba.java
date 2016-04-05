package database;

import java.sql.SQLException;

public class Proba {

	public static void main(String[] args) {
		Data dd = new Data();
		try {
			dd.setUserInfo("aaa", "aaa", "aaa", "aaa", "aaa", "aaa", "aaa", "aaa", "2016-03-03", "aaa", "Lizentziarik ez");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
