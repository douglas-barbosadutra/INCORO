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
import it.contrader.services.KeywordService;

@CrossOrigin(value="*")
@RestController
@RequestMapping("/Keyword")

public class KeywordController {
	private KeywordService keywordService;
	
	@Autowired
	public KeywordController(KeywordService ks) {
		keywordService = ks;
	}
	
	@RequestMapping(value="/insertKeyword", method= RequestMethod.POST)
	public boolean insertKeyword(@RequestBody KeywordDTO keywordDTO) {
		return keywordService.insertKeyword(keywordDTO);
	}
	
	@RequestMapping(value="/updateKeyword", method= RequestMethod.PUT)
	public boolean updateKeyword(@RequestBody KeywordDTO KeywordDTO) {
		return keywordService.updateKeyword(KeywordDTO);
	}
	
	@RequestMapping(value="/delete" , method= RequestMethod.DELETE)
	public void delete(@RequestParam(value="idKeyword") KeywordDTO KeywordDTO) {		
		keywordService.deleteKeywordById(KeywordDTO.getIdKeyword());
	}

	@RequestMapping(value="/showKeywords", method= RequestMethod.GET)
	public List<KeywordDTO> showKeywords(){
		return keywordService.getAllKeyword();
	}

}
