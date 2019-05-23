package it.contrader.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.contrader.dto.KeywordDTO;
import it.contrader.dto.ThingDTO;
import it.contrader.model.Keyword;
import it.contrader.services.KeywordService;
import it.contrader.services.LinkTKService;
import it.contrader.dto.LinkTKDTO;

@CrossOrigin(value="*")
@RestController
@RequestMapping("/Keyword")

public class KeywordController {
	private final KeywordService keywordService;
	private final LinkTKService linkTKService;
	
	@Autowired
	public KeywordController(KeywordService ks, LinkTKService ltks) {
		this.keywordService = ks;
		this.linkTKService = ltks;
	}
	
	@RequestMapping(value="/insertKeyword", method= RequestMethod.POST)
	public boolean insertKeyword(@RequestBody KeywordDTO keywordDTO) {
		return keywordService.insertKeyword(keywordDTO);
	}
	
	@RequestMapping(value="/updateKeyword", method= RequestMethod.PUT)
	public boolean updateKeyword(@RequestBody KeywordDTO KeywordDTO) {
		return keywordService.updateKeyword(KeywordDTO);
	}
	
	@RequestMapping(value="/deleteKeyword", method= RequestMethod.DELETE)
	public boolean deleteKeyword(@RequestParam(value="id") Integer id) {		
		return keywordService.deleteKeyword(id);
	}

	@RequestMapping (value="/showKeywords", method= RequestMethod.GET)
	public List<KeywordDTO> showKeywords(){
		return keywordService.getAllKeyword();
	}
	
	
	@RequestMapping (value="/showThingOfKey", method = RequestMethod.GET)
	public List<LinkTKDTO> showThingOfKey(@RequestParam(value="id") Integer id){
		KeywordDTO key = new KeywordDTO();
		key = keywordService.getKeywordById(id);
		return linkTKService.getAllByKeyword(key);
	}
}
;