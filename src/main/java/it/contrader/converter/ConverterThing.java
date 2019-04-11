package it.contrader.converter;

import java.util.ArrayList;
import java.util.List;
import it.contrader.dto.ThingDTO;
import it.contrader.model.Thing;
import it.contrader.model.User;
import it.contrader.model.Label;

public class ConverterThing {
	 
	public static ThingDTO toDTO(Thing thing) {
		ThingDTO thingDTO = null;
		if (thing != null) {
			thingDTO = new ThingDTO();
			thingDTO.setId(thing.getIdThing());
			thingDTO.setCode(thing.getCode());
			thingDTO.setImage(thing.getImage());		
			thingDTO.setName(thing.getName());
			thingDTO.setXml(thing.getXml());
			thingDTO.setIdUser(thing.getUser().getIdUser());
			thingDTO.setIdLabel(thing.getLabel().getIdLabel());
		}
		return thingDTO;
	}
	
	public static Thing toEntity(ThingDTO thingDTO) {
		Thing thing = null;
		if (thingDTO != null) {
			thing = new Thing();
			thing.setIdThing(thingDTO.getId());
			thing.setCode(thingDTO.getCode());
			thing.setImage(thingDTO.getImage());
			thing.setName(thingDTO.getName());
			thing.setXml(thingDTO.getXml());
			
			User user = new User();
			user.setIdUser(thingDTO.getIdUser());
			Label label = new Label();
			label.setIdLabel(thingDTO.getIdLabel());
			
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