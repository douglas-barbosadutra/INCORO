package it.contrader.toXml;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.w3c.dom.Document;

public class Tesstt {

	public static void main(String[] args) throws SQLException, ClassNotFoundException, TransformerException, ParserConfigurationException {
		TableToDTD scrivi = new TableToDTD();
		
		scrivi.writeUsingFiles(TableToDTD.generateDTD());
		
		Document doc = null;
		try {
			doc = TableToXML.generateXML();
		} catch (TransformerException | ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		// TODO Auto-generated method stub


	}

}
