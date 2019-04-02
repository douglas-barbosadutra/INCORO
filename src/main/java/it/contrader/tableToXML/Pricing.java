package it.contrader.tableToXML;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import org.w3c.dom.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import java.sql.ResultSet;


public class Pricing extends Object {
	 
	   public static void main (String args[]){
		   
		   Document mapDoc = null;
		    try {
		      //Create the DocumentBuilderFactory
		      DocumentBuilderFactory dbfactory = 
		               DocumentBuilderFactory.newInstance();
		      //Create the DocumentBuilder
		      DocumentBuilder docbuilder = dbfactory.newDocumentBuilder();
		      //Parse the file to create the Document
		      mapDoc = docbuilder.parse("mapping.xml");
		    } catch (Exception e) {
		      System.out.println("Problem creating document: "+e.getMessage());
		    }
		   String driverName = "com.mysql.jdbc.Driver";
		      String connectURL = "jdbc:mysql://127.0.0.1:3306/incorojava2";
		      Connection db = null;    
		      try {
		         Class.forName(driverName);
		         db = DriverManager.getConnection(connectURL, "root", "root");
		      } catch (ClassNotFoundException e) {
		         System.out.println("Error creating class: "+e.getMessage());
		      } catch (SQLException e) {
		         System.out.println("Error creating connection: "+e.getMessage());
		         Statement statement = null;
		         //Create the ResultSet object, which ultimately holds the data retrieved
		         ResultSet resultset = null;
		         try {
		            statement = db.createStatement();
		            //Execute the query to populate the ResultSet
		            resultset = statement.executeQuery("SELECT * FROM things");
		            
		          
		         if (resultset.next()) {
		             System.out.println("Data exists.");
		          } else {
		             System.out.println("No data exists.");
		          }
		         }
		         catch (SQLException ex) {
		            System.out.println("SQL Error: "+ex.getMessage());
		         
		         }   finally {
		            System.out.println("Closing connections...");
		            try {
		               db.close();
		            } catch (SQLException es) {
		               System.out.println("Can't close connection.");
		            }
		         }
		      }
	   }
}
		      
	 


