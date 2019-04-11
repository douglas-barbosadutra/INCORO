package it.contrader.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.contrader.dto.LabelDTO;
import it.contrader.services.LabelService;

import java.util.List;

@Controller
@RequestMapping("/Label")
public class LabelController {
	
	private final LabelService labelService;
	private HttpSession session;
	private int idLabel;
	
	@Autowired
	public LabelController(LabelService labelService) {
		this.labelService = labelService;
	}
	
	private void visualLabel(HttpServletRequest request) {
		List<LabelDTO> allLabel = this.labelService.getListLabelDTO();
		request.getSession().setAttribute("allLael", allLabel);
	}
	
	@RequestMapping(value = "/labelManagement", method = RequestMethod.GET)
	public String labelManagement(HttpServletRequest request) {
		visualLabel(request);
		return "showLabel";
	}

	@RequestMapping(value ="/delete", method = RequestMethod.GET)
	public String delete(HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("id"));
		request.getSession().setAttribute("id", id);
		this.labelService.deleteLabelById(id);
		visualLabel(request);
		return "showLabel";
	}
	
	@RequestMapping(value = "/crea", method = RequestMethod.GET)
	public String insert(HttpServletRequest request) {
		//visualThing(request);
		//request.setAttribute("option", "insert");
		return "creaLabel";
	}
	
	@RequestMapping(value="/creaLabel", method = RequestMethod.GET)
	private String insertLabel(HttpServletRequest request) {
		String name = request.getParameter("name").toString();
		Integer idUser = Integer.parseInt(request.getParameter("idUser"));
		LabelDTO labelObj = new LabelDTO(0, name, idUser);
		labelService.insertLabel(labelObj);
		return "homeBO";
	}
	
	@RequestMapping(value= "/opnUpdateLabel", method = RequestMethod.GET)
	public String updateLabel(HttpServletRequest request) {
		String name = request.getParameter("name");
		Integer idUser = Integer.parseInt(request.getParameter("idUser"));
		LabelDTO labelDTO = new LabelDTO(idLabel, name, idUser);
		labelService.insertLabel(labelDTO);
		return "homeBO";
	}
}

















