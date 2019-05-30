package it.contrader.controller;

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

import it.contrader.dto.ThingDTO;
import it.contrader.dto.LabelDTO;
import it.contrader.dto.ParamDTO;
import it.contrader.services.ThingService;
import it.contrader.utils.JwtUtils;
import it.contrader.services.LabelService;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(value="*")
@RestController
@RequestMapping("/Thing")

public class ThingController {
private final ThingService thingService;
private final LabelService labelService;
//private HttpSession session;
private int idThing;
private int idUser;
	
@Autowired 
public ThingController(ThingService ts, LabelService ls) {
	this.thingService = ts;
	this.labelService = ls;
}

@RequestMapping(value = "/showThing", method = RequestMethod.GET)
// caso particolare di passaggio. la jwt ha id e tipo
public ResponseEntity<List<ThingDTO>> showThing(@RequestParam(value="jwt") String jwt) {
int type;
int idUser;
	try {			
		type = this.getTypeFromJwt(jwt);
		if(type == 1) {
			idUser = this.getIdUserFromJwt(jwt);
			return ResponseEntity.status(HttpStatus.OK).body(thingService.getThingDTOByIdUser(idUser));
			}
			else
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		} catch (ExpiredJwtException | UnsupportedEncodingException e) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
	}
}


@RequestMapping(value = "/byId", method = RequestMethod.POST)
//caso particolare di passaggio. la jwt ha id e tipo
public List<ThingDTO> showThingById(@RequestBody List<String> num) {

	
			return thingService.findAllThingById(num);
			
}







	
@RequestMapping(value="/deleteThing" , method= RequestMethod.POST)
public ResponseEntity<Boolean> deleteThing(@RequestBody ParamDTO paramDTO) {			
	int type;
	try {
		type = this.getTypeFromJwt(paramDTO.getJwt());
		if(type == 1) {
			LinkedHashMap thing = (LinkedHashMap) paramDTO.getParam();
			return ResponseEntity.status(HttpStatus.OK).body(thingService.deleteThing(Integer.parseInt(thing.get("idThing").toString())));
		}
		else
			return ResponseEntity.status(HttpStatus.OK).body(null);
		} catch (ExpiredJwtException | UnsupportedEncodingException e) {
			return ResponseEntity.status(HttpStatus.OK).body(null);
	}
}
		
@RequestMapping(value="/insertThing", method= RequestMethod.POST)
public ResponseEntity<ThingDTO> insertThing(@RequestBody ParamDTO paramDTO) {
	int rank;
	int idUser, idLabel;
	try {
		rank = this.getTypeFromJwt(paramDTO.getJwt());
		if(rank == 1) {
			idUser = this.getIdUserFromJwt(paramDTO.getJwt());
			LinkedHashMap thing = (LinkedHashMap) paramDTO.getParam();
			LinkedHashMap label = (LinkedHashMap) thing.get("label");
			idLabel= Integer.parseInt(label.get("idLabel").toString());
			LabelDTO labelDTO = labelService.findLabelById(idLabel);
			ThingDTO thingDTO = new ThingDTO(0,thing.get("code").toString(), thing.get("description").toString(), thing.get("image").toString(), thing.get("name").toString(), thing.get("xml").toString(), thing.get("protocol").toString(), idUser, labelDTO);
			ThingDTO thingInsert = thingService.insertThing(thingDTO);
			if(thingInsert != null)
				return ResponseEntity.status(HttpStatus.OK).body(thingInsert);
			else
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
			}
			else
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);		
		} catch (ExpiredJwtException | UnsupportedEncodingException e) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
	}
}
	
