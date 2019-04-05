package it.contrader.servlets;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import it.contrader.toXml.TableToDTD;
import it.contrader.toXml.TableToXML;

/**
 * Servlet implementation class XMLServlet
 */
public class XMLServlet extends HttpServlet {


	protected void service (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		final HttpSession session = request.getSession(true); //sto impostando una serie di pulsanti
		TableToDTD scrivi = new TableToDTD();
		
		try {
			scrivi.writeUsingFiles(TableToDTD.generateDTD());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Document doc = null;
		try {
			doc = TableToXML.generateXML();
		} catch (TransformerException | ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		getServletContext().getRequestDispatcher("/users.jsp").forward(request, response);
	}
	

}
