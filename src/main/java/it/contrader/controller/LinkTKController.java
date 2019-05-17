package it.contrader.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import it.contrader.dto.KeywordDTO;
import it.contrader.dto.LinkTKDTO;
import it.contrader.dto.ThingDTO;
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
	
	@RequestMapping(value="/deleteLinkTK" , method= RequestMethod.DELETE)
	public boolean deleteLinkTK(@RequestParam(value="id") Integer id) {		
		return lkts.deleteLinkTK(id);
	}
	
	@RequestMapping(value="/showLinkTK" , method= RequestMethod.GET)
	public List<LinkTKDTO> showLinkTK() {		
		return lkts.getListLinkTKDTO();
	}
	
	@RequestMapping(value="/updateLinkTK" , method= RequestMethod.PUT)
	public LinkTKDTO showLinkTK(@RequestBody LinkTKDTO link) {		
		return lkts.insertLinkTK(link);
	}
	
	@RequestMapping(value="/insertLinkTK", method = RequestMethod.POST)
	private LinkTKDTO insertLinkTKprova(@RequestBody LinkTKDTO linkTKDTO) {
		ThingDTO thingDTO = linkTKDTO.getThing();
		KeywordDTO keywordDTO = linkTKDTO.getKeyword();
		if (lkts.getLinkTKDTOByThingAndKeyword(thingDTO, keywordDTO) == null) {
			return lkts.insertLinkTK(linkTKDTO);
			
		} else {
			return null;
		}
	}
	
	/*
	@RequestMapping(value="/insertLinkTK", method= RequestMethod.POST)
	public LinkTKDTO insertLinkTK(@RequestBody LinkTKDTO link) {
		return lkts.insertLinkTK(link);
	}
	*/
}
