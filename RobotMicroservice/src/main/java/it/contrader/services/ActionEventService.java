package it.contrader.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.contrader.converter.ConverterActionEvent;
import it.contrader.converter.ConverterLabel;
import it.contrader.dao.ActionEventRepository;
import it.contrader.dto.ActionEventDTO;
import it.contrader.dto.LabelDTO;
import it.contrader.model.ActionEvent;

@Service
public class ActionEventService {

private final ActionEventRepository actionEventRepository;
	
	@Autowired
	public ActionEventService(ActionEventRepository actionEventRepository) {
		this.actionEventRepository = actionEventRepository;
	}
	
	public ActionEventDTO insertActionEvent(ActionEventDTO actionEventDTO) {
		ActionEvent actionEvent = ConverterActionEvent.toEntity(actionEventDTO);
		actionEventRepository.save(actionEvent); 
		return ConverterActionEvent.toDto(actionEvent);
	}
		
	public boolean updateActionEvent(ActionEventDTO actionEventDTO) {
		return actionEventRepository.save(ConverterActionEvent.toEntity(actionEventDTO)) != null;
	}
	
	public boolean deleteActionEvent(int id) {
		actionEventRepository.deleteById(id);
		return true;
	}
	
	public List<ActionEventDTO> getAllActionEvent(){
		return ConverterActionEvent.toListDTO((List<ActionEvent>) actionEventRepository.findAll());
	}
	
	public List<ActionEventDTO> getAllActionEventByName(String name){
		return ConverterActionEvent.toListDTO((List<ActionEvent>) actionEventRepository.findAllByName(name));
	}
	
	public List<ActionEventDTO> getListActionEventDTO(){
		List<ActionEvent> listActionEvent = (List<ActionEvent>) actionEventRepository.findAll();
		List<ActionEventDTO> listActionEventDTO = ConverterActionEvent.toListDTO(listActionEvent);
		return listActionEventDTO;
	}
	
	public ActionEventDTO getActionEventDTOById(int id) {
		ActionEvent ActionEvent = actionEventRepository.findActionEventByIdActionEvent(id);
		ActionEventDTO ActionEventDTO = ConverterActionEvent.toDto(ActionEvent);
		return ActionEventDTO;
	}
	
	public List<ActionEventDTO> findActionEventbyLabel(LabelDTO labelDTO){
		List<ActionEventDTO> listActionEventDTO = new ArrayList<>();
		List<ActionEvent> list = actionEventRepository.findAllByLabel(ConverterLabel.toEntity(labelDTO));
		for(ActionEvent actionEvent : list) {
			listActionEventDTO.add(ConverterActionEvent.toDto(actionEvent));
		}
		return listActionEventDTO;
	}
	
	public List<ActionEventDTO> getActionEventByLabel(List<LabelDTO> listLabelDTO){
		LabelDTO labelDTO;
		List<ActionEvent> list = new ArrayList<>();
		List<ActionEventDTO> listActionEventDTO = new ArrayList<>();
		for (int i=0; i<listLabelDTO.size(); i++) {
			labelDTO = listLabelDTO.get(i);
			list = actionEventRepository.findAllByLabel(ConverterLabel.toEntity(labelDTO));
		}
		for(ActionEvent actionEvent : list) {
			listActionEventDTO.add(ConverterActionEvent.toDto(actionEvent));
		}
		return listActionEventDTO;
	}
	
	//metodo mio
	public List<ActionEventDTO> getActionEventByLabelAndName(List<LabelDTO> listLabelDTO, String name){
		//LabelDTO labelDTO;
		List<ActionEventDTO> newList = new ArrayList<>();
		List<ActionEvent> list = new ArrayList<>();
		List<ActionEventDTO> listActionEventDTO = new ArrayList<>();
		for (int i=0; i<listLabelDTO.size(); i++) {
			LabelDTO labelDTO = listLabelDTO.get(i);
			list = actionEventRepository.findAllByLabelAndName(ConverterLabel.toEntity(labelDTO), name);
			for(int j=0; j<list.size(); j++) {
				ActionEventDTO actionEventDTO = ConverterActionEvent.toDto(list.get(j));
				newList.add(actionEventDTO);
				
			}
		}
		
		
		/*for(ActionEvent actionEvent : list) {
			listActionEventDTO.add(ConverterActionEvent.toDto(actionEvent));
		}*/
		return newList;
	}
}
