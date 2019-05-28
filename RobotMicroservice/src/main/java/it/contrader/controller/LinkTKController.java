package it.contrader.controller;

import java.io.UnsupportedEncodingException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import it.contrader.dto.LinkTKDTO;
import it.contrader.dto.ParamDTO;
import it.contrader.dto.ThingDTO;
import it.contrader.dto.KeywordDTO;
import it.contrader.services.LinkTKService;
import it.contrader.services.KeywordService;
import it.contrader.services.ThingService;
import it.contrader.utils.JwtUtils;

@CrossOrigin(value="*")
@RestController
@RequestMapping("/LinkTK")

public class LinkTKController {
	private final LinkTKService linkTKService;
	private final KeywordService keywordService;
	private final ThingService thingService;
	
@Autowired 
public LinkTKController(LinkTKService linkTKService, KeywordService keywordService, ThingService thingService) {
	this.linkTKService = linkTKService;
	this.keywordService = keywordService;
	this.thingService = thingService;
}
	
@RequestMapping(value = "/showLinkTK", method = RequestMethod.GET)
public ResponseEntity<List<LinkTKDTO>> showLinkTK(@RequestParam(value="jwt") String jwt) {
	int type;
	try {			
		type = this.getTypeFromJwt(jwt);
		if(type == 1) {
			int idUser = this.getIdUserFromJwt(jwt);
			return ResponseEntity.status(HttpStatus.OK).body(linkTKService.getListLinkTKDTO());
			}
			else
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		} catch (ExpiredJwtException | UnsupportedEncodingException e) {
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
	}
}
	
@RequestMapping(value = "/findLinkTKByKeyword", method = RequestMethod.POST)
public ResponseEntity<List<LinkTKDTO>> findLinkTKByKeyword(@RequestBody ParamDTO paramDTO) {
	int rank, idUser;
	try {	
		rank = this.getTypeFromJwt(paramDTO.getJwt());
		if(rank == 1) {
			idUser = this.getIdUserFromJwt(paramDTO.getJwt());
			LinkedHashMap keyword = (LinkedHashMap) paramDTO.getParam();
			int idKeyword = Integer.parseInt(keyword.get("idKeyword").toString());
			KeywordDTO keywordDTO = keywordService.findKeywordById(idKeyword);
			return ResponseEntity.status(HttpStatus.OK).body(linkTKService.getAllByKeyword(keywordDTO));
			}
			else
					return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
			} catch (ExpiredJwtException | UnsupportedEncodingException e) {
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
	}
}
	
@RequestMapping(value="/deleteLinkTK" , method= RequestMethod.POST)
public ResponseEntity<Boolean> deleteLinkTK(@RequestBody ParamDTO paramDTO) {			
	int type;
	try {
		type = this.getTypeFromJwt(paramDTO.getJwt());
		if(type == 1) {
			LinkedHashMap linkTKDTO = (LinkedHashMap) paramDTO.getParam();
			return ResponseEntity.status(HttpStatus.OK).body(linkTKService.deleteLinkTK(Integer.parseInt(linkTKDTO.get("idLinkTK").toString())));
			}
			else
				return ResponseEntity.status(HttpStatus.OK).body(null);
		} catch (ExpiredJwtException | UnsupportedEncodingException e) {
			return ResponseEntity.status(HttpStatus.OK).body(null);
	}
}
	
@RequestMapping(value="/insertLinkTK", method= RequestMethod.POST)
public ResponseEntity<LinkTKDTO> insertLinkTK(@RequestBody ParamDTO paramDTO) {
	int rank;
	int idUser, idThing, idKeyword;
	try {
		rank = this.getTypeFromJwt(paramDTO.getJwt());
		if(rank == 1) {
			idUser = this.getIdUserFromJwt(paramDTO.getJwt());
			LinkedHashMap linkTK = (LinkedHashMap) paramDTO.getParam();
			LinkedHashMap thingLHM = (LinkedHashMap) linkTK.get("thing");
			idThing = Integer.parseInt(thingLHM.get("idThing").toString());
			LinkedHashMap keywordLHM = (LinkedHashMap) linkTK.get("keyword");
			idKeyword = Integer.parseInt(keywordLHM.get("idKeyword").toString());
			ThingDTO thingDTO = thingService.findThingById(idThing);
			KeywordDTO keywordDTO = keywordService.findKeywordById(idKeyword);
			LinkTKDTO linkTKDTO = new LinkTKDTO(0, thingDTO, keywordDTO);
			LinkTKDTO linkTKDTOInsert = linkTKService.insertLinkTK(linkTKDTO);
			if(linkTKDTOInsert != null)
				return ResponseEntity.status(HttpStatus.OK).body(linkTKDTOInsert);
			else
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		}
		else
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);		
		} catch (ExpiredJwtException | UnsupportedEncodingException e) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
	}
}
	
@RequestMapping(value="/updateLinkTK", method= RequestMethod.PUT)
public ResponseEntity<LinkTKDTO> updateLinkTK(@RequestBody ParamDTO paramDTO) {
int rank;
int idUser, idThing, idKeyword;	
try {
	rank = this.getTypeFromJwt(paramDTO.getJwt());	
		if(rank == 1) {
			idUser = this.getIdUserFromJwt(paramDTO.getJwt());
			LinkedHashMap linkTK = (LinkedHashMap) paramDTO.getParam();
			LinkedHashMap thingLHM = (LinkedHashMap) linkTK.get("thing");
			idThing = Integer.parseInt(thingLHM.get("idThing").toString());
			ThingDTO thingDTO = thingService.findThingById(idThing);
			LinkedHashMap keywordLHM = (LinkedHashMap) linkTK.get("keyword");
			idKeyword = Integer.parseInt(keywordLHM.get("idKeyword").toString());
			KeywordDTO keywordDTO = keywordService.findKeywordById(idKeyword);
			LinkTKDTO linkTKDTO = new LinkTKDTO(Integer.parseInt(linkTK.get("idLinkTK").toString()), thingDTO, keywordDTO);
			LinkTKDTO linkTKDTOInsert = linkTKService.insertLinkTK(linkTKDTO);
			if(linkTKDTOInsert != null)
			return ResponseEntity.status(HttpStatus.OK).body(linkTKDTOInsert);
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