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
			ThingsDTO.setIdthing(thing.getIdthing());
			ThingsDTO.setNome(thing.getNome());
			ThingsDTO.setIduser(thing.getIduser());
			ThingsDTO.setIdlabel(thing.getIdlabel());
		}
		return ThingsDTO;
	}

	public static Things toEntity(thingsDTO thingsDTO) {
		Things things = null;
		if (thingsDTO != null) {
			things = new Things();
			things.setIdthing(thingsDTO.getIdthing());
			things.setNome(thingsDTO.getNome());
			thingsDTO.setIduser(thingsDTO.getIduser());
			thingsDTO.setIdlabel(thingsDTO.getIdlabel());
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