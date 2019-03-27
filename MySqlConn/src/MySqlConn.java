import java.sql.*;
public class MySqlConn {
	public static void main(String[] args) {
		
		// parametri di connessione
		 String vendor = "mysql";
		 String  jdbcAdditionalParams= "useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		 String host = "127.0.0.1";
		 String port = "3306";
		 String dbName = "incorojava";
		 String username ="root";
		 String password ="root";
		 
		String DRIVER = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:" + vendor + "://" + host + ":" + port + "/" + dbName+"?"+jdbcAdditionalParams;
		
		try {
			Class.forName(DRIVER).newInstance();
		} catch (ClassNotFoundException | IllegalAccessException | InstantiationException  e1) {
			System.out.println("Driver non trovato...");
			System.exit(1);
		}
		Connection connection = null;
		System.out.println("Driver trovarto");
		System.out.println("Avvio la connessione");
		try {
			// Connessione al RDBMS mySql
			connection = DriverManager.getConnection(url, username, password);
			Statement statement = connection.createStatement();
			ResultSet resultset = statement.executeQuery("SELECT * FROM labels");
			if(resultset.next()==false) {
				System.out.println("non ci sono record da viusalizzare");
			}
			while (resultset.next()) {
					System.out.println(resultset.getString("nomeLabel"));
			}
		} catch (SQLException e) {
			System.out.println("Conessione fallita...");
			System.exit(1);
		} finally {
			if (connection != null) {
				try {
					connection.close();
					} catch (Exception e) {
				}
			}
		}
		System.out.println("eseguito correttamente");
	}
}