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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.ExpiredJwtException;
import it.contrader.dto.KeywordDTO;
import it.contrader.services.KeywordService;
import it.contrader.services.LinkTKService;
import it.contrader.utils.JwtUtils;
import it.contrader.dto.LinkTKDTO;
import it.contrader.dto.ParamDTO;

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

@RequestMapping (value="/showKeywords", method= RequestMethod.GET)
public List<KeywordDTO> showKeywords(){
	return keywordService.getAllKeyword();
}
	
@RequestMapping (value="/showThingOfKey", method = RequestMethod.GET)
public List<LinkTKDTO> showThingOfKey(@RequestParam(value="id") Integer id){
	KeywordDTO key = new KeywordDTO();
	key = keywordService.findKeywordById(id);
	return linkTKService.getAllByKeyword(key);
}
	
@RequestMapping(value = "/showKeyword", method = RequestMethod.GET)
public ResponseEntity<List<KeywordDTO>> showKeyword(@RequestParam(value="jwt") String jwt) {
	int type;
	int idUser;		
	try {			
		type = this.getTypeFromJwt(jwt);
		if(type == 1) {
			idUser = this.getIdUserFromJwt(jwt);
			return ResponseEntity.status(HttpStatus.OK).body(keywordService.getAllKeyword());
			}
			else
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		} catch (ExpiredJwtException | UnsupportedEncodingException e) {
	return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
	}
}
	
@RequestMapping(value="/deleteKeyword" , method= RequestMethod.POST)
public ResponseEntity<Boolean> deleteKeyword(@RequestBody ParamDTO paramDTO) {			
	int type;
	try {
		type = this.getTypeFromJwt(paramDTO.getJwt());
		if(type == 1) {
			LinkedHashMap keyword = (LinkedHashMap) paramDTO.getParam();
			return ResponseEntity.status(HttpStatus.OK).body(keywordService.deleteKeyword(Integer.parseInt(keyword.get("idKeyword").toString())));		                                                 
			}
		else
			return ResponseEntity.status(HttpStatus.OK).body(null);
		} catch (ExpiredJwtException | UnsupportedEncodingException e) {
			return ResponseEntity.status(HttpStatus.OK).body(null);
	}
}
	
@RequestMapping(value="/insertKeyword", method= RequestMethod.POST)
public ResponseEntity<KeywordDTO> insertKeyword(@RequestBody ParamDTO paramDTO) {
	int rank;
	int idUser;		
	try {
		rank = this.getTypeFromJwt(paramDTO.getJwt());
		if(rank == 1) {
			idUser = this.getIdUserFromJwt(paramDTO.getJwt());
			LinkedHashMap keyword = (LinkedHashMap) paramDTO.getParam();
			KeywordDTO keywordDTO = new KeywordDTO(0, keyword.get("name").toString());
			KeywordDTO keywordInsert = keywordService.insertKeyword(keywordDTO);
			if(keywordInsert != null)
				return ResponseEntity.status(HttpStatus.OK).body(keywordInsert);
			else
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		}
		else
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);		
		} catch (ExpiredJwtException | UnsupportedEncodingException e) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
	}
}
	
@RequestMapping(value="/updateKeyword", method= RequestMethod.PUT)
public ResponseEntity<KeywordDTO> updateKeyword(@RequestBody ParamDTO paramDTO) {
int rank;		
try {
		rank = this.getTypeFromJwt(paramDTO.getJwt());	
			if(rank == 1) {
				int idUser = this.getIdUserFromJwt(paramDTO.getJwt());
				LinkedHashMap keyword = (LinkedHashMap) paramDTO.getParam();
				KeywordDTO keywordDTO = new KeywordDTO(Integer.parseInt(keyword.get("idKeyword").toString()),keyword.get("name").toString());
				KeywordDTO keywordInsert = keywordService.insertKeyword(keywordDTO);
				if(keywordInsert != null)
					return ResponseEntity.status(HttpStatus.OK).body(keywordInsert);
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