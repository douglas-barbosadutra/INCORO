package it.contrader.converter;

import java.util.ArrayList;
import java.util.List;

import it.contrader.dto.LabelDTO;
import it.contrader.dto.UserDTO;
import it.contrader.model.Label;
import it.contrader.model.User;



public class ConverterLabel {
	
	public static Label convertToEntity(LabelDTO labeldto) {
		Label label = null;
		if(labeldto != null) {
			label = new Label();
			label.setIdLabel(labeldto.getIdLabel());
			label.setName(labeldto.getName());
			User user = new User();
			user.setIdUser(labeldto.getFktouser());    //dai al nuovo utente come chiave primaria quella che è chiave esterna su label
			
			
			
			label.setUser(user);
		}
		return label;

	}

	public static LabelDTO convertToDto(Label label) {
		LabelDTO labeldto = null;
		if(label != null) {
			labeldto = new LabelDTO();
			labeldto.setIdLabel(label.getIdLabel());
			labeldto.setName(label.getName());
			
		}
		return labeldto;		
	}
	
	public static List<LabelDTO> toListDTO(List<Label> list){
		List<LabelDTO> listLabelDTO = new ArrayList<>();
		if (!list.isEmpty()) {
			for(Label label : list) {
				listLabelDTO.add(ConverterLabel.convertToDto(label));
			}
		}
		return listLabelDTO;
	}

}
