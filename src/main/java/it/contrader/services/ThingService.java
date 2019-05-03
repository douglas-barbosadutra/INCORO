package it.contrader.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import java.util.ArrayList;
//import java.util.List;

import it.contrader.converter.ConverterThing;
import it.contrader.converter.ConverterUser;
import it.contrader.dao.ThingRepository;
import it.contrader.dto.ThingDTO;
import it.contrader.dto.UserDTO;
import it.contrader.model.Thing;
import it.contrader.model.User;

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

	public ThingDTO getThingDTOById(int id) {
		return ConverterThing.toDTO(thingRepository.findThingByIdThing(id));
	}

	/*
	public boolean insertThing(ThingDTO thingDTO) {
		return thingRepository.save(ConverterThing.toEntity(thingDTO)) != null;
	}*/
	
	// METODO DI REST CONTROLLER
	public ThingDTO insertThing(ThingDTO thingDTO) {
		Thing thing = ConverterThing.toEntity(thingDTO);
		// userDAO.saveAndFlush(user);
		thingRepository.save(thing); 
		return ConverterThing.toDTO(thing);
	}

	public boolean updateThing(ThingDTO thingDTO) {
		return thingRepository.save(ConverterThing.toEntity(thingDTO)) != null;
	}
	
	/*
	public void deleteThingById(Integer id) {
		thingRepository.deleteById(id);
	}*/
	
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
		User u = new User();
		u.setIdUser(idUser);
		List<Thing> listThing = (List<Thing>) thingRepository.findAllByUser(u);
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