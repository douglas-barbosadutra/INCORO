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
import it.contrader.dto.ActionEventDTO;
import it.contrader.dto.LabelDTO;
import it.contrader.dto.ParamDTO;
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
			//LabelDTO labelDTO = labelService.findLabelDTOByIdUser(idUser); 
			List<LabelDTO> listLabelDTO = labelService.getLabelDTOByIdUser(idUser);
			return ResponseEntity.status(HttpStatus.OK).body(actionEventService.getActionEventByLabel(listLabelDTO));				
		}
			else
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		} catch (ExpiredJwtException | UnsupportedEncodingException e) {
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
	}
}
		

@RequestMapping(value = "/getByLabel", method = RequestMethod.GET)
public List<ActionEventDTO> showActionEventByLabelList(@RequestBody List<LabelDTO> labelList) {
	
			
			return actionEventService.getActionEventByLabel(labelList);				
		
}
// actionEvent type = 0 = out = action  actionEvent type = 1 = in = event
@RequestMapping(value = "/findAction", method = RequestMethod.POST)
public ResponseEntity<List<ActionEventDTO>> showAction(@RequestBody ParamDTO paramDTO) {
	int rank, idLabel;
	try {
		rank = this.getTypeFromJwt(paramDTO.getJwt());
		if(rank == 1) {
			int idUser = this.getIdUserFromJwt(paramDTO.getJwt());
			LinkedHashMap label = (LinkedHashMap) paramDTO.getParam();			
			idLabel= Integer.parseInt(label.get("idLabel").toString());
			LabelDTO labelDTO = labelService.findLabelById(idLabel);
			List<ActionEventDTO> listTotalActionEventDTOByLabel= actionEventService.findActionEventbyLabel(labelDTO);
			List<ActionEventDTO> listActionDTO = new ArrayList<>();
			if (!listTotalActionEventDTOByLabel.isEmpty()) {
				for(ActionEventDTO actionDTO : listTotalActionEventDTOByLabel) {
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
	
// actionEvent type = 0 = out = action  actionEvent type = 1 = in = event
@RequestMapping(value = "/findEvent", method = RequestMethod.POST)
public ResponseEntity<List<ActionEventDTO>> showEvent(@RequestBody ParamDTO paramDTO) {
	int rank, idLabel;
	try {
		rank = this.getTypeFromJwt(paramDTO.getJwt());
		if(rank == 1) {
			int idUser = this.getIdUserFromJwt(paramDTO.getJwt());
			LinkedHashMap label = (LinkedHashMap) paramDTO.getParam();			
			idLabel= Integer.parseInt(label.get("idLabel").toString());
			LabelDTO labelDTO = labelService.findLabelById(idLabel);
			//List<LabelDTO> listLabelDTO = labelService.getLabelDTOByIdUser(idUser);
			List<ActionEventDTO> listTotalActionEventDTOByLabel= actionEventService.findActionEventbyLabel(labelDTO);
			List<ActionEventDTO> listActionDTO = new ArrayList<>();
			if (!listTotalActionEventDTOByLabel.isEmpty()) {
				for(ActionEventDTO actionDTO : listTotalActionEventDTOByLabel) {
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
	
@RequestMapping(value="/insertActionEvent", method= RequestMethod.POST)
public ResponseEntity<ActionEventDTO> insertThing(@RequestBody ParamDTO paramDTO) {
	int rank;
	int idLabel;
	try {
	rank = this.getTypeFromJwt(paramDTO.getJwt());
	if(rank == 1) {
		int tidUser = this.getIdUserFromJwt(paramDTO.getJwt());
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

@RequestMapping(value="/updateActionEvent", method= RequestMethod.PUT)
public ResponseEntity<ActionEventDTO> updateActionEvent(@RequestBody ParamDTO paramDTO) {
int rank;
int idLabel;
try {
		rank = this.getTypeFromJwt(paramDTO.getJwt());	
			if(rank == 1) {
				int idUser = this.getIdUserFromJwt(paramDTO.getJwt());
				LinkedHashMap actionEvent = (LinkedHashMap) paramDTO.getParam();
				LinkedHashMap label = (LinkedHashMap) actionEvent.get("label");
				idLabel= Integer.parseInt(label.get("idLabel").toString());
				LabelDTO labelDTO = labelService.findLabelById(idLabel);
				ActionEventDTO ActionEventDTO = new ActionEventDTO(Integer.parseInt(actionEvent.get("idActionEvent").toString()), actionEvent.get("description").toString(), actionEvent.get("name").toString(), labelDTO, Integer.parseInt(actionEvent.get("type").toString()));
				ActionEventDTO actionEventDTOInsert = actionEventService.insertActionEvent(ActionEventDTO);
				if(actionEventDTOInsert != null)
					return ResponseEntity.status(HttpStatus.OK).body(actionEventDTOInsert);
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