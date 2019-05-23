package it.contrader.controller;

import java.util.ArrayList;
import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.contrader.converter.ConverterActionEvent;
import it.contrader.converter.ConverterLabel;
import it.contrader.dto.ActionEventDTO;
import it.contrader.dto.LabelDTO;
import it.contrader.model.ActionEvent;
import it.contrader.services.ActionEventService;
import it.contrader.services.LabelService;

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
	@RequestMapping(value="/showActionEvent" , method= RequestMethod.GET)
	public List<ActionEventDTO> showActionEvent() {		
		return actionEventService.getAllActionEvent();
	}
		
	@RequestMapping(value = "/showActionByLabel", method = RequestMethod.GET)
	public List<ActionEventDTO> showAction(@RequestParam(value="id") Integer idLabel) {
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
		return listActionDTO;
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
	
	@RequestMapping(value="/deleteActionEvent" , method= RequestMethod.DELETE)
	public boolean deleteActionEvent(@RequestParam(value="id") Integer id) {		
		return actionEventService.deleteActionEvent(id);
	}
	
	@RequestMapping(value="/insertActionEvent", method= RequestMethod.POST)
	public ActionEventDTO insertActionEvent(@RequestBody ActionEventDTO ActionEventDTO) {
		return actionEventService.insertActionEvent(ActionEventDTO);
	}
	

	@RequestMapping(value="/updateActionEvent" , method= RequestMethod.PUT)
	public ActionEventDTO showActionEvent(@RequestBody ActionEventDTO ActionEventDTO) {	
			return actionEventService.insertActionEvent(ActionEventDTO);
		
	}
}