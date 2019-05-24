package it.contrader.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
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
import it.contrader.converter.ConverterActionEvent;
import it.contrader.converter.ConverterLabel;
import it.contrader.dto.ActionEventDTO;
import it.contrader.dto.LabelDTO;
import it.contrader.dto.ParamDTO;
import it.contrader.dto.ThingDTO;
import it.contrader.model.ActionEvent;
import it.contrader.services.ActionEventService;
import it.contrader.services.LabelService;
import it.contrader.utils.JwtUtils;

@CrossOrigin(value="*")
@RestController
@RequestMapping("/ActionEvent")
public class ActionEventController {
	
	private final ActionEventService actionEventService;
	private final LabelService labelService;

	
	@Autowired
	public ActionEventController(ActionEventService actionEventService, LabelService labelService) {
		this.actionEventService = actionEventService;
		this.labelService= labelService;

	}
	@RequestMapping(value = "/showActionEvent", method = RequestMethod.GET)
	public ResponseEntity<List<ActionEventDTO>> showActionEvent(@RequestParam(value="jwt") String jwt) {
		int type;
		int idUser;
			
		try {			
			type = this.getTypeFromJwt(jwt);
			if(type == 1) {
				idUser = this.getIdUserFromJwt(jwt);
				
				LabelDTO labelDTO = labelService.findLabelDTOByIdUser(idUser);
				return ResponseEntity.status(HttpStatus.OK).body(actionEventService.findActionEventbyLabel(labelDTO));
				}
				else
					return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
			} catch (ExpiredJwtException | UnsupportedEncodingException e) {
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		}
	}
		
	@RequestMapping(value = "/findAction", method = RequestMethod.POST)
	public ResponseEntity<List<ActionEventDTO>> showAction(@RequestBody ParamDTO paramDTO) {
		int rank;
		int idUser, idLabel;
			
		try {
			rank = this.getTypeFromJwt(paramDTO.getJwt());
				
			if(rank == 1) {
					
				idUser = this.getIdUserFromJwt(paramDTO.getJwt());
					
				
				LinkedHashMap label = (LinkedHashMap) paramDTO.getParam();
				
				
				
				
				idLabel= Integer.parseInt(label.get("idLabel").toString());
				LabelDTO labelDTO = labelService.findLabelById(idLabel);
		List<ActionEventDTO> listTotalDTO= actionEventService.findActionEventbyLabel(labelDTO);
		List<ActionEventDTO> listActionDTO = new ArrayList<>();
		if (!listTotalDTO.isEmpty()) {
			for(ActionEventDTO actionDTO : listTotalDTO) {
				if(actionDTO.getType()==0) 
				{
				listActionDTO.add(actionDTO);
				}
			}
		}
		return ResponseEntity.status(HttpStatus.OK).body(listActionDTO);
			}
			else
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		} catch (ExpiredJwtException | UnsupportedEncodingException e) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
	}
	}
	
	@RequestMapping(value = "/findEvent", method = RequestMethod.POST)
	public ResponseEntity<List<ActionEventDTO>> showEvent(@RequestBody ParamDTO paramDTO) {
		int rank;
		int idUser, idLabel;
			
		try {
			rank = this.getTypeFromJwt(paramDTO.getJwt());
				
			if(rank == 1) {
					
				idUser = this.getIdUserFromJwt(paramDTO.getJwt());
					
				
				LinkedHashMap label = (LinkedHashMap) paramDTO.getParam();
				
				
				
				
				idLabel= Integer.parseInt(label.get("idLabel").toString());
				LabelDTO labelDTO = labelService.findLabelById(idLabel);
		List<ActionEventDTO> listTotalDTO= actionEventService.findActionEventbyLabel(labelDTO);
		List<ActionEventDTO> listActionDTO = new ArrayList<>();
		if (!listTotalDTO.isEmpty()) {
			for(ActionEventDTO actionDTO : listTotalDTO) {
				if(actionDTO.getType()==1) 
				{
				listActionDTO.add(actionDTO);
				}
			}
		}
		return ResponseEntity.status(HttpStatus.OK).body(listActionDTO);
			}
			else
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		} catch (ExpiredJwtException | UnsupportedEncodingException e) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
	}
	}
	
	
	
	@RequestMapping(value = "/showEventByLabel", method = RequestMethod.GET)
	public List<ActionEventDTO> showEvent(@RequestParam(value="id") Integer idLabel) {
		LabelDTO labelDTO = labelService.findLabelById(idLabel);
		List<ActionEventDTO> listTotalDTO= actionEventService.findActionEventbyLabel(labelDTO);
		List<ActionEventDTO> listEventDTO = new ArrayList<>();
		if (!listTotalDTO.isEmpty()) {
			for(ActionEventDTO eventDTO : listTotalDTO) {
				if(eventDTO.getType()==1) 
				{
				listEventDTO.add(eventDTO);
				}
			}
		}
		return listEventDTO;
	}
	
	/*
	@RequestMapping(value="/deleteActionEvent" , method= RequestMethod.DELETE)
	public boolean deleteActionEvent(@RequestParam(value="id") Integer id) {		
		return actionEventService.deleteActionEvent(id);
	}*/
	
	@RequestMapping(value="/insertActionEvent", method= RequestMethod.POST)
	public ResponseEntity<ActionEventDTO> insertThing(@RequestBody ParamDTO paramDTO) {
		int rank;
		int idUser, idLabel;
			
		try {
			rank = this.getTypeFromJwt(paramDTO.getJwt());
				
			if(rank == 1) {
					
				idUser = this.getIdUserFromJwt(paramDTO.getJwt());
					
				
				LinkedHashMap actionEvent = (LinkedHashMap) paramDTO.getParam();
				LinkedHashMap label = (LinkedHashMap) actionEvent.get("label");
				
				
				
				idLabel= Integer.parseInt(label.get("idLabel").toString());
				LabelDTO labelDTO = labelService.findLabelById(idLabel);
				ActionEventDTO actionEventDTO = new ActionEventDTO(0,actionEvent.get("description").toString(), actionEvent.get("name").toString(),labelDTO, Integer.parseInt(actionEvent.get("type").toString()));
				ActionEventDTO actionEventInsert = actionEventService.insertActionEvent(actionEventDTO);
				
				if(actionEventInsert != null)
					return ResponseEntity.status(HttpStatus.OK).body(actionEventInsert);
				else
					return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
			}
			else
					return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);		
			} catch (ExpiredJwtException | UnsupportedEncodingException e) {
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		}
	}
	

	@RequestMapping(value="/updateActionEvent" , method= RequestMethod.PUT)
	public ActionEventDTO showActionEvent(@RequestBody ActionEventDTO ActionEventDTO) {	
			return actionEventService.insertActionEvent(ActionEventDTO);
		
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
