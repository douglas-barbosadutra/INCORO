package it.contrader.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import it.contrader.dto.LinkTKDTO;

import it.contrader.services.LinkTKService;


@CrossOrigin(value="*")
@RestController
@RequestMapping("/LinkTK")

public class LinkTKController {
	private final LinkTKService lkts;
	
	@Autowired 
	public LinkTKController(LinkTKService lkts) {
		this.lkts = lkts;
	}
	
	@RequestMapping(value="/insertLinkTK", method= RequestMethod.POST)
	public LinkTKDTO insertLinkTK(@RequestBody LinkTKDTO link) {
		return lkts.insertLinkTK(link);
	}
	
	@RequestMapping(value="/deleteLinkTK" , method= RequestMethod.DELETE)
	public boolean deleteLinkTK(@RequestParam(value="id") Integer id) {		
		return lkts.deleteLinkTK(id);
	}
	
	/*
	@RequestMapping(value="/showLinkTK" , method= RequestMethod.GET)
	public List<LinkTKDTO> showLinkTK() {		
		return lkts.getAllLinkTK();
	}*/
	
	@RequestMapping(value="/updateLinkTK" , method= RequestMethod.PUT)
	public LinkTKDTO showLinkTK(@RequestBody LinkTKDTO link) {		
		return lkts.insertLinkTK(link);
	}
	
	
}
