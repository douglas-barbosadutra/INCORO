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
import it.contrader.dto.HardwareDTO;
import it.contrader.dto.ThingDTO;
import it.contrader.dto.ParamDTO;
import it.contrader.services.HardwareService;
import it.contrader.services.ThingService;
import it.contrader.utils.JwtUtils;

@CrossOrigin(value="*")
@RestController
@RequestMapping("/Hardware")

public class HardwareController {
	private HardwareService hardwareService;
	private ThingService thingService;
	
@Autowired
public HardwareController(HardwareService hs, ThingService ts) {
	this.hardwareService = hs;
	this.thingService = ts;
}

@RequestMapping(value = "/showHardware", method = RequestMethod.GET)
public ResponseEntity<List<HardwareDTO>> showHardware(@RequestParam(value="jwt") String jwt) {
	int type;
	try {			
		type = this.getTypeFromJwt(jwt);
		if(type == 1) {
			int idUser = this.getIdUserFromJwt(jwt);
			return ResponseEntity.status(HttpStatus.OK).body(hardwareService.getAllHardware());
			}
			else
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		} catch (ExpiredJwtException | UnsupportedEncodingException e) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
	}
}
	
@RequestMapping(value="/deleteHardware" , method= RequestMethod.POST)
public ResponseEntity<Boolean> deleteHardware(@RequestBody ParamDTO paramDTO) {			
	int type;
	try {
		type = this.getTypeFromJwt(paramDTO.getJwt());
		if(type == 1) {
			LinkedHashMap hardware = (LinkedHashMap) paramDTO.getParam();
			return ResponseEntity.status(HttpStatus.OK).body(hardwareService.deleteHardware(Integer.parseInt(hardware.get("idHardware").toString())));
		}
		else
			return ResponseEntity.status(HttpStatus.OK).body(null);
		} catch (ExpiredJwtException | UnsupportedEncodingException e) {
			return ResponseEntity.status(HttpStatus.OK).body(null);
	}
}
	
@RequestMapping(value="/insertHardware", method= RequestMethod.POST)
public ResponseEntity<HardwareDTO> inserHardware(@RequestBody ParamDTO paramDTO) {
	int rank;
	int idUser;
	int idThing;		
	try {
		rank = this.getTypeFromJwt(paramDTO.getJwt());
		if(rank == 1) {
			idUser = this.getIdUserFromJwt(paramDTO.getJwt());
			LinkedHashMap hardware = (LinkedHashMap) paramDTO.getParam();
			LinkedHashMap thing = (LinkedHashMap) hardware.get("thing"); 
			idThing = Integer.parseInt(thing.get("idThing").toString());
			ThingDTO thingDTO = thingService.findThingById(idThing);
			HardwareDTO hardwareDTO = new HardwareDTO(0,hardware.get("name").toString(), hardware.get("description").toString(), (Boolean) hardware.get("master") , thingDTO);
			HardwareDTO hardwareInsert = hardwareService.insertHardware(hardwareDTO);
			if(hardwareInsert != null)
					return ResponseEntity.status(HttpStatus.OK).body(hardwareInsert);
				else
					return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
			}
			else
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);		
			} catch (ExpiredJwtException | UnsupportedEncodingException e) {
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
	}
}
	
@RequestMapping(value="/updateHardware", method= RequestMethod.PUT)
public ResponseEntity<HardwareDTO> updateHardware(@RequestBody ParamDTO paramDTO) {
int rank;
int idUser;
int idThing;
try {
		rank = this.getTypeFromJwt(paramDTO.getJwt());	
		if(rank == 1) {
				idUser = this.getIdUserFromJwt(paramDTO.getJwt());
				LinkedHashMap hardware = (LinkedHashMap) paramDTO.getParam();
				LinkedHashMap thing = (LinkedHashMap) hardware.get("thing");
				idThing = Integer.parseInt(thing.get("idThing").toString());
				ThingDTO thingDTO = thingService.findThingById(idThing);
				HardwareDTO hardwareDTO = new HardwareDTO(Integer.parseInt(hardware.get("idHardware").toString()),hardware.get("name").toString(), hardware.get("description").toString(), (Boolean) hardware.get("master"), thingDTO);
				HardwareDTO hardwareInsert = hardwareService.insertHardware(hardwareDTO);
				if(hardwareInsert != null)
					return ResponseEntity.status(HttpStatus.OK).body(hardwareInsert);
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
	
private int getIdUserFromJwt(String jwt) throws ExpiredJwtException, UnsupportedEncodingException {
	Map<String, Object> data = JwtUtils.jwt2Map(jwt);
	int idUser = Integer.parseInt(data.get("subject").toString());
	return idUser;
}
}