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
// caso particolare di passaggio. la jwt ha id e tipo
public ResponseEntity<List<LabelDTO>> showLabel(@RequestParam(value="jwt") String jwt) {
	int type;
	int idUser;
		
	try {			
		type = this.getTypeFromJwt(jwt);
		if(type == 1) {
			idUser = this.getIdUserFromJwt(jwt);
			return ResponseEntity.status(HttpStatus.OK).body(labelService.findLabelByIdUser(idUser));
			}
			else
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		} catch (ExpiredJwtException | UnsupportedEncodingException e) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
	}
}
	
// non si passa un id ma un PadramDTO con post
@RequestMapping(value="/deleteLabel" , method= RequestMethod.POST)
public ResponseEntity<Boolean> deleteLabel(@RequestBody ParamDTO paramDTO) {			
	int type;
	try {
		type = this.getTypeFromJwt(paramDTO.getJwt());
		if(type == 1) {
			int idLabel = (int) paramDTO.getParam();
			return ResponseEntity.status(HttpStatus.OK).body(labelService.deleteLabel(idLabel));
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














/*
@RequestMapping(value ="/deleteLabel", method = RequestMethod.DELETE)
public boolean delete(@RequestBody LabelDTO labelDto) {
	int id = labelDto.getIdLabel();
	this.labelService.deleteLabelById(id);
	return true;
}*/

/*
@RequestMapping(value="/insertLabel", method = RequestMethod.PUT)
private boolean insertLabel(@RequestBody LabelDTO labelDto) {
	String name = labelDto.getName();
	if (labelService.getLabelDTOByNameAndUser(name, ConverterUser.toEntity(labelDto.getUser())) == null) {
		LabelDTO labelObj = new LabelDTO(0, name, labelDto.getUser());
		labelService.insertLabel(labelObj);
		return true;
	} else {
		return false;
	}
}*/

/*
private void visualLabel(HttpServletRequest request) {
List<LabelDTO> allLabel = this.labelService.findLabelbyIdUser(idUser);
request.getSession().setAttribute("allLabel", allLabel);
}*/