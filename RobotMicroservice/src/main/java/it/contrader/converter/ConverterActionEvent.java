package it.contrader.converter;

import java.util.ArrayList;
import java.util.List;

import it.contrader.dto.ActionEventDTO;
import it.contrader.dto.LabelDTO;

import it.contrader.model.ActionEvent;
import it.contrader.model.Label;


public class ConverterActionEvent {
	public static ActionEventDTO toDto(ActionEvent ActionEvent) {
		ActionEventDTO ActionEventDTO = null;
		if(ActionEvent != null) {
			ActionEventDTO = new ActionEventDTO();
			ActionEventDTO.setIdActionEvent(ActionEvent.getIdActionEvent());
			ActionEventDTO.setDescription(ActionEvent.getDescription());
			ActionEventDTO.setName(ActionEvent.getName());
			ActionEventDTO.setType(ActionEvent.getType());
			
			
			LabelDTO labelDTO = new LabelDTO();
			labelDTO = ConverterLabel.toDto(ActionEvent.getLabel());
			ActionEventDTO.setLabel(labelDTO);
			
		}
	return ActionEventDTO;		
}
	
public static ActionEvent toEntity(ActionEventDTO ActionEventDTO) {
	ActionEvent ActionEvent = null;
	if(ActionEventDTO != null) {
		ActionEvent = new ActionEvent();
		ActionEvent.setIdActionEvent(ActionEventDTO.getIdActionEvent());
		ActionEvent.setDescription(ActionEventDTO.getDescription());
		ActionEvent.setName(ActionEventDTO.getName());
		ActionEvent.setType(ActionEventDTO.getType());		
		Label label = new Label();
		label = ConverterLabel.toEntity(ActionEventDTO.getLabel());
		ActionEvent.setLabel(label);	
		}
	return ActionEvent;
}

public static List<ActionEventDTO> toListDTO(List<ActionEvent> list){
	List<ActionEventDTO> listActionEventDTO = new ArrayList<>();
	if (!list.isEmpty()) {
		for(ActionEvent ActionEvent : list) {
			listActionEventDTO.add(ConverterActionEvent.toDto(ActionEvent));
		}
	}
	return listActionEventDTO;
}
	
public static List<ActionEvent> toListEntity(List<ActionEventDTO> listActionEventDTO){
	List<ActionEvent> listActionEvent = new ArrayList<>();
	if (!listActionEventDTO.isEmpty()) {
		for(ActionEventDTO ActionEventDTO : listActionEventDTO) {
			listActionEvent.add(ConverterActionEvent.toEntity(ActionEventDTO));
		}
	}
	return listActionEvent;
}
}