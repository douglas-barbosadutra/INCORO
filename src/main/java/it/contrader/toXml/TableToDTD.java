package it.contrader.toXml;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;




public class TableToDTD {

	public static String generateDTD() throws SQLException {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/incorojava2", "root", "root");
		} catch (Exception e) {
			System.out.println(e);
			System.exit(0);
		}

		pstmt = con.prepareStatement("select * from things");

		rs = pstmt.executeQuery();

		System.out.println("Col count pre ");
		ResultSetMetaData rsmd = rs.getMetaData();// to retrieve table name, column name, column type and column
													// precision, etc..
		int colCount = rsmd.getColumnCount();
		String data = "<!DOCTYPE " + (rsmd.getTableName(1)) + " [\r\n" + 
		"<!ELEMENT " + (rsmd.getTableName(1)) + "("
				+ rsmd.getColumnName(1) + ", " 
				+ rsmd.getColumnName(2) + ", " 
				+ rsmd.getColumnName(3) + ", "
				+ rsmd.getColumnName(4) + ", "
				+ rsmd.getColumnName(5) + ", "
				+ rsmd.getColumnName(6) + ")+>\r\n" +

				"<!ELEMENT " + rsmd.getColumnName(1) + " EMPTY>\r\n" + 
				"<!ATTLIST " + rsmd.getColumnName(1) + " id "+ rsmd.getColumnTypeName(1) + " #REQUIRED>\r\n" + 
				"<!ELEMENT " + rsmd.getColumnName(2) + " (#PCDATA)>\r\n" + 
				"<!ELEMENT " + rsmd.getColumnName(3) + " (#PCDATA)>\r\n" + 
				"<!ATTLIST " + rsmd.getColumnName(3) + " idrefs " + rsmd.getColumnTypeName(3) + " #REQUIRED>\r\n" + 
				"<!ELEMENT " + rsmd.getColumnName(4) + " (#PCDATA)>\r\n" + 
				"<!ATTLIST " + rsmd.getColumnName(4) + " idrefs " + rsmd.getColumnTypeName(4) + " #REQUIRED>\r\n" + 
				"<!ELEMENT " + rsmd.getColumnName(5) + " (#PCDATA)>\r\n" +
				"<!ELEMENT " + rsmd.getColumnName(6) + " ANY> ]>\r\n";

		System.out.println("Done creating DTD File");
		return data;

	}

	public static void writeUsingFiles(String data) {
		try {
			Files.write(Paths.get("C:\\Program Files\\things.dtd"), data.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

// return sw.toString();

}
