package it.contrader.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
//import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.contrader.dto.ThingDTO;
import it.contrader.dto.UserDTO;
import it.contrader.services.ThingService;

import java.util.List;

@Controller
@RequestMapping("/Thing")
public class ThingController {
	
	private final ThingService thingService;
	private HttpSession session;
	private int idThing;
	
	@Autowired 
	public ThingController(ThingService thingService) {
		this.thingService = thingService;
	}
	
	private void visualThing(HttpServletRequest request){
		List<ThingDTO> allThing = this.thingService.getListThingDTO();
		//System.out.println("lista things: " + allThing);
		request.getSession().setAttribute("allThing", allThing);
	}
		
	@RequestMapping(value ="/thingManagement", method = RequestMethod.GET)
	public String thingManagement(HttpServletRequest request) {
		visualThing(request);
		return "showThing";		
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("id"));
		request.getSession().setAttribute("id", id);
		this.thingService.deleteThingById(id);
		visualThing(request);
		return "showThing";
	}
	
	@RequestMapping(value = "/crea", method = RequestMethod.GET)
	public String insert(HttpServletRequest request) {
		//visualThing(request);
		//request.setAttribute("option", "insert");
		return "creaThing";
	}

	@RequestMapping(value = "/creaThing", method = RequestMethod.POST)
	public String insertThing(HttpServletRequest request) {
		String code = request.getParameter("code").toString();
		String image = request.getParameter("image").toString();
		String name = request.getParameter("name").toString();
		String xml = request.getParameter("xml").toString();
		Integer idLabel = Integer.parseInt(request.getParameter("idLabel"));
		Integer idUser = Integer.parseInt(request.getParameter("idUser"));
		ThingDTO thingObj = new ThingDTO(0, code, image, name, xml, idLabel, idUser);
		thingService.insertThing(thingObj);
		//visualThing(request);
		return "homeBO";
	}
	
	@RequestMapping(value="/openUpdateThing")
	public String openUpdateThing(HttpServletRequest request) {
		idThing = Integer.parseInt(request.getParameter("id"));
		System.out.println("id: "+idThing);
		return "thingUpdate";
	}
	
	@RequestMapping(value="/updateThing", method= RequestMethod.POST)
	public String updateThing(HttpServletRequest request) {
		String code = request.getParameter("code");
		String image = request.getParameter("image");
		String name = request.getParameter("name");
		String xml = request.getParameter("xml");
		Integer idLabel = Integer.parseInt(request.getParameter("idLabel"));
		Integer idUser = Integer.parseInt(request.getParameter("idUser"));
		ThingDTO thingDTO = new ThingDTO(idThing, code, image, name, xml, idLabel, idUser);
		thingService.insertThing(thingDTO);
		return "homeBO";
	}
	
	@RequestMapping(value = "/cercaThing", method = RequestMethod.GET)
	public String cercaThing(HttpServletRequest request) {
		final String content = request.getParameter("search");
		List<ThingDTO> allThing = this.thingService.findThingDTOByName(content);
		request.setAttribute("allThingDTO", allThing);
		return "homeThing";
	}
}