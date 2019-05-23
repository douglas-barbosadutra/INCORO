package it.contrader.controller;

import java.io.UnsupportedEncodingException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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

import it.contrader.dto.BehaviorDTO;
import it.contrader.dto.LinkTKDTO;
import it.contrader.dto.ParamDTO;
import it.contrader.dto.ThingDTO;
import it.contrader.dto.KeywordDTO;
import it.contrader.services.LinkTKService;
import it.contrader.utils.JwtUtils;


@CrossOrigin(value="*")
@RestController
@RequestMapping("/LinkTK")

public class LinkTKController {
	private final LinkTKService linkTKService;
	
	@Autowired 
	public LinkTKController(LinkTKService linkTKService) {
		this.linkTKService = linkTKService;
	}
	
	/*
	@RequestMapping(value="/deleteLinkTK" , method= RequestMethod.DELETE)
	public boolean deleteLinkTK(@RequestParam(value="id") Integer id) {		
		return linkTKService.deleteLinkTK(id);
	}*/
	
	/*
	@RequestMapping(value="/showLinkTK" , method= RequestMethod.GET)
	public List<LinkTKDTO> showLinkTK() {		
		return lkts.getListLinkTKDTO();
	}*/
	
	/*
	@RequestMapping(value="/updateLinkTK" , method= RequestMethod.PUT)
	public LinkTKDTO showLinkTK(@RequestBody LinkTKDTO link) {		
		return linkTKService.insertLinkTK(link);
	}*/
	
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
	
	@RequestMapping(value="/deleteLinkTK" , method= RequestMethod.POST)
	public ResponseEntity<Boolean> deleteLinkTK(@RequestBody ParamDTO paramDTO) {			
		int type;
		try {
			type = this.getTypeFromJwt(paramDTO.getJwt());
			if(type == 1) {
				int idLinkTK = (int) paramDTO.getParam();
				return ResponseEntity.status(HttpStatus.OK).body(linkTKService.deleteLinkTK(idLinkTK));
			}
			else
				return ResponseEntity.status(HttpStatus.OK).body(null);

			} catch (ExpiredJwtException | UnsupportedEncodingException e) {
				return ResponseEntity.status(HttpStatus.OK).body(null);
			}

		}
	
	@RequestMapping(value="/insertLinkTK", method= RequestMethod.POST)
	public ResponseEntity<LinkTKDTO> insertbehavior(@RequestBody ParamDTO paramDTO) {
		int rank;
		int idUser;
			
		try {
			rank = this.getTypeFromJwt(paramDTO.getJwt());
				
			if(rank == 1) {
					
				idUser = this.getIdUserFromJwt(paramDTO.getJwt());
					
				LinkedHashMap linkTK = (LinkedHashMap) paramDTO.getParam();
				LinkTKDTO linkTKDTO = new LinkTKDTO(0, (ThingDTO)(linkTK.get("thing")), (KeywordDTO)(linkTK.get("keyword")));
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
	int idUser;
			
	try {
			rank = this.getTypeFromJwt(paramDTO.getJwt());	
				if(rank == 1) {
					idUser = this.getIdUserFromJwt(paramDTO.getJwt());
					LinkedHashMap linkTK = (LinkedHashMap) paramDTO.getParam();
					LinkTKDTO linkTKDTO = new LinkTKDTO(Integer.parseInt(linkTK.get("idLinkTK").toString()),(ThingDTO)(linkTK.get("thing")), (KeywordDTO)(linkTK.get("keyword")));
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
	
/*
@RequestMapping(value="/insertLinkTK", method = RequestMethod.POST)
private LinkTKDTO insertLinkTKprova(@RequestBody LinkTKDTO linkTKDTO) {
	Integer idThing = linkTKDTO.getIdthing();
	Integer idKeyword = linkTKDTO.getIdKeyword();
	
	if (lkts.getLinkTKDTOByThingAndKeyword(thingDTO, keywordDTO) == null) {
		return lkts.insertLinkTK(linkTKDTO);
		
	} else {
		return null;
	}
}*/

/*
@RequestMapping(value="/insertLinkTK", method= RequestMethod.POST)
public LinkTKDTO insertLinkTK(@RequestBody LinkTKDTO link) {
	return lkts.insertLinkTK(link);
}
*/
