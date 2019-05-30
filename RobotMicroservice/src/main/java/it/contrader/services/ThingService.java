package it.contrader.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.contrader.converter.ConverterActionEvent;
import it.contrader.converter.ConverterLabel;
import it.contrader.converter.ConverterThing;
import it.contrader.dao.LabelRepository;
import it.contrader.dao.ThingRepository;
import it.contrader.dto.ActionEventDTO;
import it.contrader.dto.LabelDTO;
import it.contrader.dto.ThingDTO;
import it.contrader.model.ActionEvent;
import it.contrader.model.Thing;
import it.contrader.model.Label;;


@Service
public class ThingService {
	private final ThingRepository thingRepository;
	private final LabelRepository labelRepository;
	
	@Autowired
	public ThingService(ThingRepository thingRepository, LabelRepository labelRepository) {
		this.thingRepository = thingRepository;
		this.labelRepository = labelRepository;
	}

	public List<ThingDTO> getAllThing() {
		return ConverterThing.toListDTO ((List<Thing>)thingRepository.findAll());
	}

	public ThingDTO getThingDTOById(int id) {
		return ConverterThing.toDTO(thingRepository.findThingByIdThing(id));
	}
	
	// METODO DI REST CONTROLLER
	public ThingDTO insertThing(ThingDTO thingDTO) {
		Thing thing = ConverterThing.toEntity(thingDTO);
		thingRepository.save(thing); 
		return ConverterThing.toDTO(thing);
	}

	public boolean updateThing(ThingDTO thingDTO) {
		return thingRepository.save(ConverterThing.toEntity(thingDTO)) != null;
	}
	
	// METODO DI REST CONTROLLER
	public boolean deleteThing(int id) {
		this.thingRepository.deleteById(id);
		return true;
	}
	
	// METODO DI REST CONTROLLER
	public ThingDTO findThingById(int id) {
		return ConverterThing.toDTO(thingRepository.findThingByIdThing(id));
	}
	
	public List<ThingDTO> getAllThings(){
		return ConverterThing.toListDTO((List<Thing>) thingRepository.findAll());
	}
	
	public List<ThingDTO> getThingDTOByIdUser(int idUser){
		List<Thing> listThing = (List<Thing>) thingRepository.findAllByIdUser(idUser);
		List<ThingDTO> listThingDTO = ConverterThing.toListDTO(listThing);
		return listThingDTO;
	}
	
	public List<ThingDTO> getThingDTOByIdLabel(int idLabel){
		List<Thing> listThing = (List<Thing>) thingRepository.findAllByLabel(idLabel);
		List<ThingDTO> listThingDTO = ConverterThing.toListDTO(listThing);
		return listThingDTO;
	}
	
	public List<ThingDTO> findThingDTOByName(String name) {
		final List<Thing> list = thingRepository.findAllByName(name);
		final List<ThingDTO> thingDTOs = new ArrayList<>();
		list.forEach(i -> thingDTOs.add(ConverterThing.toDTO(i)));
		return thingDTOs;	
	}
	
	
	//mio
	public List<ThingDTO> getThingByLabel(List<LabelDTO> listLabelDTO){
		LabelDTO labelDTO;
		List<Thing> list = new ArrayList<>();
		List<ThingDTO> listThingDTO = new ArrayList<>();
		for (int i=0; i<listLabelDTO.size(); i++) {
			labelDTO = listLabelDTO.get(i);
			list = thingRepository.findAllByLabel(ConverterLabel.toEntity(labelDTO));
		}
		for(Thing thing : list) {
			listThingDTO.add(ConverterThing.toDTO(thing));
		}
		return listThingDTO;
	}
	


}