package it.contrader.converter;

import it.contrader.dto.ThingsDTO;
import it.contrader.model.Things;


public class ThingsConverter {
	/**
	 * Converte un NodesDTO in Nodes
	 */
public static Things toEntity(ThingsDTO thingDTO) {
	Things thing = null;
	if (thingDTO != null) {
		thing = new Things(thingDTO.getId(), thingDTO.getName(), thingDTO.getFktouser(), thingDTO.getFktolabel());
	}
	return thing;
}

public static Things toEntityCode(ThingsDTO thingDTO) {
	Things thing = null;
	if (thingDTO != null) {
		thing = new Things(thingDTO.getId(), thingDTO.getCode());
	}	
	return thing;
}
	/*
	 * Converte un Nodes in NodesDTO
	 */

public static ThingsDTO toDTO(Things thing) {
	ThingsDTO thingDTO = null;
	if (thing != null) {
		thingDTO = new ThingsDTO(thing.getId(), thing.getName(), thing.getFktouser(), thing.getFktolabel());
	}
	return thingDTO;
	}
}