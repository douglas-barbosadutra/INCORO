import java.sql.*;

public class ProvaJDBS {
	  public static void main (String args[]){
	    
	    try {
	      String driver = "sun.jdbc.odbc.JdbcOdbcDriver";
	      Class.forName(driver);
	      String url = "jdbc:odbc:myDataSource";
	      Connection con = DriverManager.getConnection(url, "username", "password");
	      Statement cmd = con.createStatement();
	      String query = "SELECT * FROM nomeTabella";
	      ResultSet res = cmd.executeQuery(query);
	      while (res.next()) {
	        System.out.println(res.getString("nomeColonna1"));
	        System.out.println(res.getString("nomeColonna2"));
	      }
	      res.close(); // chiudere le risorse DB è obbligatorio
	      cmd.close();
	      con.close();
	    }

	    catch (SQLException e){
	      e.printStackTrace();
	    }

	    catch (ClassNotFoundException e){
	      e.printStackTrace();
	    }
	  }
	}