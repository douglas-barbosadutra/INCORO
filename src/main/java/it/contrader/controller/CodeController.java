package it.contrader.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import it.contrader.dto.CodeDTO;
import it.contrader.services.CodeService;


@CrossOrigin(value="*")
@RestController
@RequestMapping("/Code")

public class CodeController {
	private CodeService CodeService;
	
	@Autowired
	public CodeController(CodeService hs) {
		this.CodeService = hs;
	}
	
	@RequestMapping(value="/insertCode", method= RequestMethod.POST)
	public boolean insertCode(@RequestBody CodeDTO CodeDTO) {
		return CodeService.insertCode(CodeDTO);
	}
	
	@RequestMapping(value="/updateCode", method= RequestMethod.PUT)
	public boolean updateCode(@RequestBody CodeDTO CodeDTO) {
		return CodeService.updateCode(CodeDTO);
	}
	
	@RequestMapping(value="/delete" , method= RequestMethod.DELETE)
	public void delete(@RequestParam(value="idCode") CodeDTO CodeDTO) {		
		CodeService.deleteCodeById(CodeDTO.getIdCode());
	}
	
	@RequestMapping(value="/showCodes", method= RequestMethod.GET)
	public List<CodeDTO> showCodes(){
		return CodeService.getLisCodeDTO();
	}
}