package it.contrader.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.contrader.dto.LabelDTO;
import it.contrader.dto.UserDTO;
import it.contrader.services.LabelService;
import it.contrader.services.UserService;

import java.util.List;

@Controller
@RequestMapping("/Label")
public class LabelController {
	
	private final LabelService labelService;
	private HttpSession session;
	private int idLabel;
	private int idUser;
	
	@Autowired
	public LabelController(LabelService labelService) {
		this.labelService = labelService;
	}
	
	private void visualLabel(HttpServletRequest request) {
		List<LabelDTO> allLabel = this.labelService.findLabelbyUser(idUser);
		request.getSession().setAttribute("allLabel", allLabel);
	}
	
	@RequestMapping(value = "/labelManagement", method = RequestMethod.GET)
	public String labelManagement(HttpServletRequest request) {
		idUser = Integer.parseInt(request.getParameter("idUser"));
		visualLabel(request);
		return "showLabel";
	}
	
	@RequestMapping(value = "/indietro", method = RequestMethod.GET)
	public String indietro(HttpServletRequest request) {
		
		visualLabel(request);
		return "homeBO";
	}

	@RequestMapping(value ="/delete", method = RequestMethod.GET)
	public String delete(HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("idLabel"));
		//request.getSession().setAttribute("id", id);
		this.labelService.deleteLabelById(id);
		visualLabel(request);
		return "showLabel";
	}
	
	@RequestMapping(value = "/crea", method = RequestMethod.GET)
	public String insert(HttpServletRequest request) {
		idUser = Integer.parseInt(request.getParameter("idUser"));
		visualLabel(request);
		//request.setAttribute("option", "insert");
		return "creaLabel";
	}
	
	@RequestMapping(value="/creaLabel", method = RequestMethod.POST)
	private String insertLabel(HttpServletRequest request) {
		String name = request.getParameter("name").toString();
		//Integer idUser = Integer.parseInt(request.getParameter("idUser"));
		//int idUser = UserService.getUserLogged().getIdUser();
		LabelDTO labelObj = new LabelDTO(0, name, idUser);
		labelService.insertLabel(labelObj);
		visualLabel(request);
		return "homeBO";
	}
	
	@RequestMapping(value = "/openUpdate", method = RequestMethod.GET)
	public String openUpdate (HttpServletRequest request) {
		idLabel=Integer.parseInt(request.getParameter("idLabel"));
		visualLabel(request);
		return "modificaLabel";	
}
	@RequestMapping(value = "/modifica", method = RequestMethod.GET)
	public String modifica(HttpServletRequest request) {
		//int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name").toString();
		LabelDTO labelObj = new LabelDTO(idLabel, name, idUser);
		//UserDTO userObj = new UserDTO(0, username, password, type,"");
		labelService.updateLabel(labelObj);
		visualLabel(request);
		return "homeBO";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout (HttpServletRequest request) {
		session.invalidate();
		return "index";	
	
	}
}

















