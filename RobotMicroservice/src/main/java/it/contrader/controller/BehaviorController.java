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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import io.jsonwebtoken.ExpiredJwtException;



import it.contrader.dto.BehaviorDTO;
import it.contrader.services.BehaviorService;
import it.contrader.utils.JwtUtils;
import it.contrader.dto.ParamDTO;
import it.contrader.dto.ThingDTO;

@CrossOrigin(value="*")
@RestController
@RequestMapping("/Behavior")
public class BehaviorController {
	
private BehaviorService behaviorService;
	
@Autowired
public BehaviorController(BehaviorService behaviorService) {
	this.behaviorService = behaviorService;
}
	
/*
@RequestMapping(value="/insertBehavior", method= RequestMethod.POST)
public boolean insertBehavior(@RequestBody BehaviorDTO BehaviorDTO) {
	return behaviorService.insertBehavior(BehaviorDTO);
}*/
	
/*
@RequestMapping(value="/updateBehavior", method= RequestMethod.PUT)
public boolean updateBehavior(@RequestBody BehaviorDTO BehaviorDTO) {
	return behaviorService.updateBehavior(BehaviorDTO);
}*/
	
@RequestMapping(value = "/showBehavior", method = RequestMethod.GET)
public ResponseEntity<List<BehaviorDTO>> showBehavior(@RequestParam(value="jwt") String jwt) {
	int type;
		
	try {			
		type = this.getTypeFromJwt(jwt);
		if(type == 1) {
			//int idUser = this.getIdUserFromJwt(jwt);
			return ResponseEntity.status(HttpStatus.OK).body(behaviorService.getListBehaviorDTO());
			}
			else
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		} catch (ExpiredJwtException | UnsupportedEncodingException e) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
	}
}

@RequestMapping(value="/deleteBehavior" , method= RequestMethod.POST)
public ResponseEntity<Boolean> deleteBehavior(@RequestBody ParamDTO paramDTO) {			
	int type;
	try {
		type = this.getTypeFromJwt(paramDTO.getJwt());
		if(type == 1) {
			int idBehavior = (int) paramDTO.getParam();
			return ResponseEntity.status(HttpStatus.OK).body(behaviorService.deleteBehaviorById(idBehavior));
		}
		else
			return ResponseEntity.status(HttpStatus.OK).body(null);

		} catch (ExpiredJwtException | UnsupportedEncodingException e) {
			return ResponseEntity.status(HttpStatus.OK).body(null);
		}

	}

@RequestMapping(value="/insertBehavior", method= RequestMethod.POST)
public ResponseEntity<BehaviorDTO> insertBehavior(@RequestBody ParamDTO paramDTO) {
	int rank;
	int idUser;
		
	try {
		rank = this.getTypeFromJwt(paramDTO.getJwt());
			
		if(rank == 1) {
				
			idUser = this.getIdUserFromJwt(paramDTO.getJwt());
				
			LinkedHashMap behavior = (LinkedHashMap) paramDTO.getParam();
			BehaviorDTO behaviorDTO = new BehaviorDTO(0, behavior.get("name").toString(), (ThingDTO) behavior.get("thing"));
			BehaviorDTO behaviorInsert = behaviorService.insertBehavior(behaviorDTO);
				
			if(behaviorInsert != null)
				return ResponseEntity.status(HttpStatus.OK).body(behaviorInsert);
			else
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		}
		else
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);		
		} catch (ExpiredJwtException | UnsupportedEncodingException e) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
	}
}

@RequestMapping(value="/updateBehavior", method= RequestMethod.PUT)
public ResponseEntity<BehaviorDTO> updateBehavior(@RequestBody ParamDTO paramDTO) {
int rank;
int idUser;
		
try {
		rank = this.getTypeFromJwt(paramDTO.getJwt());	
			if(rank == 1) {
				idUser = this.getIdUserFromJwt(paramDTO.getJwt());
				LinkedHashMap behavior = (LinkedHashMap) paramDTO.getParam();
				BehaviorDTO behaviorDTO = new BehaviorDTO(Integer.parseInt(behavior.get("idBehavior").toString()),behavior.get("name").toString(), (ThingDTO) behavior.get("thing"));
				BehaviorDTO behaviorInsert = behaviorService.insertBehavior(behaviorDTO);
				if(behaviorInsert != null)
					return ResponseEntity.status(HttpStatus.OK).body(behaviorInsert);
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