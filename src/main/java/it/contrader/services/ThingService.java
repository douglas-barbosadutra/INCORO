package it.contrader.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import java.util.ArrayList;
//import java.util.List;

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

	public List<ThingDTO> getListThingDTO() {
		return ConverterThing.toListDTO ((List<Thing>)thingRepository.findAll());
	}

	public ThingDTO getThingDTOById(Integer id) {
		return ConverterThing.toDTO(thingRepository.findById(id).get());
	}

	public boolean insertThing(ThingDTO thingDTO) {
		
		return thingRepository.save(ConverterThing.toEntity(thingDTO)) != null;
	}

	public boolean updateThing(ThingDTO thingDTO) {
		return thingRepository.save(ConverterThing.toEntity(thingDTO)) != null;
	}
	
	public void deleteThingById(Integer id) {
		thingRepository.deleteById(id);
	}
	
	public List<ThingDTO> findThingDTOByName(String name) {
		final List<Thing> list = thingRepository.findAllByName(name);
		final List<ThingDTO> thingDTOs = new ArrayList<>();
		list.forEach(i -> thingDTOs.add(ConverterThing.toDTO(i)));
		return thingDTOs;	
	}
}