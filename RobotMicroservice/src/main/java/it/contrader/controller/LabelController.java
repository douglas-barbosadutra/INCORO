package it.contrader.controller;

//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;

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
import it.contrader.dto.LabelDTO;
import it.contrader.dto.ParamDTO;
import it.contrader.services.LabelService;
import it.contrader.utils.JwtUtils;
import java.io.UnsupportedEncodingException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(value="*")
@RestController
@RequestMapping("/Label")
public class LabelController {
	
private final LabelService labelService;
	
@Autowired
public LabelController(LabelService labelService) {
	this.labelService = labelService;	
}
	
@RequestMapping(value = "/showLabel", method = RequestMethod.GET)
public ResponseEntity<List<LabelDTO>> showLabel(@RequestParam(value="jwt") String jwt) {
	int type;
	int idUser;
	try {			
		type = this.getTypeFromJwt(jwt);
		if(type == 1) {
			idUser = this.getIdUserFromJwt(jwt);
			return ResponseEntity.status(HttpStatus.OK).body(labelService.findLabelbyIdUser(idUser));
			}
			else
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		} catch (ExpiredJwtException | UnsupportedEncodingException e) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
	}
}

@RequestMapping(value="/deleteLabel" , method= RequestMethod.POST)
public ResponseEntity<Boolean> deleteLabel(@RequestBody ParamDTO paramDTO) {		
	int type;
	try {
		type = this.getTypeFromJwt(paramDTO.getJwt());
		if(type == 1) {
			LinkedHashMap label = (LinkedHashMap) paramDTO.getParam();
			return ResponseEntity.status(HttpStatus.OK).body(labelService.deleteLabel(Integer.parseInt(label.get("idLabel").toString())));
		}	
		else
			return ResponseEntity.status(HttpStatus.OK).body(null);
		} catch (ExpiredJwtException | UnsupportedEncodingException e) {
			return ResponseEntity.status(HttpStatus.OK).body(null);
	}
}
	
@RequestMapping(value="/insertLabel", method= RequestMethod.POST)
public ResponseEntity<LabelDTO> insertLabel(@RequestBody ParamDTO paramDTO) {
	int rank;
	int idUser;		
	try {
		rank = this.getTypeFromJwt(paramDTO.getJwt());
		if(rank == 1) {
			idUser = this.getIdUserFromJwt(paramDTO.getJwt());
			LinkedHashMap label = (LinkedHashMap) paramDTO.getParam();
			LabelDTO labelDTO = new LabelDTO(0,label.get("name").toString(), idUser);
			LabelDTO labelInsert = labelService.insertLabel(labelDTO);
			if(labelInsert != null)
				return ResponseEntity.status(HttpStatus.OK).body(labelInsert);
			else
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		}
		else
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);		
		} catch (ExpiredJwtException | UnsupportedEncodingException e) {
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
	}
}

	
@RequestMapping(value="/updateLabel", method= RequestMethod.PUT)
public ResponseEntity<LabelDTO> updateLabel(@RequestBody ParamDTO paramDTO) {
int rank;
int idUser;
try {
	rank = this.getTypeFromJwt(paramDTO.getJwt());	
		if(rank == 1) {
			idUser = this.getIdUserFromJwt(paramDTO.getJwt());
			LinkedHashMap label = (LinkedHashMap) paramDTO.getParam();
			LabelDTO labelDTO = new LabelDTO(Integer.parseInt(label.get("idLabel").toString()),label.get("name").toString(), idUser);
			LabelDTO labelInsert = labelService.insertLabel(labelDTO);
			if(labelInsert != null)
				return ResponseEntity.status(HttpStatus.OK).body(labelInsert);
			else
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		}
		else
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);	
		} catch (ExpiredJwtException | UnsupportedEncodingException e) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
	}
}
	
@RequestMapping(value="/showLabelNavbar", method= RequestMethod.POST)
public ResponseEntity<List<LabelDTO>> showLabelNavbar(@RequestBody ParamDTO paramDTO) {
	int rank;
	try {
		rank = this.getTypeFromJwt(paramDTO.getJwt());
		// recupero l'idUtente dalla Param e dalla Jwt
		int idUser = this.getIdUserFromJwt(paramDTO.getJwt());
		if(rank == 1) {
			//List<LabelDTO> labelByUser= labelService.findLabelbyIdUser(idUser);
			LinkedHashMap label = (LinkedHashMap) paramDTO.getParam();
			String nome = label.get("name").toString();
			//List<LabelDTO> labelShow = labelService.findLabelDTOByPart(nome);
			List<LabelDTO> labelShow = labelService.findLabelDTOByPartUser(nome, idUser);
			if(labelShow!= null)
				return ResponseEntity.status(HttpStatus.OK).body(labelShow);
			else
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		}
		else
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);		
		} catch (ExpiredJwtException | UnsupportedEncodingException e) {
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
	}
}

// func che estrae il tipo utente dalla Jwt.	
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