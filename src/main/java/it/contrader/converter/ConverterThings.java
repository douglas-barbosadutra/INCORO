package it.contrader.converter;

import java.util.ArrayList;
import java.util.List;

import it.contrader.dto.thingsDTO;
import it.contrader.model.Things;

public class ConverterThings {

	public static thingsDTO toDTO(Things thing) {
		thingsDTO ThingsDTO = null;
		if (thing != null) {
			ThingsDTO = new thingsDTO();
			ThingsDTO.setThingsId(thing.getThingsId());
			ThingsDTO.setName(thing.getThingsname());
		}
		return ThingsDTO;
	}

	public static Things toEntity(thingsDTO thingsDTO) {
		Things things = null;
		if (thingsDTO != null) {
			things = new Things();
			things.setThingsId(thingsDTO.getThingsId());
			things.setThingsname(thingsDTO.getName());
		}
		return things;
	}

	public static List<thingsDTO> toListDTO(List<Things> list) {
		List<thingsDTO> listUserDTO = new ArrayList<>();
		if (!list.isEmpty()) {
			for (Things thing : list) {
				listUserDTO.add(ConverterThings.toDTO(thing));
			}
		}
		return listUserDTO;
	}

	public static List<Things> toListEntity(List<thingsDTO> listThingDTO) {
		List<Things> list = new ArrayList<>();
		if (!listThingDTO.isEmpty()) {
			for (thingsDTO thingsDTO : listThingDTO) {
				list.add(ConverterThings.toEntity(thingsDTO));
			}
		}
		return list;
	}
}