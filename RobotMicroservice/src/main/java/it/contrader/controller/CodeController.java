package it.contrader.controller;

import java.io.UnsupportedEncodingException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.ExpiredJwtException;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import it.contrader.dto.CodeDTO;
import it.contrader.dto.BehaviorDTO;
import it.contrader.dto.LabelDTO;
import it.contrader.dto.ParamDTO;
import it.contrader.services.CodeService;
import it.contrader.utils.JwtUtils;

@CrossOrigin(value="*")
@RestController
@RequestMapping("/Code")

public class CodeController {
	private CodeService codeService;
	
	@Autowired
	public CodeController(CodeService codeService) {
		this.codeService = codeService;
	}
	
	/*
	@RequestMapping(value="/updateCode", method= RequestMethod.PUT)
	public boolean updateCode(@RequestBody CodeDTO CodeDTO) {
		return codeService.updateCode(CodeDTO);
	}*/
	
	@RequestMapping(value = "/showCodes", method = RequestMethod.GET)
	public ResponseEntity<List<CodeDTO>> showCode(@RequestParam(value="jwt") String jwt) {
		int type;
		int idUser;
			
		try {			
			type = this.getTypeFromJwt(jwt);
			if(type == 1) {
				idUser = this.getIdUserFromJwt(jwt);
				return ResponseEntity.status(HttpStatus.OK).body(codeService.getLisCodeDTO());
				}
				else
					return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
			} catch (ExpiredJwtException | UnsupportedEncodingException e) {
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		}
	}
	
	@RequestMapping(value="/deleteCode" , method= RequestMethod.POST)
	public ResponseEntity<Boolean> deleteCode(@RequestBody ParamDTO paramDTO) {			
		int type;
		try {
			type = this.getTypeFromJwt(paramDTO.getJwt());
			if(type == 1) {
				int idCode = (int) paramDTO.getParam();
				return ResponseEntity.status(HttpStatus.OK).body(codeService.deleteCodeById(idCode));
			}
			else
				return ResponseEntity.status(HttpStatus.OK).body(null);

			} catch (ExpiredJwtException | UnsupportedEncodingException e) {
				return ResponseEntity.status(HttpStatus.OK).body(null);
		}
	}
	
	@RequestMapping(value="/insertCode", method= RequestMethod.POST)
	public ResponseEntity<CodeDTO> insertCode(@RequestBody ParamDTO paramDTO) {
		int rank;
		int idUser;
			
		try {
			rank = this.getTypeFromJwt(paramDTO.getJwt());
				
			if(rank == 1) {
					
				idUser = this.getIdUserFromJwt(paramDTO.getJwt());
					
				LinkedHashMap code = (LinkedHashMap) paramDTO.getParam();
				CodeDTO codeDTO = new CodeDTO(0,code.get("name").toString(), code.get("body").toString(), code.get("type").toString(),(BehaviorDTO)(code.get("behavior")));
				CodeDTO codeInsert = codeService.insertCode(codeDTO);
					
				if(codeInsert != null)
					return ResponseEntity.status(HttpStatus.OK).body(codeInsert);
				else
					return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
			}
			else
					return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);		
			} catch (ExpiredJwtException | UnsupportedEncodingException e) {
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		}
	}

@RequestMapping(value="/updateCode", method= RequestMethod.PUT)
public ResponseEntity<CodeDTO> updateCode(@RequestBody ParamDTO paramDTO) {
int rank;
int idUser;
			
try {
		rank = this.getTypeFromJwt(paramDTO.getJwt());	
			if(rank == 1) {
				idUser = this.getIdUserFromJwt(paramDTO.getJwt());
				LinkedHashMap code = (LinkedHashMap) paramDTO.getParam();
				CodeDTO codeDTO = new CodeDTO(Integer.parseInt(code.get("idCode").toString()),code.get("name").toString(), code.get("body").toString(), code.get("type").toString(),  (BehaviorDTO) (code.get("behavior")));
				CodeDTO codeInsert = codeService.insertCode(codeDTO);
				if(codeInsert != null)
					return ResponseEntity.status(HttpStatus.OK).body(codeInsert);
				else
					return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
			}
			else
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		} catch (ExpiredJwtException | UnsupportedEncodingException e) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
	}
}
	
private int getTypeFromJwt(String jwt) throws ExpiredJwtException, UnsupportedEncodingException {
		Map<String, Object> data = JwtUtils.jwt2Map(jwt);
		int type = Integer.parseInt(data.get("scope").toString());
		return type;
	}
	
//func che estrae l'id utente dalla Jwt.
private int getIdUserFromJwt(String jwt) throws ExpiredJwtException, UnsupportedEncodingException {
		Map<String, Object> data = JwtUtils.jwt2Map(jwt);
		int idUser = Integer.parseInt(data.get("subject").toString());
		return idUser;
	}	
}

/*
@RequestMapping(value="/insertCode", method= RequestMethod.POST)
public boolean insertCode(@RequestBody CodeDTO CodeDTO) {
	return codeService.insertCode(CodeDTO);
}*/

/*
@RequestMapping(value="/delete" , method= RequestMethod.DELETE)
public void delete(@RequestParam(value="idCode") CodeDTO CodeDTO) {		
	codeService.deleteCodeById(CodeDTO.getIdCode());
}*/