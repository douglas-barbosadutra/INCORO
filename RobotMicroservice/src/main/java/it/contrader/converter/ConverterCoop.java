package it.contrader.converter;


import java.util.ArrayList;
import java.util.List;

import it.contrader.dto.CoopDTO;
import it.contrader.dto.LabelDTO;

import it.contrader.model.Coop;
import it.contrader.model.Label;



public class ConverterCoop {
	
	public static CoopDTO toDto(Coop coop) {
		CoopDTO coopDTO = null;
		if(coop != null) {
			coopDTO = new CoopDTO();
			coopDTO.setIdCoop(coop.getIdCoop());
			coopDTO.setTOne(coop.getTOne());
			coopDTO.setTTwo(coop.getTTwo());			
		}
		return coopDTO;		
	}
	
	public static Coop toEntity(CoopDTO coopDTO) {
		Coop coop = null;
		if(coopDTO != null) {
			coop = new Coop();
			coop.setIdCoop(coopDTO.getIdCoop());
			coop.setTOne(coopDTO.getTOne());
			coop.setTTwo(coopDTO.getTTwo());			
		}
		return coop;
	}

	public static List<CoopDTO> toListDTO(List<Coop> list){
		List<CoopDTO> listCoopDTO = new ArrayList<>();
		if (!list.isEmpty()) {
			for(Coop coop : list) {
				listCoopDTO.add(ConverterCoop.toDto(coop));
			}
		}
		return listCoopDTO;
	}
	
	public static List<Coop> toListEntity(List<CoopDTO> listCoopDTO){
		List<Coop> listLabel = new ArrayList<>();
		if (!listCoopDTO.isEmpty()) {
			for(CoopDTO CoopDTO : listCoopDTO) {
				listLabel.add(ConverterCoop.toEntity(CoopDTO));
			}
		}
		return listLabel;
	}
}