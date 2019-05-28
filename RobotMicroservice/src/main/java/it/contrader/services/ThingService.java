package it.contrader.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.contrader.converter.ConverterThing;
import it.contrader.dao.ThingRepository;
import it.contrader.dto.ThingDTO;
import it.contrader.model.Thing;


@Service
public class ThingService {
	private final ThingRepository thingRepository;
	
	@Autowired
	public ThingService(ThingRepository thingRepository) {
		this.thingRepository = thingRepository;
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
	
	public List<ThingDTO> findThingDTOByName(String name) {
		final List<Thing> list = thingRepository.findAllByName(name);
		final List<ThingDTO> thingDTOs = new ArrayList<>();
		list.forEach(i -> thingDTOs.add(ConverterThing.toDTO(i)));
		return thingDTOs;	
	}
}