@RequestMapping(value="/updateThing", method= RequestMethod.PUT)
public ResponseEntity<ThingDTO> updateThing(@RequestBody ParamDTO paramDTO) {
int rank;
int idUser, idLabel;		
try {
		rank = this.getTypeFromJwt(paramDTO.getJwt());	
		if(rank == 1) {
				idUser = this.getIdUserFromJwt(paramDTO.getJwt());
				LinkedHashMap thing = (LinkedHashMap) paramDTO.getParam();
				LinkedHashMap label = (LinkedHashMap) thing.get("label");
				idLabel= Integer.parseInt(label.get("idLabel").toString());
				LabelDTO labelDTO = labelService.findLabelById(idLabel);
				ThingDTO thingDTO = new ThingDTO(Integer.parseInt(thing.get("idThing").toString()),thing.get("code").toString(), thing.get("description").toString(), thing.get("image").toString(), thing.get("name").toString(), thing.get("xml").toString(), thing.get("protocol").toString(), idUser, labelDTO);
				ThingDTO thingInsert = thingService.insertThing(thingDTO);
				if(thingInsert != null)
					return ResponseEntity.status(HttpStatus.OK).body(thingInsert);
				else
					return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
			}
			else
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
			} catch (ExpiredJwtException | UnsupportedEncodingException e) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		}		
}
	
@RequestMapping(value="/findThing" , method= RequestMethod.GET)
public ThingDTO findUser( @RequestBody ThingDTO thing) {		
	return thingService.findThingById(thing.getIdThing());
}
	
@RequestMapping(value="/getAllThing", method= RequestMethod.GET)
public List<ThingDTO> getAllThing(){
	return thingService.getAllThings();
}
		
@RequestMapping(value ="/thingManagement", method = RequestMethod.GET)
public String thingManagement(HttpServletRequest request) {
	idUser = Integer.parseInt(request.getParameter("idUser"));
	List<ThingDTO> allThing = this.thingService.getThingDTOByIdUser(idUser);
	request.getSession().setAttribute("allThing", allThing);
	return "showThing";		
}
	
@RequestMapping(value = "/showCode", method = RequestMethod.GET)
public String codice(HttpServletRequest request) {
	ThingDTO thingById;
	thingById = this.thingService.getThingDTOById(Integer.parseInt(request.getParameter("id")));
	request.getSession().setAttribute("codice",thingById.getCode());
	return "readCode";
}
	
private String createXmlFromDataThings(Integer i,String code, String string, Integer idUser2, Integer rIdLabel, String name) {
	String result = new String("<Thing>\n");
	String pt = new String("c:\\webdata\\xml\\"+name+".xml");
	result = result.concat("<id_thing>" + i.toString() + "</id_thing>\n");
	result =result.concat("<code>"+ code +"</code>\n");
	result =result.concat("<image>"+ string +"</image>\n");
	result =result.concat("<name>"+name+"</name>\n");
	result =result.concat("<xml>"+pt+"</xml>\n");
	result =result.concat("<id_label>"+ rIdLabel.toString()+"</id_label>\n");
	result =result.concat("<id_user>" + idUser2.toString()+"</id_user>\n");
	result =result.concat("</Thing>\n");
		try {
			Files.write(Paths.get(pt), result.getBytes());
		} catch (IOException e) {
	}
	return pt;
}

private String getFileName(String imagePath) {
   	String fn = imagePath.substring(0).trim().replace("\"", "");
    int slashPos = fn.lastIndexOf( '\\' );
      if ( slashPos == -1 )
	              slashPos = fn.lastIndexOf( '/' );
	            return fn.substring( slashPos > 0 ? slashPos + 1 : 0 );
	}
	
	
private String updateXmlFromDataThings(Integer i,String code, String string, Integer idUser2, Integer rIdLabel,String name) {
i=idThing;
String result = new String("<Thing>\n");
String pt = new String("c:\\webdata\\xml\\"+name+".xml");
result = result.concat("<id_thing>" + i.toString() + "</id_thing>\n");
result = result.concat("<code>"+ code +"</code>\n");
result = result.concat("<image>"+ string +"</image>\n");
result = result.concat("<name>"+name+"</name>\n");
result = result.concat("<xml>"+pt+"</xml>\n");
result = result.concat("<id_label>"+ rIdLabel.toString()+"</id_label>\n");
result = result.concat("<id_user>" + idUser2.toString()+"</id_user>\n");
result = result.concat("</Thing>\n");
try {
	Files.write(Paths.get(pt), result.getBytes());
	} catch (IOException e) {		
		}
	return pt;
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