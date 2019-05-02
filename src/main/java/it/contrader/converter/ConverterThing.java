package it.contrader.converter;

import java.util.ArrayList;
import java.util.List;
import it.contrader.dto.ThingDTO;
import it.contrader.dto.UserDTO;
import it.contrader.dto.LabelDTO;
import it.contrader.model.Thing;
import it.contrader.model.User;
import it.contrader.model.Label;

public class ConverterThing {
	 
	public static ThingDTO toDTO(Thing thing) {
		ThingDTO thingDTO = null;
		if (thing != null) {
			thingDTO = new ThingDTO();
			thingDTO.setIdThing(thing.getIdThing());
			thingDTO.setCode(thing.getCode());
			thingDTO.setImage(thing.getImage());		
			thingDTO.setName(thing.getName());
			thingDTO.setXml(thing.getXml());
			
			UserDTO user = new UserDTO();
			user = ConverterUser.toDTO(thing.getUser());
			thingDTO.setUser(user);
			
			LabelDTO label = new LabelDTO();
			label = ConverterLabel.convertToDto(thing.getLabel());
			thingDTO.setLabel(label);
		}
		return thingDTO;
	}
	
	public static Thing toEntity(ThingDTO thingDTO) {
		Thing thing = null;
		if (thingDTO != null) {
			thing = new Thing();
			thing.setIdThing(thingDTO.getIdThing());
			thing.setCode(thingDTO.getCode());
			thing.setImage(thingDTO.getImage());
			thing.setName(thingDTO.getName());
			thing.setXml(thingDTO.getXml());
			
			User user = new User();
			user = ConverterUser.toEntity(thingDTO.getUser());
			
			Label label = new Label();
			label = ConverterLabel.convertToEntity(thingDTO.getLabel());
			
			thing.setUser(user);
			thing.setLabel(label);
		}
		return thing;
	}
	
	public static List<ThingDTO> toListDTO(List<Thing> list) {
		List<ThingDTO> listThingDTO = new ArrayList<>();
		if (!list.isEmpty()) {
			for (Thing thing : list) {
				listThingDTO.add(ConverterThing.toDTO(thing));
			}
		}
		return listThingDTO;
	}
	
	public static List<Thing> toListEntity(List<ThingDTO> listThingDTO) {
		List<Thing> list = new ArrayList<>();
		if (!listThingDTO.isEmpty()) {
			for (ThingDTO thingDTO : listThingDTO) {
				list.add(ConverterThing.toEntity(thingDTO));
			}
		}
		return list;
	}	
}