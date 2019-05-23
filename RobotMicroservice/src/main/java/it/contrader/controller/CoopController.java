package it.contrader.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.contrader.dto.CoopDTO;
import it.contrader.dto.KeywordDTO;
import it.contrader.dto.ThingDTO;
import it.contrader.model.Keyword;
import it.contrader.services.CoopService;
import it.contrader.services.KeywordService;
import it.contrader.services.LinkTKService;
import it.contrader.dto.LinkTKDTO;

@CrossOrigin(value="*")
@RestController
@RequestMapping("/Coop")

public class CoopController {
	private final CoopService coopService;
	
	@Autowired
	public CoopController(CoopService ks) {
		this.coopService = ks;
	}
	


	@RequestMapping (value="/showCoop", method= RequestMethod.GET)
	public List<CoopDTO> showCoop(){
		return coopService.getAllCoop();
	}

}
;