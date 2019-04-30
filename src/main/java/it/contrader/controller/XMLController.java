package it.contrader.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.w3c.dom.Document;

import it.contrader.toXml.TableToDTD;
import it.contrader.toXml.TableToXML;

@Controller
@RequestMapping("/XML")
public class XMLController {
	//private HttpSession session;
	
	@RequestMapping(value = "/export", method = RequestMethod.GET)
	public String esporta(HttpServletRequest request) {
	try {
		TableToDTD.writeUsingFiles(TableToDTD.generateDTD());
	} catch (SQLException e1) {
		e1.printStackTrace();
	}
	
	Document doc = null;
	try {
		doc = TableToXML.generateXML();
	} catch (TransformerException | ParserConfigurationException e) {
		e.printStackTrace();
	}
	return("homeAdmin");
}
}